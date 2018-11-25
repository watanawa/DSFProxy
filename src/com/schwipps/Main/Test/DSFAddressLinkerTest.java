package com.schwipps.Main.Test;

import com.schwipps.DSFBuilder.DSFRecordElement;
import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;
import com.schwipps.Main.DSFAddressLinker;
import com.schwipps.Main.Unmarshaller;
import com.schwipps.dsf.TypeEquipmentDescription;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedList;

class DSFAddressLinkerTest {
    @Test
    public void test() {
        //Setup
        Unmarshaller unmarshaller = new Unmarshaller();
        TypeEquipmentDescription typeEquipmentDescription = unmarshaller.unmarshal(new File("C:\\Users\\Chris\\Desktop\\Bachelorarbeit\\QC11_Testing\\QC11_Testing\\Release\\EquipmentDefinition_C1.xml"));
        DSFAddressLinker dsfAddressLinker = new DSFAddressLinker(typeEquipmentDescription);
        //Declare variables
        String variableName = "NAV_BasicConsolidation_Context";
        LinkedList<String> datarecordElement = new LinkedList<>();
        datarecordElement.add("Context_Upsampling_9");
        datarecordElement.add("fby_2_10");
        datarecordElement.add("items");
        datarecordElement.add("3");

        DSFRecordElement dsfRecordElement = new DSFRecordElement(variableName, datarecordElement, DebugDataReadRequestCommand.READ_DATA_PERIODICALLY);


        dsfAddressLinker.registerMessage(2000, dsfRecordElement);
    }
}