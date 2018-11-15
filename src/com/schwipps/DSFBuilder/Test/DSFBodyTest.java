package com.schwipps.DSFBuilder.Test;

import com.schwipps.DSFBuilder.DSFBody;
import org.junit.jupiter.api.Test;

class DSFBodyTest {

    @Test
    void byteToInt() {
        int number = 2094967295;

        DSFBody body = new DSFBody(null);
        //System.out.println(body.byteToInt(body.intToByte(number)));
    }


    @Test
    void byteToLong() {
    }

    @Test
    void intToByte() {
    }

    @Test
    void longToByte() {
        long number = 3432094967295L;

        DSFBody body = new DSFBody(null);
        //System.out.println(body.byteToLong(body.longToByte(number)));
    }
}