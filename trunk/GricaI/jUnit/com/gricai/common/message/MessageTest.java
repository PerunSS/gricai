package com.gricai.common.message;

import org.junit.After;
import org.junit.Before;

import com.gricai.common.message.*;


public class MessageTest {

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
	
	public static void main(String[] args) 
	{
		org.junit.runner.JUnitCore.main("MessageTest");
	}
}
