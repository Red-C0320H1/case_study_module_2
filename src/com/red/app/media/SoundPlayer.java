package com.red.app.media;

import com.red.app.App;
import com.red.app.helpers.FormatTime;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundPlayer {
	private MediaPlayer mediaPlayer;

	public SoundPlayer(SoundInfo sound) {
		this.mediaPlayer = new MediaPlayer(new Media(sound.getURI()));
		this.init();
	}

	public void init() {
		this.mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				SoundPlayer.this.updateValues();
			}
		});
		this.mediaPlayer.setOnPlaying(new Runnable() {
			public void run() {
			}
		});
		this.mediaPlayer.setOnPaused(new Runnable() {
			public void run() {
				System.out.println("onPaused");
			}
		});
		this.mediaPlayer.setOnReady(new Runnable() {
			public void run() {
				App.homeController.setDuration(SoundPlayer.this.mediaPlayer.getMedia().getDuration());
				SoundPlayer.this.updateValues();
			}
		});
	}

	protected void updateValues() {
		Duration duration   = App.homeController.getDuration();
		Label playTime      = App.homeController.getPlayTime();
		Slider timeSlider   = App.homeController.getTimeSlider();
		Slider volumeSlider = App.homeController.getVolumeSlider();
		if (playTime != null && App.homeController.getTimeSlider() != null && App.homeController.getVolumeSlider() != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					Duration currentTime = SoundPlayer.this.mediaPlayer.getCurrentTime();
					playTime.setText(FormatTime.parse(currentTime));
					timeSlider.setDisable(duration.isUnknown());
					if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
						timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0D);
					}

					if (!volumeSlider.isValueChanging()) {
						volumeSlider.setValue((double)((int)Math.round(SoundPlayer.this.mediaPlayer.getVolume() * 100.0D)));
					}

				}
			});
		}

	}

	public MediaPlayer getMediaPlayer() {
		return this.mediaPlayer;
	}

	public void play() {
		if (this.mediaPlayer != null) {
			this.mediaPlayer.play();
		}
	}

	public void stop() {
		if (this.mediaPlayer != null) {
			this.mediaPlayer.stop();
		}

	}
}
