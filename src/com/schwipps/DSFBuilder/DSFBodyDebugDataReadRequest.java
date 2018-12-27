package com.schwipps.DSFBuilder;

import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;

import java.util.Arrays;

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
        super(new byte[2+ debugDataItems.length*5]);
        setTargetAgentId(targetAgentId);
        setDebugDataReadRequestCommand(command);
        setDebugDataItems(debugDataItems);
    }

    private void setTargetAgentId(int id){
        b[0] = intToByte(id)[3];
    }
    private void setDebugDataReadRequestCommand(DebugDataReadRequestCommand command){
        b[1] = intToByte(command.getValue())[3];
    }
    private void setDebugDataItems(DSFDebugDataItem[] debugDataItems){
        if(debugDataItems != null){
            for(int i = 0; i < debugDataItems.length; i++){
                if(debugDataItems[i] != null){
                    b[2 + 5 * i] = intToByte(debugDataItems[i].getDataLength())[3];
                    System.arraycopy(debugDataItems[i].getDataItemAddress(), 0, b, 3 + 5 * i, 4);

                }
            }
        }
    }

    public int getTargetAgentId(){
        return byteToInt(b[0]);
    }
    public DebugDataReadRequestCommand getDebugDataReadRequestCommand(){
        switch (byteToInt(b[1])){
            case (0): return DebugDataReadRequestCommand.READ_DATA_ONCE;
            case (1): return DebugDataReadRequestCommand.READ_DATA_PERIODICALLY;
            case (2): return DebugDataReadRequestCommand.CANCEL_ALL_PERIODIC_TRANSFERS;
        }
        return DebugDataReadRequestCommand.INVALID_COMMAND_HANDLE;
    }

    public DSFDebugDataItem[] getDebugDataItems(){
        int debugDataItemsLength = (b.length-2)/5;
        DSFDebugDataItem[] dsfDebugDataItems = new DSFDebugDataItem[debugDataItemsLength];
        for(int i = 0; i < debugDataItemsLength; i++){
            dsfDebugDataItems[i] = new DSFDebugDataItem(Arrays.copyOfRange(b,2+i*5 , 7+i*5));
        }
        return dsfDebugDataItems;
    }
}
