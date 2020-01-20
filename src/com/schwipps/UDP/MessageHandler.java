package com.schwipps.UDP;

import com.schwipps.DSFBuilder.*;
import com.schwipps.DSFBuilder.enums.*;
import com.schwipps.Json.JSONDebugDataMessage;
import com.schwipps.Json.JSONDebugDataReadRequest;
import com.schwipps.Json.JSONDebugDataWriteRequest;
import com.schwipps.Main.DSFAddressLinker;
import com.schwipps.Main.DSFTuple;
import com.schwipps.dsf.TypeCompilationUnit;

import java.io.*;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MessageHandler {

    private  boolean connectedToTargetAgent = false;
    private  int targetAgentID = 0;
    private  boolean targetAgentWritable = false;
    private  int targetAgentRXBuffer = 0;

    private  UDPSenderTargetAgent udpSenderTargetAgent;
    private  UDPSenderClient udpSenderClient;
    private  DSFAddressLinker dsfAddressLinker;

    int counterAircraftstate =0;
    int counterControlCommands=0;
    FileWriter out;
    public MessageHandler(){
        /*try {
            out = new FileWriter(new File("filename.txt"), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void setUdpSenderTargetAgent(UDPSenderTargetAgent udpSenderTargetAgentArgument){
        udpSenderTargetAgent = udpSenderTargetAgentArgument;
    }
    public void setUdpSenderClient(UDPSenderClient udpSenderClientArg){
        udpSenderClient = udpSenderClientArg;
    }
    public void setDSFAddressLinker(DSFAddressLinker dsfAddressLinkerArg){
        dsfAddressLinker = dsfAddressLinkerArg;
    }

    public  UDPSenderClient getUdpSenderClient(){
        return udpSenderClient;
    }
    public  UDPSenderTargetAgent getUdpSenderTargetAgent(){
        return udpSenderTargetAgent;
    }

    public void handleDSFMessageTargetAgent(byte message[], int offset, int length){
        byte[] temp = Arrays.copyOfRange(message,offset , offset+length);;
        DSFMessage dsfMessage = new DSFMessage(temp);
        if(dsfMessage.messageErrorFree())
        {
        MessageType messageType =  dsfMessage.getMessageType();
        switch (messageType) {
            case TARGET_AGENT_DATA_MESSAGE:
                handleDSFTargetAgentDataMessage(dsfMessage);
                break;
            case DEBUG_DATA_MESSAGE:
                long time = System.nanoTime();
                handleDSFDebugDataMessage(dsfMessage);
                time = System.nanoTime() - time;
                counterControlCommands++;
                if(counterControlCommands == 100){
                    System.out.println("Delay Controlsurface" + time );
                    counterControlCommands = 0;
                }
                break;
            //THESE WILL ACTUALLY NEVER BE RECEIVED FROM THE TARGET AGENT BUT ONLY SEND FROM THE PROXY
            case TARGET_AGENT_REQUEST_MESSAGE:
            case DEBUG_DATA_WRITE_REQUEST_MESSAGE:
            case INVALID_MESSAGE_HANDLE:
            case DEBUG_DATA_READ_REQUEST_MESSAGE:
                System.out.println("Unreasonable message from Target Agent received");
        }
        }
    }
    public void handleDSFTargetAgentDataMessage(DSFMessage dsfMessage){
        DSFBodyTargetAgentDataMessage targetAgentDataMessage = new DSFBodyTargetAgentDataMessage(dsfMessage.getBody().getByte());
        if(!connectedToTargetAgent){
            if(targetAgentDataMessage.getMode().equals(TargetAgentMode.CONNECTED)){
                connectedToTargetAgent = true;
                System.out.println("Succesfully connected to TargetAgent " +targetAgentDataMessage.getTargetAgentId());
                targetAgentID = targetAgentDataMessage.getTargetAgentId();
                targetAgentRXBuffer = targetAgentDataMessage.getRXBufferSize();
                cancelAllDataItems();
                dsfAddressLinker.clearRoutingLists();
            }
            else if(targetAgentDataMessage.getMode().equals(TargetAgentMode.DISCONNECTED)){
                System.out.println("Target Agent found");
                udpSenderTargetAgent.sendMessage(Builder.buildTargetAgentRequestMessage(0, targetAgentDataMessage.getTargetAgentId(), TargetAgentRequestCommand.CONNECT_TO_TARGET_AGENT).getByte());
            }
            else if(targetAgentDataMessage.getMode().equals(TargetAgentMode.INVALID)){
                //TODO
            }
        }//Connected
        else{
            if(targetAgentDataMessage.getMode().equals(TargetAgentMode.DISCONNECTED)){
                connectedToTargetAgent = false;
                System.out.println("Target Agent Disconnected for unknown reason trying to reconnect");
                udpSenderTargetAgent.sendMessage(Builder.buildTargetAgentRequestMessage(0, targetAgentDataMessage.getTargetAgentId(), TargetAgentRequestCommand.CONNECT_TO_TARGET_AGENT).getByte());
            }
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) +" Target Agent Utilization "+ targetAgentDataMessage.getMaxUtilization()+"%");
        }


    }
    public void handleDSFDebugDataMessage(DSFMessage dsfMessage){
        DSFBodyDebugDataMessage debugDataMessage = new DSFBodyDebugDataMessage(dsfMessage.getBody().getByte());
        ArrayList<Integer> receiverClientsPorts = dsfAddressLinker.getRegisteredPorts();

        HashMap<Integer, JSONDebugDataMessage> hashMapPortJSONDebugDataMessage = assembleJsonDebugDataMessages(debugDataMessage, receiverClientsPorts);
        //Send the messages
        for(int port: receiverClientsPorts){
            udpSenderClient.sendMessage(port, hashMapPortJSONDebugDataMessage.get(port).toByte());
        }
        dsfAddressLinker.EmptyUnregisterQueue();

    }

    public void handleJSONMessageClient(byte message[], int offset, int length, int port) {
        long time = System.nanoTime();
        byte[] temp = Arrays.copyOfRange(message,offset , offset+length);
        String jsonMessage = null;
        try {
            jsonMessage = new String(temp, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(jsonMessage.startsWith("{\"JSONDebugDataReadRequest\"")){
            handleJSONDebugDataReadRequest(jsonMessage, port);
        }
        else if(jsonMessage.startsWith("{\"JSONDebugDataWriteRequest\"")){
            handleJSONDebugDataWriteRequest(jsonMessage);
        }

        time = System.nanoTime() - time;
        counterAircraftstate++;
        if(counterAircraftstate == 100){
            System.out.println("Delay Aircraftstate" + time );
            counterAircraftstate = 0;
        }
    }
    private void handleJSONDebugDataReadRequest(String jsonMessage, int port) {
        JSONDebugDataReadRequest jsonDebugDataReadRequest = new JSONDebugDataReadRequest(jsonMessage);
        //Extract the requested recordElements from the message
        ArrayList<DSFRecordElement> recordElements = jsonDebugDataReadRequest.getDSFRecordElement();
        //These are neccessary because we need to create two seperate DSFDebugDataReadRequest messages; the command is specified in the header
        ArrayList<DSFDebugDataItem> recordElementsPeriodic = new ArrayList<>();
        ArrayList<DSFDebugDataItem> recordElementsOnce = new ArrayList<>();
        //Check if the recordElement was formely requested by another client
        if(recordElements.size() > 0){
            for(DSFRecordElement dsfRecordElement : recordElements){
                if(!dsfAddressLinker.dsfRecordElementAlreadyRegistered(dsfRecordElement)){
                    DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement = dsfAddressLinker.getDSFEquipmentDefinitionRecordElement(dsfRecordElement);
                    if(dsfEquipmentDefinitionRecordElement != null){
                        ArrayList<DSFEquipmentDefinitionRecordElement> dsfEquipmentDefinitionRecordElementsSmall = dsfAddressLinker.splitMesssage(dsfEquipmentDefinitionRecordElement);
                        if(dsfRecordElement.getReadRequestCommand().equals(DebugDataReadRequestCommand.READ_DATA_PERIODICALLY)){
                            for(int i=0; i< dsfEquipmentDefinitionRecordElementsSmall.size();i++){
                                int requestNecessary = dsfAddressLinker.registerMessage(port,dsfEquipmentDefinitionRecordElementsSmall.get(i).getDsfRecordElement());
                                if(requestNecessary !=  0){
                                    recordElementsPeriodic.add(new DSFDebugDataItem(dsfEquipmentDefinitionRecordElementsSmall.get(i).getBitSize()/8, dsfEquipmentDefinitionRecordElementsSmall.get(i).getAddressLong()));
                                }
                            }
                        }
                        else if(dsfRecordElement.getReadRequestCommand().equals(DebugDataReadRequestCommand.READ_DATA_ONCE)){
                            for(int i=0; i< dsfEquipmentDefinitionRecordElementsSmall.size();i++){
                                recordElementsOnce.add(new DSFDebugDataItem(dsfEquipmentDefinitionRecordElementsSmall.get(i).getBitSize()/8, dsfEquipmentDefinitionRecordElementsSmall.get(i).getAddressLong()));
                                dsfAddressLinker.registerMessage(port,dsfEquipmentDefinitionRecordElementsSmall.get(i).getDsfRecordElement());
                            }
                        }
                        else if(dsfRecordElement.getReadRequestCommand().equals(DebugDataReadRequestCommand.CANCEL_ALL_PERIODIC_TRANSFERS)){
                            for(int i=0; i< dsfEquipmentDefinitionRecordElementsSmall.size();i++){
                                dsfAddressLinker.unregisterMessage(port,dsfEquipmentDefinitionRecordElementsSmall.get(i).getDsfRecordElement());
                            }
                        }
                    }
                }
            }
            //Now send these items
            // Split them in such a way that they are smaller than the targetAgentRXBuffer
            if(recordElementsPeriodic.size() >0){
                udpSenderTargetAgent.sendMessage(Builder.buildDebugDataReadRequest(targetAgentID, DebugDataReadRequestCommand.READ_DATA_PERIODICALLY   , recordElementsPeriodic.toArray(new DSFDebugDataItem[recordElementsPeriodic.size()])).getByte());
            }
            if(recordElementsOnce.size()>0){
                udpSenderTargetAgent.sendMessage(Builder.buildDebugDataReadRequest(targetAgentID, DebugDataReadRequestCommand.READ_DATA_ONCE   , recordElementsOnce.toArray(new DSFDebugDataItem[recordElementsOnce.size()])).getByte());

            }
        }

    }
    private void handleJSONDebugDataWriteRequest(String jsonMessage) {
        JSONDebugDataWriteRequest jsonDebugDataWriteRequest = new JSONDebugDataWriteRequest(jsonMessage);
        HashMap<DSFRecordElement, Object> hashMap = jsonDebugDataWriteRequest.getValues();
        DSFDebugDataItem[] dsfDebugDataItems = new DSFDebugDataItem[hashMap.size()];
        int i=0;
        if(hashMap != null){
            //OPtimize Subsequent write processes

            for( Map.Entry<DSFRecordElement, Object> entry : hashMap.entrySet()){
                DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement = dsfAddressLinker.getDSFEquipmentDefinitionRecordElement(entry.getKey());
                int dataByteLength = dsfEquipmentDefinitionRecordElement.getBitSize()/8;
                Object value = entry.getValue();
                byte[] byteValue = objectValueToByte(value, dsfEquipmentDefinitionRecordElement.getDataTypeItem(), dsfEquipmentDefinitionRecordElement.getDataTypeEnum());
                dsfDebugDataItems[i] = new DSFDebugDataItem(dataByteLength, dsfEquipmentDefinitionRecordElement.getAddressLong(),byteValue);
                i++;
            }
        }

        if(!targetAgentWritable){
            makeTargetAgentWritable();
        }
        udpSenderTargetAgent.sendMessage(Builder.buildDebugDataWriteRequest(targetAgentID, dsfDebugDataItems).getByte());
    }

    // Updating the Aircraft state works only if these magic bits have been set.
    private void makeTargetAgentWritable() {

        DSFDebugDataItem[] magicItems = new DSFDebugDataItem[2];

        LinkedList<String> listTable = new LinkedList<>();
        listTable.add("MinorFrameTaskDisableTable");
        listTable.add("0");
        listTable.add("DisableTaskBitmapWord");
        DSFEquipmentDefinitionRecordElement tableElement = dsfAddressLinker.getDSFEquipmentDefinitionRecordElement(new DSFRecordElement("RTMS_Scheduler_Context", listTable, DebugDataReadRequestCommand.READ_DATA_ONCE));

        byte[] dataTable = new byte[tableElement.getBitSize()/8];
        //12 - 15, the magic number at the 3rd position
        System.arraycopy(ByteBuffer.allocate(Integer.BYTES).putInt(4).array(),0 , dataTable, 12, Integer.BYTES);



        LinkedList<String> list = new LinkedList<>();
        list.add("MinorFrameTaskDisableInterLock");
        DSFEquipmentDefinitionRecordElement interlockElement = dsfAddressLinker.getDSFEquipmentDefinitionRecordElement(new DSFRecordElement("RTMS_Scheduler_Context", list, DebugDataReadRequestCommand.READ_DATA_ONCE));
        byte[] dataInterlock =  new byte[]{(byte)0xAD, (byte)0xAC,(byte)0xCA,(byte)0xFE };
        magicItems[1] = new DSFDebugDataItem(4, interlockElement.getAddressLong(), dataInterlock);
        magicItems[0] = new DSFDebugDataItem(tableElement.getBitSize()/8, tableElement.getAddressLong(), dataTable);

            udpSenderTargetAgent.sendMessage(Builder.buildDebugDataWriteRequest(targetAgentID, magicItems).getByte());


        targetAgentWritable = true;
    }
    //Assembles the JSONDebugDataMessages from the DSFDebugDataMessages and links the registered Clientports to them
    //Additionally, single read requested items are removed from the dsfAddressLinker
    private HashMap<Integer, JSONDebugDataMessage> assembleJsonDebugDataMessages(DSFBodyDebugDataMessage debugDataMessage, ArrayList<Integer> receiverClientsPorts){
        HashMap<Integer, JSONDebugDataMessage> hashMapPortMessage = new HashMap<>();
        //Create an empty  JSONDebugDataMessage for each port;
        for(int i : receiverClientsPorts){
            hashMapPortMessage.put(i, new JSONDebugDataMessage());
        }

        //loop through all debugDataitems
        for(DSFDebugDataItem dsfDebugDataItem : debugDataMessage.getDebugDataItems()){
            //Get ports, which registered to receive that particular address
            ArrayList<DSFTuple> tuples = dsfAddressLinker.getTuple(dsfDebugDataItem.getDataItemAddress());
            // Add data to each JSON Message
            // Loop thourgh all Client ports, which registered to that address
            ArrayList<Integer> ports = new ArrayList<>();
            ArrayList<DSFRecordElement> dsfRecordElements = new ArrayList<>();
            if(tuples != null){
                for(DSFTuple tuple : tuples){
                    //Get the Json Message Corresponding to the port
                    JSONDebugDataMessage jsonDebugDataMessage = hashMapPortMessage.get(tuple.getPort());
                    if(dsfDebugDataItem.getDataLength() == tuple.getDsfEquipmentDefinitionRecordElement().getBitSize()/8){
                        jsonDebugDataMessage.addField(dsfDebugDataItem, tuple.getDsfEquipmentDefinitionRecordElement());
                        //Remove the tuple from the DSFAddressLinker if the message was request only once
                        if( (!tuple.getDsfEquipmentDefinitionRecordElement().getDsfRecordElement().isPeriodic()) && (tuple.getDsfEquipmentDefinitionRecordElement().getBitSize()/8 == dsfDebugDataItem.getDataLength())){
                            //we need to remove these elements after sending, so we put these elements in a list
                            dsfAddressLinker.putTupleIntoUnregisterQueue(tuple);
                        }
                    }
                }
            }
        }
        return hashMapPortMessage;
    }

    public int getTargetAgentID() {
        return targetAgentID;
    }
    private byte[] objectValueToByte(Object object, Object dataTypeItem, EquipmentDefinitionDataType dataType){
        byte[] array = null;
        byte[] temp = null;
        int length = 0;
        switch (dataType){
            case VARIABLE:
                break;
            case CHARACTER:
                //Char is always 1 Byte
                TypeCompilationUnit.CharacterDataType characterDataType = (TypeCompilationUnit.CharacterDataType) dataTypeItem;
                if(object instanceof Character){
                    char charValue = (Character) object;
                    return ByteBuffer.allocate(Character.BYTES).putChar(charValue).array();
                }
                break;
            case BOOLEAN:
                break;
            case INTEGER:
                TypeCompilationUnit.IntegerDataType integerDataType= (TypeCompilationUnit.IntegerDataType) dataTypeItem;
                length = integerDataType.getBitSize().intValue()/8;
                if(object instanceof Integer){
                    int integerValue = (Integer) object;
                    temp = ByteBuffer.allocate(Integer.BYTES).putInt(integerValue).array();
                }else if(object instanceof  Short){
                    short shortValue = (Short) object;
                    temp = ByteBuffer.allocate(Short.BYTES).putShort(shortValue).array();
                }else if(object instanceof Long){
                    long longValue = (Long) object;
                    temp = ByteBuffer.allocate(Long.BYTES).putLong(longValue).array();
                }
                else if(object instanceof Float){
                    float floatValue = (Float) object;
                    temp = ByteBuffer.allocate(Long.BYTES).putLong(((Float)floatValue).longValue()).array();
                }
                else if(object instanceof Double){
                    double doubleValue = (Double) object;
                    temp = ByteBuffer.allocate(Long.BYTES).putLong(((Double)doubleValue).longValue()).array();
                }
                break;
            case FLOAT:
                TypeCompilationUnit.FloatDataType floatDataType= (TypeCompilationUnit.FloatDataType) dataTypeItem;
                length = floatDataType.getBitSize().intValue()/8;
                float floatValue = 0;
                double doubleValue = 0;
                if(object instanceof Double ){
                    floatValue = ((Double)object).floatValue();
                    doubleValue = (double) object;
                }
                else if(object instanceof Float){
                    floatValue = ((Float)object).floatValue();
                    doubleValue = ((Float)object).doubleValue();
                }
                else if(object instanceof Long){
                    floatValue = ((Long)object).floatValue();
                    doubleValue = ((Long)object).doubleValue();
                }
                else if(object instanceof Integer){
                    floatValue = ((Integer)object).floatValue();
                    doubleValue = ((Integer)object).doubleValue();
                }
                else if(object instanceof Short){
                    floatValue = ((Short)object).floatValue();
                    doubleValue = ((Short)object).doubleValue();
                }

                if(length == 4){
                    temp = ByteBuffer.allocate(Float.BYTES).putFloat(floatValue).array();
                }else if(length == 8) {
                    temp = ByteBuffer.allocate(Double.BYTES).putDouble(doubleValue).array();
                }
                break;
            case POINTER:
                TypeCompilationUnit.PointerDataType pointerDataType= (TypeCompilationUnit.PointerDataType) dataTypeItem;
                length = pointerDataType.getBitSize().intValue()/8;
                if(object instanceof Integer){
                    int integerValue = (Integer) object;
                    temp = ByteBuffer.allocate(Integer.BYTES).putInt(integerValue).array();
                }else if(object instanceof Short){
                    short shotValue = (Short) object;
                    temp = ByteBuffer.allocate(Short.BYTES).putShort(shotValue).array();
                }else if(object instanceof Long){
                    long longValue = (Long) object;
                    temp = ByteBuffer.allocate(Long.BYTES).putLong(longValue).array();
                }
                else if(object instanceof Float){
                    float aFloatValue = (Float) object;
                    temp = ByteBuffer.allocate(Long.BYTES).putLong(((Float)aFloatValue).longValue()).array();
                }
                else if(object instanceof Double){
                    double aDoubleValue = (Double) object;
                    temp = ByteBuffer.allocate(Long.BYTES).putLong(((Double)aDoubleValue).longValue()).array();
                }
                break;
            case ENUM:
                TypeCompilationUnit.EnumDataType enumDataType= (TypeCompilationUnit.EnumDataType) dataTypeItem;
                length = enumDataType.getBitSize().intValue()/8;
                if(object instanceof Integer){
                    int integerValue = (Integer) object;
                    temp = ByteBuffer.allocate(Integer.BYTES).putInt(integerValue).array();
                }else if(object instanceof Short){
                    short shortValue = (Short) object;
                    temp = ByteBuffer.allocate(Integer.BYTES).putShort(shortValue).array();
                }else if(object instanceof Long){
                    long longValue = (Long) object;
                    temp = ByteBuffer.allocate(Integer.BYTES).putLong(longValue).array();
                }else if(object instanceof String){
                    String stringValue = (String) object;
                    for(TypeCompilationUnit.EnumDataType.EnumElement enumElement :enumDataType.getEnumElement()){
                        if(stringValue.equalsIgnoreCase(enumElement.getName())){
                            temp = ByteBuffer.allocate(Integer.BYTES).putInt(enumElement.getValue().intValue()).array();
                            break;
                        }
                    }
                }
                else if(object instanceof Float){
                    float aFloatValue = (Float) object;
                    temp = ByteBuffer.allocate(Long.BYTES).putLong(((Float)aFloatValue).longValue()).array();
                }
                else if(object instanceof Double){
                    double aDoubleValue = (Double) object;
                    temp = ByteBuffer.allocate(Long.BYTES).putLong(((Double)aDoubleValue).longValue()).array();
                    // Test
                }
                break;
            case LIST:
                break;
            case RECORD:
                break;
            case UNION:
                break;
            case UNDEFINED:
                break;
        }
        if(temp != null){
            array = new byte[length];
            if(length < temp.length){
                //TODO MESSAGE
                System.arraycopy(temp, temp.length-length, array, 0, length);
            }
            else{
                System.arraycopy(temp, 0, array, temp.length-length,length );
            }
        }
        return array;
    }
    public boolean isConnectedToTargetAgent() {
        return connectedToTargetAgent;
    }
    public void cancelAllDataItems(){
        udpSenderTargetAgent.sendMessage(Builder.buildDebugDataReadRequest(targetAgentID, DebugDataReadRequestCommand.CANCEL_ALL_PERIODIC_TRANSFERS, new DSFDebugDataItem[]{null}).getByte() );
    }
}