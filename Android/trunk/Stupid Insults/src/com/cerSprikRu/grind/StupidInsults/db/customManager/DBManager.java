package com.cerSprikRu.grind.StupidInsults.db.customManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;

import com.cerSprikRu.grind.StupidInsults.db.DBAdapter;
import com.cerSprikRu.grind.StupidInsults.insult.Fact;

public class DBManager {

	private static final String dbName = "RNBFacts";
	private static final String projectPath = "com.cerSprikRu.grind.StupidInsults";
	DBAdapter adapter;

	public DBManager(Context context) {
		adapter = new DBAdapter(context, dbName, projectPath);
	}

	public List<Fact> read() {
		adapter.openDataBase();
		List<Fact> result = new ArrayList<Fact>();
		Map<Integer, String> persons = new HashMap<Integer, String>();
		
		Cursor c = adapter.executeSql("select * from RNBPerson", null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String name = c.getString(c.getColumnIndex("personName"));
					int personIndex = c.getInt(c.getColumnIndex("_id"));
					persons.put(personIndex, name);
				} while (c.moveToNext());
			}
		}
		c.close();
		c = adapter.executeSql("select * from RNBFacts", null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String text = c.getString(c.getColumnIndex("fact"));
					int personID = c.getInt(c.getColumnIndex("personID"));
					int id = c.getInt(c.getColumnIndex("_id"));
					boolean fav = false;
					try{
						int fint = c.getInt(c.getColumnIndex("fav"));
						if(fint == 1)
							fav = true;
					}catch (Exception e) {
						fav = false;
					}
					
					Fact fact = new Fact(text, persons.get(personID), fav, id, personID);
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
		String sql = "update RNBFacts set fav = "+(fact.isFavorite()?1:0)+" where _id = "+fact.getId();
		adapter.update(sql);
		adapter.close();
	}
}
