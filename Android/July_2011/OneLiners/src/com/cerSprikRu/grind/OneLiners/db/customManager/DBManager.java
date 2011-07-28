package com.cerSprikRu.grind.OneLiners.db.customManager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.cerSprikRu.grind.OneLiners.db.DBAdapter;
import com.cerSprikRu.grind.OneLiners.insult.Fact;

public class DBManager {

	private static final String dbName = "OneLiners";
	private static final String projectPath = "com.cerSprikRu.grind.OneLiners";
	DBAdapter adapter;

	public DBManager(Context context) {
		adapter = new DBAdapter(context, dbName, projectPath);
	}

	public List<Fact> read() {
		adapter.openDataBase();
		List<Fact> result = new ArrayList<Fact>();
		
		Cursor c = adapter.executeSql("select * from OneLiners", null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String text = c.getString(c.getColumnIndex("OneLiners")).trim();
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
		String sql = "update OneLiners set fav = "+(fact.isFavorite()?1:0)+" where _id = "+fact.getId();
		adapter.update(sql);
		adapter.close();
	}
}
