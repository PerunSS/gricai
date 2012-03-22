package com.funforall.template;
/**
 * this class is used to change all necessary data for new application
 * @author aleksandarvaricak
 *
 */
public class Constants {

	/**
	 * change to main activity class name
	 */
	public static final Class<?> APPLICATION = AppTemplateActivity.class;
	public static final String DB_NAME = "template";
	public static final String PROJECT_PATH = "com.funforall."+DB_NAME;
	public static final boolean REWRITE_DB = true;
	public static final String LIMIT = "100";
	public static final String APPLICATION_QUERY="details?id=com.funforall.template";
	public static final String DEVELOPER_QUERY="search?q=funforall.perun";
}
