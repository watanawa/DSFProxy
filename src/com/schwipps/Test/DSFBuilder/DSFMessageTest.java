package com.schwipps.Test.DSFBuilder;

import com.schwipps.DSFBuilder.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DSFMessageTest {

    @Test
    void getByte() {
        DSFHeader header    = new DSFHeader(1,DSFMessage.messageType.TARGET_AGENT_REQUEST_MESSAGE.getValue(),3,true,10, 4);
        DSFBody body        = new DSFBodyTargetAgentRequestMessage(1, DSFBodyTargetAgentRequestMessage.targetAgentRequestCommand.CONNECT_TO_TARGET_AGENT);
        DSFFooter footer    = new DSFFooter(header.calculateChecksum()+body.calculateChecksum(),header.getChecksumSize());

        DSFMessage dsfMessage = new DSFMessage(header,body,footer);

        System.out.println(dsfMessage.getMessageType().toString());
        if(dsfMessage.getBody() instanceof  DSFBodyTargetAgentRequestMessage){
            System.out.println( ((DSFBodyTargetAgentRequestMessage)dsfMessage.getBody()).getCommand().toString());
            System.out.println(footer.getChecksumNumber());
            System.out.println(dsfMessage.messageNoError());
        }

    }

    @Test
    void getHead() {
    }

    @Test
    void setHead() {
    }

    @Test
    void getBody() {
    }

    @Test
    void setBody() {
    }

    @Test
    void getFooter() {
    }

    @Test
    void setFooter() {
    }

    @Test
    void getMessageType() {
    }
}