package com.client.web;

import org.apache.http.HttpResponse;

import com.client.web.beans.requests.InitialValidationPostRequest;


public class NetworkClient
{
	private String server_addr,server_req_target;
	
	InitialValidationPostRequest initReq;
	public NetworkClient(String serverAddress,String relativePageLocation)
	{
		
		this.server_addr = serverAddress;
		this.server_req_target  = relativePageLocation;
		initReq = new InitialValidationPostRequest(serverAddress);
	}

	
	public String executePost()
	{
		System.out.println("SERVER ADDR : "+server_addr +server_req_target);
		HttpResponse response = initReq.executeRequest("1", "2", "3", "4", "5", server_req_target);
		response.getParams();
		System.out.println("MAIL MOTHERFUCKER!");
		return "done";
	}
	
	
	
	public String sendInitialRequest()
	{
		return "";
	}


	
}
