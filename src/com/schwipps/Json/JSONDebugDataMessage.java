package com.schwipps.Json;

import com.schwipps.DSFBuilder.DSFDebugDataItem;
import com.schwipps.DSFBuilder.DSFEquipmentDefinitionRecordElement;
import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.DSFBuilder.enums.EquipmentDefinitionDataType;
import com.schwipps.dsf.TypeCompilationUnit;
import org.json.JSONArray;
import org.json.JSONObject;


import java.nio.ByteBuffer;
import java.util.Arrays;


public class JSONDebugDataMessage {
    private JSONObject jsonDebugDataObject;

    public JSONDebugDataMessage(){
        jsonDebugDataObject = new JSONObject();
        jsonDebugDataObject.put("JSONDebugDataMessage",new JSONObject());
    }
    public JSONObject getJsonDebugDataObject(){
        return  jsonDebugDataObject;
    }

    public byte[] toByte(){
        return jsonDebugDataObject.toString().getBytes();
    }
    public void addField(DSFDebugDataItem dsfDebugDataItem, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement) {
        //check if this message actually registered to receive this dataItem
        if ((dsfDebugDataItem.getDataLength() == dsfEquipmentDefinitionRecordElement.getBitSize()/8) && dsfDebugDataItem.getDataItemAddressLong() == dsfEquipmentDefinitionRecordElement.getAddressLong()) {
            //Check if the variable name already exists anywhere
            JSONObject jsonObjectVariable = jsonDebugDataObject.getJSONObject("JSONDebugDataMessage");
            int numOfRecordElementParents = dsfEquipmentDefinitionRecordElement.getDsfRecordElement().getRecordElementNames().size();
            DSFRecordElement dsfRecordElement = dsfEquipmentDefinitionRecordElement.getDsfRecordElement();
            JSONObject jsonRecordElements[] = new JSONObject[numOfRecordElementParents];

            if(numOfRecordElementParents==0){
                fillDataObject(jsonObjectVariable, dsfDebugDataItem.getData(), dsfEquipmentDefinitionRecordElement, dsfRecordElement.getVariable());
                return;
            }
            //Building the Hirarchylayers from the variableName to the actual RecordElement
            else{
                for(int i= 0; i < numOfRecordElementParents; i++){
                    if(i==0){
                        jsonRecordElements[i] = getJsonObjectByString(jsonObjectVariable, dsfRecordElement.getRecordElementNames().get(i));
                    }
                    else {
                        jsonRecordElements[i] = getJsonObjectByString(jsonRecordElements[i-1],dsfRecordElement.getRecordElementNames().get(i));
                    }
                }
            }
            //Fill the data into the corresponding dataObject
            //numOfRecordElementParents >= 1
            fillDataObject(jsonRecordElements[numOfRecordElementParents-1],dsfDebugDataItem.getData() , dsfEquipmentDefinitionRecordElement,dsfRecordElement.getRecordElementNames().getLast() );
        }

    }
    public JSONObject getJsonObjectByString(JSONObject jsonObject, String string){
            JSONObject jsonObjectAns = null;
            if (jsonObject.has(string)) {
                jsonObjectAns = jsonObject.getJSONObject(string);
            } else {
                jsonObjectAns = new JSONObject();
                jsonObject.put(string, jsonObjectAns);
            }
            return jsonObjectAns;
    }
    public JSONArray getJsonArrayByString(JSONObject jsonObject, String string){
        JSONArray jsonArray = null;
        if (jsonObject.has(string)) {
            if(jsonObject.get(string) instanceof JSONArray){
                jsonArray = jsonObject.getJSONArray(string);
            }
        } else {
            jsonArray = new JSONArray();
            jsonObject.put(string, jsonArray);
        }
        return jsonArray;
    }

    //Put into the JSON Object the key + corespponing data
    private void fillDataObject(JSONObject dataObject, byte[] byteData, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement, String key) {
        JSONArray dataArray;
        switch (dsfEquipmentDefinitionRecordElement.getDataTypeEnum()){
            case VARIABLE: //Will not happen
                break;
            case CHARACTER://Char is always 8 bit -> 1 byte
                dataArray = getJsonArrayByString(dataObject, key);
                dataArrayAddChar(dataArray, byteData[0]);
                break;
            case BOOLEAN: //Not Existing
                break;
            case INTEGER:
                dataArray = getJsonArrayByString(dataObject, key);
                TypeCompilationUnit.IntegerDataType integerDataType = (TypeCompilationUnit.IntegerDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataArrayAddInteger(dataArray, byteData, integerDataType);
                break;
            case FLOAT:
                dataArray = getJsonArrayByString(dataObject, key);
                TypeCompilationUnit.FloatDataType floatDataType = (TypeCompilationUnit.FloatDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataArrayAddFloat(dataArray, byteData, floatDataType);
                break;
            case POINTER:
                dataArray = getJsonArrayByString(dataObject, key);
                TypeCompilationUnit.PointerDataType pointerDataType = (TypeCompilationUnit.PointerDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataArrayAddPointer(dataArray,byteData,pointerDataType);
                break;
            case ENUM:
                dataArray = getJsonArrayByString(dataObject, key);
                TypeCompilationUnit.EnumDataType enumDataType = (TypeCompilationUnit.EnumDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataArrayAddEnum(dataArray, byteData, enumDataType);
                break;
            case LIST:
                dataArray = getJsonArrayByString(dataObject, key);
                TypeCompilationUnit.ListDataType listDataType = (TypeCompilationUnit.ListDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataArrayAddList(dataArray, byteData, listDataType, dsfEquipmentDefinitionRecordElement);
                break;
            case RECORD:
                TypeCompilationUnit.RecordDataType recordDataType = (TypeCompilationUnit.RecordDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                // Get Dataobject from key
                dataArrayAddRecord(getJsonObjectByString(dataObject, key),byteData,recordDataType, dsfEquipmentDefinitionRecordElement);

                break;
            //TODO - BEGINNING FROM HERE NOT SUPPORTED
            case UNION:
                break;
            case UNDEFINED:
                break;
        }

    }
    private void dataArrayAddChar(JSONArray dataArray, byte data){
        dataArray.put((char)data);
    }
    private void dataArrayAddPointer(JSONArray dataArray, byte[] byteData, TypeCompilationUnit.PointerDataType pointerDataTypeItem) {
        int byteLength = pointerDataTypeItem.getBitSize().intValue()/8;
        byte[] temp = new byte[Long.BYTES];
        System.arraycopy(byteData, 0, temp, Long.BYTES-byteLength, byteLength);
        dataArray.put(ByteBuffer.wrap(temp).getLong());
    }
    private void dataArrayAddInteger(JSONArray dataArray, byte[] byteData,TypeCompilationUnit.IntegerDataType integerDataTypeItem){
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
                dataArray.put(ByteBuffer.wrap(byteData).getInt());
            }
            else {
                //TODO 8Bit and Signed
            }
        }
        else {
            byte [] temp = new byte[Long.BYTES];
            System.arraycopy(byteData, 0, temp, Long.BYTES-byteLength, byteLength);
            dataArray.put(ByteBuffer.wrap(temp).getLong());
        }
    }
    private void dataArrayAddFloat(JSONArray dataArray, byte[] byteData, TypeCompilationUnit.FloatDataType floatDataTypeItem){
        int byteLength = floatDataTypeItem.getBitSize().intValue()/8;
        byte[]temp = new byte[Double.BYTES];
        System.arraycopy(byteData, 0, temp, Double.BYTES-byteLength, byteLength);
        dataArray.put(ByteBuffer.wrap(temp).getDouble());
    }
    private void dataArrayAddEnum(JSONArray dataArray, byte[] byteData, TypeCompilationUnit.EnumDataType enumDataTypeItem){
        int byteLength = enumDataTypeItem.getBitSize().intValue()/8;
        byte[]temp = new byte[Long.BYTES];
        System.arraycopy(byteData, 0, temp, Long.BYTES-byteLength, byteLength);
        long val = ByteBuffer.wrap(temp).getLong();
        for(TypeCompilationUnit.EnumDataType.EnumElement enumElement : enumDataTypeItem.getEnumElement()){
            if(enumElement.getValue().longValue() == val){
                dataArray.put(enumElement.getName());
            }
        }
    }
    private void dataArrayAddList(JSONArray dataArray, byte[] byteData, TypeCompilationUnit.ListDataType listDataTypeItem, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement){
        TypeCompilationUnit compilationUnit = dsfEquipmentDefinitionRecordElement.getCompilationUnit();
        int numberOfElements = listDataTypeItem.getListIndex().get(0).getRangeUpper().intValue()+1;
        //int numberOfElements = dsfEquipmentDefinitionRecordElement.getBitSize()/listDataTypeItem.getListElement().getBitSize().intValue();
        int putBytes = listDataTypeItem.getListElement().getBitSize().intValue()/8;
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
                            dataArray.put((char)byteData[i]);
                        }
                    }
                    break;
                case BOOLEAN:
                    break;
                case INTEGER:
                    TypeCompilationUnit.IntegerDataType integerDataType = (TypeCompilationUnit.IntegerDataType) variableAndDataType;
                    if(integerDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for(int i = 0; i <numberOfElements;i++){
                            dataArrayAddInteger(dataArray, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), integerDataType);
                        }
                    }
                    break;
                case FLOAT:
                    TypeCompilationUnit.FloatDataType floatDataType = (TypeCompilationUnit.FloatDataType)   variableAndDataType;
                    if(floatDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for(int i = 0; i<numberOfElements; i++){
                            dataArrayAddFloat(dataArray, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), floatDataType);
                        }
                    }
                    break;
                case POINTER:
                    TypeCompilationUnit.PointerDataType pointerDataType = (TypeCompilationUnit.PointerDataType)   variableAndDataType;
                    if(pointerDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for(int i = 0; i<numberOfElements; i++){
                            dataArrayAddPointer(dataArray, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), pointerDataType);
                        }
                    }
                    break;
                case ENUM:
                    TypeCompilationUnit.EnumDataType enumDataType = (TypeCompilationUnit.EnumDataType)   variableAndDataType;
                    if(enumDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for(int i = 0; i<numberOfElements; i++){
                            dataArrayAddEnum(dataArray, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), enumDataType);
                        }
                    }
                    break;
                //THE CASES LISTED BELOW WILL NOT OCCUR
                case LIST:
                    TypeCompilationUnit.ListDataType listDataType = (TypeCompilationUnit.ListDataType) variableAndDataType;
                    if(listDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for (int i=0; i<numberOfElements;i++){
                            dataArrayAddList(dataArray, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), listDataType,dsfEquipmentDefinitionRecordElement );
                        }
                    }
                    break;
                case RECORD:
                    TypeCompilationUnit.RecordDataType recordDataType = (TypeCompilationUnit.RecordDataType) variableAndDataType;
                    if(recordDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for(int i = 0; i<numberOfElements; i++){
                                JSONObject jsonObject = new JSONObject();
                                dataArrayAddRecord(jsonObject, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), recordDataType, dsfEquipmentDefinitionRecordElement);
                                dataArray.put(jsonObject);

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
    //Write Key + value to the Object
    private void dataArrayAddRecord(JSONObject dataObject, byte[] byteData, TypeCompilationUnit.RecordDataType recordDataType, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement) {
        //Loop trhough all record puts of the RecordDataType
        //And create a new JSON object for each
        JSONArray jsonArray;
        for(TypeCompilationUnit.RecordDataType.RecordElement recordElement : recordDataType.getRecordElement()) {
            //Identify the datype of each and act accordingly
            String datatypeId = recordElement.getDataTypeId();
            for(Object variableOrDataType : dsfEquipmentDefinitionRecordElement.getCompilationUnit().getVariableAndCharacterDataTypeAndBooleanDataType()){
                switch (getDataType(variableOrDataType)){
                    case CHARACTER:
                        TypeCompilationUnit.CharacterDataType characterDataType = (TypeCompilationUnit.CharacterDataType) variableOrDataType;
                        if(characterDataType.getId().equalsIgnoreCase(datatypeId)){
                            jsonArray = getJsonArrayByString(dataObject, recordElement.getName());
                            jsonArray.put((char)(byteData[(recordElement.getBitOffset().intValue()/8)]));
                            dataObject.put(recordElement.getName(), jsonArray);
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
                            jsonArray = getJsonArrayByString(dataObject, recordElement.getName());
                            dataArrayAddInteger(jsonArray, Arrays.copyOfRange(byteData, start/8, end/8), integerDataType);
                            dataObject.put(recordElement.getName(), jsonArray);
                        }
                        break;
                    case FLOAT:
                        TypeCompilationUnit.FloatDataType floatDataType = (TypeCompilationUnit.FloatDataType) variableOrDataType;
                        if(floatDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            jsonArray = getJsonArrayByString(dataObject, recordElement.getName());
                            dataArrayAddFloat(jsonArray, Arrays.copyOfRange(byteData, start/8, end/8), floatDataType);
                            dataObject.put(recordElement.getName(), jsonArray);
                        }
                        break;
                    case POINTER:
                        TypeCompilationUnit.PointerDataType pointerDataType = (TypeCompilationUnit.PointerDataType) variableOrDataType;
                        if(pointerDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            jsonArray = getJsonArrayByString(dataObject, recordElement.getName());
                            dataArrayAddPointer(jsonArray, Arrays.copyOfRange(byteData, start/8, end/8), pointerDataType);
                            dataObject.put(recordElement.getName(), jsonArray);
                        }
                        break;
                    case ENUM:
                        TypeCompilationUnit.EnumDataType enumDataType = (TypeCompilationUnit.EnumDataType) variableOrDataType;
                        if(enumDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            jsonArray = getJsonArrayByString(dataObject, recordElement.getName());
                            dataArrayAddEnum(jsonArray, Arrays.copyOfRange(byteData, start/8 , end/8), enumDataType);
                            dataObject.put(recordElement.getName(), jsonArray);
                        }
                        break;
                    case LIST:
                        TypeCompilationUnit.ListDataType listDataType = (TypeCompilationUnit.ListDataType) variableOrDataType;
                        if(listDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            jsonArray = getJsonArrayByString(dataObject, recordElement.getName());
                            dataArrayAddList(jsonArray, Arrays.copyOfRange(byteData, start/8, end/8), listDataType,dsfEquipmentDefinitionRecordElement);
                            dataObject.put(recordElement.getName(), jsonArray);
                        }
                        break;
                    case RECORD:
                        TypeCompilationUnit.RecordDataType recordDataType1 = (TypeCompilationUnit.RecordDataType) variableOrDataType;
                        if(recordDataType1.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            JSONObject subJsonObject = new JSONObject();
                            dataArrayAddRecord(subJsonObject, Arrays.copyOfRange(byteData, start/8, end/8), recordDataType1, dsfEquipmentDefinitionRecordElement);
                            dataObject.put(recordElement.getName(), subJsonObject);
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
