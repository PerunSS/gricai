package com.gricai.central.server.dbManager.exception;

import java.sql.SQLException;

public class HavingWithoutGroupByException extends SQLException{
	
		private static final long serialVersionUID = 1L;
		
		public HavingWithoutGroupByException( String reason){
			super(reason);
		}
}
