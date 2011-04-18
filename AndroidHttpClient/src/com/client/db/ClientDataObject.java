package com.client.db;

public class ClientDataObject
{
	int clientID, applicationID,clientPlatformID;
	
	public ClientDataObject(int clientID,int applicationID, int clientPlatformID)
	{
		this.clientID = clientID;
		this.applicationID = applicationID;
		this.clientPlatformID = clientPlatformID;
	}
	public ClientDataObject()
	{
		clientID=-1;
		applicationID = -1;
		clientPlatformID = -1;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public int getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}
	public int getClientPlatformID() {
		return clientPlatformID;
	}
	public void setClientPlatformID(int clientPlatformID) {
		this.clientPlatformID = clientPlatformID;
	}
	
	
}
