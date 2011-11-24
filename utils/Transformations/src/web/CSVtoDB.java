package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CSVtoDB {

	CSVtoDB () throws ClassNotFoundException, SQLException{
		 Class.forName("org.sqlite.JDBC");
		 Connection conn =
		      DriverManager.getConnection("jdbc:sqlite:cocktails.db");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new CSVtoDB();
	}
}
