package com.client.web.beans;

import java.util.ArrayList;

import android.os.Build;

public class HTTPPostHeaderDataPair 
{
	
	String headerField, fieldValue;
	public static final String USER_AGENT = "user-agent";
	public static final String CONNECTION = "connection";
	public HTTPPostHeaderDataPair(String headerField,String fieldValue)
	{
		this.headerField = headerField;
		this.fieldValue = fieldValue;
	}
	
	public static ArrayList<HTTPPostHeaderDataPair> getDefaultHTTPHeader()
	{
		ArrayList<HTTPPostHeaderDataPair> list = new ArrayList<HTTPPostHeaderDataPair>();
		list.add(new HTTPPostHeaderDataPair(USER_AGENT,"Android-"+Build.VERSION.RELEASE));
		list.add(new HTTPPostHeaderDataPair(CONNECTION, "keep-alive"));
		return list;
	}

	public String getHeaderField() {
		return headerField;
	}

	public void setHeaderField(String headerField) {
		this.headerField = headerField;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
}
