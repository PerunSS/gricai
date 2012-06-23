package com.cerspri.star.NickiMinaj.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cerspri.star.NickiMinaj.db.DBManager;

public class Model {

	private static Model instance = new Model();
	private int currentQuote = 0;
	private int currentFact = 0;
	private List<String> quotes;
	private List<String> facts;
	private List<News> news;
	private List<Video> videos;
	private int currentVideo = 0;
	private int version = 0;
	private int newNews = 0;
	private int currentNews = 0;
	private DBManager manager;

	private Model() {
	}

	public static Model getInstance() {
		return instance;
	}

	public String nextQuote() {
		if (quotes.size() > 0) {
			currentQuote %= quotes.size();
			return quotes.get(currentQuote++);
		}
		return "";
	}

	public String currentQuote() {
		if (quotes.size() > 0)
			return quotes.get((currentQuote - 1 + quotes.size())
					% quotes.size());
		return "";
	}

	public String nextFact() {
		if (facts.size() > 0) {
			currentFact %= facts.size();
			return facts.get(currentFact++);
		}
		return "";
	}

	public String currentFact() {
		if (facts.size() > 0)
			return facts.get((currentFact - 1 + facts.size()) % facts.size());
		return "";
	}

	public Video nextVideo() {
		if (videos.size() > 0) {
			currentVideo++;
			currentVideo %= videos.size();
			return videos.get(currentVideo);
		}
		return null;
	}

	public Video currentVideo() {
		if (videos.size() > 0)
			return videos.get(currentVideo);
		return null;
	}

	public void putCurrentVideo(Video video) {
		videos.remove(currentVideo);
		videos.add(currentVideo, video);
	}

	public Video previousVideo() {
		if (videos.size() > 0) {
			currentVideo--;
			if (currentVideo < 0) {
				currentVideo += videos.size();
			}
			return videos.get(currentVideo);
		}
		return null;
	}

	public News currentNews() {
		if (news.size() > 0)
			return news.get(currentNews);
		return null;
	}

	public News nextNews() {
		if (news.size() > 0) {
			currentNews++;
			currentNews %= news.size();
			return news.get(currentNews);
		}
		return null;
	}

	public News prevoiusNews() {
		if (news.size() > 0) {
			currentNews--;
			if (currentNews < 0)
				currentNews += news.size();
			return news.get(currentNews);
		}
		return null;
	}

	public int loadNews(Integer version, String name) {
		this.version = version;
		if (news == null) {
			news = new ArrayList<News>();
		}
		StringBuilder builder = readFromLink("http://www.cerspri.com/api/stars/get_news.php?star="
				+ name.toLowerCase().replace(' ', '+') + "&version="+(Constants.REWRITE_DB?0:version));
		try {
			JSONObject jsonobj = new JSONObject(builder.toString());
			JSONArray elements = jsonobj.getJSONArray("data");
			newNews = elements.length();
			for (int i = 0; i < elements.length(); i++) {
				JSONObject elementobj = elements.getJSONObject(i);
				if (elementobj.getInt("version") > this.version) {
					this.version = elementobj.getInt("version");
				}
				News holder = new News();
				holder.setContent(elementobj.getString("content"));
				holder.setImagePath(elementobj.getString("image"));
				holder.setNewsUrl(elementobj.getString("url"));
				holder.setPubDate(elementobj.getString("pub_date"));
				holder.setTitle(elementobj.getString("title"));
				holder.setId(elementobj.getString("news_id"));
				news.add(holder);
				if(manager != null)
					manager.saveNews(holder);
			}
			//TODO remove
			System.out.println("NEWS FROM NET: "+news.size());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this.version;
	}

	public void loadTextData(boolean rated) {
		quotes = new ArrayList<String>();
		facts = new ArrayList<String>();
		if (manager == null)
			return;
		quotes = manager.read(rated, "quote");
		facts = manager.read(rated, "fact");
	}

	public void loadVideos() {
		videos = new ArrayList<Video>();
		if (manager == null)
			return;
		videos = manager.readVideos();
	}
	
	public void loadNews(){
		news = new ArrayList<News>();
		if(manager == null)
			return;
		news = manager.readNews();
	}

	private StringBuilder readFromLink(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content), 8192);
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder;
	}

	public List<News> getNews() {
		return news;
	}

	public void setManager(DBManager manager) {
		this.manager = manager;
	}

	public int getNewNews() {
		return newNews;
	}

	public List<Video> getVideos() {
		return videos;
	}

}
