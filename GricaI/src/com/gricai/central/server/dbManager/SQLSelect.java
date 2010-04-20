package com.gricai.central.server.dbManager;

import com.gricai.central.server.dbManager.impl.SQLSelectImpl;

public abstract class SQLSelect extends SQLQuery{

	public abstract SQLSelect select(String columnName);
	public abstract SQLSelect from(String tablename);
	public abstract SQLSelect where(String condition);
	public abstract SQLSelect orderBy(String condition);
	
	public static SQLSelect getInstance(){
		return new SQLSelectImpl();
	}
}
