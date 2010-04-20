package com.gricai.central.server.dbManager.impl;

import com.gricai.central.server.dbManager.SQLSelect;

public class SQLSelectImpl extends SQLSelect {
	
	@Override
	public SQLSelect from(String tablename) {
		setFromString(getFromString()+ " "+ tablename);
		return null;
	}

	@Override
	public SQLSelect orderBy(String condition) {
		setOrderByString(getOrderByString()+ " "+ condition);
		return null;
	}

	@Override
	public SQLSelect select(String columnName) {
		setSelectString(getSelectString()+ " "+ columnName);
		return null;
	}

	@Override
	public SQLSelect where(String condition) {
		setWhereString(getWhereString()+ " "+ condition);
		return null;
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