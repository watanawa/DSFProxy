package com.schwipps.Test.DSFBuilder;

import com.schwipps.DSFBuilder.DSFHeader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DSFHeaderTest {

    @Test
    void dsfHeader(){
        DSFHeader head = new DSFHeader(1,2,3,true,4,4);
        Assertions.assertEquals(1,head.getInstanceID());
        Assertions.assertEquals(2,head.getMessageType());
        Assertions.assertEquals(3,head.getMessageLength());
        Assertions.assertEquals(true,head.getAckRequired());
        Assertions.assertEquals(4,head.getIDDVersion());
        Assertions.assertEquals(4,head.getChecksumSize());
    }

    @Test
    void getByte() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        DSFHeader head = new DSFHeader(b );
        Assertions.assertArrayEquals(b, head.getByte());
    }

    @Test
    void getLength() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        DSFHeader head = new DSFHeader(b );
        Assertions.assertEquals(8, head.getLength());
    }

    @Test
    void getInstanceID() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        b[0] = (byte) 0xFF;
        b[1] = (byte) 0xFF;
        DSFHeader head = new DSFHeader(b );
        Assertions.assertEquals(65535, head.getInstanceID());

    }

    @Test
    void getMessageType() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        b[2] = (byte) 0xFF;
        b[3] = (byte) 0xFF;
        DSFHeader head = new DSFHeader(b );
        Assertions.assertEquals(65535, head.getMessageType());
    }

    @Test
    void getMessageLength() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        DSFHeader head = new DSFHeader(b );
        Assertions.assertEquals(b.length, head.getLength());
    }

    @Test
    void getAckRequired() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        b[6] = (byte)0x80; // 1000 0000 -> True
        DSFHeader head = new DSFHeader(b);
        Assertions.assertEquals(true, head.getAckRequired());
        b[6] = (byte)0xFF; // 1111 1111 -> True
        head = new DSFHeader(b);
        Assertions.assertEquals(true, head.getAckRequired());
        b[6] = (byte)0x7F; // 0111 1111 -> False
        head = new DSFHeader(b);
        Assertions.assertEquals(false, head.getAckRequired());
        b[6] = (byte)0x00; // 0000 0000 -> False
        head = new DSFHeader(b);
        Assertions.assertEquals(false, head.getAckRequired());
    }

    @Test
    void getIDDVersion() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        b[6] = (byte)0xFF; // 1111 1111
        DSFHeader head = new DSFHeader(b );
        Assertions.assertEquals(127, head.getIDDVersion());

    }

    @Test
    void getChecksumSize() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        b[7] = (byte)0x3F; // 0011 1111 -> 0 Byte
        DSFHeader head = new DSFHeader(b );
        Assertions.assertEquals(0 , head.getChecksumSize());
        b[7] = (byte)0x40; // 0100 0000 -> 2 Byte
        head = new DSFHeader(b );
        Assertions.assertEquals(2 , head.getChecksumSize());
        b[7] = (byte)0x80; // 1000 0000 -> 4 Byte
        head = new DSFHeader(b );
        Assertions.assertEquals(4 , head.getChecksumSize());
    }

    @Test
    void setInstanceID() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        DSFHeader head = new DSFHeader(b);
        head.setInstanceID(400);
        Assertions.assertEquals(400, head.getInstanceID());
    }

    @Test
    void setMessageType() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        DSFHeader head = new DSFHeader(b);
        head.setMessageType(400);
        Assertions.assertEquals(400, head.getMessageType());
    }

    @Test
    void setMessageLength() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        DSFHeader head = new DSFHeader(b);
        head.setMessageLength(40000);
        Assertions.assertEquals(40000,head.getMessageLength());
    }

    @Test
    void setAckRequired() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        DSFHeader head = new DSFHeader(b);
        head.setAckRequired(true);
        head.setIDDVersion(2000);
        Assertions.assertEquals(true,head.getAckRequired());
        head.setAckRequired(false);
        head.setIDDVersion(200);
        Assertions.assertEquals(false,head.getAckRequired());

    }

    @Test
    void getIDDVersion1() {
        byte[] b = new byte[8];
        for(int i = 0; i < 8; i++) {
            b[i] = (byte) 0x00;
        }
        DSFHeader head = new DSFHeader(b);
        head.setAckRequired(true);
        head.setIDDVersion(127);
        head.setAckRequired(false);
        Assertions.assertEquals(127,head.getIDDVersion());
        head.setAckRequired(true);
        head.setIDDVersion(0);
        head.setAckRequired(false);
        Assertions.assertEquals(0,head.getIDDVersion());
    }

    @Test
    void setChecksumSize() {
    }
}