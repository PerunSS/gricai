package com.client.web.beans.requests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;

public class InitialValidationPostRequest extends AbstractRequest
{
	
	HttpPost post;
	public InitialValidationPostRequest(String serverAddress)
	{
		super(serverAddress);
		client = new DefaultHttpClient();
		
	}

	@Override
	public HttpResponse executeRequest(String requestID,String resourceID,String applicationID, String clientID,String resourceTypeID,String relativeResourceLocation) 
	{
		post = createDefaultPostRequest(requestID,"", applicationID, clientID,"",relativeResourceLocation);
		
		HttpResponse response =null ;
		try {
			response = this.client.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
