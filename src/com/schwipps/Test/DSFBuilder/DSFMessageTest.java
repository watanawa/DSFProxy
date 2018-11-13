package com.schwipps.Test.DSFBuilder;

import com.schwipps.DSFBuilder.*;
import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentMode;
import org.junit.jupiter.api.Test;

class DSFMessageTest {

    @Test
    void getByte() {

        DSFHeader header    = new DSFHeader(1, MessageType.TARGET_AGENT_DATA_MESSAGE,29,true,10, 4);
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
    void testDSFMessageWithByteArgument() {
        DSFHeader header = new DSFHeader(1, MessageType.TARGET_AGENT_DATA_MESSAGE, 29, true, 2, 4);
        DSFBody body = new DSFBodyTargetAgentDataMessage(2000, 12, new byte[16], TargetAgentMode.CONNECTED, 99, 10, 1000, 2000, 50);
        DSFFooter footer = new DSFFooter(header.calculateChecksum() + body.calculateChecksum(), header.getChecksumSize());

        int length = header.getLength() + header.getMessageLength() + header.getChecksumSize();

        byte[] b = new byte[header.getLength() + header.getMessageLength() + header.getChecksumSize()];
        for (int i = 0; i < length; i++) {
            if (i < header.getLength()) {
                b[i] = header.getByte()[i];
            } else if ((header.getLength() <= i) & (i < header.getLength() + header.getMessageLength())) {
                b[i] = body.getByte()[i - header.getLength()];
            } else if (i >= header.getLength() + header.getMessageLength()) {
                b[i] = footer.getByte()[i - header.getLength() - header.getMessageLength()];
            }
        }
        //Induced Error
        // b[14] = 0x13;


        DSFMessage dsfMessage = new DSFMessage(b);


        if(dsfMessage.getMessageType().equals(MessageType.TARGET_AGENT_DATA_MESSAGE)){
            DSFBodyTargetAgentDataMessage  targetAgentDataMessage = new DSFBodyTargetAgentDataMessage(dsfMessage.getBody().getByte());
            DSFFooter myFooter = dsfMessage.getFooter();
            DSFHeader head = new DSFHeader(dsfMessage.getHead().getByte());

            System.out.println(head.getInstanceID());
            System.out.println(head.getMessageType().toString());
            System.out.println(head.getMessageLength());
            System.out.println(head.getAckRequired());
            System.out.println(head.getIDDVersion());
            System.out.println(head.getChecksumSize());

            System.out.println(targetAgentDataMessage.getTimeStamp());
            System.out.println(targetAgentDataMessage.getTargetAgentId());
            System.out.println(targetAgentDataMessage.getMode().toString());
            System.out.println(targetAgentDataMessage.getMaxUtilization());
            System.out.println(targetAgentDataMessage.getLostClientRequestMessages());
            System.out.println(targetAgentDataMessage.getRXBufferSize());
            System.out.println(targetAgentDataMessage.getTXBufferSize());
            System.out.println(targetAgentDataMessage.getMaxSamplingFrequency());

            System.out.println(footer.getChecksumNumber());
            System.out.println(dsfMessage.messageErrorFree());

        }
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