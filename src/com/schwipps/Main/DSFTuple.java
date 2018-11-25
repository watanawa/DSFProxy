package com.schwipps.Main;

import com.schwipps.DSFBuilder.DSFEquipmentDefinitionRecordElement;

public class DSFTuple{
    int port;
    DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement;

    public DSFTuple(int port, DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement){
        setPort(port);
        setDSFEquipmentDefinitionRecordElement(dsfEquipmentDefinitionRecordElement);

    }

    private void setPort(int port) {
        this.port = port;
    }
    private void setDSFEquipmentDefinitionRecordElement(DSFEquipmentDefinitionRecordElement dsfEquipmentDefinitionRecordElement){
        this.dsfEquipmentDefinitionRecordElement = dsfEquipmentDefinitionRecordElement;
    }
    public int getPort(){
        return port;
    }
    public DSFEquipmentDefinitionRecordElement getDsfEquipmentDefinitionRecordElement(){
        return dsfEquipmentDefinitionRecordElement;
    }

}