package com.schwipps.DSFBuilder;

import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;

public class DSFBodyDebugDataReadRequest extends DSFBody{
    /* Structure
    0   TargetAgentID   1Byte   uint
    1   Command         1Byte   enum

        this may repeat 0..n times

        DATAITEM1
    2   DataLength      1Byte   uint
    3   DataItemAdress  4Byte   uint

        DATAITEM2
    8   DataLength      1Byte   uint
    9   DataItemAdress  4Byte   uint
    ...
    */



    public DSFBodyDebugDataReadRequest (byte[] b){
        super(b);
    }
    public DSFBodyDebugDataReadRequest (int targetAgentId, DebugDataReadRequestCommand command, DSFDebugDataItem[] debugDataItems){
        super(new byte[1]);
        //TODO
    }

}
