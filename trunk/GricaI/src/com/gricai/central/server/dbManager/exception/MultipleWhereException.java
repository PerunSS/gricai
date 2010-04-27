package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class MultipleWhereException extends SQLException{

	public MultipleWhereException(String reason){
		super(reason);
	}
}
