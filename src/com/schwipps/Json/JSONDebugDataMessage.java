package com.schwipps.Json;


import com.schwipps.DSFBuilder.DSFDebugDataItem;
import com.schwipps.DSFBuilder.DSFEquipmentDefinitionRecordElement;
import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.DSFBuilder.enums.EquipmentDefinitionDataType;
import com.schwipps.Main.DSFAddressLinker;
import com.schwipps.dsf.TypeCompilationUnit;
import com.schwipps.dsf.TypeEquipmentDescription;

import javax.json.*;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedList;


public class JSONDebugDataMessage {
    private JsonObjectBuilder jsonDebugDataMessage;
    private JsonArrayBuilder dataItems;


    public JSONDebugDataMessage(){
        jsonDebugDataMessage    = Json.createObjectBuilder();
        dataItems               = Json.createArrayBuilder();

    }

    public byte[] toByte(){
        jsonDebugDataMessage.add("JSONDebugDataMessage",dataItems);
        JsonObject jsonObject = jsonDebugDataMessage.build();
        return null;
    }



    public void addDataItem(DSFDebugDataItem dsfDebugDataItem, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement){
        //Variable Name Head
        JsonObjectBuilder dataItem = Json.createObjectBuilder();


        //Fill the actual data first
        JsonArrayBuilder dataArrayData = Json.createArrayBuilder();
        fillDataArray(dataArrayData,dsfDebugDataItem.getData(),dsfEquipmentDefinitionRecordElement);

        //We build the object from variable to RecordElement

        //Object "RecordElementName":[{"RecordElementname":[]}]
        dataItem.add(dsfEquipmentDefinitionRecordElement.getDsfRecordElement().getVariable().toString(),buildHirarchichalDataArray(dataArrayData, dsfEquipmentDefinitionRecordElement.getDsfRecordElement().getRecordElementNames()));
        dataItems.add(dataItem);

    }

    private JsonArrayBuilder buildHirarchichalDataArray(JsonArrayBuilder dataArrayData, LinkedList<String> recordElementnames) {
        JsonArrayBuilder last = null;

        if((recordElementnames == null) | (recordElementnames.size() == 0)){
            return dataArrayData;
        }
        else {
            for (int i = (recordElementnames.size()-1); i >= 0; i--){
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                if(i ==(recordElementnames.size()-1) ){
                        objectBuilder.add(recordElementnames.get(i),dataArrayData );

                       arrayBuilder.add(objectBuilder);
                       last = arrayBuilder;
                       //NICE after first
                }
                else {
                    objectBuilder.add(recordElementnames.get(i),last);
                    arrayBuilder.add(objectBuilder);
                }

            }
        }
        return last;

    }

    private void fillDataArray(JsonArrayBuilder dataArray, byte[] byteData, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement) {
        switch (dsfEquipmentDefinitionRecordElement.getDataTypeEnum()){
            case VARIABLE: //Will not happen
                //TODO ?
                break;
            case CHARACTER: //Char is always 8 bit -> 1 byte
                dataArray.add((char) byteData[0]);
                break;
            case BOOLEAN: //Not Existing
                break;
            case INTEGER:
                    TypeCompilationUnit.IntegerDataType integerDataType = (TypeCompilationUnit.IntegerDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                    dataArrayAddInteger(dataArray, byteData, integerDataType);
                    break;
            case FLOAT:
                    TypeCompilationUnit.FloatDataType floatDataType = (TypeCompilationUnit.FloatDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                    dataArrayAddFloat(dataArray, byteData, floatDataType);
                    break;
            case POINTER:
                    TypeCompilationUnit.PointerDataType pointerDataType = (TypeCompilationUnit.PointerDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                    dataArrayAddPointer(dataArray,byteData,pointerDataType);
                    break;
            case ENUM:
                    TypeCompilationUnit.EnumDataType enumDataType = (TypeCompilationUnit.EnumDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                    dataArrayAddEnum(dataArray, byteData, enumDataType);
                break;
            case LIST:
                    TypeCompilationUnit.ListDataType listDataType = (TypeCompilationUnit.ListDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                    dataArrayAddList(dataArray, byteData, listDataType, dsfEquipmentDefinitionRecordElement);
                break;
            case RECORD:
                    TypeCompilationUnit.RecordDataType recordDataType = (TypeCompilationUnit.RecordDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                    dataArrayAddRecord(dataArray,byteData,recordDataType, dsfEquipmentDefinitionRecordElement);
                break;
                //TODO - BEGINNING FROM HERE NOT SUPPORTED
            case UNION:
                break;
            case UNDEFINED:
                break;
        }

    }
    private void dataArrayAddPointer(JsonArrayBuilder dataArray, byte[] byteData, TypeCompilationUnit.PointerDataType pointerDataTypeItem) {
        int byteLength = pointerDataTypeItem.getBitSize().intValue()/8;
        byte[] temp = new byte[Long.BYTES];
        System.arraycopy(byteData, 0, temp, Long.BYTES-byteLength, byteLength);
        dataArray.add(ByteBuffer.wrap(temp).getLong());
    }
    private void dataArrayAddInteger(JsonArrayBuilder dataArray, byte[] byteData,TypeCompilationUnit.IntegerDataType integerDataTypeItem){
        int byteLength = integerDataTypeItem.getBitSize().intValue()/8;
        boolean signed = true;
        switch (integerDataTypeItem.getSigned()){
            case NO: signed = false;
                break;
            case YES:signed = true;
                break;
        }
        if(signed){
            if(byteLength == 4){
                dataArray.add(ByteBuffer.wrap(byteData).getInt());
            }
            else {
                //TODO 8Bit and Signed
            }
        }
        else {
            byte [] temp = new byte[Long.BYTES];
            System.arraycopy(byteData, 0, temp, Long.BYTES-byteLength, byteLength);
            dataArray.add(ByteBuffer.wrap(temp).getLong());
        }
    }
    private void dataArrayAddFloat(JsonArrayBuilder dataArray, byte[] byteData, TypeCompilationUnit.FloatDataType floatDataTypeItem){
        int byteLength = floatDataTypeItem.getBitSize().intValue()/8;
        byte[]temp = new byte[Double.BYTES];
        System.arraycopy(byteData, 0, temp, Double.BYTES-byteLength, byteLength);
        dataArray.add(ByteBuffer.wrap(temp).getDouble());
    }
    private void dataArrayAddEnum(JsonArrayBuilder dataArray, byte[] byteData, TypeCompilationUnit.EnumDataType enumDataTypeItem){
        int byteLength = enumDataTypeItem.getBitSize().intValue()/8;
        byte[]temp = new byte[Long.BYTES];
        System.arraycopy(byteData, 0, temp, Long.BYTES-byteLength, byteLength);
        long val = ByteBuffer.wrap(temp).getLong();
        for(TypeCompilationUnit.EnumDataType.EnumElement enumElement : enumDataTypeItem.getEnumElement()){
            if(enumElement.getValue().longValue() == val){
                dataArray.add(enumElement.getName());
            }
        }
    }
    private void dataArrayAddList(JsonArrayBuilder dataArray, byte[] byteData, TypeCompilationUnit.ListDataType listDataTypeItem, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement){
        TypeCompilationUnit compilationUnit = dsfEquipmentDefinitionRecordElement.getCompilationUnit();
        int numberOfElements = listDataTypeItem.getListIndex().get(0).getRangeUpper().intValue()+1;
        //int numberOfElements = dsfEquipmentDefinitionRecordElement.getBitSize()/listDataTypeItem.getListElement().getBitSize().intValue();
        int elementBytes = listDataTypeItem.getListElement().getBitSize().intValue()/8;
        String dataTypeId = listDataTypeItem.getListElement().getDataTypeId();

        for(Object variableAndDataType : compilationUnit.getVariableAndCharacterDataTypeAndBooleanDataType()){
            switch (getDataType(variableAndDataType)){
                case VARIABLE: //WILL NOT HAPPEN
                    break;
                case CHARACTER:
                        TypeCompilationUnit.CharacterDataType characterDataType = (TypeCompilationUnit.CharacterDataType) variableAndDataType;
                        //found
                        if(characterDataType.getId().equalsIgnoreCase(dataTypeId)){
                            for (int i = 0; i< numberOfElements; i++){
                                dataArray.add((char)byteData[i]);
                            }
                        }
                    break;
                case BOOLEAN:
                    break;
                case INTEGER:
                        TypeCompilationUnit.IntegerDataType integerDataType = (TypeCompilationUnit.IntegerDataType) variableAndDataType;
                        if(integerDataType.getId().equalsIgnoreCase(dataTypeId)){
                            for(int i = 0; i <numberOfElements;i++){
                                dataArrayAddInteger(dataArray, Arrays.copyOfRange(byteData, i*elementBytes,i*elementBytes+elementBytes ), integerDataType);
                            }
                        }
                    break;
                case FLOAT:
                        TypeCompilationUnit.FloatDataType floatDataType = (TypeCompilationUnit.FloatDataType)   variableAndDataType;
                        if(floatDataType.getId().equalsIgnoreCase(dataTypeId)){
                            for(int i = 0; i<numberOfElements; i++){
                                dataArrayAddFloat(dataArray, Arrays.copyOfRange(byteData, i*elementBytes,i*elementBytes+elementBytes ), floatDataType);
                            }
                        }
                    break;
                case POINTER:
                    TypeCompilationUnit.PointerDataType pointerDataType = (TypeCompilationUnit.PointerDataType)   variableAndDataType;
                    if(pointerDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for(int i = 0; i<numberOfElements; i++){
                            dataArrayAddPointer(dataArray, Arrays.copyOfRange(byteData, i*elementBytes,i*elementBytes+elementBytes ), pointerDataType);
                        }
                    }
                    break;
                case ENUM:
                    TypeCompilationUnit.EnumDataType enumDataType = (TypeCompilationUnit.EnumDataType)   variableAndDataType;
                    if(enumDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for(int i = 0; i<numberOfElements; i++){
                            dataArrayAddEnum(dataArray, Arrays.copyOfRange(byteData, i*elementBytes,i*elementBytes+elementBytes ), enumDataType);
                        }
                    }
                    break;
                    //THE CASES LISTED BELOW WILL NOT OCCUR
                case LIST:
                    TypeCompilationUnit.ListDataType listDataType = (TypeCompilationUnit.ListDataType) variableAndDataType;
                    if(listDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for (int i=0; i<numberOfElements;i++){
                            dataArrayAddList(dataArray, Arrays.copyOfRange(byteData, i*elementBytes,i*elementBytes+elementBytes ), listDataType,dsfEquipmentDefinitionRecordElement );
                        }
                    }
                    break;
                case RECORD:
                    TypeCompilationUnit.RecordDataType recordDataType = (TypeCompilationUnit.RecordDataType) variableAndDataType;
                    if(recordDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for(int i = 0; i<numberOfElements; i++){
                            dataArrayAddRecord(dataArray, Arrays.copyOfRange(byteData, i*elementBytes,i*elementBytes+elementBytes ), recordDataType, dsfEquipmentDefinitionRecordElement);
                        }
                    }
                    break;
                case UNION: //TODO
                    break;
                case UNDEFINED:
                    break;
            }

        }
    }
    private void dataArrayAddRecord(JsonArrayBuilder dataArray, byte[] byteData, TypeCompilationUnit.RecordDataType recordDataType, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement) {
        //Loop trhough all record elements of the RecordDataType
        //And create a new JSON object for each
        for(TypeCompilationUnit.RecordDataType.RecordElement recordElement : recordDataType.getRecordElement()) {
            JsonObjectBuilder subDataObject = Json.createObjectBuilder();
            JsonArrayBuilder subDataArray = Json.createArrayBuilder();
            //Identify the datype of each and act accordingly
            String datatypeId = recordElement.getDataTypeId();
            for(Object variableOrDataType : dsfEquipmentDefinitionRecordElement.getCompilationUnit().getVariableAndCharacterDataTypeAndBooleanDataType()){
                switch (getDataType(variableOrDataType)){
                    case CHARACTER:
                        TypeCompilationUnit.CharacterDataType characterDataType = (TypeCompilationUnit.CharacterDataType) variableOrDataType;
                        if(characterDataType.getId().equalsIgnoreCase(datatypeId)){
                            subDataArray.add((char)(byteData[(recordElement.getBitOffset().intValue()/8)]));
                        }
                        break;
                    case BOOLEAN:
                        //TODO
                        break;
                    case INTEGER:
                        TypeCompilationUnit.IntegerDataType integerDataType = (TypeCompilationUnit.IntegerDataType) variableOrDataType;
                        if(integerDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataArrayAddInteger(subDataArray, Arrays.copyOfRange(byteData, start/8, end/8), integerDataType);
                        }
                        break;
                    case FLOAT:
                        TypeCompilationUnit.FloatDataType floatDataType = (TypeCompilationUnit.FloatDataType) variableOrDataType;
                        if(floatDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataArrayAddFloat(subDataArray, Arrays.copyOfRange(byteData, start/8, end/8), floatDataType);
                        }
                        break;
                    case POINTER:
                        TypeCompilationUnit.PointerDataType pointerDataType = (TypeCompilationUnit.PointerDataType) variableOrDataType;
                        if(pointerDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataArrayAddPointer(subDataArray, Arrays.copyOfRange(byteData, start/8, end/8), pointerDataType);
                        }
                        break;
                    case ENUM:
                        TypeCompilationUnit.EnumDataType enumDataType = (TypeCompilationUnit.EnumDataType) variableOrDataType;
                        if(enumDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataArrayAddEnum(subDataArray, Arrays.copyOfRange(byteData, start/8 , end/8), enumDataType);
                        }
                        break;
                    case LIST:
                        TypeCompilationUnit.ListDataType listDataType = (TypeCompilationUnit.ListDataType) variableOrDataType;
                        if(listDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataArrayAddList(subDataArray, Arrays.copyOfRange(byteData, start/8, end/8), listDataType,dsfEquipmentDefinitionRecordElement);
                        }
                        break;
                    case RECORD:
                        TypeCompilationUnit.RecordDataType recordDataType1 = (TypeCompilationUnit.RecordDataType) variableOrDataType;
                        if(recordDataType1.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataArrayAddRecord(subDataArray, Arrays.copyOfRange(byteData, start/8, end/8), recordDataType1, dsfEquipmentDefinitionRecordElement);
                        }
                        break;
                        //TODO
                    case UNION:
                        break;
                        //Not happen
                    case VARIABLE:
                        break;
                    case UNDEFINED:
                        break;
                }
            }
            subDataObject.add(recordElement.getName(), subDataArray);
            dataArray.add(subDataObject);
        }
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
}
