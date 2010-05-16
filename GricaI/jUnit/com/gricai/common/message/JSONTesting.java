package com.gricai.common.message;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JSONTesting {
	
	@Before
	// Will be performed before each test.
	public void testSetup()
	{
		System.out.println("Setup for test complete.");
	}

	@After
	// Will be performed after each test.
	public void testComplete()
	{
		System.out.println("Test complete.");
	}
	
	@Test
	public void test1(){
		JSONUtils.testValidity("{ \"LoginMessage\" : { \"username\" :  \"pera\" , \"password\" : \"1234\" } }");
		JSONObject testObject = JSONObject.fromObject("{ \"LoginMessage\" : { \"username\" :  \"pera\" , \"password\" : \"1234\" } }");
		for(Object o:testObject.values()){
			System.out.println(o);
		}
	}
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("JSONTesting");

	}
}
