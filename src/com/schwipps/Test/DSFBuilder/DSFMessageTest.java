package com.schwipps.Test.DSFBuilder;

import com.schwipps.DSFBuilder.*;
import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentMode;
import org.junit.jupiter.api.Test;

class DSFMessageTest {

    @Test
    void getByte() {
        DSFHeader header    = new DSFHeader(1, MessageType.TARGET_AGENT_DATA_MESSAGE,3,true,10, 4);
        DSFBody body        = new DSFBodyTargetAgentDataMessage(2000,12,new byte[16], TargetAgentMode.CONNECTED,99,10,1000,2000,50);
        DSFFooter footer    = new DSFFooter(header.calculateChecksum()+body.calculateChecksum(),header.getChecksumSize());

        DSFMessage dsfMessage = new DSFMessage(header,body,footer);

        System.out.println(dsfMessage.getMessageType().toString());
        if(dsfMessage.getBody() instanceof  DSFBodyTargetAgentDataMessage){
            System.out.println( ((DSFBodyTargetAgentDataMessage)dsfMessage.getBody()).getMode().toString());
            System.out.println(footer.getChecksumNumber());
            System.out.println(dsfMessage.messageErrorFree());
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