package com.schwipps.DSFBuilder;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class DSFDebugDataItem {
    /* Structure
    0   DataLength      1Byte uint
    1   DataItemAddress 4Byte uint
    6   Data            1..Byte uint

    */

    private byte[] b;



    public DSFDebugDataItem(byte[] b){
        this.b = b;
    }
    public DSFDebugDataItem(int dataLength, long dataItemAddress, byte[] data){
        if(data == null){
            this.b = new byte[5];
        }
        else{
            this.b = new byte[5+ data.length];
        }
        setDataLength(dataLength);
        setDataItemAddress(dataItemAddress);
        setData(data);

    }


    public int getDataLength(){
        byte[] temp = {0x00, 0x00,0x00,b[0]};
        return ByteBuffer.allocate(4).wrap(temp).getInt();
    }

    public long getDataItemAdress(){
        byte[] temp = {0x00, 0x00,0x00,0x00,b[1],b[2],b[3],b[4]};

        return ByteBuffer.wrap(temp).getLong();
    }

    public byte[] getData(){
       return Arrays.copyOfRange(b,5,b.length+1);
    }

    public void setDataLength(int dataLength){
        b[0] = ByteBuffer.allocate(4).putInt(dataLength).array()[3];
    }
    public void setDataItemAddress(long dataItemAddress){
        byte[] temp = ByteBuffer.allocate(8).putLong(dataItemAddress).array();
        b[1] = temp[4];
        b[2] = temp[5];
        b[3] = temp[6];
        b[4] = temp[7];
    }
    public void setData(byte[] data){
        if(data != null){
            for(int i = 0; i < data.length; i++){
                b[5+i] = data[i];
            }
        }
    }
        //TODO
}
