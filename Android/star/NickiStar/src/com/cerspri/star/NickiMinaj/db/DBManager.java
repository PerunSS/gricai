package com.cerspri.star.NickiMinaj.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cerspri.star.NickiMinaj.model.Constants;
import com.cerspri.star.NickiMinaj.model.News;

public class DBManager {

	//DBAdapter adapter;
	SQLiteDatabase db;
	Context context;

	public DBManager(Context context) {
		this.context = context;
	}

	public void saveNews(News news) {
		openDatabase();
		ContentValues values = new ContentValues();
		values.put("title", news.getTitle());
		values.put("content", news.getContent());
		values.put("pub_date", news.getPubDate());
		values.put("url", news.getNewsUrl());
		values.put("image", news.getImagePath());
		values.put("_id", news.getId());
		db.insert("news", null, values);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -20);
		db.execSQL("delete from news where pub_date<?",
				new String[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(calendar.getTime()) });
		db.close();
	}

	public List<News> readNews() {
		openDatabase();
		List<News> allNews = new ArrayList<News>();
		String sql = "select * from news order by pub_date desc";
		Cursor c = db.rawQuery(sql, null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					News news = new News();
					news.setTitle(c.getString(c.getColumnIndex("title")));
					news.setContent(c.getString(c.getColumnIndex("content")));
					news.setPubDate(c.getString(c.getColumnIndex("pub_date")));
					news.setNewsUrl(c.getString(c.getColumnIndex("url")));
					news.setImagePath(c.getString(c.getColumnIndex("image")));
					news.setId(c.getString(c.getColumnIndex("_id")));
					allNews.add(news);
				} while (c.moveToNext());
			}
		}
		c.close();
		db.close();
		return allNews;
	}
	
	private void openDatabase(){
		db = context.openOrCreateDatabase(Constants.DB_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
		db.setVersion(Constants.DB_VERSION);
		db.setLocale(Locale.getDefault());
		db.execSQL(Constants.CREATE_NEWS_TABLE);
	}

}
