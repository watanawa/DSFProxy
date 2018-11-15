package com.schwipps.DSFBuilder.Test;

import com.schwipps.DSFBuilder.DSFDebugDataItem;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

class DSFDebugDataItemTest {


    @Test
    void getDataLength() {
        byte[] temp = ByteBuffer.allocate(4).putInt(12341231).array();
        DSFDebugDataItem dataItem = new DSFDebugDataItem(temp.length,4294000000L,temp);

        System.out.println(dataItem.getDataLength());
        System.out.println(dataItem.getDataItemAdress());
        System.out.println(ByteBuffer.wrap(dataItem.getData()).getInt() );
    }

    @Test
    void getDataItemAdress() {
    }

    @Test
    void getData() {
    }

    @Test
    void setDataLength() {
    }

    @Test
    void setDataItemAdress() {
    }

    @Test
    void setData() {
    }
}