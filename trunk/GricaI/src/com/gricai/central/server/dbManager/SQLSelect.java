package com.gricai.central.server.dbManager;

import java.lang.reflect.Constructor;
import java.sql.SQLException;

import com.gricai.central.server.dbManager.exception.InvalidParameterException;
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
	public abstract SQLSelect where(String condition) throws SQLException;
	
	/**
	 * 
	 * @param condition
	 * @return
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

	/**
	 * 
	 * @param condition
	 * @return
	 */
	public abstract SQLSelect groupBy(String condition);
}
