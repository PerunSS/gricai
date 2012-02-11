package com.cerspri.star.NickiMinaj.model;

import java.util.LinkedList;
import java.util.List;

public class NewsQueue {

	private List<News> data;
	private int size;
	
	public NewsQueue(int size){
		data = new LinkedList<News>();
		this.size = size;
	}
	
	public void add(News news){
		while(data.size()>=size)
			data.remove(0);
		data.add(news);
	}
	
	public List<News> list(){
		return data;
	}
}
