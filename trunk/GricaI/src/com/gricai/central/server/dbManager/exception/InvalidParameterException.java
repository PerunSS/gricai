package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class InvalidParameterException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidParameterException(String reason){
		super(reason);
	}
}
