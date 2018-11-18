package com.schwipps.DSFBuilder.Test;

import com.schwipps.DSFBuilder.DSFBodyTargetAgentDataMessage;
import com.schwipps.DSFBuilder.enums.TargetAgentMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DSFBodyTargetAgentDataMessageTest {
    @Test
    void check() {

        int timeStamp = 2000;
        int targetAgentId = 12;
        byte[] checksum = new byte[]{12,23,34,45,56,67,89,12,23,34,45,12,23,34,45,56};
        TargetAgentMode mode = TargetAgentMode.CONNECTED;
        int maxUtilization = 42;
        int lostClientRequestMessages = 30;
        int rxBufferSize = 5000;
        int txBufferSize = 65535;
        int maxSamplingFrequency = 200;

        DSFBodyTargetAgentDataMessage targetAgentDataMessage = new DSFBodyTargetAgentDataMessage(timeStamp,targetAgentId,checksum,mode,maxUtilization,lostClientRequestMessages,rxBufferSize,txBufferSize,maxSamplingFrequency);

        assertEquals(timeStamp,targetAgentDataMessage.getTimeStamp());
        assertEquals(targetAgentId, targetAgentDataMessage.getTargetAgentId());
        assertEquals(mode,targetAgentDataMessage.getMode() );
        assertEquals(maxUtilization,targetAgentDataMessage.getMaxUtilization() );
        assertEquals(lostClientRequestMessages,targetAgentDataMessage.getLostClientRequestMessages() );
        assertEquals(rxBufferSize,targetAgentDataMessage.getRXBufferSize() );
        assertEquals(txBufferSize,targetAgentDataMessage.getTXBufferSize() );
        assertEquals(maxSamplingFrequency,targetAgentDataMessage.getMaxSamplingFrequency());

        //Check Byte Constructor
        targetAgentDataMessage = new DSFBodyTargetAgentDataMessage(targetAgentDataMessage.getByte());
        assertEquals(timeStamp,targetAgentDataMessage.getTimeStamp());
        assertEquals(targetAgentId, targetAgentDataMessage.getTargetAgentId());
        assertEquals(mode,targetAgentDataMessage.getMode() );
        assertEquals(maxUtilization,targetAgentDataMessage.getMaxUtilization() );
        assertEquals(lostClientRequestMessages,targetAgentDataMessage.getLostClientRequestMessages() );
        assertEquals(rxBufferSize,targetAgentDataMessage.getRXBufferSize() );
        assertEquals(txBufferSize,targetAgentDataMessage.getTXBufferSize() );
        assertEquals(maxSamplingFrequency,targetAgentDataMessage.getMaxSamplingFrequency());


    }

}