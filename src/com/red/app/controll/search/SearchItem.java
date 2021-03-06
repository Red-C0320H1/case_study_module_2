package com.red.app.controll.search;

import com.red.app.App;
import com.red.app.controll.playlist.PlaylistController;
import com.red.app.helpers.FormatTime;
import com.red.app.helpers.SoundIO;
import com.red.app.media.Sound;
import com.red.app.media.SoundURL;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SearchItem {
	Sound sound;

	@FXML
	private HBox item;

	@FXML
	private HBox optional;

	@FXML
	private ImageView thumb;

	@FXML
	private Label title;

	@FXML
	private Label artist;

	@FXML
	private Label duration;

	public void initialize() {
	}

	public void setSound(Sound sound) {
		this.sound = sound;
		title.setText(sound.getTitle());
		artist.setText(sound.getArtist());
		thumb.setImage(sound.getThumbnail());
		this.duration.setText(FormatTime.parse(sound.getSeconds()));

		item.getStyleClass().add("." + sound.getSEO());
	}

	@FXML
	public void handleClick() {
		App.getHome().PlaySound(this.sound);
	}

	@FXML
	public void handleEntered() {
		this.item.setStyle("-fx-background-color:#fafafa;");
		optional.setVisible(true);
	}

	@FXML
	public void handleExited() {
		this.item.setStyle("-fx-background-color:#ffffff;");
		optional.setVisible(false);
	}

	@FXML
	public void onClickAddPlaylis(){
		PlaylistController playlist = PlaylistController.getInstance();
		playlist.add(sound);
	}

	@FXML
	public void onClickDownload(){
		SoundURL soundURL = (SoundURL) sound;
		soundURL.setIsDownload(true);
		SoundIO.getInstance().download(sound);
	}
}
