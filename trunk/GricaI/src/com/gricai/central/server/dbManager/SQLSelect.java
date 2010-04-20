package com.gricai.central.server.dbManager;

import java.lang.reflect.Constructor;

import com.gricai.central.server.dbManager.impl.SQLSelectImpl;

public abstract class SQLSelect extends SQLQuery{
	
	public abstract SQLSelect select(String columnName);
	public abstract SQLSelect from(String tablename);
	public abstract SQLSelect where(String condition);
	public abstract SQLSelect orderBy(String condition);
	public abstract SQLSelect parameter(String paramName, Object paramValue);
	public abstract SQLSelect or();
	public abstract SQLSelect and();
	
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
