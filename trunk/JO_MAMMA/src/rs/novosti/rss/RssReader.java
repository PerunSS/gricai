package rs.novosti.rss;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import rs.novosti.model.Article;
import rs.novosti.model.Category;

public class RssReader {

	private static final String politika = new String(
			"http://novosti.rs/rss/2-Sve%20vesti");
	private static final String drustvo = new String(
			"http://novosti.rs/rss/1-Sve%20vesti");
	private static final String ekonomija = new String(
			"http://novosti.rs/rss/3-Sve%20vesti");
	private static final String hronika = new String(
			"http://novosti.rs/rss/4-Sve%20vesti");
	private static final String beograd = new String(
			"http://novosti.rs/rss/16-Sve%20vesti");
	private static final String dosije = new String(
			"http://novosti.rs/rss/5-Sve%20vesti");
	private static final String spektakl = new String(
			"http://novosti.rs/rss/10-Sve%20vesti");
	private static final String zivot_plus = new String(
			"http://novosti.rs/rss/24%7C33%7C34%7C25%7C20%7C18%7C32%7C19-Sve%20vesti");
	private static final String tehnologije = new String(
			"http://novosti.rs/rss/35-Sve%20vesti");
	private static final String auto = new String(
			"http://novosti.rs/rss/50-Sve%20vesti");
	private static final String sport = new String(
			"http://novosti.rs/rss/11%7C47%7C12%7C14%7C13-Sve%20vesti");
	// private static final String CHANNEL_TAG = "channel";
	private static final String TITLE_TAG = "title";
	private static final String LINK_TAG = "link";
	private static final String DESCRIPTION_TAG = "description";
	// private static final String LANGUAGE_TAG = "language";
	// private static final String COPYRIGHT_TAG = "copyright";
	// private static final String WEB_MASTER_TAG = "webMaster";
	// private static final String CATEGORY_TAG = "category";
	// private static final String DOCS_TAG = "docs";
	// private static final String TTL_TAG = "ttl";
	private static final String ITEM_TAG = "item";
	// private static final String GUID_TAG = "guid";
	private static final String ENCLOSURE_TAG = "enclosure";
	private static final String CONTENT_TAG = "content:encoded";
	private static final String PUB_DATE_TAG = "pubDate";

	private boolean itemStarted = false;

	public Category readCategory(String path, String name) {
		Category category = new Category();
		category.setName(name);
		category.setPath(path);
		Map<Integer, Article> articles = new TreeMap<Integer, Article>();
		int key = -1;
		try {
			URL url = new URL(path);
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(url.openStream(), "UTF-8");
			int parserEvent = parser.getEventType();
			String tag = "";
			Article article = null;
			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG:
					tag = parser.getName();

					if (tag.equalsIgnoreCase(ITEM_TAG)) {
						itemStarted = true;
						key++;
						article = new Article();
						articles.put(key, new Article());
					}
					if (itemStarted && article != null) {
						if (tag.equalsIgnoreCase(ENCLOSURE_TAG)) {
							article.setPhotoPath(parser.getAttributeValue(null,
									"url"));
							String length = parser.getAttributeValue(null,
									"length");
							int imageLength = -1;
							try {
								imageLength = Integer.parseInt(length);
							} catch (Exception e) {
								e.printStackTrace();
							}
							article.setImageLength(imageLength);
						}
					}
					break;
				case XmlPullParser.END_TAG:
					tag = parser.getName();
					if (tag.equalsIgnoreCase(ITEM_TAG)) {
						itemStarted = false;
						articles.put(key, article);
						article = null;
					}
					break;
				case XmlPullParser.TEXT:
					String text = parser.getText();
					if (key >= 0 && itemStarted && article != null)
						if (tag.length() > 0) {
							if (text.trim().length() > 0)
								if (tag.equalsIgnoreCase(TITLE_TAG)) {
									article.setName(text);
								} else if (tag.equalsIgnoreCase(LINK_TAG)) {
									article.setSource(text);
								} else if (tag
										.equalsIgnoreCase(DESCRIPTION_TAG)) {
									article.setShortText(text);
									String oneLine = article.getName()+": "+article.getShortText();
									article.setOneLine(oneLine.substring(0,40)+"...");
								} else if (tag.equalsIgnoreCase(PUB_DATE_TAG)) {
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
								} else if (tag.equalsIgnoreCase(CONTENT_TAG)) {
									article.setText(text);
								}
						}
					break;
				}
				parserEvent = parser.next();
			}
			for (Map.Entry<Integer, Article> entry : articles.entrySet()) {
				
				category.getArticles().add(entry.getValue());
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
