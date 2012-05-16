package com.cerspri.games.tapit.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HighScore {

	private String name;
	private double value;
	private Date date;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public final static List<HighScore> parseScores(JSONObject jsonobj){
		List<HighScore> highScores = new ArrayList<HighScore>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			JSONArray elements = jsonobj.getJSONArray("data");
			for(int i=0;i<elements.length();i++){
				JSONObject elementobj = elements.getJSONObject(i);
				HighScore score = new HighScore();
				score.setName(elementobj.getString("name"));
				score.setValue(elementobj.getDouble("score"));
				try {
					score.setDate(format.parse(elementobj.getString("date")));
				} catch (ParseException e) {
					score.setDate(new Date());
					e.printStackTrace();
				}
				highScores.add(score);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return highScores;
	}

}
