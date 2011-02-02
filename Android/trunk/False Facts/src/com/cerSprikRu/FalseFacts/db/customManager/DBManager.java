package com.cerSprikRu.FalseFacts.db.customManager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.cerSprikRu.FalseFacts.db.DBAdapter;
import com.cerSprikRu.FalseFacts.fact.Fact;

public class DBManager {

	private static final String dbName = "FalseFacts";
	private static final String projectPath = "com.cerSprikRu.FalseFacts";
	DBAdapter adapter;

	public DBManager(Context context) {
		adapter = new DBAdapter(context, dbName, projectPath);
	}

	public List<Fact> read() {
		adapter.openDataBase();
		List<Fact> result = new ArrayList<Fact>();
		
		Cursor c = adapter.executeSql("select * from AllFacts where type = 1 limit 250", null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String text = c.getString(c.getColumnIndex("fact")).trim();
					int id = c.getInt(c.getColumnIndex("_id"));
					boolean fav = false;
					try{
						fav = c.getInt(c.getColumnIndex("fav"))==1?true:false;
					}catch (Exception e) {
						fav = false;
					}
					Fact fact = new Fact(text, fav, id);
					result.add(fact);
				} while (c.moveToNext());
			}
		}
		c.close();
		adapter.close();
		return result;
	}
	
	public void updateFact(Fact fact){
		adapter.openDataBase();
		String sql = "update AllFacts set fav = "+(fact.isFavorite()?1:0)+" where _id = "+fact.getId();
		adapter.update(sql);
		adapter.close();
	}
}
