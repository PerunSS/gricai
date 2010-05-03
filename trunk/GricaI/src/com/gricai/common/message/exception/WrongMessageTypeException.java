package com.gricai.common.message.exception;

public class WrongMessageTypeException extends Exception{

	private static final long serialVersionUID = 1L;
		
	public WrongMessageTypeException( String reason){
		super(reason);
	}
}
