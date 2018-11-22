package com.schwipps.Main;

import com.schwipps.DSFBuilder.DSFEquipmentDefinitionRecordElement;
import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.DSFBuilder.enums.EquipmentDefinitionDataType;
import com.schwipps.dsf.TypeCompilationUnit;
import com.schwipps.dsf.TypeDebugSymbolSet;
import com.schwipps.dsf.TypeEquipmentDescription;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;

public class DSFAddressLinker {
    private class RecordTuple{
        private TypeCompilationUnit.RecordDataType recordDataType;
        private TypeCompilationUnit.RecordDataType.RecordElement recordElement;
        private RecordTuple(TypeCompilationUnit.RecordDataType recordDataType, TypeCompilationUnit.RecordDataType.RecordElement recordElement){
            setRecordDataType(recordDataType);
            setRecordElement(recordElement);
        }

        public TypeCompilationUnit.RecordDataType getRecordDataType() {
            return recordDataType;
        }

        public void setRecordDataType(TypeCompilationUnit.RecordDataType recordDataType) {
            this.recordDataType = recordDataType;
        }

        public TypeCompilationUnit.RecordDataType.RecordElement getRecordElement() {
            return recordElement;
        }

        public void setRecordElement(TypeCompilationUnit.RecordDataType.RecordElement recordElement) {
            this.recordElement = recordElement;
        }
    }

    //This class keeps track, which Client registered for which dataitem

    //Address as long is used as key for minimal delay
    private HashMap<Long, ArrayList<DSFTuple>>  hashMapAddressToPort;
    private ArrayList<Integer>                  registeredPorts;
    private TypeDebugSymbolSet                  typeDebugSymbolSet;

    public  DSFAddressLinker (TypeEquipmentDescription typeEquipmentDescription){
        this.typeDebugSymbolSet = typeEquipmentDescription.getDebugSymbols().getDebugSymbolSet().get(0);
        hashMapAddressToPort = new HashMap<>();
        registeredPorts = new ArrayList<>();
    }
    public ArrayList<Integer> getRegisteredPorts(){
        return registeredPorts;
    }
    public void registerMessage(int portClient, DSFRecordElement dsfRecordElement ){
        DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement = getDSFEquipmentDefinitionRecordElement(dsfRecordElement);
        byte[] address = dsfEquipmentDefinitionRecordElement.getAddress();
        long addressVal = byteToLong(address);

        //Address already exists
        if(hashMapAddressToPort.containsKey(addressVal) ){
            //check if already registered
            boolean existing = false;
            //Address is already existing as key
            for(DSFTuple dsfTuple : hashMapAddressToPort.get(addressVal)){
                //check if the port already registered to that address
                if(dsfTuple.getPort() == portClient){
                    //DO NOTHING
                    //existing = true;
                    //if(!registeredPorts.contains(new Integer(portClient))){
                    //registeredPorts.add(portClient);
                    return;
                }
            }
            //add entry to arraylist
            if(!existing){
                hashMapAddressToPort.get(addressVal).add(new DSFTuple(portClient, dsfEquipmentDefinitionRecordElement));
                if(!registeredPorts.contains(new Integer(portClient))){
                    registeredPorts.add(portClient);
                }
            }
        }
        //Address is not existing yet
        else{
            ArrayList<DSFTuple> dsfTuple = new ArrayList<DSFTuple>();
            dsfTuple.add(new DSFTuple(portClient, dsfEquipmentDefinitionRecordElement));
            hashMapAddressToPort.put(addressVal,dsfTuple);
            if(!registeredPorts.contains(new Integer(portClient))){
                registeredPorts.add(portClient);
            }
        }
    }
    public void unregisterMessage(int portClient, DSFRecordElement dsfRecordElement){
        DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement = getDSFEquipmentDefinitionRecordElement(dsfRecordElement);
        byte[] address = dsfEquipmentDefinitionRecordElement.getAddress();
        Long addressVal = byteToLong(address);
        ArrayList<DSFTuple> dsfTuples;
        //Delete the corresponding dsfTuple
        if(hashMapAddressToPort.containsKey(addressVal)){
            dsfTuples = hashMapAddressToPort.get(addressVal);
            for(int i = 0; i<  dsfTuples.size(); i++ ){
                if(dsfTuples.get(i).getPort() == portClient){
                    dsfTuples.remove(i);
                }
            }
            // Clear key if only one was present
            if (dsfTuples.size() == 0){
                hashMapAddressToPort.remove(addressVal);
            }
        }

        //remove from portList if necessary
        if(!portRegistered(portClient)){
            registeredPorts.remove(new Integer(portClient));
        }
    }

    public ArrayList<DSFTuple> getTuple (byte[] address){
        return hashMapAddressToPort.get(byteToLong(address));
    }
    // Um in die Hashmap zu schreiben
    public DSFEquipmentDefinitionRecordElement getDSFEquipmentDefinitionRecordElement(DSFRecordElement dsfRecordElement){
        //BUILDS a DSFEquipmentDefinitionRecordElement
        //TODO build the DSFequipment instance
        //String compilationUnitName  = dsfRecordElement.;
        String variableName         = dsfRecordElement.getVariable();
        LinkedList<String> recordElementNames = dsfRecordElement.getRecordElementNames();


        byte[] address = null;
        Object datatype = null;
        int offset = 0;
        int size = 0;
        DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement = null;
        List<Object> scope = null;
        TypeCompilationUnit compilationUnitArgument = null;
        String variableDataTypeId = "";

        //Fix the starting addres from the variable
        for(TypeCompilationUnit compilationUnit : typeDebugSymbolSet.getCompilationUnit()) {

                List<Object> variableAndDatatype = compilationUnit.getVariableAndCharacterDataTypeAndBooleanDataType();
                for (Object variableAndDataTypeItem : variableAndDatatype) {
                    if (getDataType(variableAndDataTypeItem).equals(EquipmentDefinitionDataType.VARIABLE)) {
                        TypeCompilationUnit.Variable variable = (TypeCompilationUnit.Variable) variableAndDataTypeItem;
                        if (variable.getName().equals(variableName)) {
                            address = variable.getAddress();
                            scope = variableAndDatatype;
                            compilationUnitArgument = compilationUnit;
                            variableDataTypeId = variable.getDataTypeId();
                        }
                    }
                }
        }
        //If only variable name given
        if(dsfRecordElement.getRecordElementNames().size() == 0){
            for(Object variableAndDataTypeItem : scope){
                if (getDataType(variableAndDataTypeItem).equals(EquipmentDefinitionDataType.RECORD)){
                    TypeCompilationUnit.RecordDataType recordDataType = (TypeCompilationUnit.RecordDataType) variableAndDataTypeItem;
                    if(recordDataType.getId().equals(variableDataTypeId)){
                        for (TypeCompilationUnit.RecordDataType.RecordElement recordElement : recordDataType.getRecordElement()){
                            size += recordElement.getBitSize().intValue();
                        }
                        datatype = recordDataType;
                    }
                }
            }
        }
        else {
            for (int i = 0; i < recordElementNames.size(); i++) {
                for (Object variableAndDataTypeItem : scope) {
                    if (getDataType(variableAndDataTypeItem).equals(EquipmentDefinitionDataType.RECORD)) {
                        TypeCompilationUnit.RecordDataType recordDataType = (TypeCompilationUnit.RecordDataType) variableAndDataTypeItem;
                        if (recordDataType.getId().equals(variableDataTypeId)) {
                            for (TypeCompilationUnit.RecordDataType.RecordElement recordElement : recordDataType.getRecordElement()) {
                                if(recordElement.getName().equalsIgnoreCase(recordElementNames.get(i))){
                                    offset += recordElement.getBitOffset().intValue();
                                    variableDataTypeId = recordElement.getDataTypeId();
                                    //Arrived at destination
                                    if(i == (recordElementNames.size()-1)){
                                        size = recordElement.getBitSize().intValue();
                                        datatype = getEquipmentDefinitionDataType(scope, recordElement.getDataTypeId());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        //Assemble the information
            if(address!= null ){
                //Add offset to address and convert to byte again
                byte[] temp = new byte[8];
                System.arraycopy(address, 0, temp, 8-address.length, address.length);
                long addressLong = ByteBuffer.wrap(temp).getLong();
                addressLong += offset;
                byte[] addressByte = Arrays.copyOfRange(ByteBuffer.allocate(Long.BYTES).putLong(addressLong).array(), 4, 8);

            dsfEquipmentDefinitionRecordElement = new DSFEquipmentDefinitionRecordElement(addressByte, datatype,getDataType(datatype), size,compilationUnitArgument, dsfRecordElement );
        }
        return dsfEquipmentDefinitionRecordElement;
    }


    private Object getEquipmentDefinitionDataType(List<Object> scope , String datatypeId){
        Object dataType = null;
        for (Object obj : scope){
            switch (getDataType(obj)){
                case VARIABLE:
                    if( ((TypeCompilationUnit.Variable)obj).getDataTypeId().equalsIgnoreCase(datatypeId)){
                            return obj;
                    }
                    break;
                case CHARACTER:
                    if( ((TypeCompilationUnit.CharacterDataType)obj).getId().equalsIgnoreCase(datatypeId)){
                        return obj;
                    }
                    break;
                case BOOLEAN:
                    if( ((TypeCompilationUnit.BooleanDataType)obj).getId().equalsIgnoreCase(datatypeId)){
                        return obj;
                    }
                    break;
                case INTEGER:
                    if( ((TypeCompilationUnit.IntegerDataType)obj).getId().equalsIgnoreCase(datatypeId)){
                        return obj;
                    }
                    break;
                case FLOAT:
                    if( ((TypeCompilationUnit.FloatDataType)obj).getId().equalsIgnoreCase(datatypeId)){
                        return obj;
                    }
                    break;
                case POINTER:
                    if( ((TypeCompilationUnit.PointerDataType)obj).getId().equalsIgnoreCase(datatypeId)){
                        return obj;
                    }
                    break;
                case ENUM:
                    if( ((TypeCompilationUnit.EnumDataType)obj).getId().equalsIgnoreCase(datatypeId)){
                        return obj;
                    }
                    break;
                case LIST:
                    if( ((TypeCompilationUnit.ListDataType)obj).getId().equalsIgnoreCase(datatypeId)){
                        return obj;
                    }
                    break;
                case RECORD:
                    if( ((TypeCompilationUnit.RecordDataType)obj).getId().equalsIgnoreCase(datatypeId)){
                        return obj;
                    }
                    break;
                case UNION:
                    if( ((TypeCompilationUnit.UnionDataType)obj).getId().equalsIgnoreCase(datatypeId)){
                        return obj;
                    }
                    break;
                case UNDEFINED:
                    return obj;
            }
        }


        return dataType;
    }
    private EquipmentDefinitionDataType getDataType(Object variableAndDataType){
        if(variableAndDataType instanceof TypeCompilationUnit.Variable) {
            return EquipmentDefinitionDataType.VARIABLE;
        }
        else if(variableAndDataType instanceof TypeCompilationUnit.CharacterDataType){
            return EquipmentDefinitionDataType.CHARACTER;
        }
        else if(variableAndDataType instanceof TypeCompilationUnit.BooleanDataType){
            return EquipmentDefinitionDataType.BOOLEAN;
        }
        else if(variableAndDataType instanceof TypeCompilationUnit.IntegerDataType){
            return EquipmentDefinitionDataType.INTEGER;
        }
        else if(variableAndDataType instanceof TypeCompilationUnit.FloatDataType){
            return EquipmentDefinitionDataType.FLOAT;
        }
        else if(variableAndDataType instanceof TypeCompilationUnit.PointerDataType){
            return EquipmentDefinitionDataType.POINTER;
        }
        else if(variableAndDataType instanceof TypeCompilationUnit.EnumDataType){
            return EquipmentDefinitionDataType.ENUM;
        }
        else if(variableAndDataType instanceof TypeCompilationUnit.RecordDataType){
            return EquipmentDefinitionDataType.RECORD;
        }
        else if(variableAndDataType instanceof TypeCompilationUnit.UnionDataType){
            return EquipmentDefinitionDataType.UNION;
        }
        else if(variableAndDataType instanceof TypeCompilationUnit.ListDataType){
            return EquipmentDefinitionDataType.LIST;
        }
        else{
            return EquipmentDefinitionDataType.UNDEFINED;
        }
    }


    private boolean portRegistered(int port){
        Iterator it = hashMapAddressToPort.entrySet().iterator();
        while (it.hasNext()){
            for (DSFTuple dsfTuple : (ArrayList<DSFTuple>)((Map.Entry)it.next()).getValue()){
                if(dsfTuple.getPort() == port){
                    return true;
                }
            }
        }
        return false;
    }

    private long byteToLong(byte[] b){
        byte[] bytes = new byte[Long.BYTES];
        System.arraycopy(b, 0, bytes, Long.BYTES-b.length, b.length);
        return ByteBuffer.wrap(bytes).getLong();
    }
    private byte[] LongToByte(long n, int size){
        return Arrays.copyOfRange(ByteBuffer.allocate(8).putLong(n).array(), 8-size, 8);
    }

}
