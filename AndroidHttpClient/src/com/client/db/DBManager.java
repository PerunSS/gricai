package com.client.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class DBManager 
{

	private DBBasicHandler handler;
	private static SQLiteDatabase db;
	private Context context;
	private SQLiteStatement stmntInsert,stmntSelect;
	private String insertAppID;
	private ClientDataObject clientInfo;
	
	public DBManager(Context context)
	{
		this.context = context;
		clientInfo= new ClientDataObject();
		handler= new DBBasicHandler(this.context);
		db = handler.getWritableDatabase();
		insertAppID = "insert into tblClient (applicationID) values ";
		
		
//		stmntInsert=db.compileStatement();
	
//		stmntSelect = db.compileStatement("select applicationID,clientID,applicationID from tblClient");
	}
	public boolean clientDataExists()
	{
		Cursor c = this.db.query("tblClient", new String[]{"clientID","applicationID","clientPlatformID",}, null, null, null, null, null,null);
		if(c.moveToFirst())
		{
			try
			{
				clientInfo = new ClientDataObject(c.getInt(0),c.getInt(1),c.getInt(2));
			}catch(NullPointerException e)
			{
				return false;
			}
			finally {c.close();}
		}else return false;
		c.close();
		return true;
	}
	
	
	public ClientDataObject getClientInfo() {
		return clientInfo;
	}
	public void setClientInfo(ClientDataObject clientInfo) {
		this.clientInfo = clientInfo;
	}
	
}
