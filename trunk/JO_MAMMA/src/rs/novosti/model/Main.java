package rs.novosti.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rs.novosti.rss.RssReader;
import android.text.format.DateFormat;

public class Main {

	private List<Category> categories;
	private RssReader reader;
	private Category naslovna;
	private List<Category> galleryCategories;
	private String timeRefreshed;
	
	private static Main instance = new Main();
	
	public static Main getInstance(){
		return instance;
	}
	
	private Main(){
		
		categories = new ArrayList<Category>();
		reader = new RssReader();
		naslovna = reader.readNaslovna();
		categories.add(naslovna);
		categories.add(reader.readPolitika());
		categories.add(reader.readDrustvo());
		categories.add(reader.readEkonomija());
		categories.add(reader.readHronika());
		categories.add(reader.readBeograd());
		categories.add(reader.readDosije());
		categories.add(reader.readSpektakl());
		categories.add(reader.readZivotPlus());
		categories.add(reader.readTehnologije());
		categories.add(reader.readAuto());
		categories.add(reader.readSport());
		

		timeRefreshed = "Osveženo "+android.text.format.DateFormat.format("dd.MM.yyyy hh:mm", new java.util.Date())+"h";
	}
	
	public void refreshCategory(){
		categories.clear();
		
	}
	
	public String getTimeRefreshed() {
		return timeRefreshed;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public Article readArticle(Article article){
		return reader.readArticle(article);
	}

	public Category getNaslovna() {
		return naslovna;
	}
	
	public List<Category> getGalleryCategories(){
		if(galleryCategories == null){
			galleryCategories = new ArrayList<Category>();
			galleryCategories.add(naslovna);
			galleryCategories.add(categories.get(1));
			galleryCategories.add(categories.get(categories.size()-1));
		}
		return galleryCategories;
	}
}
