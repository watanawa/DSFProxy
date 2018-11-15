package com.schwipps.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class UDPSenderTargetAgent {
    DatagramSocket socket;
    InetAddress inetAddress;
    int port;

    public UDPSenderTargetAgent(DatagramSocket socket){
        this.socket = socket;
    }

    public void sendMessage( byte[] message){
        DatagramPacket datagramPacket = new DatagramPacket(message,message.length,inetAddress,port);
        try {
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setReceiver(InetAddress inetAddress,int port){
        this.inetAddress = inetAddress;
        this.port = port;
    }

}
