package com.schwipps.UDP;
import  com.schwipps.DSFBuilder.*;
import com.schwipps.DSFBuilder.enums.MessageType;

public class MessageHandler {
    private static UDPSenderTargetAgent udpSenderTargetAgent;
    private static UDPSenderClient udpSenderClient;

    public static void setUdpSenderTargetAgent(UDPSenderTargetAgent udpSenderTargetAgentArgument){
        udpSenderTargetAgent = udpSenderTargetAgentArgument;
    }
    public static void setUdpSenderClient(UDPSenderClient udpSenderClientArg){
        udpSenderClient = udpSenderClientArg;
    }

    public static UDPSenderClient getUdpSenderClient(){
        return udpSenderClient;
    }
    public static UDPSenderTargetAgent getUdpSenderTargetAgent(){
        return udpSenderTargetAgent;
    }

    public static void handleMessageTargetAgent(byte message[]){

        DSFMessage dsfMessage = new DSFMessage(message);
        MessageType messageType =  dsfMessage.getMessageType();

        switch (messageType){
            case TARGET_AGENT_DATA_MESSAGE:
                //TODO

                System.out.println(dsfMessage.getHead().getChecksumSize());
                System.out.println("Error free \t"+dsfMessage.messageErrorFree());
                System.out.println("Message type \t"+dsfMessage.getMessageType().toString());
                DSFBodyTargetAgentDataMessage targetAgentDataMessage = new DSFBodyTargetAgentDataMessage(dsfMessage.getBody().getByte());
                System.out.println(targetAgentDataMessage.getMaxSamplingFrequency());
                System.out.println(targetAgentDataMessage.getTXBufferSize());
                System.out.println(targetAgentDataMessage.getRXBufferSize());
                System.out.println(targetAgentDataMessage.getMaxUtilization());
                System.out.println(targetAgentDataMessage.getMode());
                System.out.println(targetAgentDataMessage.getTimeStamp());
                System.out.println(targetAgentDataMessage.getTargetAgentId());
                break;
            case DEBUG_DATA_MESSAGE:
                //TODO

                break;
            //THESE WILL ACTUALLY NEVER BE RECEIVED FROM THE TARGET AGENT BUT ONLY SEND FROM THE PROXY
            case TARGET_AGENT_REQUEST_MESSAGE:
                System.out.println("Error free \t"+dsfMessage.messageErrorFree());
                System.out.println("Message type \t"+dsfMessage.getMessageType().toString());
                DSFBodyTargetAgentRequestMessage targetAgentRequestMessage = new DSFBodyTargetAgentRequestMessage(dsfMessage.getBody().getByte());
                System.out.println(targetAgentRequestMessage.getCommand());
                System.out.println(targetAgentRequestMessage.getTargetAgentId());

            case DEBUG_DATA_WRITE_REQUEST_MESSAGE:
            case INVALID_MESSAGE_HANDLE:
            case DEBUG_DATA_READ_REQUEST_MESSAGE:


        }
    }


    public static void handleMessageClient(byte message[]) {
    }
}