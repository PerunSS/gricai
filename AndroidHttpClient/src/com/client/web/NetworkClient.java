package com.client.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.client.web.beans.HTTPConnectionPostRequest;

import android.os.AsyncTask;
import android.os.Build;

public class NetworkClient
{
	private String server_addr,server_req_target;
	BufferedReader br ;
	NetworkConnection  connection;
	public NetworkClient(NetworkConnection connection)
	{
		this.connection = connection;
	}

	
	public String executePost()
	{
		
		connection.doPost(new HTTPConnectionPostRequest().createPostRequest(null,null, true));
		
		return "done";
	}
	
	
	
	public String sendInitialRequest()
	{
		return "";
	}


	
}
