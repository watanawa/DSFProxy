package com.schwipps.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPReceiverClient implements Runnable {
    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer;

    public UDPReceiverClient(DatagramSocket socket){
        this.socket = socket;
        buffer = new byte[1024];
    }

    //Receiver get Message+Sender
    @Override
    public void run() {
        running = true;
        while(running){
            DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length);
            try {
                socket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            MessageHandler.handleMessageClient(datagramPacket.getData(),datagramPacket.getOffset(),datagramPacket.getLength());
        }
        running = false;
    }
}
