package com.schwipps.DSFBuilder;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class DSFBody {
	protected byte[] b;
	
	public DSFBody(byte[] b)
	{
		this.b = b;
	}
	
	public byte[] getByte() {
		return b;
	}
	public int calculateChecksum(){
		int sum = 0;
		byte[] temp = new byte[4];
		temp[0] = 0x00;
		temp[1] = 0x00;
		temp[2] = 0x00;

		for(byte byteSet : b){
			temp[3] = byteSet;
			sum += ByteBuffer.wrap(temp).getInt();
		}
		return sum;
	}

	protected int byteToInt(byte[] bArray){
		byte[] temp = new byte[Integer.BYTES]; //ZERO INITIALIZED AUTOMATICALLY
		if(bArray.length <= Integer.BYTES){
			System.arraycopy(bArray,0,temp,Integer.BYTES-bArray.length,bArray.length);
		}
		else{
			throw new IllegalArgumentException("Argument may not be longer than "+Integer.BYTES+" Byte");
		}
		return ByteBuffer.wrap(temp).getInt();
	}
	protected int byteToInt(byte b){
		//Returns the unsigned int value
		byte[] temp = new byte[4];
		temp[0] = 0x00;
		temp[1] = 0x00;
		temp[2] = 0x00;
		temp[3] = b;
		return ByteBuffer.wrap(temp).getInt();
	}
	protected long byteToLong(byte[] bArray){
		byte[] temp = new byte[Long.BYTES];
		if(bArray.length <= Long.BYTES){
			System.arraycopy(bArray,0,temp,Long.BYTES-bArray.length,bArray.length);
		}
		else{
			throw new IllegalArgumentException("Argument may not be longer than "+Long.BYTES+" Byte");
		}/*
		for(int i = 0; i < Long.BYTES; i++){
			if(i < Long.BYTES-bArray.length ){
				temp[i] = 0x00;
			}
			// i has reached the first index which should be copied
			else{
				temp[i] = bArray[i-bArray.length];
			}
		}*/

		return ByteBuffer.wrap(temp).getLong();
	}
	protected byte[] intToByte(int val){
		return ByteBuffer.allocate(4).putInt(val).array();
	}
	protected byte[] longToByte(long val){
		return ByteBuffer.allocate(8).putLong(val).array();
	}

}
