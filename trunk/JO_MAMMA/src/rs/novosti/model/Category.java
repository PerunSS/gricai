package rs.novosti.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String path;
	private List<Article> articles;

	public Category() {
		articles = new ArrayList<Article>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
