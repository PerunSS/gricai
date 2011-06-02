package rs.novosti.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.novosti.rss.RssReader;

public class Main {

	private Map<String, Category> categories;
	private RssReader reader;
	private Category naslovna;
	private List<Category> galleryCategories;
	private String timeRefreshed;

	private static Main instance = new Main();

	public static Main getInstance() {
		return instance;
	}

	private Main() {

		categories = new HashMap<String, Category>();
		reader = new RssReader();
		naslovna = reader.readNaslovna();
		categories.put("Top vesti", naslovna);
		categories.put("Politika", reader.readPolitika());
		categories.put("Društvo", reader.readDrustvo());
		categories.put("Ekonomija", reader.readEkonomija());
//		categories.put("Hronika", reader.readHronika());
//		categories.put("Beograd", reader.readBeograd());
//		categories.put("Dosije", reader.readDosije());
//		categories.put("Spektakl", reader.readSpektakl());
//		categories.put("Život plus", reader.readZivotPlus());
//		categories.put("Tehnologije", reader.readTehnologije());
//		categories.put("Auto", reader.readAuto());
		categories.put("Sport", reader.readSport());

		timeRefreshed = "Osveženo "
				+ android.text.format.DateFormat.format("dd.MM.yyyy hh:mm",
						new java.util.Date()) + "h";
	}

	public void refreshCategory(String categoryName, boolean clear) {
		if(clear){
			categories.clear();
			naslovna = reader.readNaslovna();
			categories.put("Top vesti", naslovna);
		}
		if(categoryName.equals("Politika")){
			categories.put("Politika", reader.readPolitika());
		}else if(categoryName.equals("Društvo")){
			categories.put("Društvo", reader.readDrustvo());
		}else if(categoryName.equals("Ekonomija")){
			categories.put("Ekonomija", reader.readEkonomija());
		}else if(categoryName.equals("Hronika")){
			categories.put("Hronika", reader.readHronika());
		}else if(categoryName.equals("Beograd")){
			categories.put("Beograd", reader.readBeograd());
		}else if(categoryName.equals("Dosije")){
			categories.put("Dosije", reader.readDosije());
		}else if(categoryName.equals("Spektakl")){
			categories.put("Spektakl", reader.readSpektakl());
		}else if(categoryName.equals("Život plus")){
			categories.put("Život plus", reader.readZivotPlus());
		}else if(categoryName.equals("Tehnologije")){
			categories.put("Tehnologije", reader.readTehnologije());
		}else if(categoryName.equals("Tehnologije")){
			categories.put("Tehnologije", reader.readTehnologije());
		}else if(categoryName.equals("Auto")){
			categories.put("Auto", reader.readAuto());
		}else if(categoryName.equals("Sport")){
			categories.put("Sport", reader.readSport());
		}
	}
	
	public void readCategory(String categoryName){
		Category cat = categories.get(categoryName);
		if(cat == null){
			refreshCategory(categoryName, false);
		}
	}

	public String getTimeRefreshed() {
		return timeRefreshed;
	}

	public Map<String, Category> getCategories() {
		return categories;
	}

	public Article readArticle(Article article) {
		return reader.readArticle(article);
	}

	public Category getNaslovna() {
		return naslovna;
	}

	public List<Category> getGalleryCategories() {
		if (galleryCategories == null) {
			galleryCategories = new ArrayList<Category>();
			galleryCategories.add(naslovna);
			galleryCategories.add(categories.get("Politika"));
			galleryCategories.add(categories.get("Sport"));
		}
		return galleryCategories;
	}
}
