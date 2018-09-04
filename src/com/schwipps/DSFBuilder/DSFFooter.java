package com.schwipps.DSFBuilder;

import java.nio.ByteBuffer;

public class DSFFooter {
	/* 	Footer Structure
	0	Checksum 0/2/4 Byte uint
	*/

	private byte[] b;
	
	public DSFFooter(byte[] b) {
		this.b = b;
	}
	public DSFFooter(int value, int size){
		b = new byte[size];
		setChecksumNumber(value, size);
	}

	public byte[] getByte() {
		return b;
	}
	public int getLength() {
		return b.length;
	}
	public int getChecksumNumber(){
		if(getLength() == 2){
			byte[] temp = new byte[4];
			temp[0] = 0x00;
			temp[1] = 0x00;
			temp[2] = b[0];
			temp[3] = b[1];
			return ByteBuffer.wrap(temp).getInt();
		}else{
			return ByteBuffer.wrap(b).getInt();
		}

	}
	public void setChecksumNumber(int checksum, int size){
		byte[] temp = ByteBuffer.allocate(4).putInt(checksum).array();
		switch(size){
			case(2):
				b[0] = temp[2];
				b[1] = temp[3];
				break;
			case(4):
				b[0] = temp[0];
				b[1] = temp[1];
				b[2] = temp[2];
				b[3] = temp[3];
				break;
		}
	}
}
