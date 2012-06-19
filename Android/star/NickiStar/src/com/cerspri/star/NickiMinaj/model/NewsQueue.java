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
		data.add(0,news);
		while(data.size()>size){
			data.remove(size - 1);
		}
	}
	
	public List<News> list(){
		return data;
	}
}
