package rs.novosti.model;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String shortText;
	private String text;
	private String source;
	private Date date;
	private String photoPath;
	private String oneLine;
	private int imageLength;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortText() {
		return shortText;
	}
	public void setShortText(String shortText) {
		this.shortText = shortText;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public void setOneLine(String oneLine) {
		this.oneLine = oneLine;
	}
	public String getOneLine() {
		return oneLine;
	}
	public void setImageLength(int imageLength) {
		this.imageLength = imageLength;
	}
	public int getImageLength() {
		return imageLength;
	}
	
	@Override
	public String toString() {
		return name+"\n"+shortText+"\n"+text+"\n"+date;
	}
	
}
