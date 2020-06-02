package com.red.app.controll.playlist;

import com.red.app.App;
import com.red.app.config.Resources;
import com.red.app.controll.HomeController;
import com.red.app.helpers.Helpers;
import com.red.app.media.Sound;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class PlaylistController {
	private static PlaylistController instance;
	private ArrayList<Sound>   playList;
	private PlaylistController() {
		playList = new ArrayList<>();
	}

	public static PlaylistController getInstance(){
		if (instance == null) instance = new PlaylistController();
		return instance;
	}

	public ArrayList<Sound> getPlayList() {
		return playList;
	}

	public void add(Sound sound){
		playList.add(sound);
	}

	public void remove(Sound sound){
		playList.remove(sound);
	}

	public void update(){
		HomeController home = App.getHome();
		String count = String.valueOf(playList.size());
		home.getPlaylistCount1().setText(count);
		home.getPlaylistCount2().setText(count);

		Helpers helpers = Helpers.getInstance();

		try {
			for (Sound sound : playList) {
				FXMLLoader itemPlaylistXML = new FXMLLoader(App.getResource(Resources.PLAYLIST_ITEM));
				AnchorPane itemPl = itemPlaylistXML.load();
				helpers.setAnchorNodeFull(itemPl, 0.0D, 0.0D, 0.0D, 0.0D);
				ItemPlaylistController itemPlaylist = itemPlaylistXML.getController();
				itemPlaylist.setSound(sound);
				home.getPlaylistContent().getChildren().add(itemPl);
			}
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}
}
