package com.schwipps.DSFBuilder.enums;

public enum MessageType {
    TARGET_AGENT_REQUEST_MESSAGE(10000),
    TARGET_AGENT_DATA_MESSAGE(10001),
    DEBUG_DATA_READ_REQUEST_MESSAGE(10004),
    DEBUG_DATA_WRITE_REQUEST_MESSAGE(10013),
    DEBUG_DATA_MESSAGE(10005),
    INVALID_MESSAGE_HANDLE(42);
    private final int i;

    MessageType(int i) {
        this.i = i;
    }
    public int getValue(){
        return i;
    }


}