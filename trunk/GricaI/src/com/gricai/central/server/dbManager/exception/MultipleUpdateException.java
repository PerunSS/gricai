package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class MultipleUpdateException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleUpdateException(String message){
		super(message);
	}
}
