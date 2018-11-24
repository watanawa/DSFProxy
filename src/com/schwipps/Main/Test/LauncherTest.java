package com.schwipps.Main.Test;

import com.schwipps.Main.Launcher;

class LauncherTest {

    static void main(){
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



    }
}