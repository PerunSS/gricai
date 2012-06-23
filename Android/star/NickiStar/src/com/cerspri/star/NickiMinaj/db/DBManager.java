package com.cerspri.star.NickiMinaj.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.cerspri.star.NickiMinaj.model.Constants;
import com.cerspri.star.NickiMinaj.model.News;
import com.cerspri.star.NickiMinaj.model.Video;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBManager {

	DBAdapter adapter;

	public DBManager(Context context) {
		adapter = new DBAdapter(context, Constants.DB_NAME,
				Constants.PROJECT_PATH);
	}

	public List<String> read(boolean rated, String type) {
		adapter.openDataBase();
		List<String> result = new ArrayList<String>();
		String sql = "select * from " + Constants.TABLE_NAME
				+ " where text_type = ?";
		if (!rated)
			sql += " limit " + Constants.LIMIT;
		String params[] = { type };
		Cursor c = adapter.executeSql(sql, params);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String text = c.getString(c.getColumnIndex("text_value"));
					result.add(text);
				} while (c.moveToNext());
			}
		}
		c.close();
		adapter.close();
		return result;
	}

	public List<Video> readVideos() {
		adapter.openDataBase();
		List<Video> result = new ArrayList<Video>();
		String sql = "select * from videos";
		Cursor c = adapter.executeSql(sql, null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					Video video = new Video();
					video.setVideoTag(c.getString(c.getColumnIndex("video_tag")));
					video.setDescription(c.getString(c
							.getColumnIndex("video_description")));
					video.setTitle(c.getString(c.getColumnIndex("video_title")));
					video.setImagePath(c.getString(c
							.getColumnIndex("video_image_path")));
					result.add(video);
				} while (c.moveToNext());
			}
		}
		c.close();
		adapter.close();
		return result;
	}

	public void saveNews(News news) {
		adapter.openDataBase();
		ContentValues values = new ContentValues();
		values.put("title", news.getTitle());
		values.put("content", news.getContent());
		values.put("pub_date", news.getPubDate());
		values.put("url", news.getNewsUrl());
		values.put("image", news.getImagePath());
		values.put("news_id", news.getId());
		adapter.insert("news", null, values);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -20);
		adapter.executeSql("delete from news where pub_date<?",
				new String[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(calendar.getTime()) });
		adapter.close();
	}

	public List<News> readNews() {
		List<News> allNews = new ArrayList<News>();
		adapter.openDataBase();
		String sql = "select * from news order by pub_date desc";
		Cursor c = adapter.executeSql(sql, null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					News news = new News();
					news.setTitle(c.getString(c.getColumnIndex("title")));
					news.setContent(c.getString(c.getColumnIndex("content")));
					news.setPubDate(c.getString(c.getColumnIndex("pub_date")));
					news.setNewsUrl(c.getString(c.getColumnIndex("url")));
					news.setImagePath(c.getString(c.getColumnIndex("image")));
					news.setId(c.getString(c.getColumnIndex("news_id")));
					allNews.add(news);
				} while (c.moveToNext());
			}
		}
		c.close();
		adapter.close();
		return allNews;
	}

}
