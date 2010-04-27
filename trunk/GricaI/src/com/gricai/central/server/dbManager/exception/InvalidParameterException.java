package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class InvalidParameterException extends SQLException {

	public InvalidParameterException(String reason){
		super(reason);
	}
}
