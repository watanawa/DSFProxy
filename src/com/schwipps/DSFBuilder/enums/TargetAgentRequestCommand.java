package com.schwipps.DSFBuilder.enums;

public enum TargetAgentRequestCommand {
    PRESENCE_CHECK(0),
    CONNECT_TO_TARGET_AGENT(1),
    DISCONNECT_FROM_TARGET_AGENT(2);

    private final int i;
    TargetAgentRequestCommand(int i) {
        this.i = i;
    }
    public int getValue(){
        return i;
    }
}
