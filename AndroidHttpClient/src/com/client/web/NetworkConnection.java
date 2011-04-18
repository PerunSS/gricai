package com.client.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.http.message.BasicNameValuePair;

import com.client.web.beans.HTTPConnectionPostRequest;
import com.client.web.interfaces.RequestMethodHandler;

public class NetworkConnection implements RequestMethodHandler {
	
	HttpURLConnection connection;
	String serverBaseIPAddr;
	String pageRelativePath;
	BufferedReader br;
	public NetworkConnection(String serverBaseIPAddr,String pageRelativePath)
	{
		this.serverBaseIPAddr = serverBaseIPAddr;
		this. pageRelativePath = pageRelativePath;
	}
	
	public void connect(String serverBaseIPAddr,String pageRelativePath)
	{
		try {
			URL url = new URL(serverBaseIPAddr+pageRelativePath);
			connection = (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String doPost(HTTPConnectionPostRequest pReq)
	{
		connect(serverBaseIPAddr, pageRelativePath);
		try {
			
			connection.setRequestMethod(pReq.getMethod());
			for (int i =0;i<pReq.getPostHeader().size();i++)
			{
				connection.setRequestProperty(pReq.getPostHeader().get(i).getHeaderField(), pReq.getPostHeader().get(i).getFieldValue());
			}
			// ne posalje header ako se ovo odradi pre req,jbno logicno.
//			connection.setDoOutput(true);
			String line="";
			
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line=br.readLine())!=null)
				;
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		disconnect();
		return "";
	}
	public HttpURLConnection getConnection() {
		return connection;
	}

	public void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	public String getServerBaseIPAddr() {
		return serverBaseIPAddr;
	}

	public void setServerBaseIPAddr(String serverBaseIPAddr) {
		this.serverBaseIPAddr = serverBaseIPAddr;
	}

	public String getPageRelativePath() {
		return pageRelativePath;
	}

	public void setPageRelativePath(String pageRelativePath) {
		this.pageRelativePath = pageRelativePath;
	}

	public void disconnect()
	{
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.disconnect();
	}

	@Override
	public String doGet() {
		// TODO Auto-generated method stub
		return null;
	}
}
