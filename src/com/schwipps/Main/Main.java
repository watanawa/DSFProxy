package com.schwipps.Main;

import com.schwipps.DSFBuilder.*;
import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentRequestCommand;
import com.schwipps.UDP.UDPClient;
import com.schwipps.dsf.TypeEquipmentDescription;

import java.io.File;


public class Main {


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Unmarshaller unmarshaller = new Unmarshaller();
        TypeEquipmentDescription equipment =  unmarshaller.unmarshal(new File("C:\\Users\\Chris\\Desktop\\Bachelor\\QC11_Testing\\Release\\CF_EquipmentDefinition.xml"));

        DSFHeader header    = new DSFHeader(1, MessageType.TARGET_AGENT_REQUEST_MESSAGE,3,true,10, 4);
        DSFBody body        = new DSFBodyTargetAgentRequestMessage(1, TargetAgentRequestCommand.CONNECT_TO_TARGET_AGENT);
        DSFFooter footer    = new DSFFooter(header.calculateChecksum()+body.calculateChecksum(),header.getChecksumSize());

        DSFMessage dsfMessage = new DSFMessage(header,body,footer);

        UDPClient client = new UDPClient();
        client.test();
        System.out.println("success");
    }

}