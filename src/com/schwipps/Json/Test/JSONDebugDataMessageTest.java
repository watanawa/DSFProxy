package com.schwipps.Json.Test;

import com.schwipps.DSFBuilder.DSFDebugDataItem;
import com.schwipps.DSFBuilder.DSFEquipmentDefinitionRecordElement;
import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.Json.JSONDebugDataMessage;
import com.schwipps.Main.DSFAddressLinker;
import com.schwipps.Main.Unmarshaller;
import com.schwipps.dsf.TypeEquipmentDescription;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;

class JSONDebugDataMessageTest {

    @Test
    public void  check(){
        Random random = new Random();
        byte[] b = new byte[832];
        random.nextBytes(b);

        DSFDebugDataItem dsfDebugDataItem = new DSFDebugDataItem(832, 123L ,b);

        Unmarshaller unmarshaller = new Unmarshaller();
        TypeEquipmentDescription typeEquipmentDescription = unmarshaller.unmarshal(new File("C:\\Users\\Chris\\Desktop\\Bachelorarbeit\\Practical\\QC11_Testing\\Release\\EquipmentDefinition_C1.xml"));
        DSFAddressLinker dsfAddressLinker = new DSFAddressLinker(typeEquipmentDescription);


        String variableName         = "LDR_UdpIp_Context";
        LinkedList<String> datarecordElement = new LinkedList<>();
        //datarecordElement.add("IpInterfaceContext");

        DSFRecordElement dsfRecordElement = new DSFRecordElement(variableName,datarecordElement);

        DSFEquipmentDefinitionRecordElement recordElement = dsfAddressLinker.getDSFEquipmentDefinitionRecordElement(dsfRecordElement);

        JSONDebugDataMessage jsonDebugDataMessage = new JSONDebugDataMessage();
        jsonDebugDataMessage.addDataItem(dsfDebugDataItem, recordElement);
        jsonDebugDataMessage.toByte();

    }
}