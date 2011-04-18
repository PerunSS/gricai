package com.client.web.beans.requests;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BasicNameValuePair;

import android.os.Build;

import com.client.web.interfaces.ExecutableHttpRequest;


public abstract class AbstractRequest implements ExecutableHttpRequest
{
	String resourceID,applicationID,clientID,resourceTypeID,relativeResourceLocation,requestID;
	HttpPost httpRequest;
	HttpResponse httpResponse;
	String method;
	String serverAddress;
	HttpClient client;
	public AbstractRequest(String serverAddress)
	{
		this.serverAddress = serverAddress;
	}
	
	public HttpPost createDefaultPostRequest(String requestID,String resourceID,String applicationID, String clientID,String resourceTypeID,String relativeResourceLocation) 
	{
		this.resourceID = resourceID;
		this.applicationID = applicationID;
		this.clientID= clientID;
		this.resourceTypeID = resourceTypeID;
		this.relativeResourceLocation = relativeResourceLocation;
		this.requestID = requestID;
		httpRequest = new HttpPost(serverAddress+relativeResourceLocation);
		httpRequest.addHeader(new BasicHeader("user-agent", "Android-"+Build.VERSION.RELEASE));
//		httpRequest.addHeader(new BasicHeader("connection", "keep-alive"));
//		httpRequest.setHeader("host", this.serverAddress);
//		httpRequest.setHeader("user-agent", "Android-"+Build.VERSION.RELEASE);
//		httpRequest.setHeader("connection", "keep-alive");
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("REQID", requestID));
		list.add(new BasicNameValuePair("RESID", resourceID));
		list.add(new BasicNameValuePair("APPID", applicationID));
		list.add(new BasicNameValuePair("CID", clientID));
		list.add(new BasicNameValuePair("RTYPEID", resourceTypeID));
		list.add(new BasicNameValuePair("RESLOC", relativeResourceLocation));
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(list));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpRequest;
		
	}
	
	
	
}
