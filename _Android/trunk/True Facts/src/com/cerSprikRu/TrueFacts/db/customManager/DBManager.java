package com.cerSprikRu.TrueFacts.db.customManager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.cerSprikRu.TrueFacts.db.DBAdapter;
import com.cerSprikRu.TrueFacts.fact.Fact;

public class DBManager {

	private static final String dbName = "TrueFacts";
	private static final String projectPath = "com.cerSprikRu.TrueFacts";
	DBAdapter adapter;

	public DBManager(Context context) {
		adapter = new DBAdapter(context, dbName, projectPath);
	}

	public List<Fact> read() {
		adapter.openDataBase();
		List<Fact> result = new ArrayList<Fact>();
		
		Cursor c = adapter.executeSql("select * from TrueFacts", null);
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
					String publisher = c.getString(c.getColumnIndex("publisher"));
					Fact fact = new Fact(text, fav, id, publisher);
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
		String sql = "update TrueFacts set fav = "+(fact.isFavorite()?1:0)+" where _id = "+fact.getId();
		adapter.update(sql);
		adapter.close();
	}
}
