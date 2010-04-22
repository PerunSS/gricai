package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class MultipleWhereException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleWhereException(String reason){
		super(reason);
	}
}
