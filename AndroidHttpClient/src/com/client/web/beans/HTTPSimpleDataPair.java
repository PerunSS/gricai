package com.client.web.beans;

public class HTTPSimpleDataPair 
{
	String param,value;
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public HTTPSimpleDataPair(String param,String value)
	{
		this.param = param;
		this.value = value;
	}
	
	
}
