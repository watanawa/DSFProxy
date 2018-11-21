package com.schwipps.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPSenderClient {
    DatagramSocket socket;
    public UDPSenderClient(DatagramSocket socket){
        this.socket = socket;
    }

    public void sendMessage(int port, byte[] message){
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if(inetAddress!= null){
            DatagramPacket datagramPacket = new DatagramPacket(message,message.length,inetAddress,port);
            try {
                socket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
