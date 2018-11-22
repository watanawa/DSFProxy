package com.schwipps.Json.Test;

import com.schwipps.DSFBuilder.DSFDebugDataItem;
import com.schwipps.DSFBuilder.DSFEquipmentDefinitionRecordElement;
import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.Json.JSONDebugDataMessage;
import com.schwipps.Main.DSFAddressLinker;
import com.schwipps.Main.Unmarshaller;
import com.schwipps.dsf.TypeEquipmentDescription;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;

class JSONDebugDataMessageTest {

    @Test
    public void  check(){
        Random random = new Random();
        byte[] b = new byte[96];
        random.nextBytes(b);
        byte[] a = new byte[104];
        random.nextBytes(a);


        DSFDebugDataItem dsfDebugDataItemIp = new DSFDebugDataItem(96, 134552016L ,b);
        DSFDebugDataItem dsfDebugDataItemChannel = new DSFDebugDataItem(104, 134551952L, a);

        Unmarshaller unmarshaller = new Unmarshaller();
        TypeEquipmentDescription typeEquipmentDescription = unmarshaller.unmarshal(new File("C:\\Users\\Chris\\Desktop\\Bachelorarbeit\\Practical\\QC11_Testing\\Release\\EquipmentDefinition_C1.xml"));
        DSFAddressLinker dsfAddressLinker = new DSFAddressLinker(typeEquipmentDescription);


        String variableName         = "LDR_UdpIp_Context";
        LinkedList<String> datarecordElementChannel = new LinkedList<>();
        //datarecordElementChannel.add("EthChannelContext");
        //LinkedList<String> datarecordElementIp = new LinkedList<>();
        //datarecordElementIp.add("IpInterfaceContext");


        DSFRecordElement dsfRecordElementChannel = new DSFRecordElement(variableName,datarecordElementChannel);
        //DSFRecordElement dsfRecordElementIp = new DSFRecordElement(variableName,datarecordElementIp);

        DSFEquipmentDefinitionRecordElement recordElementChannel = dsfAddressLinker.getDSFEquipmentDefinitionRecordElement(dsfRecordElementChannel);
        //DSFEquipmentDefinitionRecordElement recordElementIp = dsfAddressLinker.getDSFEquipmentDefinitionRecordElement(dsfRecordElementIp);

        JSONDebugDataMessage jsonDebugDataMessage = new JSONDebugDataMessage();
        jsonDebugDataMessage.addField(dsfDebugDataItemChannel, recordElementChannel);
        //jsonDebugDataMessage.addField(dsfDebugDataItemIp, recordElementIp);

        JSONObject s = jsonDebugDataMessage.getJsonDebugDataObject();

    }
}