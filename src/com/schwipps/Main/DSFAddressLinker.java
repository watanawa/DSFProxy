package com.schwipps.Main;

import com.schwipps.DSFBuilder.DSFEquipmentDefinitionRecordElement;
import com.schwipps.DSFBuilder.enums.EquipmentDefinitionDataType;
import com.schwipps.dsf.TypeCompilationUnit;
import com.schwipps.dsf.TypeDebugSymbolSet;
import com.schwipps.dsf.TypeEquipmentDescription;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DSFAddressLinker {

    //This class keeps track, which Client registered for which dataitem

    //Address as long is used as key for minimal delay
    private HashMap<Long, ArrayList<DSFTuple>> hashMapAddressToPort;


    private TypeDebugSymbolSet typeDebugSymbolSet;

    public  DSFAddressLinker (TypeEquipmentDescription typeEquipmentDescription){
        this.typeDebugSymbolSet = typeEquipmentDescription.getDebugSymbols().getDebugSymbolSet().get(0);
        hashMapAddressToPort = new HashMap<>();
    }

    // Um in die Hashmap zu schreiben
    public DSFEquipmentDefinitionRecordElement getDSFEquipmentDefinitionRecordElement(String variableName, String recordElementName){
        byte[] address = null;
        BigInteger offset = null;
        BigInteger size = null;
        String datatypeId = null;
        DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement = null;
        List<Object> scope = null;

        for(TypeCompilationUnit compilationUnit : typeDebugSymbolSet.getCompilationUnit()) {
            List<Object> variableAndDatatype = compilationUnit.getVariableAndCharacterDataTypeAndBooleanDataType();
            //we need to find the variable first because the record Element name is ambitious
            for (Object variableAndDataTypeItem : variableAndDatatype) {
                if (getDataType(variableAndDataTypeItem).equals(EquipmentDefinitionDataType.VARIABLE)) {
                    TypeCompilationUnit.Variable variable = (TypeCompilationUnit.Variable) variableAndDataTypeItem;
                    //Variable found - needed to fix start pointer
                    if (variable.getName().equalsIgnoreCase(variableName)) {
                        address = variable.getAddress();
                        scope = variableAndDatatype;
                    }
                }
            }
        }
        for(Object variableAndDataTypeItem : scope) {
            if(getDataType(variableAndDataTypeItem).equals(EquipmentDefinitionDataType.RECORD)){
                //Bruteforce cast
                TypeCompilationUnit.RecordDataType recordDataTypes = (TypeCompilationUnit.RecordDataType) variableAndDataTypeItem;
                for( TypeCompilationUnit.RecordDataType.RecordElement recordElement : recordDataTypes.getRecordElement()){
                    //Record Element found
                    if(recordElement.getName().equalsIgnoreCase(recordElementName)){
                        offset = recordElement.getBitOffset();
                        size = recordElement.getBitSize();
                        datatypeId = recordElement.getDataTypeId();
                    }
                }
            }
        }

        //Assemble the information
            if(address!= null && offset!= null){
                //Add offset to address and convert to byte again
                byte[] temp = new byte[8];
                System.arraycopy(address, 0, temp, 8-address.length, address.length);
                long addressLong = ByteBuffer.wrap(temp).getLong();
                addressLong += offset.longValue();
                byte[] addressByte = Arrays.copyOfRange(ByteBuffer.allocate(Long.BYTES).putLong(addressLong).array(), 4, 8);

                Object datatypeItem = getEquipmentDefinitionDataType(scope,datatypeId);

            dsfEquipmentDefinitionRecordElement = new DSFEquipmentDefinitionRecordElement(addressByte, datatypeItem,getDataType(datatypeItem), size.intValue() );
        }
        return dsfEquipmentDefinitionRecordElement;
    }

    private Object getEquipmentDefinitionDataType(List<Object> scope , String datatypeId){
        Object dataType = null;
        for (Object obj : scope){
            switch (getDataType(obj)){
                case VARIABLE:
                    if( ((TypeCompilationUnit.Variable)obj).getName().equalsIgnoreCase(datatypeId)){
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

    public void registerMessage(int portClient, String variableName, String recordElementName ){
        DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement = getDSFEquipmentDefinitionRecordElement(variableName, recordElementName);
        byte[] address = dsfEquipmentDefinitionRecordElement.getAddress();
        long addressVal = byteToLong(address);

        if(hashMapAddressToPort.containsKey(addressVal) ){
            //check if already registered
            boolean existing = false;
            for(DSFTuple dsfTuple : hashMapAddressToPort.get(addressVal)){
                if(dsfTuple.getPort() == portClient){
                    existing = true;
                    return;
                }
            }
            //add entry to arraylist
            if(!existing){
                hashMapAddressToPort.get(addressVal).add(new DSFTuple(portClient, dsfEquipmentDefinitionRecordElement));
            }
        }
        else{
            ArrayList<DSFTuple> dsfTuple = new ArrayList<DSFTuple>();
            dsfTuple.add(new DSFTuple(portClient, dsfEquipmentDefinitionRecordElement));
            hashMapAddressToPort.put(addressVal,dsfTuple);
        }
    }
    public void unregisterMessage(int portClient, String variableName, String recordElementName){
        DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement = getDSFEquipmentDefinitionRecordElement(variableName, recordElementName);
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
    }

    public ArrayList<DSFTuple> getTuple (byte[] address){
        return hashMapAddressToPort.get(byteToLong(address));
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
