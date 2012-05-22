package com.cerspri.games.tod.model;

import java.util.HashMap;
import java.util.Map;

public class QueryParser {
	
	private Map<String,String> malePersonalization;
	private Map<String,String> femalePersonalization;
	
	private static QueryParser queryParser;
	
	private QueryParser(){
		malePersonalization = new HashMap<String, String>();
		femalePersonalization = new HashMap<String, String>();
		malePersonalization.put("G", "his");
		malePersonalization.put("R", "he");
		malePersonalization.put("A", "him");
		malePersonalization.put("S", "himself");
		femalePersonalization.put("G", "her");
		femalePersonalization.put("R", "she");
		femalePersonalization.put("A", "her");
		femalePersonalization.put("S", "herself");
	}
	
	public static QueryParser getInstance(){
		if (queryParser == null){
			queryParser = new QueryParser();
		}
		return queryParser;
	}
	
	public String parseQuery(String query, Player player, Player target){
		String playerName = player.getName();
		String playerGender = player.getGender();
		String targetName = target.getName();
		String targetGender = target.getGender();
		query = query.replaceAll("#\\{S\\}", playerName);
		query = query.replaceAll("#\\{D\\}", targetName);
		if(playerGender == "Male"){
			query = query.replaceAll("#\\{SG\\}", malePersonalization.get("G"));
			query = query.replaceAll("#\\{SR\\}", malePersonalization.get("R"));
			query = query.replaceAll("#\\{SA\\}", malePersonalization.get("A"));
			query = query.replaceAll("#\\{SS\\}", malePersonalization.get("S"));
		} else {
			query = query.replaceAll("#\\{SG\\}", femalePersonalization.get("G"));
			query = query.replaceAll("#\\{SR\\}", femalePersonalization.get("R"));
			query = query.replaceAll("#\\{SA\\}", femalePersonalization.get("A"));
			query = query.replaceAll("#\\{SS\\}", femalePersonalization.get("S"));
		}
		if(targetGender == "Male"){
			query = query.replaceAll("#\\{DG\\}", malePersonalization.get("G"));
			query = query.replaceAll("#\\{DR\\}", malePersonalization.get("R"));
			query = query.replaceAll("#\\{DA\\}", malePersonalization.get("A"));
			query = query.replaceAll("#\\{DS\\}", malePersonalization.get("S"));
		} else {
			query = query.replaceAll("#\\{DG\\}", femalePersonalization.get("G"));
			query = query.replaceAll("#\\{DR\\}", femalePersonalization.get("R"));
			query = query.replaceAll("#\\{DA\\}", femalePersonalization.get("A"));
			query = query.replaceAll("#\\{DS\\}", femalePersonalization.get("S"));
		}
		return query;
	}
}
