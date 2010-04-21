package com.gricai.central.server.dbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	private static final String DB_LOCATION = "localhost:xxxx/db_gricai";
	private static final String USERNAME = "";
	private static final String password = "";
	private static DBManager instance = new DBManager();

	private DBManager(){
		//TODO init driver, etc;
	}
	
	public static DBManager getInstance(){
		return instance;
	}
	
	//TODO finish this
	public Connection connect(){
		return null;
	}
	public final void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {}
		try{
			if(ps!=null)
				ps.close();
		}catch (SQLException e) {}
		try{
			if(rs!=null)
				rs.close();
		}catch (SQLException e) {}
	}
}
