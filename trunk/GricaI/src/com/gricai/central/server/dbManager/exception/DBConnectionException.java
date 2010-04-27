package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class DBConnectionException extends SQLException {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public DBConnectionException(String reason){
		super(reason);
	}

}