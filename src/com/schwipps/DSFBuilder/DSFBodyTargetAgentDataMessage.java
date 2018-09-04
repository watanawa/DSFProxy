package com.schwipps.DSFBuilder;

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

    public enum targetAgentMode{
        DISCONNECTED(0),
        CONNECTED(1);

        private final int i;

        targetAgentMode(int i){ this.i = i;}
        public int getValue(){return i;}
    }
    public DSFBodyTargetAgentDataMessage(byte[] b) {
        super(b);
    }

    public DSFBodyTargetAgentDataMessage(){
        super(new byte[29]);
    }

    public long getTimeStamp(){
        return byteToLong(Arrays.copyOfRange(b,0,4));
    }

    public int getTargetAgentId(){

    }
    public byte[] getChecksum(){

    }
    public targetAgentMode getMode(){

    }
    public int getMaxUtilization(){

    }
    public int getLostClientRequestMessage(){

    }
    public int getRXBufferSize(){

    }
    public int getTXBufferSize(){

    }
    public int getMaxSamplingFrequency(){

    }

    // Field Setter not needed
    /*
    public void getTimeStamp(long timeStamp){

    }

    public void setTargetAgentId(int targetAgentId){

    }
    public void setChecksum(byte[] checkSum){

    }
    public void setMode(targetAgentMode mode){

    }
    public void setMaxUtilization(int utilization){

    }
    public void setLostClientRequestMessage(int lostClientRequestMessages){

    }
    public void setRXBufferSize(int rxBufferSize){

    }
    public void setTXBufferSize(int txBufferSize){

    }
    public void setMaxSamplingFrequency(int maxSamplingFrequency){

    }*/
}
