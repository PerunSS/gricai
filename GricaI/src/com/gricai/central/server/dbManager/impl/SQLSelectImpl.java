package com.gricai.central.server.dbManager.impl;

import java.util.HashMap;
import java.util.Map;

import com.gricai.central.server.dbManager.SQLSelect;

public class SQLSelectImpl extends SQLSelect {

	// strings for saving select procedure arguments
	private String selectString = null;
	private String fromString = null;
	private String whereString = null;
	private String orderByString = null;
	private Map<String, Object> whereArguments = new HashMap<String, Object>();


	public Map<String, Object> getWhereArguments() {
		return whereArguments;
	}

	public void setWhereArguments(Map<String, Object> whereArguments) {
		this.whereArguments = whereArguments;
	}

	private SQLSelectImpl() {
	}

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
		if (getFromString() == null) {
			setFromString(tablename);
		} else {
			setFromString(getFromString() + "," + tablename);
		}
		return this;

	}

	@Override
	public SQLSelect orderBy(String condition) {
		if (getOrderByString() == null) {
			setOrderByString(condition);
		} else {
			setOrderByString(getOrderByString() + "," + condition);
		}
		return this;

	}

	@Override
	public SQLSelect select(String columnName) {
		if (getSelectString() == null) {
			setSelectString(columnName);
		} else {
			setSelectString(getSelectString() + "," + columnName);
		}
		return this;

	}

	@Override
	public SQLSelect where(String condition) throws Exception{
		// puts WHERE arguments in whereArguments 
		try{
			String parameterDelimiter = "=:";
			if (condition.indexOf(parameterDelimiter) != -1){
				String[] tokens = condition.split(parameterDelimiter);
				whereArguments.put(tokens[0], null);
			} else {
				parameterDelimiter = "=";
				if (condition.indexOf(parameterDelimiter) != -1){
					String[] tokens = condition.split(parameterDelimiter);
					whereArguments.put(tokens[0], tokens[1]);
				} else throw new Exception("invalid WHERE argument");
			}
		} catch (Exception e){
			//TODO log
			System.err.println("Error:" + e.getMessage());
			throw e;
		}
		return this;
	}
	
	@Override
	public SQLSelect and() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public SQLSelect or() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public SQLSelect parameter(String paramName, Object paramValue) {
		// TODO Auto-generated method stub
		return this;
	}

	// Making SELECT query from select,from,where,orderby strings throws
	// exception if select and/or from are empty

	// TODO primer:
	// select.select("a").select("b").select("c").from("table1").from("table2")
	//		.where("a=:a").and().where("b=:b").or().where("a=c").parameter("a",234).parameter("b","test");
	//
	// izgleda ovako:
	// select a,b,c from table1,table2 where a=234 and b='test' or a=c
	@Override
	public String evaluate() throws Exception {
		String selectQuery = null;
		try {
			if (getSelectString() != null) {
				selectQuery = "SELECT " + getSelectString();
				if (getFromString() != null) {
					selectQuery = selectQuery + " FROM " + getFromString();
					if (getWhereString() != null) {
						selectQuery = selectQuery + " WHERE "
								+ getWhereString();
					}
					if (getOrderByString() != null) {
						selectQuery = selectQuery + " ORDER BY "
								+ getOrderByString();
					}
				} else
					throw new Exception("cant execute query without FROM arguments");
			} else
				throw new Exception("cant execute query without SELECT arguments");
		} catch (Exception e) {
			//TODO log
			System.err.println("Error :" + e.getMessage());
			throw e;
		}
		return selectQuery;
	}

	
}