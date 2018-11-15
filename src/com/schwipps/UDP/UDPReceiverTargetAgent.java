package com.schwipps.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiverTargetAgent implements Runnable{
    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer;

    public UDPReceiverTargetAgent(DatagramSocket socket){
        this.socket = socket;
        buffer = new byte[1024*1024];
    }

    //Receiver get Message+Sender
    @Override
    public void run() {
        running = true;
        DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length);
        while(running){
            try {
                socket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            MessageHandler.handleMessageTargetAgent(datagramPacket.getData());
        }
        running = false;
    }
}
