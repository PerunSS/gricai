package com.gricai.central.server.dbManager;

public interface SQLSelect extends SQLQuery{

	public SQLSelect select(String columnName);
	public SQLSelect from(String tablename);
	public SQLSelect where(String condition);
	public SQLSelect orderBy(String condition);
}
