package com.schwipps.DSFBuilder;

import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentRequestCommand;

public class  Builder {
    public static DSFMessage buildTargetAgentRequestMessage(int instance, int targetAgentId,TargetAgentRequestCommand targetAgentRequestCommand){
        DSFHeader header    = new DSFHeader(1, MessageType.TARGET_AGENT_REQUEST_MESSAGE,3,false,2, 0);
        DSFBody     body    = new DSFBodyTargetAgentRequestMessage(1, targetAgentRequestCommand);
        //DSFFooter footer    = new DSFFooter(header.getChecksum()+body.getChecksum(),header.getChecksumSize());

        DSFMessage dsfMessage = new DSFMessage(header,body,null);
        return dsfMessage;
    }
}
