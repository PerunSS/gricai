package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class MultipleHavingException extends SQLException{
	private static final long serialVersionUID = 1L;
	
	public MultipleHavingException(String reason){
		super(reason);
	}
}
