package com.cerspri.games.tod.db.customManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;

import com.cerspri.games.tod.db.DBAdapter;
import com.cerspri.games.tod.model.ToDQuery;

public class DBManager {

	private static final String dbName = "tod";
	private static final String projectPath = "com.cerspri.games.tod";
	DBAdapter adapter;

	public DBManager(Context context) {
		adapter = new DBAdapter(context, dbName, projectPath);
	}
	
	public Map<Integer, ToDQuery> getTruths(){
		adapter.openDataBase();
		Map<Integer, ToDQuery> truths= new HashMap<Integer, ToDQuery>();
		Cursor c = adapter.executeSql("select * from truths", null);
		if (c != null){
			if (c.moveToFirst()) {
				do {
					
					String query = c.getString(c.getColumnIndex("text"));
					int mask = c.getInt(c.getColumnIndex("mask"));
					int dirtiness = c.getInt(c.getColumnIndex("dirtiness"));
					int id = c.getInt(c.getColumnIndex("id"));
					ToDQuery truth = new ToDQuery(query, mask, dirtiness);
					truths.put(id, truth);
				} while (c.moveToNext());
			}
		}
		return truths;
	}
	
	public Map<Integer, ToDQuery> getDares(){
		adapter.openDataBase();
		Map<Integer, ToDQuery> truths= new HashMap<Integer, ToDQuery>();
		Cursor c = adapter.executeSql("select * from dares", null);
		if (c != null){
			if (c.moveToFirst()) {
				do {
					String query = c.getString(c.getColumnIndex("text"));
					int mask = c.getInt(c.getColumnIndex("mask"));
					int dirtiness = c.getInt(c.getColumnIndex("dirtiness"));
					int id = c.getInt(c.getColumnIndex("id"));
					ToDQuery truth = new ToDQuery(query, mask, dirtiness);
					truths.put(id, truth);
				} while (c.moveToNext());
			}
		}
		return truths;
	}
	
	public boolean insertTruths(List<String> truths){
		adapter.openDataBase();
		for (int i =0; i<truths.size(); i+=4){
			Cursor c = adapter.executeSql("insert into tod.truths (dirtiness,text,mask,couples) " +
					"values ("+truths.get(i)+","+truths.get(i+1)+","+truths.get(i+2)+","+truths.get(i+3)+","+")", null);
			return true;
		}
		return true;
	}
//	public List<Fact> read() {
//		adapter.openDataBase();
//		List<Fact> result = new ArrayList<Fact>();
//		Map<Integer, String> persons = new HashMap<Integer, String>();
//		
//		Cursor c = adapter.executeSql("select * from RNBPerson", null);
//		if (c != null) {
//			if (c.moveToFirst()) {
//				do {
//					String name = c.getString(c.getColumnIndex("personName"));
//					int personIndex = c.getInt(c.getColumnIndex("_id"));
//					persons.put(personIndex, name);
//				} while (c.moveToNext());
//			}
//		}
//		c.close();
//		c = adapter.executeSql("select * from RNBFacts", null);
//		if (c != null) {
//			if (c.moveToFirst()) {
//				do {
//					String text = c.getString(c.getColumnIndex("fact"));
//					int personID = c.getInt(c.getColumnIndex("personID"));
//					int id = c.getInt(c.getColumnIndex("_id"));
//					boolean fav = false;
//					try{
//						int fint = c.getInt(c.getColumnIndex("fav"));
//						if(fint == 1)
//							fav = true;
//					}catch (Exception e) {
//						fav = false;
//					}
//					
//					Fact fact = new Fact(text, persons.get(personID), fav, id, personID);
//					result.add(fact);
//				} while (c.moveToNext());
//			}
//		}
//		c.close();
//		adapter.close();
//		return result;
//	}
//	
//	public void updateFact(Fact fact){
//		adapter.openDataBase();
//		String sql = "update RNBFacts set fav = "+(fact.isFavorite()?1:0)+" where _id = "+fact.getId();
//		adapter.update(sql);
//		adapter.close();
//	}
}
