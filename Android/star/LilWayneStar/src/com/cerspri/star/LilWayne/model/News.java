package com.cerspri.star.LilWayne.model;

import java.io.Serializable;

public class News implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private String imagePath;
	private String pubDate;
	private String newsUrl;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getNewsUrl() {
		return newsUrl;
	}
	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

//	public boolean extractFromTag(String videoTag) {
//		this.videoTag = videoTag;
//		try {
//			URL url = new URL("http://gdata.youtube.com/feeds/api/videos/"
//					+ videoTag);
//			XmlPullParser parser = XmlPullParserFactory.newInstance()
//					.newPullParser();
//			StringBuffer buffer = new StringBuffer();
//			BufferedReader stream = new BufferedReader(
//					new InputStreamReader(url.openStream(), "UTF-8"),
//					64 * 1024);
//			String line;
//			while ((line = stream.readLine()) != null) {
//				buffer.append(line);
//			}
//			stream.close();
//			String result = new String(buffer.toString().getBytes(),
//					"UTF-8");
//			result = result.replaceAll("&", "&amp;");
//			parser.setInput(new StringReader(result));
//			int parserEvent = parser.getEventType();
//			String tag = "";
//			while (parserEvent != XmlPullParser.END_DOCUMENT) {
//				switch (parserEvent) {
//				case XmlPullParser.START_TAG:
//					tag = parser.getName().replace(':', '_');
//					if (tag.equalsIgnoreCase("media_thumbnail")) {
//						if (imagePath == null) {
//							imagePath = parser.getAttributeValue(null,
//									"url");
//						}
//					}
//					break;
//				case XmlPullParser.TEXT:
//					if (tag.equalsIgnoreCase("title")) {
//						title = parser.getText();
//					} else if (tag.equalsIgnoreCase("content")) {
//						description = parser.getText();
//						if(description.length()>150){
//							description = description.substring(0,150)+"...";
//						}
//					}
//					break;
//				}
//				parserEvent = parser.next();
//			}
//			return true;
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (XmlPullParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}


}



//package com.cerspri.star.NickiMinaj.model;
//
//public class News {
//	static int PAGE_SIZE = 10;
//	static String NEWS_LINK_START = "<h3 class=r><a href=";
//	static String NEWS_LINK_END = "</a></h3>";
//	static String NEWS_TEXT_START = "<div class=st>";
//	static String NEWS_TEXT_END = "</div>";
//	static String NEWS_LINK_DATA_START = "class=l>";
//
//	private String link;
//	private String linkDescription;
//	private String text;
//
//	boolean extractLink(String linkData) {
//		// +1 se dodaje da se preskoce znaci navoda
//		System.out.println(linkData);
//		try {
//			int linkIndex = NEWS_LINK_START.length() + 1;
//			int endIndex = linkData.indexOf("\" class");
//			link = linkData.substring(linkIndex, endIndex);
//			int linkDescIndex = linkData.indexOf(NEWS_LINK_DATA_START);
//			linkDescIndex += NEWS_LINK_DATA_START.length();
//			linkDescription = linkData.substring(linkDescIndex).replaceAll(
//					"em>", "b>");
//			return true;
//		} catch (IndexOutOfBoundsException exception) {
//			return false;
//		}
//	}
//
//	boolean extractText(String textData) {
//		try {
//			int textStartIndex = NEWS_TEXT_START.length();
//			text = textData.substring(textStartIndex).replaceAll("em>", "b>");
//			return true;
//		} catch (IndexOutOfBoundsException exception) {
//			return false;
//		}
//	}
//
//	@Override
//	public String toString() {
//		return "(" + link + ", " + linkDescription + ", " + text + ")";
//	}
//
//	public String getLink() {
//		return link;
//	}
//
//	public void setLink(String link) {
//		this.link = link;
//	}
//
//	public String getLinkDescription() {
//		return linkDescription;
//	}
//
//	public void setLinkDescription(String linkDescription) {
//		this.linkDescription = linkDescription;
//	}
//
//	public String getText() {
//		return text;
//	}
//
//	public void setText(String text) {
//		this.text = text;
//	}
//}
