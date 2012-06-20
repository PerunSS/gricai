package com.cerspri.star.NickiMinaj.db;

import java.util.ArrayList;
import java.util.List;

import com.cerspri.star.NickiMinaj.model.Constants;
import com.cerspri.star.NickiMinaj.model.Video;

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
		String params [] = {type};
		Cursor c = adapter.executeSql(sql, params);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String text = c.getString(c.getColumnIndex("text_value"));
					// int id = c.getInt(c.getColumnIndex("_id"));
					// boolean fav = false;
					// try {
					// fav = c.getInt(c.getColumnIndex("fav")) == 1 ? true
					// : false;
					// } catch (Exception e) {
					// fav = false;
					// }
					result.add(text);
				} while (c.moveToNext());
			}
		}
		c.close();
		adapter.close();
		return result;
	}
	
	public List<Video> readVideos(){
		adapter.openDataBase();
		List<Video> result = new ArrayList<Video>();
		String sql = "select * from videos";
		Cursor c = adapter.executeSql(sql, null);
		if(c!=null){
			if(c.moveToFirst()){
				do {
					Video video = new Video();
					video.setVideoTag(c.getString(c.getColumnIndex("video_tag")));
					video.setDescription(c.getString(c.getColumnIndex("video_description")));
					video.setTitle(c.getString(c.getColumnIndex("video_title")));
					video.setImagePath(c.getString(c.getColumnIndex("video_image_path")));
					result.add(video);
				}while(c.moveToNext());
			}
		}
		c.close();
		adapter.close();
		return result;
	}

	// public void updateText(Text textObj) {
	// adapter.openDataBase();
	// String sql = "update all_text set fav = "
	// + (textObj.isFavorite ? 1 : 0) + " where _id = " + textObj.id;
	// adapter.update(sql);
	// adapter.close();
	// }
}
