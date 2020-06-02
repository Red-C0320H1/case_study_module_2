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
	private Media media;
	private MediaPlayer mediaPlayer;
	private Sound sound;

	public SoundPlayer(Sound sound) {
		this.sound  = sound;

		media       = new Media(sound.getURL());
		mediaPlayer = new MediaPlayer(media);
		init();
	}

	public void init() {
		mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				updateValues();
			}
		});
		mediaPlayer.setOnPlaying(new Runnable() {
			public void run() {
			}
		});
		mediaPlayer.setOnPaused(new Runnable() {
			public void run() {
			}
		});
		mediaPlayer.setOnReady(new Runnable() {
			public void run() {
				App.getHome().setDuration(mediaPlayer.getMedia().getDuration());
				updateValues();
			}
		});

		mediaPlayer.setOnError(new Runnable() {
			public void run() {
				final String errorMessage = media.getError().getMessage();
				// Handle errors during playback
				System.out.println("MediaPlayer Error: " + errorMessage);
			}
		});

		mediaPlayer.setCycleCount(1);

		// media vừa play song,
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				if (App.getHome().getSound() == sound)
					App.getHome().onClickNext();
			}
		});
	}

	protected void updateValues() {
		Duration duration   = App.getHome().getDuration();
		Label playTime      = App.getHome().getPlayTime();
		Slider timeSlider   = App.getHome().getTimeSlider();
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					Duration currentTime = mediaPlayer.getCurrentTime();
					playTime.setText(FormatTime.parse(currentTime));
					timeSlider.setDisable(duration.isUnknown());
					if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
						timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0D);
					}
				}catch (NullPointerException e){

				}
			}
		});
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public boolean chekMediaPlayer(){
		if (mediaPlayer == null)
			return false;
		return true;
	}

	public void play() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
		}
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}

	public void destroy() {
		try{
			mediaPlayer.stop();
			mediaPlayer.dispose();
		}catch (NullPointerException e){
		}finally {
			mediaPlayer = null;
			sound = null;
		}
	}

	public void seek(Duration duration){
		if (chekMediaPlayer()) {
			mediaPlayer.seek(duration);
		}
	}
	public void resetSeek(){
		seek(Duration.ZERO);
	}
}
