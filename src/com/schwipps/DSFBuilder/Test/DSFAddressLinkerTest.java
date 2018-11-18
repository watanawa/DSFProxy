package com.schwipps.DSFBuilder.Test;

import com.schwipps.Main.DSFAddressLinker;
import com.schwipps.Main.Unmarshaller;
import com.schwipps.dsf.TypeEquipmentDescription;
import org.junit.jupiter.api.Test;

import java.io.File;

class DSFAddressLinkerTest {
    @Test
    public void test(){
        //Setup
        Unmarshaller unmarshaller = new Unmarshaller();
        TypeEquipmentDescription typeEquipmentDescription = unmarshaller.unmarshal(new File("C:\\\\Users\\\\Chris\\\\Desktop\\\\Bachelorarbeit\\\\Practical\\\\QC11_Testing\\\\Release\\\\EquipmentDefinition_C1.xml"));
        DSFAddressLinker dsfAddressLinker = new DSFAddressLinker(typeEquipmentDescription);
        //Declare variables
        String variableName = "AD_CanProbeCanReceive_Context";
        String recordElementName = "AnglesByteBuffer";

        String variableName1 = "ACT_ControlSurfaceServosEalSpecialize_Context";
        String recordElementName1 = "RudderRightPercent";

        dsfAddressLinker.registerMessage(2000, variableName, recordElementName);
        dsfAddressLinker.registerMessage(2001, variableName, recordElementName);

        dsfAddressLinker.registerMessage(2000, variableName1 ,recordElementName1);
        dsfAddressLinker.registerMessage(2001, variableName1, recordElementName1);

        dsfAddressLinker.unregisterMessage(2000, variableName, recordElementName);
        dsfAddressLinker.unregisterMessage(2001, variableName, recordElementName);

        dsfAddressLinker.unregisterMessage(2000, variableName1 ,recordElementName1);
        dsfAddressLinker.unregisterMessage(2001, variableName1, recordElementName1);


    }
}