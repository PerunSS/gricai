package com.gricai.central.server.dbManager.impl;

import com.gricai.central.server.dbManager.SQLSelect;

import static org.junit.Assert.*;
import org.junit.*;


public class SQLSelectTest {

	SQLSelect select = new SQLSelectImpl();

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
	// tests SQLSelectImpl
	public void test1()	{
		select = new SQLSelectImpl();
		try
		{
			assertEquals("SELECT nesto FROM negde", select.select("nesto").from("negde").evaluate());
			assertEquals("SELECT nesto FROM negde,negde2 WHERE nesto", new SQLSelectImpl().select("nesto").from("negde").from("negde2").where("nesto").evaluate());
			System.out.println("Test 1 completed successfully.");
		} catch (NullPointerException e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	//Tests SQLSelectImpl exceptions
	public void test2(){
		select =  new SQLSelectImpl();
		try{
			String s = select.evaluate();
		} catch (NullPointerException e){
			assertEquals("Null pointer exception:empty SELECT string", e.getMessage());
		}
		try{
			String s = select.select("nesto").evaluate();
		} catch ( NullPointerException e){
			assertEquals("Null pointer exception:empty FROM string", e.getMessage());
		}
		System.out.println("Test 2 completed successfully.");
	}

	public static void main(String[] args) 
	{
		org.junit.runner.JUnitCore.main("SQLSelectTest");
	}

	
}
