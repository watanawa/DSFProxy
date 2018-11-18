package com.schwipps.UDP;
import  com.schwipps.DSFBuilder.*;
import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentMode;
import com.schwipps.DSFBuilder.enums.TargetAgentRequestCommand;
import com.schwipps.dsf.TypeDebugSymbolSet;

import java.util.Arrays;

public class MessageHandler {

    private static boolean connectedToTargetAgent = false;
    private static int targetAgentID = 0;

    private static UDPSenderTargetAgent udpSenderTargetAgent;
    private static UDPSenderClient udpSenderClient;
    private static TypeDebugSymbolSet typeDebugSymbolSet;

    public static void setUdpSenderTargetAgent(UDPSenderTargetAgent udpSenderTargetAgentArgument){
        udpSenderTargetAgent = udpSenderTargetAgentArgument;
    }
    public static void setUdpSenderClient(UDPSenderClient udpSenderClientArg){
        udpSenderClient = udpSenderClientArg;
    }
    public static void setTypeDebugSymbolSet(TypeDebugSymbolSet typeDebugSymbolSetArgument){
        typeDebugSymbolSet = typeDebugSymbolSetArgument;
    }
    public static UDPSenderClient getUdpSenderClient(){
        return udpSenderClient;
    }
    public static UDPSenderTargetAgent getUdpSenderTargetAgent(){
        return udpSenderTargetAgent;
    }

    public static void handleMessageTargetAgent(byte message[], int offset, int length){
        byte[] temp = Arrays.copyOfRange(message,offset , offset+length);;
        DSFMessage dsfMessage = new DSFMessage(temp);

        MessageType messageType =  dsfMessage.getMessageType();
        switch (messageType){
            case TARGET_AGENT_DATA_MESSAGE:
                handleTargetAgentDataMessage(dsfMessage);
                break;
            case DEBUG_DATA_MESSAGE:
                handleDebugDataMessage(dsfMessage);
                break;
            //THESE WILL ACTUALLY NEVER BE RECEIVED FROM THE TARGET AGENT BUT ONLY SEND FROM THE PROXY
            case TARGET_AGENT_REQUEST_MESSAGE:
            case DEBUG_DATA_WRITE_REQUEST_MESSAGE:
            case INVALID_MESSAGE_HANDLE:
            case DEBUG_DATA_READ_REQUEST_MESSAGE:
                System.out.println("Unreasonable message from Target Agent received");

        }
    }
    public static void handleTargetAgentDataMessage(DSFMessage dsfMessage){
        DSFBodyTargetAgentDataMessage targetAgentDataMessage = new DSFBodyTargetAgentDataMessage(dsfMessage.getBody().getByte());
        if(!connectedToTargetAgent){
            if(targetAgentDataMessage.getMode().equals(TargetAgentMode.CONNECTED)){
                connectedToTargetAgent = true;
                System.out.println("Succesfully connected to TargetAgent " +targetAgentDataMessage.getTargetAgentId());
                targetAgentID = targetAgentDataMessage.getTargetAgentId();
            }
            else if(targetAgentDataMessage.getMode().equals(TargetAgentMode.DISCONNECTED)){
                System.out.println("Target Agent found");
                udpSenderTargetAgent.sendMessage(Builder.buildTargetAgentRequestMessage(0, targetAgentDataMessage.getTargetAgentId(), TargetAgentRequestCommand.CONNECT_TO_TARGET_AGENT).getByte());
            }
            else if(targetAgentDataMessage.getMode().equals(TargetAgentMode.INVALID)){
                //TODO
            }
        }//Connected
        else{
            System.out.println(targetAgentDataMessage.getTimeStamp()+" Target Agent Utilization "+ targetAgentDataMessage.getMaxUtilization()+"%");
        }


    }

    public static void handleDebugDataMessage(DSFMessage dsfMessage){
        //TODO
    }
    public static void handleMessageClient(byte message[], int offset, int length) {
        byte[] temp = Arrays.copyOfRange(message,offset , offset+length);
    }
}