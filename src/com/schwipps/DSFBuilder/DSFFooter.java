package com.schwipps.DSFBuilder;

public class DSFFooter {
	byte[] b;
	
	public DSFFooter(byte[] b) {
		this.b = b;
	}
	
	public byte[] getByte() {
		return b;
		
	}
	
	public int getLength() {
		return b.length;
	}
}
