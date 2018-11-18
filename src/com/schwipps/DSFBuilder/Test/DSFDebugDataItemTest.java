package com.schwipps.DSFBuilder.Test;

import com.schwipps.DSFBuilder.DSFDebugDataItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.ByteBuffer;

class DSFDebugDataItemTest {


    @Test
    void check() {
        byte[] data = ByteBuffer.allocate(4).putInt(12341231).array();
        long dataItemAddress = 4294000000L;

        DSFDebugDataItem dataItem = new DSFDebugDataItem(data.length,dataItemAddress,data);


    }
}