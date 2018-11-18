package com.schwipps.Main;
import com.schwipps.UDP.*;
import com.schwipps.dsf.TypeEquipmentDescription;
import com.schwipps.dsf.TypeEthernetLink;

import java.io.File;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

// Company: Airbus Defence and Space
// Author:  Christoph Schwipps
// Date:    November 2018

public class Launcher {
    String args[];

    private String equipmentDescriptionFile;
    TypeEquipmentDescription typeEquipmentDescription;

    DatagramSocket socketForTargetAgent;
    DatagramSocket socketForClient;

    UDPReceiverTargetAgent udpReceiverTargetAgent;
    UDPSenderTargetAgent udpSenderTargetAgent;
    UDPReceiverClient udpReceiverClient;
    UDPSenderClient udpSenderClient;

    MessageHandler messageHandler;
    DSFAddressLinker dsfAddressLinker;

    TargetAgentPresenceChecker targetAgentPresenceChecker;

    public Launcher(String[] args){
        this.args = args;
    }

    //STARTUP [0] EquipmentdescriptionFile [1] Port of TargetAgentSocket [2] Port of ClientSocket
    public static void main(String[] args) {
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

    }


    private void createDSFAddressLinker() {
        dsfAddressLinker = new DSFAddressLinker(typeEquipmentDescription);
    }

    public String getEquipmentDescriptionFile(){
        return equipmentDescriptionFile;
    }
    public TypeEquipmentDescription getTypeEquipmentDescription(){
        return typeEquipmentDescription;
    }

    public void setEquipmentDescriptionFile(){
        equipmentDescriptionFile = args[0];
    }
    public void unmarshall(){
        Unmarshaller unmarshaller = new Unmarshaller();
        typeEquipmentDescription = unmarshaller.unmarshal(new File(equipmentDescriptionFile));
    }
    public void createUDPSocket(){
        try {
            socketForTargetAgent = new DatagramSocket(Integer.valueOf(args[1]));
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            socketForClient = new DatagramSocket(Integer.valueOf(args[2]));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void createUDPSender(){

        udpSenderClient = new UDPSenderClient(socketForClient);
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
    public void createUDPReceiver(){
        udpReceiverClient = new UDPReceiverClient(socketForClient);
        udpReceiverTargetAgent = new UDPReceiverTargetAgent(socketForTargetAgent);

        udpReceiverClient.setMessageHandler(messageHandler);
        udpReceiverTargetAgent.setMessageHandler(messageHandler);

        Thread threadReceiverClient = new Thread(udpReceiverClient, "UDPReceiverClient");
        Thread threadReceiverTargetAgent = new Thread(udpReceiverTargetAgent,"UDPReceiverTargetAgent");

        threadReceiverClient.start();
        threadReceiverTargetAgent.start();
    }

    public void createMessageHandler(){
        messageHandler = new MessageHandler();
        messageHandler.setUdpSenderClient(udpSenderClient);
        messageHandler.setUdpSenderTargetAgent(udpSenderTargetAgent);
        messageHandler.setDSFAddressLinker(dsfAddressLinker);
    }
    public void createTargetAgentPresenceChecker(){
        targetAgentPresenceChecker = new TargetAgentPresenceChecker(udpSenderTargetAgent);
        Thread threadTargetAgentPresenceChecker = new Thread(targetAgentPresenceChecker,"TargetAgentPresenceChecker");
        threadTargetAgentPresenceChecker.start();
    }

}