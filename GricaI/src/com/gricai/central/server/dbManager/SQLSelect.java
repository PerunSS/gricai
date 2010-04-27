package com.gricai.central.server.dbManager;

import java.lang.reflect.Constructor;
import java.sql.SQLException;

import com.gricai.central.server.dbManager.exception.InvalidParameterException;
import com.gricai.central.server.dbManager.exception.MultipleHavingException;
import com.gricai.central.server.dbManager.exception.MultipleWhereException;
import com.gricai.central.server.dbManager.impl.SQLSelectImpl;

public abstract class SQLSelect extends SQLQuery{
	
	/**
	 * 
	 * @param columnName
	 * @return
	 */
	public abstract SQLSelect select(String columnName);
	
	/**
	 * 
	 * @param tablename
	 * @return
	 */
	public abstract SQLSelect from(String tablename);
	
	/**
	 * 
	 * @param condition
	 * @return
 * @throws SQLException
	 */
	public abstract SQLSelect where(String condition) throws MultipleWhereException;
	
	/**
	 * 
	 * @param condition
	 * @return
	 * throws MultipleWhereException
	 */
	public abstract SQLSelect orderBy(String condition);
	
	/**
	 * 
	 * @param paramName
	 * @param paramValue
	 * @return
	 * @throws InvalidParameterException
	 */
	public abstract SQLSelect parameter(String paramName, Object paramValue) throws InvalidParameterException;

	/**
	 * 
	 * @param condition
	 * @return
	 */
	public abstract SQLSelect groupBy( String condition);
	
	/**
	 * 
	 * @param condition
	 * @return
	 * throws MultipleHavingException
	 */
	public abstract SQLSelect having( String condition)throws MultipleHavingException;

	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static SQLSelect getInstance() throws Exception {
		try{
			Constructor<SQLSelectImpl> constructor = SQLSelectImpl.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor.newInstance();
		}catch (Exception e) {
			//TODO log
			throw e;
		}
	}

	
	
}
