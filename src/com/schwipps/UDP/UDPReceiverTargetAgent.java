package com.schwipps.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class UDPReceiverTargetAgent implements Runnable{
    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer;
    private MessageHandler messageHandler;

    public UDPReceiverTargetAgent(DatagramSocket socket){
        this.socket = socket;
        buffer = new byte[2048];
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
            messageHandler.handleDSFMessageTargetAgent(datagramPacket.getData(), datagramPacket.getOffset(), datagramPacket.getLength());
        }
        running = false;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
}
