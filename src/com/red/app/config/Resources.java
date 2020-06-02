package com.red.app.config;

import javafx.scene.image.Image;

public interface Resources {
	// Images
	String IMAGE_MEDIA_PLAY    = "/res/images/play.png";
	String IMAGE_MEDIA_PAUSE   = "/res/images/pause.png";
	String IMAGE_MEDIA_DEFAULT = "/res/images/audio_default.png";

	Image ICON_PLAY    = new Image(Resources.IMAGE_MEDIA_PLAY,    true);
	Image ICON_PAUSE   = new Image(Resources.IMAGE_MEDIA_PAUSE,   true);
	Image ICON_DEFAULT = new Image(Resources.IMAGE_MEDIA_DEFAULT, true);

	// FXML
	String HOME            = "/res/layout/home.fxml";
	String BODY_CHART      = "/res/layout/zingchart/chart.fxml";
	String BODY_CHART_ITEM = "/res/layout/zingchart/item.fxml";

	String BODY_SEARCH      = "/res/layout/search/search.fxml";
	String BODY_SEARCH_ITEM = "/res/layout/search/item.fxml";

	String BODY_PROFILE      = "/res/layout/profile/profile.fxml";
	String BODY_PROFILE_ITEM = "/res/layout/profile/item.fxml";

	String PLAYLIST_ITEM   = "/res/layout/playlist/item.fxml";
	String HEAD_FIND_ITEM  = "/res/layout/find/headItem.fxml";
}
