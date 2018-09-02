package com.schwipps.DSFBuilder;

import org.codehaus.groovy.tools.shell.Command;

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
        b[0] = ByteBuffer.allocate(4).putInt(targetAgentId).array()[3];
        b[1] = ByteBuffer.allocate(4).putInt(command.getValue()).array()[3];
    }
    public int getTargetAgentId(){
        byte temp[] = new byte[4];
        temp[0] = 0x00;
        temp[1] = 0x00;
        temp[2] = 0x00;
        temp[3] = b[0];
        return ByteBuffer.wrap(temp).getInt();
    }
    public targetAgentRequestCommand getCommand(){
        byte temp[] = new byte[4];
        temp[0] = 0x00;
        temp[1] = 0x00;
        temp[2] = 0x00;
        temp[3] = b[1];
        switch(ByteBuffer.wrap(temp).getInt()){
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

}
