package com.gricai.common.message.server;

import java.nio.ByteBuffer;

import com.gricai.common.message.Message;

import net.sf.json.JSONObject;

public class ErrorMessage implements Message {

	private static final String ERROR_CODE= "errorCode";
	public static final int NULL_USERNAME = 1;
	public static final int NULL_USER = 2;
	public static final int NOT_LOGGED = 3;
	public static final int UNKNOWN_MESSAGE = 4;
	
	private int errorCode;
	
	public ErrorMessage(){}
	
	public ErrorMessage(int errorCode){
		this.errorCode = errorCode;
	}
	
	@Override
	public void fillMessage(ByteBuffer data) {
		byte[] bytes =  data.array();
		String fullMessage = new String(bytes);
		int indexOfErrorCode = fullMessage.indexOf('&')+1;
		String ErrorCodeString = fullMessage.substring(indexOfErrorCode);
		setErrorCode(new Integer(ErrorCodeString.substring(ErrorCodeString.indexOf('=') + 1)).intValue());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer toByteBuffer() {
		byte[] bytes = new String("class=ErrorMessage&"+ERROR_CODE+"=" + new Integer(getErrorCode()).toString()).getBytes();
		return ByteBuffer.wrap(bytes);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public void fillMessage(JSONObject jsonMessage) {
		// TODO Auto-generated method stub
		
	}

}
