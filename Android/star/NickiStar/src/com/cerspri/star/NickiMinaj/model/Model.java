package com.cerspri.star.NickiMinaj.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

public class Model {

	private static Model instance = new Model();
	private Map<String, Integer> currents;
	private Map<String, List<String>> texts;
	private Map<Integer, News> news;
	private Map<String, Integer> numberOfChanges;
	private Map<Integer, Video> videos;
	private int lastVideoID = 0;
	
	private Model() {
	}

	public void createMaps() {
		if (currents == null) {
			currents = new HashMap<String, Integer>();
			currents.put("fact", 0);
			currents.put("quote", 0);
		}
		if (texts == null)
			texts = new HashMap<String, List<String>>();
		if (numberOfChanges == null)
			numberOfChanges = new HashMap<String, Integer>();
		if (news == null)
			news = new HashMap<Integer, News>();
		if (videos == null)
			videos = new HashMap<Integer, Video>();
	}

	public static Model getInstance() {
		return instance;
	}

	public String getNext(String type) {
		int index = (currents.get(type) + 1) % texts.get(type).size();
		currents.put(type, index);
		return texts.get(type).get(index);
	}

	public void loadNews(int page, String name) {
		if (news == null) {
			news = new TreeMap<Integer, News>();
		}
		StringBuilder builder = readFromLink("http://www.google.com/search?q="
				+ name.toLowerCase().replace(' ', '+')
				+ "&hl=en&prmd=imvnso&source=lnms&tbm=nws");
		boolean hasNext = true;
		int linkIndex = 0;
		int index = page * News.PAGE_SIZE;
		while (hasNext) {

			linkIndex = builder.indexOf(News.NEWS_LINK_START, linkIndex + 1);
			News holder = news.get(index);
			if (holder == null) {
				holder = new News();
			}
			boolean shouldAdd = false;
			if (linkIndex > 0) {
				int endIndex = builder.indexOf(News.NEWS_LINK_END, linkIndex);
				shouldAdd = holder.extractLink(builder.substring(linkIndex,
						endIndex));
				int textIndex = builder
						.indexOf(News.NEWS_TEXT_START, linkIndex);
				if (textIndex > 0 && shouldAdd) {
					endIndex = builder.indexOf(News.NEWS_TEXT_END, textIndex);
					shouldAdd = holder.extractText(builder.substring(textIndex,
							endIndex));
				} else {
					hasNext = false;
				}
			} else {
				hasNext = false;
			}
			if (shouldAdd) {
				news.put(index, holder);
				index++;
			}
		}
	}

	public void loadVideos(Integer lastID, String name) {
		System.out.println("lastid: "+lastID);
		if (videos == null) {
			videos = new HashMap<Integer, Video>();
		}
		StringBuilder builder = readFromLink("http://www.cerspri.com/api/stars/get_videos.php?star="
				+ name.toLowerCase().replace(' ', '+') + "&last=" + lastID);
		try {
			JSONObject jsonobj = new JSONObject(builder.toString());
			JSONArray elements = jsonobj.getJSONArray("data");
			for (int i = 0; i < elements.length(); i++) {
				JSONObject elementobj = elements.getJSONObject(i);
				String tag = elementobj.getString("video_tag");
				int id = elementobj.getInt("video_number");
				if(id > lastVideoID){
					lastVideoID = id;
				}
				Video holder = new Video();
				holder.setId(id);
				holder.extractFromTag(tag);
				videos.put(holder.getId(), holder);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public boolean loadData(String type, String name, int version) {
		numberOfChanges.clear();
		List<String> data = texts.get(type);
		if(data == null){
			data = new ArrayList<String>();
		}
		StringBuilder builder = readFromLink("http://www.cerspri.com/api/stars/get_data.php?"
				+ "star="
				+ name.replaceAll(" ", "%20")
				+ "&text_type="
				+ type
				+ "&version=" + version);
		boolean changed = false;
		try {
			JSONObject jsonobj = new JSONObject(builder.toString());
			JSONArray elements = jsonobj.getJSONArray("data");
			numberOfChanges.put(type, elements.length());
			for (int i = 0; i < elements.length(); i++) {
				JSONObject elementobj = elements.getJSONObject(i);
				if (elementobj.getString("text_type").equalsIgnoreCase(type)) {
					data.add(elementobj.getString("text_value"));
					if (version < elementobj.getInt("text_version")) {
						version = elementobj.getInt("text_version");
					}
					changed = true;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Collections.shuffle(data);
		texts.put(type, data);
		return changed;
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
						new InputStreamReader(content),8192);
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

	public Map<Integer, News> getNews() {
		return news;
	}

	public Map<String, Integer> getCurrents() {
		return currents;
	}

	public Map<String, List<String>> getTexts() {
		return texts;
	}

	public Map<String, Integer> getNumberOfChanges() {
		return numberOfChanges;
	}

	public Map<Integer, Video> getVideos() {
		return videos;
	}

	public int getLastVideoID() {
		return lastVideoID;
	}

	public void setLastVideoID(int lastVideoID) {
		this.lastVideoID = lastVideoID;
		System.out.println(lastVideoID+" set");
	}

}
