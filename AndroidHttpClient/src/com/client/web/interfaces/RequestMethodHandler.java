package com.client.web.interfaces;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;


public interface RequestMethodHandler
{
	public String doPost(String url,ArrayList<BasicNameValuePair> pairs,ArrayList<BasicNameValuePair> header);
	public String doGet();
}
