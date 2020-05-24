package com.red.app.controll.playlist;

import com.red.app.helpers.FormatTime;
import com.red.app.helpers.Helpers;
import com.red.app.media.SoundInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemPlaylistController {
	public SoundInfo sound;
	@FXML
	public ImageView thumb;
	@FXML
	public Label title;
	@FXML
	public Label duration;
	@FXML
	public Label artist;

	public void setSound(SoundInfo sound) {
		this.sound = sound;
		this.title.setText(sound.getTitle());
		this.artist.setText(sound.getArtist());
		this.thumb.setImage(new Image(sound.getThumbnail(), true));
		this.duration.setText(FormatTime.parse(sound.getSeconds()));
	}
}
