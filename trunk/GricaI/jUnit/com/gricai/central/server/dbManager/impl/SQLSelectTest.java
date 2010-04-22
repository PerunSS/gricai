package com.gricai.central.server.dbManager.impl;

import org.junit.After;
import org.junit.Before;

import com.gricai.central.server.dbManager.SQLSelect;


public class SQLSelectTest {

	SQLSelect select;

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

////	@Test
//	// tests SQLSelectImpl
//	public void test1()	{
//		
//		try
//		{
//			select = SQLSelect.getInstance();
//			assertEquals("SELECT nesto FROM negde", select.select("nesto").from("negde").evaluate());
//			assertEquals("SELECT nesto FROM negde,negde2 WHERE nesto", SQLSelect.getInstance().select("nesto").from("negde").from("negde2").where("nesto").evaluate());
//			System.out.println("Test 1 completed successfully.");
//		} catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//	}
//	
////	@Test
//	//Tests SQLSelectImpl exceptions
//	public void test2(){
//		
//		try{
//			select =  SQLSelect.getInstance();
//			select.evaluate();
//		} catch (Exception e){
//			assertEquals("Null pointer exception:empty SELECT string", e.getMessage());
//		}
//		try{
//			select.select("nesto").evaluate();
//		} catch ( NullPointerException e){
//			assertEquals("Null pointer exception:empty FROM string", e.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("Test 2 completed successfully.");
//	}
	
//	@Test
//	public void whereTest(){
//		try {
//			select = SQLSelect.getInstance().select("a").select("b").from("table1").from("table2").where("a=:a and b>:b and c between :a and :b");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) 
	{
		org.junit.runner.JUnitCore.main("SQLSelectTest");
	}

	
}
