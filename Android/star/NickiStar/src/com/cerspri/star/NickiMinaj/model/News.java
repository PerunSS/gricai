package com.cerspri.star.NickiMinaj.model;

public class News {
	static int PAGE_SIZE = 10;
	static String NEWS_LINK_START = "<h3 class=r><a href=";
	static String NEWS_LINK_END = "</a></h3>";
	static String NEWS_TEXT_START = "<div class=st>";
	static String NEWS_TEXT_END = "</div>";
	static String NEWS_LINK_DATA_START = "class=l>";

	private String link;
	private String linkDescription;
	private String text;

	boolean extractLink(String linkData) {
		// +1 se dodaje da se preskoce znaci navoda
		System.out.println(linkData);
		try {
			int linkIndex = NEWS_LINK_START.length() + 1;
			int endIndex = linkData.indexOf("\" class");
			link = linkData.substring(linkIndex, endIndex);
			int linkDescIndex = linkData.indexOf(NEWS_LINK_DATA_START);
			linkDescIndex += NEWS_LINK_DATA_START.length();
			linkDescription = linkData.substring(linkDescIndex).replaceAll(
					"em>", "b>");
			return true;
		} catch (IndexOutOfBoundsException exception) {
			return false;
		}
	}

	boolean extractText(String textData) {
		try {
			int textStartIndex = NEWS_TEXT_START.length();
			text = textData.substring(textStartIndex).replaceAll("em>", "b>");
			return true;
		} catch (IndexOutOfBoundsException exception) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "(" + link + ", " + linkDescription + ", " + text + ")";
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLinkDescription() {
		return linkDescription;
	}

	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
