package com.schwipps.Main;

import com.schwipps.UDP.*;
import com.schwipps.dsf.TypeCommunicationLinks;
import com.schwipps.dsf.TypeCompilationUnit;
import com.schwipps.dsf.TypeEquipmentDescription;
import com.schwipps.dsf.TypeEthernetLink;

import java.io.File;
import java.lang.reflect.Type;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;


public class Launcher {
    private String equipmentDescriptionFile;
    TypeEquipmentDescription typeEquipmentDescription;

    DatagramSocket socketForTargetAgent;
    DatagramSocket socketForClient;

    UDPReceiverTargetAgent udpReceiverTargetAgent;
    UDPSenderTargetAgent udpSenderTargetAgent;
    UDPReceiverClient udpReceiverClient;
    UDPSenderClient udpSenderClient;



    //STARTUP
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.setEquipmentDescriptionFile(args[0]);
        launcher.unmarshall();
        launcher.createUDPSenderAndReceiver();
        launcher.initializeMessageHandler();



        List<TypeCompilationUnit> list = launcher.getTypeEquipmentDescription().getDebugSymbols().getDebugSymbolSet().get(0).getCompilationUnit();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals("NAV_BasicConsolidation")){
                for(Object obj:list.get(i).getVariableAndCharacterDataTypeAndBooleanDataType()){
                    System.out.println(obj.getClass());
                }
            }
        }

        System.out.println("DSF Proxy succesfully started");
    }

    public void setEquipmentDescriptionFile(String path){
        equipmentDescriptionFile = path;
    }
    public String getEquipmentDescriptionFile(){
        return equipmentDescriptionFile;
    }
    public TypeEquipmentDescription getTypeEquipmentDescription(){
        return typeEquipmentDescription;
    }
    public void unmarshall(){
        Unmarshaller unmarshaller = new Unmarshaller();
        typeEquipmentDescription = unmarshaller.unmarshal(new File(equipmentDescriptionFile));
    }
    public void createUDPSenderAndReceiver(){
        try {
            socketForTargetAgent = new DatagramSocket(4001);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            socketForClient = new DatagramSocket(4002);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        udpSenderClient = new UDPSenderClient(socketForClient);
        udpReceiverClient = new UDPReceiverClient(socketForTargetAgent);

        udpReceiverTargetAgent = new UDPReceiverTargetAgent(socketForTargetAgent);
        udpSenderTargetAgent = new UDPSenderTargetAgent(socketForTargetAgent);

        //Initialize UDPTargetAgentSender
        if(        typeEquipmentDescription.getCommunicationLinks().getEthernetLinkOrSerialLink().get(0) instanceof TypeEthernetLink){
            TypeEthernetLink ethernetLink = (TypeEthernetLink) typeEquipmentDescription.getCommunicationLinks().getEthernetLinkOrSerialLink().get(0);
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getByName(ethernetLink.getAgentIPAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            udpSenderTargetAgent.setReceiver(inetAddress, ethernetLink.getAgentTransportProtocolPort() );
        }
        else {
            System.out.println("Invalid XML Equipment description - check CommunicationLink");
        }


    }
    public void initializeMessageHandler(){
        MessageHandler.setUdpSenderClient(udpSenderClient);
        MessageHandler.setUdpSenderTargetAgent(udpSenderTargetAgent);
    }
}