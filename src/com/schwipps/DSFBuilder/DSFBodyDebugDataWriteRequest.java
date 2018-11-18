package com.schwipps.DSFBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DSFBodyDebugDataWriteRequest extends DSFBody {
        /* Strucuture
    0   TargetAgentID   1Byte uint
    ####Dataitems####
    1   DataLength      1Byte uint
    2   DataItemAddress 4Byte uint
    6  Data       1..255Byte byte array
    ...Next DataItem
     */

    public DSFBodyDebugDataWriteRequest(byte[] b) {
        super(b);
    }

    public DSFBodyDebugDataWriteRequest( int targetAgentId, DSFDebugDataItem[] dsfDebugDataItems) {
        super(new byte[1 + getTotalDataItemsLength(dsfDebugDataItems)]);
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

    public void setTargetAgentId(int targetAgentId) {
        b[0] = intToByte(targetAgentId)[3];
    }

    public void setDebugDataItems(DSFDebugDataItem[] dsfDebugDataItems) {
        int temp = 0;
        for (int i = 0; i < dsfDebugDataItems.length; i++) {
            System.arraycopy(dsfDebugDataItems[i].getByte(), 0, b, 1 + temp, dsfDebugDataItems[i].getDataItemLength());
            temp += dsfDebugDataItems[i].getDataItemLength();
        }
    }
    public int getTargetAgentId() {
        return byteToInt(b[0]);
    }

    public DSFDebugDataItem[] getDebugDataItems(){
        List<DSFDebugDataItem> debugDataItems = new ArrayList<DSFDebugDataItem>();
        int pointer = 1;
        do{
            int dataLength = byteToInt(b[pointer]);
            debugDataItems.add(new DSFDebugDataItem(Arrays.copyOfRange(b, pointer, pointer + 5 + dataLength)));
            pointer += 5 + dataLength;
        }while(pointer < b.length);
        return debugDataItems.toArray(new DSFDebugDataItem[debugDataItems.size()]);
    }
}
