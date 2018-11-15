package com.schwipps.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSenderClient {
    DatagramSocket socket;
    public UDPSenderClient(DatagramSocket socket){
        this.socket = socket;
    }

    public void sendMessage(InetAddress inetAddress, int port, byte[] message){
        DatagramPacket datagramPacket = new DatagramPacket(message,message.length,inetAddress,port);
        try {
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
