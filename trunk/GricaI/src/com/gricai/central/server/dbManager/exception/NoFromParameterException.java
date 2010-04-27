package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class NoFromParameterException extends SQLException{
	
	
	private static final long serialVersionUID = 1L;
	
	public NoFromParameterException(String reason){
		super(reason);
	}
}
