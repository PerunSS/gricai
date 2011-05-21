package rs.novosti.model;

import java.util.ArrayList;
import java.util.List;

public class Naslovna {
	private String title = "Top vesti";
	private List<Article> articles = new ArrayList<Article>();
	
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void clear() {
		for(Article article:articles)
			article.clear();
	}

	public String getTitle() {
		return title;
	}
}
