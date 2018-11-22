package com.schwipps.Json.Test;

import com.schwipps.Json.JSONDebugDataMessage;
import org.junit.jupiter.api.Test;

class JSONDebugTest {

    @Test
    void addField() {
        JSONDebugDataMessage jsonDebug = new JSONDebugDataMessage();
        jsonDebug.addField(null, null);
    }
}