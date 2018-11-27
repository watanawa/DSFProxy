package com.schwipps.UDP;

import com.schwipps.DSFBuilder.*;
import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentMode;
import com.schwipps.DSFBuilder.enums.TargetAgentRequestCommand;
import com.schwipps.Json.JSONDebugDataMessage;
import com.schwipps.Main.DSFAddressLinker;
import com.schwipps.Main.DSFTuple;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MessageHandler {

    private  boolean connectedToTargetAgent = false;
    private  int targetAgentID = 0;

    private  UDPSenderTargetAgent udpSenderTargetAgent;
    private  UDPSenderClient udpSenderClient;
    private  DSFAddressLinker dsfAddressLinker;

    FileWriter out;
    public MessageHandler(){
        try {
            out = new FileWriter(new File("filename.txt"), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void handleMessageTargetAgent(byte message[], int offset, int length){
        byte[] temp = Arrays.copyOfRange(message,offset , offset+length);;
        DSFMessage dsfMessage = new DSFMessage(temp);

        MessageType messageType =  dsfMessage.getMessageType();
        switch (messageType){
            case TARGET_AGENT_DATA_MESSAGE:
                handleDSFTargetAgentDataMessage(dsfMessage);
                break;
            case DEBUG_DATA_MESSAGE:
                handleDSFDebugDataMessage(dsfMessage);
                break;
            //THESE WILL ACTUALLY NEVER BE RECEIVED FROM THE TARGET AGENT BUT ONLY SEND FROM THE PROXY
            case TARGET_AGENT_REQUEST_MESSAGE:
            case DEBUG_DATA_WRITE_REQUEST_MESSAGE:
            case INVALID_MESSAGE_HANDLE:
            case DEBUG_DATA_READ_REQUEST_MESSAGE:
                System.out.println("Unreasonable message from Target Agent received");

        }
    }
    public void handleDSFTargetAgentDataMessage(DSFMessage dsfMessage){
        DSFBodyTargetAgentDataMessage targetAgentDataMessage = new DSFBodyTargetAgentDataMessage(dsfMessage.getBody().getByte());
        if(!connectedToTargetAgent){
            if(targetAgentDataMessage.getMode().equals(TargetAgentMode.CONNECTED)){
                connectedToTargetAgent = true;
                System.out.println("Succesfully connected to TargetAgent " +targetAgentDataMessage.getTargetAgentId());
                targetAgentID = targetAgentDataMessage.getTargetAgentId();
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
            System.out.println(targetAgentDataMessage.getTimeStamp()+" Target Agent Utilization "+ targetAgentDataMessage.getMaxUtilization()+"%");
        }


    }
    public void handleDSFDebugDataMessage(DSFMessage dsfMessage){
        DSFBodyDebugDataMessage debugDataMessage = new DSFBodyDebugDataMessage(dsfMessage.getBody().getByte());
        ArrayList<Integer> receiverClientsPorts = dsfAddressLinker.getRegisteredPorts();
        HashMap<Integer, JSONDebugDataMessage> hashMapPortJSONDebugDataMessage = assembleJsonDebugDataMessages(debugDataMessage, receiverClientsPorts);
        //Send the messages
        for(int port: receiverClientsPorts){
            udpSenderClient.sendMessage(port, hashMapPortJSONDebugDataMessage.get(port).toByte());
            try {
                out.write("\n"+hashMapPortJSONDebugDataMessage.get(port).getJsonDebugDataObject().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleMessageClient(byte message[], int offset, int length) {
        byte[] temp = Arrays.copyOfRange(message,offset , offset+length);
        String jsonMessage = null;
        try {
            jsonMessage = new String(temp, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(jsonMessage.startsWith("{JSONDebugDataReadRequest")){
            handleJSONDebugDataReadRequest(jsonMessage);
        }
        else if(jsonMessage.startsWith("{JSONDebugDataWriteRequest")){
            handleJSONDebugDataWriteRequest(jsonMessage);
        }

    }

    private void handleJSONDebugDataReadRequest(String jsonMessage) {
    }
    private void handleJSONDebugDataWriteRequest(String jsonMessage) {
    }

    //Assembles the JSONDebugDataMessages from the DSFDebugDataMessages and links the registered Clientports to them
    //Additionally, single read requested items are removed from the dsfAddressLinker
    private HashMap<Integer, JSONDebugDataMessage> assembleJsonDebugDataMessages(DSFBodyDebugDataMessage debugDataMessage, ArrayList<Integer> receiverClientsPorts){
        HashMap<Integer, JSONDebugDataMessage> hashMapPortMessage = new HashMap<>();
        //Create an empty  JSONDebugDataMessage for each port;
        for(int i : receiverClientsPorts){
            hashMapPortMessage.put(i, new JSONDebugDataMessage());
        }

        for(DSFDebugDataItem dsfDebugDataItem : debugDataMessage.getDebugDataItems()){
            //Get ports, which registered to receive that address
            ArrayList<DSFTuple> tuples = dsfAddressLinker.getTuple(dsfDebugDataItem.getDataItemAddress());
            //Add data to each JSON Message
            // Problem: We have a port and an DataItem
            for(DSFTuple tuple : tuples){
                //Get the Json Message Corresponding to the port
                JSONDebugDataMessage jsonDebugDataMessage = hashMapPortMessage.get(tuple.getPort());
                jsonDebugDataMessage.addField(dsfDebugDataItem, tuple.getDsfEquipmentDefinitionRecordElement());
                //Remove the tuple from the DSFAddressLinker if the message was request only once
                if(!tuple.getDsfEquipmentDefinitionRecordElement().getDsfRecordElement().isPeriodic()){
                    dsfAddressLinker.unregisterMessage(tuple.getPort(), tuple.getDsfEquipmentDefinitionRecordElement().getDsfRecordElement());
                }
            }
        }
        return hashMapPortMessage;
    }

    public int getTargetAgentID() {
        return targetAgentID;
    }

    public boolean isConnectedToTargetAgent() {
        return connectedToTargetAgent;
    }
}