package com.schwipps.DSFBuilder;

import com.schwipps.DSFBuilder.enums.EquipmentDefinitionDataType;
import com.schwipps.dsf.TypeCompilationUnit;

import java.awt.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.LinkedList;

public class DSFEquipmentDefinitionRecordElement {
    private byte[] address;
    private Object dataTypeItem;
    private EquipmentDefinitionDataType dataTypeEnum;
    private int bitSize;
    private TypeCompilationUnit compilationUnit;

    private DSFRecordElement dsfRecordElement;

    public DSFEquipmentDefinitionRecordElement(byte[] address, Object dataTypeItem, EquipmentDefinitionDataType dataTypeEnum, int bitSize, TypeCompilationUnit compilationUnit ,DSFRecordElement dsfRecordElement){
        setAddress(address);
        setDataTypeItem(dataTypeItem);
        setDataTypeEnum(dataTypeEnum);
        setBitSize(bitSize);

        setCompilationUnit(compilationUnit);
        setDsfRecordElement(dsfRecordElement);
    }

    public byte[] getAddress() {
        return address;
    }

    public long getAddressLong(){
        byte[] temp = new byte[Long.BYTES];
        System.arraycopy(address, 0, temp, Long.BYTES-address.length,address.length );
       return ByteBuffer.wrap(temp).getLong();
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

    public DSFDebugDataItem getDSFDebugDataItem(){
        return new DSFDebugDataItem(bitSize/8, address);
    }



    public TypeCompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

    public void setCompilationUnit(TypeCompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }


    public DSFRecordElement getDsfRecordElement() {
        return dsfRecordElement;
    }

    public void setDsfRecordElement(DSFRecordElement dsfRecordElement) {
        this.dsfRecordElement = dsfRecordElement;
    }
}
