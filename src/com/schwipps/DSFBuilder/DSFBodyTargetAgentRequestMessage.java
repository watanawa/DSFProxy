package com.schwipps.DSFBuilder;

import java.nio.ByteBuffer;

public class DSFBodyTargetAgentRequestMessage extends DSFBody {
    /* Body Strucure
    TargetAgentId 0-255 1Byte uint
    Command       0,1,2 1Byte
    */
    public enum targetAgentRequestCommand{
        PRESENCE_CHECK(0),
        CONNECT_TO_TARGET_AGENT(1),
        DISCONNECT_FROM_TARGET_AGENT(2);

        private final int i;
        targetAgentRequestCommand(int i) {
            this.i = i;
        }
        public int getValue(){
            return i;
        }
    }

    public DSFBodyTargetAgentRequestMessage(byte[] b){
        super(b);
    }
    public DSFBodyTargetAgentRequestMessage(int targetAgentId,targetAgentRequestCommand command){
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
    public targetAgentRequestCommand getCommand(){
        switch(byteToInt(b[1])){
            case(0):
                return targetAgentRequestCommand.PRESENCE_CHECK;
            case(1):
                return targetAgentRequestCommand.CONNECT_TO_TARGET_AGENT;
            case(2):
                return targetAgentRequestCommand.DISCONNECT_FROM_TARGET_AGENT;
        }
        //should not happen
        return null;
    }
    public void setCommand(targetAgentRequestCommand command){
        b[1] = intToByte(command.getValue())[3];
    }
}
