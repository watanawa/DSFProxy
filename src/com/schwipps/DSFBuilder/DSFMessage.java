package com.schwipps.DSFBuilder;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class DSFMessage {
    private DSFHeader head;
    private DSFBody body;
    private DSFFooter footer;

    public enum messageType {
        TARGET_AGENT_REQUEST_MESSAGE(10000),
        TARGET_AGENT_DATA_MESSAGE(10001),
        DEBUG_DATA_READ_REQUEST_MESSAGE(10004),
        DEBUG_DATA_WRITE_REQUEST_MESSAGE(10013),
        DEBUG_DATA_MESSAGE(10005),
        INVALID_MESSAGE_HANDLE(42);
        private final int i;
        messageType(int i) {
            this.i = i;
        }
        public int getValue(){
            return i;
        }

    }

    public DSFMessage(DSFHeader head, DSFBody body, DSFFooter footer) {
        this.head = head;
        this.body = body;
        this.footer = footer;
    }

    /*
    public DSFMessage(byte[] b) {
        //Seperates the bytes into header+body+footer and fills the byte into the corresponding fields
        setHead(new DSFHeader(Arrays.copyOfRange(b, 0, head.getLength())));
        setBody(new DSFBody(Arrays.copyOfRange(b, head.getLength(), head.getLength()+head.getMessageLength())));
        setFooter(new DSFFooter(Arrays.copyOfRange(b, head.getLength()+head.getMessageLength(), head.getLength()+head.getMessageLength()+head.getChecksumSize())));
    }*/



    public byte[] getByte() {
        //Assembles head+body+footer
        byte[] b =  new byte[head.getLength() + head.getMessageLength() + head.getChecksumSize()];
        //fills Head
        for(int i = 0; i < head.getLength(); i++) {
            b[i] = head.getByte()[i];
        }
        //fills Body
        for(int i = head.getLength(); i< head.getLength()+head.getMessageLength(); i++ ) {
            b[i] = body.getByte()[i-head.getLength()];
        }
        //fill footer
        for(int i = head.getLength()+head.getMessageLength(); i < head.getLength()+head.getMessageLength()+head.getChecksumSize(); i++ ) {
            b[i] = footer.getByte()[i-head.getLength()-head.getMessageLength()];
        }
        return b;
    }

    public DSFHeader getHead() {
        return head;
    }

    public void setHead(DSFHeader head) {
        this.head = head;
    }

    public DSFBody getBody() {
        return body;
    }

    public void setBody(DSFBody body) {
        this.body = body;
    }

    public DSFFooter getFooter() {
        return footer;
    }

    public void setFooter(DSFFooter footer) {
        this.footer = footer;
    }

    public messageType getMessageType(){
        switch(head.getMessageType()){
            case(10000): return messageType.TARGET_AGENT_REQUEST_MESSAGE;
            case(10001): return messageType.TARGET_AGENT_DATA_MESSAGE;
            case(10004): return messageType.DEBUG_DATA_READ_REQUEST_MESSAGE;
            case(10013): return messageType.DEBUG_DATA_WRITE_REQUEST_MESSAGE;
            case(10005): return messageType.DEBUG_DATA_MESSAGE;
        }
        return messageType.INVALID_MESSAGE_HANDLE;
    }

    public boolean messageNoError(){
        byte[] calcSum = ByteBuffer.allocate(4).putInt(head.calculateChecksum()+body.calculateChecksum()).array();
        if(head.getChecksumSize() == 2){
            calcSum[0] = 0x00;
            calcSum[1] = 0x00;
        }
        return ByteBuffer.wrap(calcSum).getInt() == footer.getChecksumNumber();
    }
}
