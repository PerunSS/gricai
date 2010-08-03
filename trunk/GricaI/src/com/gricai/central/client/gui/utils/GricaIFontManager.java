package com.gricai.central.client.gui.utils;

import java.awt.Font;

//TODO (add map for font sizes)
public final class GricaIFontManager {

	public static final int VGA_FONT_SIZE = 12;
	public static final int SVGA_FONT_SIZE = 14;
	public static final int XGA_FONT_SIZE = 16;
	public static final int XGA_PLUS_FONT_SIZE = 18;
	public static final int WXGA_2_FONT_SIZE = 16;
	public static final int WXGA_FONT_SIZE = 17;
	public static final int XSGA_2_FONT_SIZE = 19;
	public static final int XSGA_FONT_SIZE = 20;
	public static final int HD_FONT_SIZE = 16;
	public static final int WSXGA_FONT_SIZE = 19;
	public static final int HD_PLUS_FONT_SIZE = 19;
	public static final int UXGA_FONT_SIZE = 22;
	public static final int WSXGA_PLUS_FONT_SIZE = 20;
	public static final int HD_FULL_FONT_SIZE = 20;
	public static final int WUXGA_FONT_SIZE = 22;
	
	private static final String verdana = "verdana";
	private static final String arial = "arial";
	private static final String tahoma = "tahoma";
	
	private static Font font = Font.decode("verdana-16");
	private static int size;
	private static String family;
	private static String style;
	
	public static void setFontSize(int fontSize){
		size = fontSize;
		createFont();
	}
	
	public static void verdana(){
		family = verdana;
		createFont();
	}
	
	public static void arial(){
		family = arial;
		createFont();
	}
	
	public static void tahoma(){
		family = tahoma;
		createFont();
	}

	public static void bold(){
		style = "BOLD";
		createFont();
	}
	
	public static void italic(){
		style = "ITALIC";
		createFont();
	}
	
	public static void plain(){
		style = "PLAIN";
		createFont(); 
	}
	
	public static void bolditalic(){
		style = "BOLDITALIC";
		createFont();
	}
	
	public static void normal(){
		style = null;
		createFont();
	}
	
	private static void createFont(){
		if(size == 0)
			size = 16;
		if(family == null)
			family = verdana;
		String createString = family;
		if(style!=null)
			createString+="-"+style;
		createString+="-"+size;
		font = Font.decode(createString);
	}
	
	public static Font getFont(){
		return font;
	}
}
