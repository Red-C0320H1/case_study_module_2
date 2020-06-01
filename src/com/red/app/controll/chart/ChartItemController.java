package com.red.app.controll.chart;

import com.red.app.App;
import com.red.app.helpers.FormatTime;
import com.red.app.media.Sound;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ChartItemController {
	public Sound sound;
	@FXML
	public HBox item;
	@FXML
	public Label sortNumber;
	@FXML
	public ImageView thumb;
	@FXML
	public Label title;
	@FXML
	public Label duration;
	@FXML
	public Label artist;

	public void initialize() {
	}

	public void setSound(int sortNumber, Sound sound) {
		this.sortNumber.setText(String.valueOf(sortNumber));
		this.sound = sound;
		this.title.setText(sound.getTitle());
		this.artist.setText(sound.getArtist());
		this.thumb.setImage(sound.getThumbnail());
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
	}

	@FXML
	public void handleExited() {
		this.item.setStyle("-fx-background-color:#ffffff;");
	}
}
