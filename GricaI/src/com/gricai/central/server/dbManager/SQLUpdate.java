package com.gricai.central.server.dbManager;

import com.gricai.central.server.dbManager.exception.InvalidParameterException;
import com.gricai.central.server.dbManager.exception.MultipleSetException;
import com.gricai.central.server.dbManager.exception.MultipleUpdateException;
import com.gricai.central.server.dbManager.exception.MultipleWhereException;

public abstract class SQLUpdate extends SQLQuery{
	
	
	public abstract SQLUpdate update( String tableName) throws MultipleUpdateException;
	/**
	 * 
	 * @param tablename
	 * @return
	 */
	
	public abstract SQLUpdate where(String condition) throws MultipleWhereException;
	
	/**
	 * 
	 * @param condition
	 * @return
	 * throws MultipleWhereException
	 */
	public abstract SQLUpdate whereParameter(String paramName, Object paramValue) throws InvalidParameterException;

	/**
	 * 
	 * @param condition
	 * @return
	 */
	
	public abstract SQLUpdate set(String updateElements) throws MultipleSetException;
	
	/**
	 * 
	 *
	 *
	 *
	 */
	public abstract SQLUpdate setParameter(String paramName, Object paramValue) throws InvalidParameterException;

	/**
	 * 
	 *
	 * 
	 */
}
