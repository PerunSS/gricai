package com.client.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBBasicHandler extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "client_db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS tblClient(clientID INT, applicationID INT, clientPlatformID INT)";
	private static final String DATABASE_UPGRADE = "DROP TABLE IF EXISTS tblClient";
	
	
	public DBBasicHandler(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DATABASE_UPGRADE);
		onCreate(db);
		
	}
	

}
