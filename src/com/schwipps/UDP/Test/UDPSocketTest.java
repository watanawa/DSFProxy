package com.schwipps.UDP.Test;

import com.schwipps.DSFBuilder.DSFMessage;
import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentRequestCommand;
import com.schwipps.UDP.UDPReceiverTargetAgent;
import com.schwipps.UDP.UDPSenderTargetAgent;
import org.junit.jupiter.api.Test;
import com.schwipps.DSFBuilder.*;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

class UDPSocketTest {


    @Test
    void run() {

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(10000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        //Set up receiver and listener
        UDPSenderTargetAgent sender = new UDPSenderTargetAgent(socket);
        UDPReceiverTargetAgent receiver = new UDPReceiverTargetAgent(socket);
        //start receive thread
        Thread thread = new Thread(receiver, "Receive Listener");
        thread.start();

        //Initalize IP Addresses
        InetAddress quadcruiserAddress = null;
        InetAddress localHost = null;
        try {
            quadcruiserAddress = InetAddress.getByName("192.169.3.80");
            localHost = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        sender.setReceiver(quadcruiserAddress, 31001);

        //Assemble dsf message
        DSFHeader dsfHeader = new DSFHeader(0, MessageType.TARGET_AGENT_REQUEST_MESSAGE, 2, false, 2, 0);
        DSFBody dsfBody = new DSFBodyTargetAgentRequestMessage(1, TargetAgentRequestCommand.PRESENCE_CHECK);
        DSFFooter dsfFooter = new DSFFooter(dsfHeader.getChecksum()+dsfBody.getChecksum(), dsfHeader.getChecksumSize());
        DSFMessage dsfMessage = new DSFMessage(dsfHeader,dsfBody,dsfFooter);

        sender.sendMessage(dsfMessage.getByte());

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void sendMessage() {
    }
}