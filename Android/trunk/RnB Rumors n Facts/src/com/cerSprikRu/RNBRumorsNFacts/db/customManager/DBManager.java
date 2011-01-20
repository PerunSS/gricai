package com.cerSprikRu.RNBRumorsNFacts.db.customManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;

import com.cerSprikRu.RNBRumorsNFacts.db.DBAdapter;
import com.cerSprikRu.RNBRumorsNFacts.person.Person;

public class DBManager {

	private static final String dbName = "RNBFacts";
	private static final String projectPath = "com.android.projectsforlearning.factstemplate";
	DBAdapter adapter;

	public DBManager(Context context) {
		adapter = new DBAdapter(context, dbName, projectPath);
	}

	public List<Person> read() {
		adapter.openDataBase();
		List<Person> result = new ArrayList<Person>();
		Map<Integer, List<String>> allRumors = new HashMap<Integer, List<String>>();
		Cursor c = adapter.executeSql("select * from RNBFacts", null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String rumor = c.getString(c.getColumnIndex("fact"));
					int id = c.getInt(c.getColumnIndex("personID"));
					List<String> rumors = allRumors.get(id);
					if (rumors == null) {
						rumors = new ArrayList<String>();
					}
					rumors.add(rumor);
					allRumors.put(id, rumors);
				} while (c.moveToNext());
			}
		}
		c.close();
		c = adapter.executeSql("select * from RNBPerson", null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String name = c.getString(c.getColumnIndex("personName"));
					int personIndex = c.getInt(c.getColumnIndex("_id"));
					Person p = Person.readPerson(personIndex, name,
							allRumors.get(personIndex));
					result.add(p);
				} while (c.moveToNext());
			}
		}
		c.close();
		adapter.close();
		return result;
	}
}
