package com.cerspri.star.NickiMinaj.model;


public class Video {
	private String title;
	private String description;
	private String imagePath;
	private String videoTag;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getImagePath() {
		return imagePath;
	}


	public String getVideoTag() {
		return videoTag;
	}
	
	public void setVideoTag(String videoTag) {
		this.videoTag=videoTag;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
