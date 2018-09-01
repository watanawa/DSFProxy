package com.schwipps.DSFBuilder;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class DSFMessage {
	private DSFHeader head;
	private DSFBody body;
	private DSFFooter footer;
	
	public DSFMessage(DSFHeader head, DSFBody body, DSFFooter footer) {
		this.head = head;
		this.body = body;
		this.footer = footer;
		
	}
	
	//Read DSFMessage
	public DSFMessage(byte[] b) {
		//Seperates the bytes into header+body+footer and fills the byte into the corresponding fields
		setHead(new DSFHeader(Arrays.copyOfRange(b, 0, head.getLength())));
		setBody(new DSFBody(Arrays.copyOfRange(b, head.getLength(), head.getLength()+head.getMessageLength())));
		setFooter(new DSFFooter(Arrays.copyOfRange(b, head.getLength()+head.getMessageLength(), head.getLength()+head.getMessageLength()+head.getChecksumSize())));
	}
	
	public byte[] getByte() {
		//Assembles head+body+footer
		byte[] b =  new byte[head.getLength() + head.getMessageLength() + head.getLength()];
		//fills Head
		for(int i = 0; i < head.getLength(); i++) {
			b[i] = head.getByte()[i];
		}
		//fills Body
		for(int i = head.getLength(); i< head.getLength()+head.getMessageLength(); i++ ) {
			b[i] = body.getByte()[i-head.getLength()];
		}
		//fill footer
		for(int i = head.getLength()+head.getMessageLength(); i < head.getLength()+head.getMessageLength()+head.getChecksumSize(); i++ ) {
			b[i] = footer.getByte()[i-head.getLength()-head.getMessageLength()];
		}
		return b;
	}

	public DSFHeader getHead() {
		return head;
	}

	public void setHead(DSFHeader head) {
		this.head = head;
	}

	public DSFBody getBody() {
		return body;
	}

	public void setBody(DSFBody body) {
		this.body = body;
	}

	public DSFFooter getFooter() {
		return footer;
	}

	public void setFooter(DSFFooter footer) {
		this.footer = footer;
	}
	
	
}
