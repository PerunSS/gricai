package com.gricai.common.message;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class JSONTesting {
	
	
	
	public String test(){
		JSONUtils.testValidity("{ \"LoginMessage\" : { \"username\" :  \"pera\" , \"password\" : \"1234\" } }");
		JSONObject testObject = JSONObject.fromObject("{ \"LoginMessage\" : { \"username\" :  \"pera\" , \"password\" : \"1234\" } }");
		for(Object o:testObject.values()){
			System.out.println(o);
		}
		return null;
	}
	
	public static void main(String[] args) {
		JSONTesting jst = new JSONTesting();
		jst.test();
	}
}
