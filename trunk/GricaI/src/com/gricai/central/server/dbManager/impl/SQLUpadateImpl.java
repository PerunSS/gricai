package com.gricai.central.server.dbManager.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gricai.central.server.dbManager.SQLUpdate;
import com.gricai.central.server.dbManager.exception.InvalidParameterException;
import com.gricai.central.server.dbManager.exception.MultipleSetException;
import com.gricai.central.server.dbManager.exception.MultipleUpdateException;
import com.gricai.central.server.dbManager.exception.MultipleWhereException;

public class SQLUpadateImpl extends SQLUpdate {
	
	private String updateString;
	private String whereString;
	private String setString;
	private Map<String, Object> whereArguments = new HashMap<String, Object>();
	private Map<String,List<Integer>> wherePositions = new HashMap<String, List<Integer>>();
	private Map<String, Object> setArguments = new HashMap<String, Object>();
	private Map<String,List<Integer>> setPositions = new HashMap<String, List<Integer>>();
	
	@Override
	public SQLUpdate update( String tableName) throws MultipleUpdateException{
		if ( updateString != null) throw new MultipleUpdateException("only one table per update permited");
		else updateString = tableName;
		return this;
	}

	@Override
	public SQLUpdate set( String updateElements) throws MultipleSetException{
		
		if ( setString != null) throw new MultipleSetException("only one set is permited");
		
		int endIndex = 0;
		int index = 0;
		while( true){
			index++;
			int startIndex = updateElements.indexOf(':', endIndex);
			if ( startIndex == -1)
				break;
			endIndex = updateElements.indexOf(" ", startIndex);
			if ( endIndex == -1){
				endIndex = updateElements.length();
			}
			String paramName = updateElements.substring(startIndex,endIndex);			
			setString = setString.replace(paramName, "?");
			List<Integer> indexes = setPositions.get(paramName.substring(1));
			if(indexes == null){
				indexes = new ArrayList<Integer>();
			}
			indexes.add(index);
			setPositions.put(paramName.substring(1), indexes);
			setArguments.put(paramName.substring(1), null);
		}
		return this;
	}

	@Override
	public SQLUpdate setParameter(String paramName, Object paramValue) throws InvalidParameterException{
		boolean contains = false;
		for(Map.Entry<String, Object> entry: setArguments.entrySet()){
			if(entry.getKey().equals(paramName)){
				contains = true;
				break;
			}
				
		}
		if(contains)
			setArguments.put(paramName, paramValue);
		else
			throw new InvalidParameterException("invalid parameter: "+paramName);
		return this;
	}

	
	
	@Override
	public SQLUpdate where( String condition) throws MultipleWhereException{
		
		if(whereString!=null)
			throw new MultipleWhereException("only one where is permited");
		whereString = condition;
//		System.out.println("before: "+whereString);
		int endIndex = 0;
		int index = 0;
		while(true){
			index++;
			int startIndex = condition.indexOf(":",endIndex);
			if(startIndex == -1)
				break;
			endIndex = condition.indexOf(" ",startIndex);
			if(endIndex == -1){
				endIndex = condition.length();
			}
			String paramName = condition.substring(startIndex,endIndex);
			
			whereString = whereString.replace(paramName, "?");
			List<Integer> indexes = wherePositions.get(paramName.substring(1));
			if(indexes == null){
				indexes = new ArrayList<Integer>();
			}
			indexes.add(index);
			wherePositions.put(paramName.substring(1), indexes);
			whereArguments.put(paramName.substring(1), null);			
		}
//		System.out.println("after: "+whereString);
//		System.out.println(wherePositions);
//		System.out.println(whereArguments);
		return this;
	}
	
	@Override
	public SQLUpdate whereParameter(String paramName, Object paramValue) throws InvalidParameterException{
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
	
	
	
	@Override
	protected String evaluate() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() throws SQLException {
		// TODO Auto-generated method stub
	}

	

}
