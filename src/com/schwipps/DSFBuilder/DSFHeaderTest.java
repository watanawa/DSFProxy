package com.schwipps.DSFBuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DSFHeaderTest {

    @Test
    void getByte() {
    }

    @Test
    void getLength() {
    }

    @Test
    void getInstanceID() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0xFF;
        }
        b[6] = (byte) 0x02;
        DSFHeader head = new DSFHeader(b );
        System.out.println(head.getInstanceID());

    }
}