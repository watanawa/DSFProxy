package com.schwipps.DSFBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DSFBodyDebugDataMessage extends DSFBody {
    /* Strucuture
    0   Time Stamp      4Byte uint
    4   TargetAgentID   1Byte uint
    5   IgnoredDataIems 1Byte uint
    ####Dataitems####
    6   DataLength      1Byte uint
    7   DataItemAddress 4Byte uint
    11  Data       1..255Byte byte array
    ...Next DataItem
     */

    public DSFBodyDebugDataMessage(byte[] b) {
        super(b);
    }

    public DSFBodyDebugDataMessage(long timeStamp, int targetAgentId, DSFDebugDataItem[] dsfDebugDataItems) {
        super(new byte[6 + getTotalDataItemsLength(dsfDebugDataItems)]);
        setTimeStamp(timeStamp);
        setTargetAgentId(targetAgentId);
        setDebugDataItems(dsfDebugDataItems);
    }

    private static int getTotalDataItemsLength(DSFDebugDataItem[] dsfDebugDataItems) {
        int totalDataItemsLength = 0;
        for (DSFDebugDataItem dsfDebugDataItem : dsfDebugDataItems) {
            totalDataItemsLength += dsfDebugDataItem.getDataItemLength();
        }
        return totalDataItemsLength;
    }

    public void setTimeStamp(long timeStamp) {
        System.arraycopy(longToByte(timeStamp), 4, b, 0, 4);
    }

    public void setTargetAgentId(int targetAgentId) {
        b[4] = intToByte(targetAgentId)[3];
    }

    public void setDebugDataItems(DSFDebugDataItem[] dsfDebugDataItems) {
        int temp = 0;
        for (int i = 0; i < dsfDebugDataItems.length; i++) {
            System.arraycopy(dsfDebugDataItems[i].getByte(), 0, b, 6 + temp, dsfDebugDataItems[i].getDataItemLength());
            temp += dsfDebugDataItems[i].getDataItemLength();
        }
    }

    public long getTimeStamp() {
        return byteToLong(Arrays.copyOfRange(b, 0, 4));
    }

    public int getTargetAgentId() {
        return byteToInt(b[4]);
    }
    public DSFDebugDataItem[] getDebugDataItems(){
        List<DSFDebugDataItem> debugDataItems = new ArrayList<DSFDebugDataItem>();
        if(b.length > 11){
        int pointer = 6;
        do{
            int dataLength = byteToInt(b[pointer]);
            debugDataItems.add(new DSFDebugDataItem(Arrays.copyOfRange(b, pointer, pointer + 5 + dataLength)));
            pointer += 5 + dataLength;
        }while(pointer < b.length);
        return debugDataItems.toArray(new DSFDebugDataItem[debugDataItems.size()]);
        }
        else{
            return null;
        }
    }
}
