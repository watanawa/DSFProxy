package com.schwipps.DSFBuilder.Test;

import com.schwipps.DSFBuilder.DSFBodyDebugDataMessage;
import com.schwipps.DSFBuilder.DSFBodyDebugDataWriteRequest;
import com.schwipps.DSFBuilder.DSFDebugDataItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DSFBodyDebugDataWriteRequestTest {

    @Test
    public void test(){
        DSFDebugDataItem item1 = new DSFDebugDataItem(3, 19324243L, new byte[]{123, 3, 30 });
        DSFDebugDataItem item2 = new DSFDebugDataItem(4, 434243L, new byte[]{2,54,13,109});
        DSFDebugDataItem item3 = new DSFDebugDataItem(2, 32423432L, new byte[]{42,42});

        DSFDebugDataItem[] dsfDebugDataItems = new DSFDebugDataItem[]{item1, item2 ,item3};

        DSFBodyDebugDataWriteRequest dsfBodyDebugDataMessageObject = new DSFBodyDebugDataWriteRequest( 42, dsfDebugDataItems);
        //Test DebugDataMessa
        assertEquals(42,dsfBodyDebugDataMessageObject.getTargetAgentId());
        //Test DebugDataItemsArray
        assertEquals(item1.getDataItemAdress(), dsfBodyDebugDataMessageObject.getDebugDataItems()[0].getDataItemAdress());
        assertEquals(item2.getDataItemAdress(), dsfBodyDebugDataMessageObject.getDebugDataItems()[1].getDataItemAdress());
        assertEquals(item3.getDataItemAdress(), dsfBodyDebugDataMessageObject.getDebugDataItems()[2].getDataItemAdress());

        assertArrayEquals(item1.getData(),dsfBodyDebugDataMessageObject.getDebugDataItems()[0].getData());
        assertArrayEquals(item2.getData(),dsfBodyDebugDataMessageObject.getDebugDataItems()[1].getData());
        assertArrayEquals(item3.getData(),dsfBodyDebugDataMessageObject.getDebugDataItems()[2].getData());

        //Create a new object from byte and test again
        DSFBodyDebugDataWriteRequest dsfBodyDebugDataMessage = new DSFBodyDebugDataWriteRequest(dsfBodyDebugDataMessageObject.getByte());
        assertEquals(42,dsfBodyDebugDataMessage.getTargetAgentId());
        //Test DebugDataItemsArray
        assertEquals(item1.getDataItemAdress(), dsfBodyDebugDataMessage.getDebugDataItems()[0].getDataItemAdress());
        assertEquals(item2.getDataItemAdress(), dsfBodyDebugDataMessage.getDebugDataItems()[1].getDataItemAdress());
        assertEquals(item3.getDataItemAdress(), dsfBodyDebugDataMessage.getDebugDataItems()[2].getDataItemAdress());

        assertArrayEquals(item1.getData(),dsfBodyDebugDataMessage.getDebugDataItems()[0].getData());
        assertArrayEquals(item2.getData(),dsfBodyDebugDataMessage.getDebugDataItems()[1].getData());
        assertArrayEquals(item3.getData(),dsfBodyDebugDataMessage.getDebugDataItems()[2].getData());
    }
}