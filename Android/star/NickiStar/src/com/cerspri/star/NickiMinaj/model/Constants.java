package com.cerspri.star.NickiMinaj.model;

import com.cerspri.star.NickiMinaj.NickiStarActivity;

/**
 * this class is used to change all necessary data for new application
 * @author aleksandarvaricak
 *
 */
public class Constants {

	/**
	 * change to main activity class name
	 */
	public static final Class<?> APPLICATION = NickiStarActivity.class;
	public static final String DB_NAME = "NickiMinaj";
	public static final String TABLE_NAME = "Nicki_Minaj";
	public static final String PROJECT_PATH = "com.cerspri.star."+DB_NAME;
	public static final boolean REWRITE_DB = false;
	public static final String LIMIT = "300";
	public static final String APPLICATION_QUERY="details?id=com.cerspri.star."+DB_NAME;
	public static final String DEVELOPER_QUERY="search?q=cerspri.star";
	public static final String STAR_NAME = "Nicki Minaj";
}
