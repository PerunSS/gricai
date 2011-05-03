package rs.novosti.model.font;

import java.util.ArrayList;
import java.util.List;

import rs.novosti.model.Main;

public class FontSize {
	private int fullArticleTitle = 20;
	private int fullArticleSource = 10;
	private int fullArticleShortText = 14;
	private int fullArticleFullText = 14;
	
	private static FontSize instance = new FontSize();
	
	public static FontSize getInstance(){
		return instance;
	}

	public FontSize() {		
	}
	
	public List<Integer> getDefaultSizeFullArticle() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(fullArticleTitle);
		list.add(fullArticleSource);
		list.add(fullArticleShortText);
		list.add(fullArticleFullText);
		return list;
	}
	
	public List<Integer> getMediumSizeFullArticle() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(fullArticleTitle+fullArticleTitle/2);
		list.add(fullArticleSource+fullArticleSource/2);
		list.add(fullArticleShortText+fullArticleShortText/2);
		list.add(fullArticleFullText+fullArticleFullText/2);
		return list;
	}
	
	public List<Integer> getBigSizeFullArticle() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(fullArticleTitle*2);
		list.add(fullArticleSource*2);
		list.add(fullArticleShortText*2);
		list.add(fullArticleFullText*2);
		return list;
	}
	
}
