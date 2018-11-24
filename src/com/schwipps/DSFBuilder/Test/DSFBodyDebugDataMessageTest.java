package com.schwipps.DSFBuilder.Test;

import com.schwipps.DSFBuilder.DSFBodyDebugDataMessage;
import com.schwipps.DSFBuilder.DSFDebugDataItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DSFBodyDebugDataMessageTest {

    @Test
    void testClass(){
        DSFDebugDataItem item1 = new DSFDebugDataItem(3, 19324243L, new byte[]{123, 3, 30 });
        DSFDebugDataItem item2 = new DSFDebugDataItem(4, 434243L, new byte[]{2,54,13,109});
        DSFDebugDataItem item3 = new DSFDebugDataItem(2, 32423432L, new byte[]{42,42});

        DSFDebugDataItem[] dsfDebugDataItems = new DSFDebugDataItem[]{item1, item2 ,item3};

        DSFBodyDebugDataMessage dsfBodyDebugDataMessageObject = new DSFBodyDebugDataMessage(9999, 42, dsfDebugDataItems);
        //Test DebugDataMessa
        assertEquals(9999, dsfBodyDebugDataMessageObject.getTimeStamp() );
        assertEquals(42,dsfBodyDebugDataMessageObject.getTargetAgentId());
        //Test DebugDataItemsArray
        assertEquals(item1.getDataItemAddressLong(), dsfBodyDebugDataMessageObject.getDebugDataItems()[0].getDataItemAddressLong());
        assertEquals(item2.getDataItemAddressLong(), dsfBodyDebugDataMessageObject.getDebugDataItems()[1].getDataItemAddressLong());
        assertEquals(item3.getDataItemAddressLong(), dsfBodyDebugDataMessageObject.getDebugDataItems()[2].getDataItemAddressLong());

        assertArrayEquals(item1.getData(),dsfBodyDebugDataMessageObject.getDebugDataItems()[0].getData());
        assertArrayEquals(item2.getData(),dsfBodyDebugDataMessageObject.getDebugDataItems()[1].getData());
        assertArrayEquals(item3.getData(),dsfBodyDebugDataMessageObject.getDebugDataItems()[2].getData());

        //Create a new object from byte and test again
        DSFBodyDebugDataMessage dsfBodyDebugDataMessage = new DSFBodyDebugDataMessage(dsfBodyDebugDataMessageObject.getByte());
        assertEquals(9999, dsfBodyDebugDataMessage.getTimeStamp() );
        assertEquals(42,dsfBodyDebugDataMessage.getTargetAgentId());
        //Test DebugDataItemsArray
        assertEquals(item1.getDataItemAddressLong(), dsfBodyDebugDataMessage.getDebugDataItems()[0].getDataItemAddressLong());
        assertEquals(item2.getDataItemAddressLong(), dsfBodyDebugDataMessage.getDebugDataItems()[1].getDataItemAddressLong());
        assertEquals(item3.getDataItemAddressLong(), dsfBodyDebugDataMessage.getDebugDataItems()[2].getDataItemAddressLong());

        assertArrayEquals(item1.getData(),dsfBodyDebugDataMessage.getDebugDataItems()[0].getData());
        assertArrayEquals(item2.getData(),dsfBodyDebugDataMessage.getDebugDataItems()[1].getData());
        assertArrayEquals(item3.getData(),dsfBodyDebugDataMessage.getDebugDataItems()[2].getData());
    }
}