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

		for(int i = 0; i < b.length; i++){
			temp[3] = b[i];
			sum += ByteBuffer.wrap(temp).getInt();
		}
		return sum;
	}
	
}
