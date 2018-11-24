package com.schwipps.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiverClient implements Runnable {
    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer;
    private MessageHandler messageHandler;

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
            messageHandler.handleMessageClient(datagramPacket.getData(),datagramPacket.getOffset(),datagramPacket.getLength());
        }
        running = false;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
}
