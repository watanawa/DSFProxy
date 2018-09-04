package com.schwipps.Test.DSFBuilder;

import com.schwipps.DSFBuilder.DSFBodyTargetAgentDataMessage;
import com.schwipps.DSFBuilder.enums.targetAgentMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DSFBodyTargetAgentDataMessageTest {
    DSFBodyTargetAgentDataMessage targetAgentDataMessage;
    @BeforeEach
    void setUp() {
        targetAgentDataMessage= new DSFBodyTargetAgentDataMessage(new byte[29]);
    }

    @Test
    void getTimeStamp() {
        System.out.println(targetAgentDataMessage.getTimeStamp());
    }

    @Test
    void getTargetAgentId() {
        System.out.println(targetAgentDataMessage.getTargetAgentId());
    }

    @Test
    void getChecksum() {
        System.out.println(targetAgentDataMessage.getChecksum().toString());
    }

    @Test
    void getMode() {
        System.out.println(targetAgentDataMessage.getMode().toString());
    }

    @Test
    void getMaxUtilization() {
        System.out.println(targetAgentDataMessage.getMaxUtilization());
    }

    @Test
    void getLostClientRequestMessage() {
        System.out.println(targetAgentDataMessage.getLostClientRequestMessage());
    }

    @Test
    void getRXBufferSize() {
        System.out.println(targetAgentDataMessage.getRXBufferSize());
    }

    @Test
    void getTXBufferSize() {
        System.out.println(targetAgentDataMessage.getTXBufferSize());
    }

    @Test
    void getMaxSamplingFrequency() {
        System.out.println(targetAgentDataMessage.getMaxSamplingFrequency());
    }

    @Test
    void checkSetter() {
        int timeStamp = 0xFFFFFFFF;
        int targetAgentId = 12;
        targetAgentMode mode = targetAgentMode.CONNECTED;
        int maxUtilization = 42;
        int lostClientRequestMessages = 30;
        int rxBufferSize = 5000;
        int txBufferSize = 65535;
        int maxSamplingFrequency = 200;

        targetAgentDataMessage.setTimeStamp(timeStamp);
        targetAgentDataMessage.setTargetAgentId(targetAgentId);
        targetAgentDataMessage.setMode(mode);
        targetAgentDataMessage.setMaxUtilization(maxUtilization);
        targetAgentDataMessage.setLostClientRequestMessage(lostClientRequestMessages);
        targetAgentDataMessage.setRXBufferSize(rxBufferSize);
        targetAgentDataMessage.setTXBufferSize(txBufferSize);
        targetAgentDataMessage.setMaxSamplingFrequency(maxSamplingFrequency);

        System.out.println(Integer.toUnsignedLong(targetAgentDataMessage.getTimeStamp()) );
        System.out.println(targetAgentDataMessage.getTargetAgentId());
        System.out.println(targetAgentDataMessage.getMode().toString());
        System.out.println(targetAgentDataMessage.getMaxUtilization());
        System.out.println(targetAgentDataMessage.getLostClientRequestMessage());
        System.out.println(targetAgentDataMessage.getRXBufferSize());
        System.out.println(targetAgentDataMessage.getTXBufferSize());
        System.out.println(targetAgentDataMessage.getMaxSamplingFrequency());
    }

}