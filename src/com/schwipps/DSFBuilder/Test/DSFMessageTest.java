package com.schwipps.DSFBuilder.Test;

import com.schwipps.DSFBuilder.*;
import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class DSFMessageTest {

    @Test
    void check() {
        //Header
        int instanceID = 1;
        MessageType messageType = MessageType.DEBUG_DATA_READ_REQUEST_MESSAGE;
        int messageLength = 29;
        boolean ackRequired = false;
        int iddVersion = 2;
        int checksumSize = 4;
        //Body
        long timeStamp = 2343;
        int targetAgentId = 12;
        byte[] data = new byte[16];
        TargetAgentMode targetAgentMode = TargetAgentMode.CONNECTED;
        int maxUtilization = 99;
        int lostClientRequestMessages = 10;
        int rxBufferSize = 1000;
        int txBufferSize = 2000;
        int maxSamplingFrequency = 50;



        DSFHeader header    = new DSFHeader(instanceID, messageType,messageLength,ackRequired,iddVersion,checksumSize);
        DSFBody body        = new DSFBodyTargetAgentDataMessage(timeStamp,targetAgentId,data,targetAgentMode,maxUtilization,lostClientRequestMessages,rxBufferSize,txBufferSize,maxSamplingFrequency);
        DSFFooter footer    = new DSFFooter(header.getChecksum()+body.getChecksum(),header.getChecksumSize());

        DSFMessage dsfMessage = new DSFMessage(header,body,footer);
        dsfMessage = new DSFMessage(dsfMessage.getByte());

        assertEquals(instanceID,dsfMessage.getHead().getInstanceID() );
        assertEquals(messageType,dsfMessage.getHead().getMessageType() );
        assertEquals(messageLength,dsfMessage.getHead().getMessageLength() );
        assertEquals(ackRequired, dsfMessage.getHead().getAckRequired());
        assertEquals(iddVersion, dsfMessage.getHead().getIDDVersion());
        assertEquals(checksumSize, dsfMessage.getHead().getChecksumSize());



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