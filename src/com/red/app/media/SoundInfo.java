package com.red.app.media;

import javafx.scene.image.Image;

abstract class SoundInfo implements Sound {
	private String ID;
	private String SEO;
	private String URL;
	private String title;
	private String artist;
	private Image  thumbnail;
	private int    duration;

	public void setID(String ID) {
		this.ID = ID;
	}

	public void setURL(String url) {
		this.URL = url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setSEO(String SEO) {
		this.SEO = SEO;
	}

	@Override
	public String getID() {
		return ID;
	}

	@Override
	public String getURL() {
		return URL;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getArtist() {
		return artist;
	}

	@Override
	public Image getThumbnail() {
		return thumbnail;
	}

	@Override
	public int getSeconds() {
		return duration;
	}

	@Override
	public String getSEO() {
		return SEO;
	}
}
