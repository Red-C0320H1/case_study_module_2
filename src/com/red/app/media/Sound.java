package com.red.app.media;

import javafx.scene.image.Image;

public interface Sound {
	String getID();
	String getTitle();
	String getArtist() ;
	Image getThumbnail();
	int getSeconds();
	String getURL();
	String getSEO();
}
