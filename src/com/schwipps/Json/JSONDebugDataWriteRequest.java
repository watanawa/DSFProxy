package com.schwipps.Json;

import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

public class JSONDebugDataWriteRequest {
    private JSONObject jsonDebugDataWriteRequest;
    private JSONArray jsonArray;

    public JSONDebugDataWriteRequest(String jsonString){
        jsonDebugDataWriteRequest = new JSONObject(jsonString);
        jsonArray = jsonDebugDataWriteRequest.getJSONArray("JSONDebugDataWriteRequest");
    }

    public HashMap<DSFRecordElement, Object>getValues(){
        HashMap<DSFRecordElement,Object> hashMap = new HashMap<>();
        for(int i= 0;i<jsonArray.length();i++){
            JSONArray subJsonArray = jsonArray.getJSONArray(i);
            LinkedList<String> recordElementPath = new LinkedList<>();
            String variableName = "";
            Object value = null;
            for(int j = 0; j <subJsonArray.length(); j++){
                if(j == 0){
                    variableName = subJsonArray.getString(j);
                }
                //The last element contains the value we want to write
                else if(j == (subJsonArray.length()-1)){
                    value = subJsonArray.get(j);
                }
                else{
                    recordElementPath.add(subJsonArray.getString(j));
                }
            }
            DSFRecordElement element = new DSFRecordElement(variableName, recordElementPath, DebugDataReadRequestCommand.READ_DATA_ONCE);
            hashMap.put(element, value);
        }
        return hashMap;
    }
}
