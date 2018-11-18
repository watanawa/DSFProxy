package com.schwipps.DSFBuilder;

import com.schwipps.DSFBuilder.enums.EquipmentDefinitionDataType;

import java.math.BigInteger;

public class DSFEquipmentDefinitionRecordElement {
    private byte[] address;
    private Object dataTypeItem;
    private EquipmentDefinitionDataType dataTypeEnum;
    private int bitSize;


    public DSFEquipmentDefinitionRecordElement(byte[] address, Object dataTypeItem, EquipmentDefinitionDataType dataTypeEnum, int bitSize){
        setAddress(address);
        setDataTypeItem(dataTypeItem);
        setDataTypeEnum(dataTypeEnum);
        setBitSize(bitSize);
    }

    public byte[] getAddress() {
        return address;
    }

    public void setAddress(byte[] address) {
        this.address = address;
    }

    public Object getDataTypeItem() {
        return dataTypeItem;
    }

    public void setDataTypeItem(Object dataTypeItem) {
        this.dataTypeItem = dataTypeItem;
    }

    public EquipmentDefinitionDataType getDataTypeEnum() {
        return dataTypeEnum;
    }

    public void setDataTypeEnum(EquipmentDefinitionDataType dataTypeEnum) {
        this.dataTypeEnum = dataTypeEnum;
    }

    public int getBitSize() {
        return bitSize;
    }

    public void setBitSize(int bitSize) {
        this.bitSize = bitSize;
    }
}
