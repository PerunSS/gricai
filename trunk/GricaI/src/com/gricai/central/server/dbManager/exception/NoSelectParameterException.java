package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class NoSelectParameterException extends SQLException{
	
	private static final long serialVersionUID = 1L;
	
	public NoSelectParameterException( String reason){
		super( reason);
	}
}
