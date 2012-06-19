package com.cerspri.star.NickiMinaj.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class Video implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private String imagePath;
	private int id;
	private String videoTag;
	private boolean loaded = false;

	public boolean extractFromTag(String videoTag) {
		this.videoTag = videoTag;
		try {
			URL url = new URL("http://gdata.youtube.com/feeds/api/videos/"
					+ videoTag);
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			StringBuffer buffer = new StringBuffer();
			BufferedReader stream = new BufferedReader(
					new InputStreamReader(url.openStream(), "UTF-8"),
					64 * 1024);
			String line;
			while ((line = stream.readLine()) != null) {
				buffer.append(line);
			}
			stream.close();
			String result = new String(buffer.toString().getBytes(),
					"UTF-8");
			result = result.replaceAll("&", "&amp;");
			parser.setInput(new StringReader(result));
			int parserEvent = parser.getEventType();
			String tag = "";
			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG:
					tag = parser.getName().replace(':', '_');
					if (tag.equalsIgnoreCase("media_thumbnail")) {
						if (imagePath == null) {
							imagePath = parser.getAttributeValue(null,
									"url");
						}
					}
					break;
				case XmlPullParser.TEXT:
					if (tag.equalsIgnoreCase("title")) {
						title = parser.getText();
					} else if (tag.equalsIgnoreCase("content")) {
						description = parser.getText();
						if(description.length()>150){
							description = description.substring(0,150)+"...";
						}
					}
					break;
				}
				parserEvent = parser.next();
			}
			loaded = true;
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideoTag() {
		return videoTag;
	}
	
	public void setVideoTag(String videoTag) {
		this.videoTag=videoTag;
	}
	
	@Override
	public String toString() {
		return id+" "+videoTag+" "+title+" "+description+" "+imagePath;
	}

	public boolean isLoaded() {
		return loaded;
	}
}
