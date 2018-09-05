package com.schwipps.DSFBuilder;

import com.schwipps.DSFBuilder.enums.TargetAgentRequestCommand;

import java.nio.ByteBuffer;

public class DSFBodyTargetAgentRequestMessage extends DSFBody {
    /* Body Strucure
    TargetAgentId 0-255 1Byte uint
    Command       0,1,2 1Byte
    */

    public DSFBodyTargetAgentRequestMessage(byte[] b){
        super(b);
    }
    public DSFBodyTargetAgentRequestMessage(int targetAgentId, TargetAgentRequestCommand command){
        super(new byte[2]);
        setTargetAgentId(targetAgentId);
        setCommand(command);
    }
    public int getTargetAgentId(){
        return byteToInt(b[0]);
    }

    public void setTargetAgentId(int targetAgentId){
        b[0] = ByteBuffer.allocate(4).putInt(targetAgentId).array()[3];
    }
    public TargetAgentRequestCommand getCommand(){
        switch(byteToInt(b[1])){
            case(0):
                return TargetAgentRequestCommand.PRESENCE_CHECK;
            case(1):
                return TargetAgentRequestCommand.CONNECT_TO_TARGET_AGENT;
            case(2):
                return TargetAgentRequestCommand.DISCONNECT_FROM_TARGET_AGENT;
        }
        //should not happen
        return null;
    }
    public void setCommand(TargetAgentRequestCommand command){
        b[1] = intToByte(command.getValue())[3];
    }
}
