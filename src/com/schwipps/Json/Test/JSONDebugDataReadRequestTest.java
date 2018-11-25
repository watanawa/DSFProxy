package com.schwipps.Json.Test;

import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;
import com.schwipps.Json.JSONDebugDataReadRequest;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class JSONDebugDataReadRequestTest {
    @Test
    public void test(){
        String variableName = "NAV_BasicConsolidation_Context";
        LinkedList<String> datarecordElement = new LinkedList<>();
        datarecordElement.add("Context_Upsampling_9");
        datarecordElement.add("fby_2_10");
        datarecordElement.add("items");
        datarecordElement.add("3");
        DSFRecordElement dsfRecordElement = new DSFRecordElement(variableName, datarecordElement, DebugDataReadRequestCommand.READ_DATA_PERIODICALLY);


        JSONDebugDataReadRequest jsonDebugDataReadRequest = new JSONDebugDataReadRequest();
        jsonDebugDataReadRequest.addDSFRecordElement(dsfRecordElement);

        String jsonString=null;
        try {
             jsonString = new String(jsonDebugDataReadRequest.toByte(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        jsonDebugDataReadRequest = new JSONDebugDataReadRequest(jsonString);
        DSFRecordElement dsfRecordElement1 = jsonDebugDataReadRequest.getDSFRecordElement().get(0);

    }
}