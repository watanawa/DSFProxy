package com.schwipps.Json;

import com.schwipps.DSFBuilder.DSFDebugDataItem;
import com.schwipps.DSFBuilder.DSFEquipmentDefinitionRecordElement;
import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.DSFBuilder.enums.EquipmentDefinitionDataType;
import com.schwipps.dsf.TypeCompilationUnit;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
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
        //(dsfDebugDataItem.getDataLength() == dsfEquipmentDefinitionRecordElement.getBitSize()/8) &&
        if (dsfDebugDataItem.getDataItemAddressLong() == dsfEquipmentDefinitionRecordElement.getAddressLong()) {
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
                        jsonRecordElements[i] = getJsonObjectByString(jsonObjectVariable, dsfRecordElement.getVariable());
                    }
                    else {
                        jsonRecordElements[i] = getJsonObjectByString(jsonRecordElements[i-1],dsfRecordElement.getRecordElementNames().get(i-1));
                    }
                }
            }
            //Fill the data into the corresponding dataObject
            //numOfRecordElementParents >= 1
            if(numOfRecordElementParents ==1){
                fillDataObject(jsonObjectVariable.getJSONObject(dsfRecordElement.getVariable()),dsfDebugDataItem.getData() , dsfEquipmentDefinitionRecordElement,dsfRecordElement.getRecordElementNames().getLast() );
            }
            else {
                //Prelastentry
                fillDataObject(jsonRecordElements[numOfRecordElementParents-1],dsfDebugDataItem.getData() , dsfEquipmentDefinitionRecordElement,dsfRecordElement.getRecordElementNames().getLast() );
            }
        }

    }
    private JSONObject getJsonObjectByString(JSONObject jsonObject, String string){
            JSONObject jsonObjectAns = null;
            if (jsonObject.has(string)) {
                jsonObjectAns = jsonObject.getJSONObject(string);
            } else {
                jsonObjectAns = new JSONObject();
                jsonObject.put(string, jsonObjectAns);
            }
            return jsonObjectAns;
    }
    private JSONArray getJsonArrayByString(JSONObject jsonObject, String string){
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
        //JSONObject dataArray;
        switch (dsfEquipmentDefinitionRecordElement.getDataTypeEnum()){
            case VARIABLE: //Will not happen
                break;
            case CHARACTER://Char is always 8 bit -> 1 byte
                dataObjectAddChar(dataObject, byteData[0], key);
                break;
            case BOOLEAN: //Not Existing
                break;
            case INTEGER:
                //dataArray = getJsonArrayByString(dataObject, key);
                TypeCompilationUnit.IntegerDataType integerDataType = (TypeCompilationUnit.IntegerDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataObjectAddInteger(dataObject, byteData, integerDataType, key);
                break;
            case FLOAT:
                //dataArray = getJsonArrayByString(dataObject, key);
                TypeCompilationUnit.FloatDataType floatDataType = (TypeCompilationUnit.FloatDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataObjectAddFloat(dataObject, byteData, floatDataType, key);
                break;
            case POINTER:
                //dataArray = getJsonArrayByString(dataObject, key);
                TypeCompilationUnit.PointerDataType pointerDataType = (TypeCompilationUnit.PointerDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataObjectAddPointer(dataObject,byteData,pointerDataType, key);
                break;
            case ENUM:
                //dataArray = getJsonArrayByString(dataObject, key);
                TypeCompilationUnit.EnumDataType enumDataType = (TypeCompilationUnit.EnumDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataObjectAddEnum(dataObject, byteData, enumDataType, key);
                break;
            case LIST:
                TypeCompilationUnit.ListDataType listDataType = (TypeCompilationUnit.ListDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                dataObjectAddList(dataObject, byteData, listDataType, dsfEquipmentDefinitionRecordElement, key);
                break;
            case RECORD:
                TypeCompilationUnit.RecordDataType recordDataType = (TypeCompilationUnit.RecordDataType) dsfEquipmentDefinitionRecordElement.getDataTypeItem();
                // Get Dataobject from key
                dataObjectAddRecord(dataObject,byteData,recordDataType, dsfEquipmentDefinitionRecordElement, key);

                break;
            //TODO - BEGINNING FROM HERE NOT SUPPORTED
            case UNION:
                break;
            case UNDEFINED:
                break;
        }

    }
    private void dataObjectAddChar(JSONObject jsonObject, byte data, String key){

        jsonObject.put(key, Character.toString((char)data));
    }
    private void dataObjectAddPointer(JSONObject jsonObject, byte[] byteData, TypeCompilationUnit.PointerDataType pointerDataTypeItem,String key) {
        int byteLength = pointerDataTypeItem.getBitSize().intValue()/8;
        byte[] temp = new byte[Long.BYTES];
        System.arraycopy(byteData, 0, temp, Long.BYTES-byteLength, byteLength);
        jsonObject.put(key,ByteBuffer.wrap(temp).getLong());
    }
    private void dataObjectAddInteger(JSONObject jsonObject, byte[] byteData,TypeCompilationUnit.IntegerDataType integerDataTypeItem, String key){
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
                jsonObject.put(key,ByteBuffer.wrap(byteData).getInt());
            }
            else {
                //TODO 8Bit and Signed
            }
        }
        else {
            byte [] temp = new byte[Long.BYTES];
            System.arraycopy(byteData, 0, temp, Long.BYTES-byteLength, byteLength);
            jsonObject.put(key,ByteBuffer.wrap(temp).getLong());
        }
    }
    private void dataObjectAddFloat(JSONObject jsonObject, byte[] byteData, TypeCompilationUnit.FloatDataType floatDataTypeItem, String key){
        int byteLength = floatDataTypeItem.getBitSize().intValue()/8;
        byte[]temp = new byte[Double.BYTES];
        System.arraycopy(byteData, 0, temp, Double.BYTES-byteLength, byteLength);
        double d = ByteBuffer.wrap(temp).getDouble();
        if(Double.isInfinite(d) || Double.isNaN(d)){
            //System.out.println("Test");
        }else
        {
            jsonObject.put(key,ByteBuffer.wrap(temp).getDouble());
        }
    }
    private void dataObjectAddEnum(JSONObject jsonObject, byte[] byteData, TypeCompilationUnit.EnumDataType enumDataTypeItem,String key){
        int byteLength = enumDataTypeItem.getBitSize().intValue()/8;
        byte[]temp = new byte[Long.BYTES];
        System.arraycopy(byteData, 0, temp, Long.BYTES-byteLength, byteLength);
        long val = ByteBuffer.wrap(temp).getLong();
        for(TypeCompilationUnit.EnumDataType.EnumElement enumElement : enumDataTypeItem.getEnumElement()){
            if(enumElement.getValue().longValue() == val){
                jsonObject.put(key,enumElement.getName());
            }
        }
    }
    private void dataObjectAddList(JSONObject dataObject, byte[] byteData, TypeCompilationUnit.ListDataType listDataTypeItem, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement,String key){
        TypeCompilationUnit compilationUnit = dsfEquipmentDefinitionRecordElement.getCompilationUnit();
        int numberOfElements = listDataTypeItem.getListIndex().get(0).getRangeUpper().intValue()+1;
        //int numberOfElements = dsfEquipmentDefinitionRecordElement.getBitSize()/listDataTypeItem.getListElement().getBitSize().intValue();
        int putBytes = listDataTypeItem.getListElement().getBitSize().intValue()/8;
        String dataTypeId = listDataTypeItem.getListElement().getDataTypeId();

        JSONObject subDataObject;
        for(Object variableAndDataType : compilationUnit.getVariableAndCharacterDataTypeAndBooleanDataType()){
            switch (getDataType(variableAndDataType)){
                case VARIABLE: //WILL NOT HAPPEN
                    break;
                case CHARACTER:
                    TypeCompilationUnit.CharacterDataType characterDataType = (TypeCompilationUnit.CharacterDataType) variableAndDataType;
                    subDataObject = new JSONObject();
                    if(characterDataType.getId().equalsIgnoreCase(dataTypeId)){
                        for (int i = 0; i< numberOfElements; i++){
                            dataObjectAddChar(subDataObject,byteData[i], String.valueOf(i));
                        }
                        dataObject.put(key, subDataObject);
                    }
                    break;
                case BOOLEAN:
                    break;
                case INTEGER:
                    TypeCompilationUnit.IntegerDataType integerDataType = (TypeCompilationUnit.IntegerDataType) variableAndDataType;
                    if(integerDataType.getId().equalsIgnoreCase(dataTypeId)){
                         subDataObject = new JSONObject();
                        for(int i = 0; i <numberOfElements;i++){
                            dataObjectAddInteger(subDataObject, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), integerDataType, String.valueOf(i));
                        }
                        dataObject.put(key, subDataObject);
                    }
                    break;
                case FLOAT:
                    TypeCompilationUnit.FloatDataType floatDataType = (TypeCompilationUnit.FloatDataType)   variableAndDataType;
                    if(floatDataType.getId().equalsIgnoreCase(dataTypeId)){
                         subDataObject = new JSONObject();
                        for(int i = 0; i<numberOfElements; i++){
                            dataObjectAddFloat(subDataObject, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), floatDataType,  String.valueOf(i));
                        }
                        dataObject.put(key, subDataObject);
                    }
                    break;
                case POINTER:
                    TypeCompilationUnit.PointerDataType pointerDataType = (TypeCompilationUnit.PointerDataType)   variableAndDataType;
                    if(pointerDataType.getId().equalsIgnoreCase(dataTypeId)){
                         subDataObject = new JSONObject();
                        for(int i = 0; i<numberOfElements; i++){
                            dataObjectAddPointer(subDataObject, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), pointerDataType, String.valueOf(i));
                        }
                        dataObject.put(key, subDataObject);
                    }
                    break;
                case ENUM:
                    TypeCompilationUnit.EnumDataType enumDataType = (TypeCompilationUnit.EnumDataType)   variableAndDataType;
                    if(enumDataType.getId().equalsIgnoreCase(dataTypeId)){
                         subDataObject = new JSONObject();
                        for(int i = 0; i<numberOfElements; i++){
                            dataObjectAddEnum(subDataObject, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), enumDataType, String.valueOf(i));
                        }
                        dataObject.put(key, subDataObject);
                    }
                    break;
                case LIST:
                    TypeCompilationUnit.ListDataType listDataType = (TypeCompilationUnit.ListDataType) variableAndDataType;
                    if(listDataType.getId().equalsIgnoreCase(dataTypeId)){
                         subDataObject = new JSONObject();
                        for (int i=0; i<numberOfElements;i++){
                            dataObjectAddList(subDataObject, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), listDataType,dsfEquipmentDefinitionRecordElement,String.valueOf(i));
                        }
                        dataObject.put(key, subDataObject);
                    }
                    break;
                case RECORD:
                    TypeCompilationUnit.RecordDataType recordDataType = (TypeCompilationUnit.RecordDataType) variableAndDataType;
                    if(recordDataType.getId().equalsIgnoreCase(dataTypeId)){
                        subDataObject = new JSONObject();
                        for(int i = 0; i<numberOfElements; i++){
                                dataObjectAddRecord(subDataObject, Arrays.copyOfRange(byteData, i*putBytes,i*putBytes+putBytes ), recordDataType, dsfEquipmentDefinitionRecordElement, String.valueOf(i));
                        }
                        dataObject.put(key, subDataObject);
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
    private void dataObjectAddRecord(JSONObject dataObject, byte[] byteData, TypeCompilationUnit.RecordDataType recordDataType, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement, String key) {
        //Loop trhough all record puts of the RecordDataType
        //And create a new JSON object for each
        JSONObject subDataObject = new JSONObject();
        for(TypeCompilationUnit.RecordDataType.RecordElement recordElement : recordDataType.getRecordElement()) {
            //Identify the datype of each and act accordingly
            String datatypeId = recordElement.getDataTypeId();
            for(Object variableOrDataType : dsfEquipmentDefinitionRecordElement.getCompilationUnit().getVariableAndCharacterDataTypeAndBooleanDataType()){
                switch (getDataType(variableOrDataType)){
                    case CHARACTER:
                        TypeCompilationUnit.CharacterDataType characterDataType = (TypeCompilationUnit.CharacterDataType) variableOrDataType;
                        if(characterDataType.getId().equalsIgnoreCase(datatypeId)){
                            dataObjectAddChar(subDataObject, byteData[(recordElement.getBitOffset().intValue()/8)], recordElement.getName());
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
                            dataObjectAddInteger(subDataObject, Arrays.copyOfRange(byteData, start/8, end/8), integerDataType, recordElement.getName());
                        }
                        break;
                    case FLOAT:
                        TypeCompilationUnit.FloatDataType floatDataType = (TypeCompilationUnit.FloatDataType) variableOrDataType;
                        if(floatDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataObjectAddFloat(subDataObject, Arrays.copyOfRange(byteData, start/8, end/8), floatDataType, recordElement.getName());
                        }
                        break;
                    case POINTER:
                        TypeCompilationUnit.PointerDataType pointerDataType = (TypeCompilationUnit.PointerDataType) variableOrDataType;
                        if(pointerDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataObjectAddPointer(subDataObject, Arrays.copyOfRange(byteData, start/8, end/8), pointerDataType, recordElement.getName());
                        }
                        break;
                    case ENUM:
                        TypeCompilationUnit.EnumDataType enumDataType = (TypeCompilationUnit.EnumDataType) variableOrDataType;
                        if(enumDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataObjectAddEnum(subDataObject, Arrays.copyOfRange(byteData, start/8 , end/8), enumDataType, recordElement.getName());
                        }
                        break;
                    case LIST:
                        TypeCompilationUnit.ListDataType listDataType = (TypeCompilationUnit.ListDataType) variableOrDataType;
                        if(listDataType.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataObjectAddList(subDataObject, Arrays.copyOfRange(byteData, start/8, end/8), listDataType,dsfEquipmentDefinitionRecordElement, recordElement.getName());
                        }
                        break;
                    case RECORD:
                        TypeCompilationUnit.RecordDataType recordDataType1 = (TypeCompilationUnit.RecordDataType) variableOrDataType;
                        if(recordDataType1.getId().equalsIgnoreCase(datatypeId)){
                            int start = recordElement.getBitOffset().intValue();
                            int end = recordElement.getBitOffset().intValue()+recordElement.getBitSize().intValue();
                            dataObjectAddRecord(subDataObject, Arrays.copyOfRange(byteData, start/8, end/8), recordDataType1, dsfEquipmentDefinitionRecordElement, recordElement.getName());
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
        dataObject.put(key, subDataObject);
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
