package com.gricai.central.client.gui.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class GricaIResolutionManager {

	private static final short WIDTH_1920 = 1920;
	private static final short WIDTH_1680 = 1680;
	private static final short WIDTH_1600 = 1600;
	private static final short WIDTH_1440 = 1440;
	private static final short WIDTH_1366 = 1366;
	private static final short WIDTH_1280 = 1280;
	private static final short WIDTH_1152 = 1152;
	private static final short WIDTH_1024 = 1024;
	private static final short WIDTH_800 = 800;
	private static final short WIDTH_640 = 640;
	
	private static final short HEIGHT_1200 = 1200;
	private static final short HEIGHT_1080 = 1080;
	private static final short HEIGHT_1050 = 1050;
	private static final short HEIGHT_1024 = 1024;
	private static final short HEIGHT_960 = 960;	
	private static final short HEIGHT_900 = 900;
	private static final short HEIGHT_864 = 864;
	private static final short HEIGHT_800 = 800;
	private static final short HEIGHT_768 = 768;
	private static final short HEIGHT_600 = 600;
	private static final short HEIGHT_480 = 480;

	private static final Dimension2D WUXGA_DIM = new Dimension(WIDTH_1920, HEIGHT_1200);
	private static final Dimension2D HD_FULL_DIM = new Dimension(WIDTH_1920, HEIGHT_1080);
	private static final Dimension2D WSXGA_PLUS_DIM = new Dimension(WIDTH_1680, HEIGHT_1050);
	private static final Dimension2D UXGA_DIM = new Dimension(WIDTH_1600, HEIGHT_1200);
	private static final Dimension2D HD_PLUS_DIM = new Dimension(WIDTH_1600, HEIGHT_900);
	private static final Dimension2D WSXGA_DIM = new Dimension(WIDTH_1440, HEIGHT_900);
	private static final Dimension2D HD_DIM = new Dimension(WIDTH_1366, HEIGHT_768);
	private static final Dimension2D XSGA_DIM = new Dimension(WIDTH_1280, HEIGHT_1024);
	private static final Dimension2D XSGA_2_DIM = new Dimension(WIDTH_1280, HEIGHT_960);
	private static final Dimension2D WXGA_DIM = new Dimension(WIDTH_1280, HEIGHT_800);
	private static final Dimension2D WXGA_2_DIM = new Dimension(WIDTH_1280, HEIGHT_768);
	private static final Dimension2D XGA_PLUS_DIM = new Dimension(WIDTH_1152, HEIGHT_864);
	private static final Dimension2D XGA_DIM = new Dimension(WIDTH_1024, HEIGHT_768);
	private static final Dimension2D SVGA_DIM = new Dimension(WIDTH_800, HEIGHT_600);
	private static final Dimension2D VGA_DIM = new Dimension(WIDTH_640, HEIGHT_480);
	
	public static final String WUXGA = "WUXGA ( 1920 x 1200 )";
	public static final String HD_FULL = "FULL HD ( 1920 x 1080 )";
	public static final String WSXGA_PLUS = "WSXGA+ ( 1680 x 1050 )";
	public static final String UXGA = "UXGA ( 1600 x 1200 )";
	public static final String HD_PLUS = "HD+ ( 1600 x 900 )";
	public static final String WSXGA = "WSXGA ( 1440 x 900 )";
	public static final String HD = "HD ( 1366 x 768 )";
	public static final String XSGA = "XSGA ( 1280 x 1024 )";
	public static final String XSGA_2 = "XSGA ( 1280 x 960 )";
	public static final String WXGA = "WXGA ( 1280 x 800 )";
	public static final String WXGA_2 = "WXGA ( 1280 x 768 )";
	public static final String XGA_PLUS = "XGA+ ( 1152 x 864 )";
	public static final String XGA = "XGA ( 1024 x 768 )";
	public static final String SVGA = "SVGA ( 800 x 600 )";
	public static final String VGA = "VGA ( 640 x 480 )";
	
	private static Map<String, Dimension2D> resolutions = new HashMap<String, Dimension2D>();
	
	static {
		resolutions.put(VGA, VGA_DIM);
		resolutions.put(SVGA,SVGA_DIM);
		resolutions.put(XGA,XGA_DIM);
		resolutions.put(XGA_PLUS, XGA_PLUS_DIM);
		resolutions.put(WXGA_2,WXGA_2_DIM);
		resolutions.put(WXGA,WXGA_DIM);
		resolutions.put(XSGA_2, XSGA_2_DIM);
		resolutions.put(XSGA, XSGA_DIM);
		resolutions.put(HD, HD_DIM);
		resolutions.put(WSXGA, WSXGA_DIM);
		resolutions.put(HD_PLUS, HD_PLUS_DIM);
		resolutions.put(UXGA, UXGA_DIM);
		resolutions.put(WSXGA_PLUS, WSXGA_PLUS_DIM);
		resolutions.put(HD_FULL, HD_FULL_DIM);
		resolutions.put(WUXGA, WUXGA_DIM);
	}
	
	private static Dimension2D gameResolution;
	private static Properties props = new Properties();
	private static File propsFile = new File("gricai.properties");
	
	static {
		loadProps();
		
	}
	
	public static void setGameResolution(String desc){
		gameResolution = resolutions.get(desc);
		props.setProperty("width", String.valueOf(gameResolution.getWidth()));
		props.setProperty("height", String.valueOf(gameResolution.getHeight()));
		FileWriter writer;
		try {
			writer = new FileWriter(propsFile);
			props.store(writer, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void loadProps() {
		try{
			if(!propsFile.exists())
				propsFile.createNewFile();
			
			FileReader reader = new FileReader(propsFile);
			props.load(reader);
			int width;
			try{
				width = Integer.parseInt(props.getProperty("width"));
			}catch (Exception e) {
				width = -1;
			}
			int height;
			try{
				height = Integer.parseInt(props.getProperty("height"));
			}catch (Exception e) {
				height = -1;
			}
			if(width == -1 || height == -1)
				gameResolution = Toolkit.getDefaultToolkit().getScreenSize();
			else
				gameResolution = new Dimension(width, height);
			reader.close();
		}catch (Exception e) {
			gameResolution = Toolkit.getDefaultToolkit().getScreenSize();
		}
	}

	public static Dimension2D getGameResolution(){
		return gameResolution;
	}
	
}
