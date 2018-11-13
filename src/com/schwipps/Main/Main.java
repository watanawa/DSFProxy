package com.schwipps.Main;

import com.schwipps.DSFBuilder.*;
import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentRequestCommand;
import com.schwipps.UDP.UDPClient;
import com.schwipps.dsf.TypeCompilationUnit;
import com.schwipps.dsf.TypeEquipmentDescription;

import java.io.File;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Unmarshaller unmarshaller = new Unmarshaller();
        TypeEquipmentDescription equipment =  unmarshaller.unmarshal(new File("C:\\Users\\Chris\\Desktop\\Bachelorarbeit\\Practical\\QC11_Testing\\Release\\EquipmentDefinition_C1.xml"));

        DSFHeader header    = new DSFHeader(1, MessageType.TARGET_AGENT_REQUEST_MESSAGE,3,true,10, 4);
        DSFBody body        = new DSFBodyTargetAgentRequestMessage(1, TargetAgentRequestCommand.CONNECT_TO_TARGET_AGENT);
        DSFFooter footer    = new DSFFooter(header.calculateChecksum()+body.calculateChecksum(),header.getChecksumSize());

        DSFMessage dsfMessage = new DSFMessage(header,body,footer);

        List<TypeCompilationUnit> list = equipment.getDebugSymbols().getDebugSymbolSet().get(0).getCompilationUnit();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals("NAV_BasicConsolidation")){
                for(Object obj:list.get(i).getVariableAndCharacterDataTypeAndBooleanDataType()){
                    System.out.println(obj.getClass());
                }
            }
        }

        UDPClient client = new UDPClient();
        client.test();
        System.out.println("success");
    }

}