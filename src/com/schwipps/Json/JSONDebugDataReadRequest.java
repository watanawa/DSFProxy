package com.schwipps.Json;

import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;
import com.schwipps.Main.DSFTuple;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class JSONDebugDataReadRequest {
    private JSONObject jsonDebugDataReadRequest;
    private JSONArray jsonArray;

    public JSONDebugDataReadRequest(String jsonString){
        jsonDebugDataReadRequest = new JSONObject(jsonString);
        jsonArray = jsonDebugDataReadRequest.getJSONArray("JSONDebugDataReadRequest");
    }

    public JSONDebugDataReadRequest(){
        jsonDebugDataReadRequest = new JSONObject();
        jsonArray = new JSONArray();
        jsonDebugDataReadRequest.put("JSONDebugDataReadRequest",jsonArray);
    }

    public byte[] toByte(){
       return jsonDebugDataReadRequest.toString().getBytes();
    }

    public void addDSFRecordElement(DSFRecordElement dsfRecordElement){
        JSONArray recordElementArray = new JSONArray();
        recordElementArray.put(dsfRecordElement.getVariable());
        for(String recordElementName: dsfRecordElement.getRecordElementNames()){
            recordElementArray.put(recordElementName);
        }
        jsonArray.put(recordElementArray);
    }

    public ArrayList<DSFRecordElement> getDSFRecordElement(){
        ArrayList<DSFRecordElement> dsfRecordElements = new ArrayList<>();
        for(int i= 0; i< jsonArray.length(); i++){
            if(jsonArray.get(i) instanceof  JSONArray){
                JSONArray requestArray = (JSONArray) jsonArray.get(i);
                String variableName = null;
                DebugDataReadRequestCommand command = null;
                LinkedList<String> recordElements = new LinkedList<>();
                for(int j=0; j < requestArray.length(); j++){
                    if(j==0){
                        variableName = requestArray.getString(0);
                    }
                    else if(j == (requestArray.length()-1)){
                        if(requestArray.getString(j).equalsIgnoreCase("Periodic")){
                            command = DebugDataReadRequestCommand.READ_DATA_PERIODICALLY;
                        }
                        else if(requestArray.getString(j).equalsIgnoreCase("Once")){
                            command = DebugDataReadRequestCommand.READ_DATA_ONCE;
                        }
                        else if(requestArray.getString(j).equalsIgnoreCase("Cancel")){
                            command = DebugDataReadRequestCommand.CANCEL_ALL_PERIODIC_TRANSFERS;
                        }
                        else{
                            command = DebugDataReadRequestCommand.INVALID_COMMAND_HANDLE;
                        }
                    }
                    else{
                        recordElements.add(requestArray.getString(j));
                    }
                }
                dsfRecordElements.add(new DSFRecordElement(variableName, recordElements, command));
            }
        }
        return dsfRecordElements;
    }
}
