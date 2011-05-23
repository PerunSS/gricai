package rs.novosti.rss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import rs.novosti.model.Article;
import rs.novosti.model.Category;
import rs.novosti.model.Naslovna;

public class RssReader {

	private static final String naslovna = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/novosti.php");

	private static final String politika = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/2-Sve%20vesti");
	private static final String drustvo = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/1-Sve%20vesti");
	private static final String ekonomija = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/3-Sve%20vesti");
	private static final String hronika = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/4-Sve%20vesti");
	private static final String beograd = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/16-Sve%20vesti");
	private static final String dosije = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/5-Sve%20vesti");
	private static final String spektakl = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/10-Sve%20vesti");
	private static final String zivot_plus = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/24%7C33%7C34%7C25%7C20%7C18%7C32%7C19-Sve%20vesti");
	private static final String tehnologije = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/35-Sve%20vesti");
	private static final String auto = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/50-Sve%20vesti");
	private static final String sport = new String(
			"http://service.tehnicomsolutions.co.uk/novosti/rss/url2xml.php?url=http://novosti.rs/rss/11%7C47%7C12%7C14%7C13-Sve%20vesti");

	private static final String TITLE_TAG = "title";
	private static final String LINK_TAG = "link";
	private static final String DESCRIPTION_TAG = "description";
	private static final String IMAGE_TAG = "image";
	private static final String SHORT_TEXT_TAG="shortText";
	private static final String ITEM_TAG = "item";

	private static final String PUB_DATE_TAG = "pubDate";

	private boolean itemStarted = false;

	public Naslovna readNaslovna() {
		Naslovna naslovna = new Naslovna();
		try {
			URL url = new URL(RssReader.naslovna);
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			
			StringBuffer buffer = new StringBuffer();
			BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"), 64 * 1024);
			String line;
			while((line = stream.readLine())!=null){
				buffer.append(line);
			}
			stream.close();
			String result = new String(buffer.toString().getBytes(),"UTF-8");
//			result = result.replaceAll("&", "&amp;");
			parser.setInput(new StringReader(result));
			int parserEvent = parser.getEventType();
			String tag = "";
			Article article = null;
			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG:
					tag = parser.getName();
					if (tag.equalsIgnoreCase(ITEM_TAG)) {
						itemStarted = true;
						article = new Article();
					}
					break;
				case XmlPullParser.TEXT:
					if (itemStarted && article != null && tag.length() > 0) {
						String text = parser.getText();
						text = text.trim();
						if(text.length()>0){
							if (tag.equalsIgnoreCase(PUB_DATE_TAG)) {
								String date = text;
								Date dateValue = null;
								try {
									dateValue = new Date(Date.parse(date));
								} catch (Exception e) {
									e.printStackTrace();
								}
								if (dateValue != null) {
									article.setDate(dateValue);
								}
							} else if (tag.equalsIgnoreCase(TITLE_TAG)) {
								article.setTitle(text);
							} else if (tag.equalsIgnoreCase(LINK_TAG)) {
								article.setLink(text);
							} else if (tag.equalsIgnoreCase(IMAGE_TAG)) {
								article.setSmallPhotoPath(text);
							}
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if (itemStarted && article != null && tag.length() > 0) {
						tag = parser.getName();
						if(tag.equalsIgnoreCase(ITEM_TAG)){
							itemStarted = false;
							naslovna.getArticles().add(article);
							article = null;
						}
					}
					break;
				}
				parserEvent = parser.next();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return naslovna;
	}

	public Category readCategory(String path, String name) {
		Category category = new Category();
		category.setTitle(name);
		category.setPath(path);
		try {
			URL url = new URL(path);
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			StringBuffer buffer = new StringBuffer();
			BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"), 64 * 1024);
			String line;
			while((line = stream.readLine())!=null){
				buffer.append(line);
			}
			stream.close();
			String result = new String(buffer.toString().getBytes(),"UTF-8");
//			result = result.replaceAll("&", "&amp;");
			parser.setInput(new StringReader(result));
			int parserEvent = parser.getEventType();
			String tag = "";
			Article article = null;
			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG:
					tag = parser.getName();
					if (tag.equalsIgnoreCase(ITEM_TAG)) {
						itemStarted = true;
						article = new Article();
					}
					break;
				case XmlPullParser.END_TAG:
					if (itemStarted && article != null && tag.length() > 0) {
						tag = parser.getName();
						if(tag.equalsIgnoreCase(ITEM_TAG)){
							itemStarted = false;
							category.getArticles().add(article);
							article = null;
						}
					}
					break;
				case XmlPullParser.TEXT:
					if (itemStarted && article != null && tag.length() > 0) {
						String text = parser.getText();
						text = text.trim();
						if(text.length()>0){
							if (tag.equalsIgnoreCase(PUB_DATE_TAG)) {
								String date = text;
								Date dateValue = null;
								try {
									dateValue = new Date(Date.parse(date));
								} catch (Exception e) {
									e.printStackTrace();
								}
								if (dateValue != null) {
									article.setDate(dateValue);
								}
							} else if (tag.equalsIgnoreCase(TITLE_TAG)) {
								article.setTitle(text);
							} else if (tag.equalsIgnoreCase(LINK_TAG)) {
								article.setLink(text);
							} else if (tag.equalsIgnoreCase(IMAGE_TAG)) {
								article.setSmallPhotoPath(text);
							} else if (tag.equalsIgnoreCase(DESCRIPTION_TAG)) {
								article.setDescription(text);
							}
						}
					}
					break;
				}
				parserEvent = parser.next();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return category;
	}

	public Article readArticle(Article article) {
		if (article.getText() != null && article.getText().length() > 0)
			return article;
		URL url;
		try {
			url = new URL(article.getLink());
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			StringBuffer buffer = new StringBuffer();
			BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"), 64 * 1024);
			String line;
			while((line = stream.readLine())!=null){
				buffer.append(line);
			}
			stream.close();
			String result = new String(buffer.toString().getBytes(),"UTF-8");
//			result = result.replaceAll("&", "&amp;");
			int parserEvent = parser.getEventType();
			parser.setInput(new StringReader(result));
			String tag = "";
			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG:
					tag = parser.getName();
					break;
				case XmlPullParser.TEXT:
					if (article != null && tag.length() > 0) {
						String text = parser.getText();
						text = text.trim();
						if(text.length()>0)
							if (tag.equalsIgnoreCase(TITLE_TAG)) {
								article.setTitle(text);
							} else if (tag.equalsIgnoreCase(SHORT_TEXT_TAG)) {
								article.setShortText(text);
							} else if (tag.equalsIgnoreCase(IMAGE_TAG)) {
								article.setPhotoPath(text);
							} else if (tag.equalsIgnoreCase(DESCRIPTION_TAG)){
								article.setText(text);
							}
					}
					break;
				}
				parserEvent = parser.next();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return article;

	}

	public Category readPolitika() {
		return readCategory(politika, "Politika");
	}

	public Category readDrustvo() {
		return readCategory(drustvo, "Društvo");
	}

	public Category readEkonomija() {
		return readCategory(ekonomija, "Ekonomija");
	}

	public Category readHronika() {
		return readCategory(hronika, "Hronika");
	}

	public Category readBeograd() {
		return readCategory(beograd, "Beograd");
	}

	public Category readDosije() {
		return readCategory(dosije, "Dosije");
	}

	public Category readSpektakl() {
		return readCategory(spektakl, "Spektakl");
	}

	public Category readZivotPlus() {
		return readCategory(zivot_plus, "Život plus");
	}

	public Category readTehnologije() {
		return readCategory(tehnologije, "Tehnologije");
	}

	public Category readAuto() {
		return readCategory(auto, "Auto");
	}

	public Category readSport() {
		return readCategory(sport, "Sport");
	}
	
}
