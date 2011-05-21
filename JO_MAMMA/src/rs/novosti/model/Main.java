package rs.novosti.model;

import java.util.ArrayList;
import java.util.List;

import rs.novosti.rss.RssReader;

public class Main {

	private List<Category> categories;
	private Naslovna naslovna;

	private static Main instance = new Main();
	
	public static Main getInstance(){
		return instance;
	}
	
	private Main(){
		categories = new ArrayList<Category>();
		RssReader reader = new RssReader();
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
		naslovna = reader.readNaslovna();
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public Naslovna getNaslovna() {
		return naslovna;
	}
}
