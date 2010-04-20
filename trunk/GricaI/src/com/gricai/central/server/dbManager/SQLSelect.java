package com.gricai.central.server.dbManager;

import com.gricai.central.server.dbManager.impl.SQLSelectImpl;

public abstract class SQLSelect extends SQLQuery{
	
	
	//strings for saving select procedure arguments
	private String selectString = null;
	private String fromString = null;
	private String whereString = null;
	private String orderByString = null;
	
	public String getOrderByString() {
		return orderByString;
	}
	public void setOrderByString(String orderByString) {
		this.orderByString = orderByString;
	}
	public String getSelectString() {
		return selectString;
	}
	public void setSelectString(String selectString) {
		this.selectString = selectString;
	}
	public String getFromString() {
		return fromString;
	}
	public void setFromString(String fromString) {
		this.fromString = fromString;
	}
	public String getWhereString() {
		return whereString;
	}
	public void setWhereString(String whereString) {
		this.whereString = whereString;
	}
	
	public abstract SQLSelect select(String columnName);
	public abstract SQLSelect from(String tablename);
	public abstract SQLSelect where(String condition);
	public abstract SQLSelect orderBy(String condition);
	
	public static SQLSelect getInstance(){
		return new SQLSelectImpl();
	}
}
