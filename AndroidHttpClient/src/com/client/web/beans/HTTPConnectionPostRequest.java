package com.client.web.beans;

import java.util.ArrayList;

public class HTTPConnectionPostRequest extends HTTPConnectionRequest
{
	private ArrayList<HTTPPostHeaderDataPair> postHeader;
	private ArrayList<HTTPSimpleDataPair> postParams;
	private String method;
	public HTTPConnectionPostRequest()
	{
		method = HTTPConnectionRequest.POST_METHOD;
	}
	
	public HTTPConnectionPostRequest createPostRequest(ArrayList<HTTPPostHeaderDataPair> postHeader,ArrayList<HTTPSimpleDataPair> postParams,boolean defaultHeader)
	{
		
		if(defaultHeader)
			this.postHeader=HTTPPostHeaderDataPair.getDefaultHTTPHeader();
		else
			this.postHeader = postHeader;
		this.postParams = postParams;
		
		return this;
	}

	public ArrayList<HTTPPostHeaderDataPair> getPostHeader() {
		return postHeader;
	}

	public void setPostHeader(ArrayList<HTTPPostHeaderDataPair> postHeader) {
		this.postHeader = postHeader;
	}

	public ArrayList<HTTPSimpleDataPair> getPostParams() {
		return postParams;
	}

	public void setPostParams(ArrayList<HTTPSimpleDataPair> postParams) {
		this.postParams = postParams;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	
	
	
}
