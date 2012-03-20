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
	public static Class<?> application = AppTemplateActivity.class;
	public static final String dbName = "template";
	public static final String projectPath = "com.funforall."+dbName;
	public static boolean rewriteDb = true;
	public static String limit = "100";
}
