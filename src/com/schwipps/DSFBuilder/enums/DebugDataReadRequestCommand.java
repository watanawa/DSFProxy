package com.schwipps.DSFBuilder.enums;

public enum DebugDataReadRequestCommand {
    READ_DATA_ONCE(0),
    READ_DATA_PERIODICALLY(1),
    CANCEL_ALL_PERIODIC_TRANSFERS(2),
    INVALID_COMMAND_HANDLE(42);

    private final int i;
    DebugDataReadRequestCommand(int i){
        this.i = i;
    }
    public int getValue(){
        return i;
    }
}
