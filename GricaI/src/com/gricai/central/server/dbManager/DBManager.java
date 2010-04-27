package com.gricai.central.server.dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
/*	
	private static final String DB_LOCATION = "localhost:xxxx/db_gricai";
	private static final String USERNAME = "";
	private static final String password = "";
	*/
// ja bi da u konnekt uzima koja je baza user u pass cisto ako imamo vishe baza da ne mora za svaku poseban connect
	
	private static DBManager instance = new DBManager();

	private DBManager(){
	}
	
	public static DBManager getInstance(){
		return instance;
	}
	
	public Connection connect ( String DB_LOCATION, String USERNAME, String password){
        try{
            Class c = Class.forName("com.mysql.jdbc.Driver");
            c.newInstance();
            return DriverManager.getConnection(DB_LOCATION,USERNAME,password);
        } catch ( Exception ex){
          throw new RuntimeException("DB connection failure");
        }
    }
	
	//TODO finish this
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
