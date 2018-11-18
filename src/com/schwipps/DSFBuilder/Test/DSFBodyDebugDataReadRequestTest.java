package com.schwipps.DSFBuilder.Test;

import com.schwipps.DSFBuilder.DSFBodyDebugDataReadRequest;
import com.schwipps.DSFBuilder.DSFDebugDataItem;
import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DSFBodyDebugDataReadRequestTest {

    @Test
    void testCase() {
        DSFDebugDataItem item0 = new DSFDebugDataItem(20, 1000000L);
        DSFDebugDataItem item1 = new DSFDebugDataItem(52, 1456789L);
        DSFDebugDataItem item2 = new DSFDebugDataItem(71, 1122336L);

        DSFDebugDataItem[] dsfDebugDataItems = new DSFDebugDataItem[]{item0, item1, item2};

        DSFBodyDebugDataReadRequest dsfBodyDebugDataReadRequest = new DSFBodyDebugDataReadRequest(200, DebugDataReadRequestCommand.READ_DATA_PERIODICALLY, dsfDebugDataItems);
        //Test getter and setter
        assertEquals (200, dsfBodyDebugDataReadRequest.getTargetAgentId());
        assertEquals(DebugDataReadRequestCommand.READ_DATA_PERIODICALLY, dsfBodyDebugDataReadRequest.getDebugDataReadRequestCommand());
        //now check array
        assertEquals(item0.getDataItemAdress(),dsfBodyDebugDataReadRequest.getDebugDataItems()[0].getDataItemAdress() );
        assertEquals(item1.getDataItemAdress(),dsfBodyDebugDataReadRequest.getDebugDataItems()[1].getDataItemAdress() );
        assertEquals(item2.getDataItemAdress(),dsfBodyDebugDataReadRequest.getDebugDataItems()[2].getDataItemAdress() );

        //Check byte contructor
        DSFBodyDebugDataReadRequest dsfBodyDebugDataReadRequestByte = new DSFBodyDebugDataReadRequest(dsfBodyDebugDataReadRequest.getByte());

        assertEquals (200, dsfBodyDebugDataReadRequestByte.getTargetAgentId());
        assertEquals(DebugDataReadRequestCommand.READ_DATA_PERIODICALLY, dsfBodyDebugDataReadRequestByte.getDebugDataReadRequestCommand());
        //now check array
        assertEquals(item0.getDataItemAdress(),dsfBodyDebugDataReadRequestByte.getDebugDataItems()[0].getDataItemAdress() );
        assertEquals(item1.getDataItemAdress(),dsfBodyDebugDataReadRequestByte.getDebugDataItems()[1].getDataItemAdress() );
        assertEquals(item2.getDataItemAdress(),dsfBodyDebugDataReadRequestByte.getDebugDataItems()[2].getDataItemAdress() );
    }

}