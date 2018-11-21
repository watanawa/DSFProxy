package com.schwipps.Main.Test;

import com.schwipps.DSFBuilder.Builder;
import com.schwipps.DSFBuilder.DSFDebugDataItem;
import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;
import com.schwipps.Main.Launcher;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class LauncherTest {
    @Test
    void check(){
        String[] args = new String[]{"C:\\Users\\Chris\\Desktop\\Bachelorarbeit\\Practical\\QC11_Testing\\Release\\EquipmentDefinition_C1.xml" , "4001", "4002"};
        System.out.println("Launching DSF Proxy");
        Launcher launcher = new Launcher(args);
        launcher.setEquipmentDescriptionFile();
        launcher.unmarshall();
        launcher.createDSFAddressLinker();
        launcher.createUDPSocket();
        launcher.createUDPSender();
        launcher.createMessageHandler();
        launcher.createUDPReceiver();

        launcher.createTargetAgentPresenceChecker();

        System.out.println("DSF Proxy succesfully started");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DSFDebugDataItem[] dataItems = new DSFDebugDataItem[1];

        String variableName         = "NAV_BasicConsolidation_Context";
        LinkedList<String> datarecordElement = new LinkedList<>();
        datarecordElement.add("IpInterfaceContext");

        DSFRecordElement dsfRecordElement = new DSFRecordElement(variableName,datarecordElement);

        dataItems[0] = launcher.dsfAddressLinker.getDSFEquipmentDefinitionRecordElement(dsfRecordElement).getDSFDebugDataItem();
        //dataItems[1] = launcher.dsfAddressLinker.getDSFEquipmentDefinitionRecordElement("NAV_BasicConsolidation_Input", "InertialRollRate").getDSFDebugDataItem();
        System.out.println("Sending request");
        launcher.udpSenderTargetAgent.sendMessage(Builder.buildDebugDataReadRequest(launcher.messageHandler.getTargetAgentID(), DebugDataReadRequestCommand.READ_DATA_PERIODICALLY, dataItems).getByte());

    }
}