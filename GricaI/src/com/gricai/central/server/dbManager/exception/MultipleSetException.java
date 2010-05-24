package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class MultipleSetException extends SQLException{
	
	/**
	* 
	*/
	
	private static final long serialVersionUID = 1L;
	
	public MultipleSetException(String reason){
		super(reason);
	}
}
