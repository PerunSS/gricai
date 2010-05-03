package com.gricai.common.message;

import java.nio.ByteBuffer;

public class ErrorMessage implements Message {

	private static final String ERROR_CODE= "error_code";
	
	int errorCode;
	
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

}
