package com.schwipps.DSFBuilder;

import com.schwipps.DSFBuilder.enums.targetAgentMode;

import java.util.Arrays;

public class DSFBodyTargetAgentDataMessage extends  DSFBody{
    /* Message structure
    0   TimeStamp                   4Byte   uint
    4   TargetAgentId               1Byte   uint
    5   Checksum                    16Byte  []
    21  Mode                        1Byte   []
    22  MaxUtilization              1Byte   int 0..100
    23  LostClientRequestMessage    1Byte   uint
    24  RXBufferSize                2Byte   uint
    26  TXBufferSize                2Byte   uint
    28  MaxSamplingFrequency        1Byte   uint
    */


    public DSFBodyTargetAgentDataMessage(byte[] b) {
        super(b);
    }

    public DSFBodyTargetAgentDataMessage(){
        super(new byte[29]);
    }
    // Field getter
    public int getTimeStamp(){
        return byteToInt(Arrays.copyOfRange(b,0,4));
    }
    public int getTargetAgentId(){
        return byteToInt(b[4]);
    }
    public byte[] getChecksum(){
        return Arrays.copyOfRange(b,5,21);
    }
    public targetAgentMode getMode(){
        switch (byteToInt(b[21])){
            case(1):
                return targetAgentMode.CONNECTED;
            case(0):
                return targetAgentMode.DISCONNECTED;
            default:
                return targetAgentMode.INVALID;
        }
    }
    public int getMaxUtilization(){
        return byteToInt(b[22]);
    }
    public int getLostClientRequestMessage(){
        return byteToInt(b[23]);
    }
    public int getRXBufferSize(){
        return byteToInt(Arrays.copyOfRange(b,24,26));
    }
    public int getTXBufferSize(){
        return byteToInt(Arrays.copyOfRange(b,26,28));
    }
    public int getMaxSamplingFrequency(){
        return byteToInt(b[28]);
    }

    // Field Setter not needed

    public void setTimeStamp(int timeStamp){
        byte[] temp= intToByte(timeStamp);

        for(int i = 0; i< 4; i++){
            b[i] = temp[i];
        }
    }

    public void setTargetAgentId(int targetAgentId){
        b[4] = intToByte(targetAgentId)[3];
    }
    public void setChecksum(byte[] checkSum){
        int offset = 5;
        for(int i = 0; i< 16; i++){
            b[i+offset] = checkSum[i];
        }
    }
    public void setMode(targetAgentMode mode){
        switch(mode){
            case CONNECTED:
                b[21] = intToByte(mode.getValue())[3];
            case DISCONNECTED:
                b[21] = intToByte(mode.getValue())[3];
            case INVALID:
                b[21] = intToByte(mode.getValue())[3];
        }
    }
    public void setMaxUtilization(int utilization){
            b[22] = intToByte(utilization)[3];
    }
    public void setLostClientRequestMessage(int lostClientRequestMessages){
            b[23] = intToByte(lostClientRequestMessages)[3];
    }
    public void setRXBufferSize(int rxBufferSize){
            byte[] temp = intToByte(rxBufferSize);
            b[24] = temp[2];
            b[25] = temp[3];
    }
    public void setTXBufferSize(int txBufferSize){
            byte[] temp = intToByte(txBufferSize);
            b[26] = temp[2];
            b[27] = temp[3];
    }
    public void setMaxSamplingFrequency(int maxSamplingFrequency){
            b[28] = intToByte(maxSamplingFrequency)[3];
    }
}
