package com.gricai.central.server.dbManager.impl;

import com.gricai.central.server.dbManager.SQLSelect;

public class SQLSelectImpl extends SQLSelect {
	
	
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
	
	@Override
	public SQLSelect from(String tablename) {

		setFromString(getFromString()+ " "+ tablename);
		return this;


	}

	@Override
	public SQLSelect orderBy(String condition) {

		setOrderByString(getOrderByString()+ " "+ condition);
		return this;

	}

	@Override
	public SQLSelect select(String columnName) {

		setSelectString(getSelectString()+ " "+ columnName);
		return this;

	}

	@Override
	public SQLSelect where(String condition) {

		setWhereString(getWhereString()+ " "+ condition);
		return this;

	}


	
	//Making SELECT query from select,from,where,orderby strings throws exception if select and/or from are empty

	@Override
	public String evaluate() throws NullPointerException{
		String selectQuery = null;
		try {
			if ( getSelectString() != null){
				selectQuery = getSelectString();
				if ( getFromString() != null){
					selectQuery = selectQuery + getFromString();
					if ( getWhereString() != null){
						selectQuery = selectQuery + getWhereString();
					}
					if ( getOrderByString() != null){
						selectQuery = selectQuery + getOrderByString();
					}
				} else throw new NullPointerException("empty FROM string");
			} else throw new NullPointerException("empty SELECT string");
		}catch (NullPointerException e){
			System.err.println("Null pointer exception:"+e.getMessage());
		}
		return selectQuery;
	}
}