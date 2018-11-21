package com.schwipps.Json;

import com.schwipps.DSFBuilder.DSFDebugDataItem;
import com.schwipps.DSFBuilder.DSFEquipmentDefinitionRecordElement;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class JSONDebug {
    private JsonObject jsonDebugDataMessage;

    public JSONDebug(){
        jsonDebugDataMessage = Json.createObjectBuilder().add("JSONDebugDataMessage", Json.createArrayBuilder().build()).build();
    }

    public void addField(DSFDebugDataItem dsfDebugDataItem, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement){
        JsonArray jsonArray = jsonDebugDataMessage.getJsonArray("JSONDebugDataMessage");
    }

}
