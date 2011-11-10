package com.cerSprikRu.grind.FatInsults.db.customManager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.cerSprikRu.grind.FatInsults.db.DBAdapter;
import com.cerSprikRu.grind.FatInsults.insult.Fact;

public class DBManager {

	private static final String dbName = "FatInsults";
	private static final String projectPath = "com.cerSprikRu.grind.FatInsults";
	DBAdapter adapter;

	public DBManager(Context context) {
		adapter = new DBAdapter(context, dbName, projectPath);
	}

	public List<Fact> read() {
		adapter.openDataBase();
		List<Fact> result = new ArrayList<Fact>();
		
		Cursor c = adapter.executeSql("select * from FatInsults", null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String text = c.getString(c.getColumnIndex("INsults")).trim();
					int id = c.getInt(c.getColumnIndex("id"));
					boolean fav = false;
					try{
						fav = c.getInt(c.getColumnIndex("Fav"))==1?true:false;
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
		String sql = "update FatInsults set Fav = "+(fact.isFavorite()?1:0)+" where id = "+fact.getId();
		adapter.update(sql);
		adapter.close();
	}
}
