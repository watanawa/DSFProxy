package com.schwipps.DSFBuilder;

import java.nio.ByteBuffer;

public class DSFBody {
	protected byte[] b;
	
	public DSFBody(byte[] b)
	{
		this.b = b;
	}
	
	public byte[] getByte() {
		return b;
	}
	public int getChecksum(){
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
		}

		return ByteBuffer.wrap(temp).getLong();
	}
	protected byte[] intToByte(int val){
		return ByteBuffer.allocate(4).putInt(val).array();
	}
	protected byte[] longToByte(long val){
		return ByteBuffer.allocate(8).putLong(val).array();
	}

}
