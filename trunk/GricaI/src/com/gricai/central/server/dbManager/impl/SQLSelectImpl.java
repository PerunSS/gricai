package com.gricai.central.server.dbManager.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.gricai.central.server.dbManager.SQLSelect;
import com.gricai.central.server.dbManager.exception.InvalidParameterException;

public class SQLSelectImpl extends SQLSelect {

	// strings for saving select procedure arguments
	private String selectString;
	private String fromString;
	//private String whereString;
	private String orderByString;
	private Map<String, Object> whereArguments = new HashMap<String, Object>();



	@Override
	public SQLSelect from(String tablename) {
		if (fromString == null) {
			fromString = tablename;
		} else {
			fromString+=","+tablename;
		}
		return this;

	}

	@Override
	public SQLSelect orderBy(String condition) {
		if (orderByString == null) {
			orderByString = condition;
		} else {
			orderByString+=","+condition;
		}
		return this;

	}

	@Override
	public SQLSelect select(String columnName) {
		if (selectString == null) {
			selectString = columnName;
		} else {
			selectString+=","+columnName;
		}
		return this;

	}

	//TODO napravi svoje exceptione i stavi ih u paket exception (vec imas jedan kao primer)
	//i ubaci svugde umesto exception i nullPointerException da izbacuje tvoje exceptione
	@Override
	public SQLSelect where(String condition) throws Exception{
		// puts WHERE arguments in whereArguments 
		//TODO 1.promeniti jer onaj and i or bas nemaju smisla nego ce where da bude a=:a and b=:b or c=:c
		// pa to malo sredi :)
		//TODO 2. sta cemo sa <,<=, >,>= <>, between, like, in :)
		//where izgleda ovako: columnName operator value
		//pri cemu operator moze biti gore navedeno i jednako :)
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
	public SQLSelect parameter(String paramName, Object paramValue) throws InvalidParameterException{
		boolean contains = false;
		for(Map.Entry<String, Object> entry: whereArguments.entrySet()){
			if(entry.getKey().equals(paramName)){
				contains = true;
				break;
			}
				
		}
		if(contains)
			whereArguments.put(paramName, paramValue);
		else
			throw new InvalidParameterException("invalid parameter: "+paramName);
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
		StringBuffer selectQuery = new StringBuffer();
		try {
			if (selectString != null) {
				selectQuery.append("SELECT " + selectString);
				if (fromString != null) {
					selectQuery.append(" FROM " + fromString);
					if (whereArguments.size()>0) {
						
						//TODO ovo ne valja, mora da se sredi
						selectQuery.append(" WHERE ");
						for(Map.Entry<String, Object> entry:whereArguments.entrySet() ){
							selectQuery.append( entry.getKey() +" = "+ (entry.getValue()==null?"?":entry.getValue()));
						}
					}
					if (orderByString != null) {
						selectQuery.append(" ORDER BY " + orderByString);
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
		return selectQuery.toString();
	}

	@Override
	public Object execute() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}