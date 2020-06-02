package com.red.app.media;

import com.red.app.controll.profile.FileItem;
import com.red.app.helpers.FormatTime;
import com.red.app.helpers.Str;
import javafx.collections.MapChangeListener;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundFile extends SoundInfo {

	private FileItem item;

	public void setItem(FileItem item) {
		this.item = item;
	}

	public SoundFile(File file){
		setURL(file.toURI().toString());

		Media media        = new Media(getURL());
		MediaPlayer player = new MediaPlayer(media);

		media.getMetadata().addListener(new MapChangeListener<String, Object>() {
			@Override
			public void onChanged(Change<? extends String, ? extends Object> ch) {
				if (ch.wasAdded()) {
					handleMetadata(ch.getKey(), ch.getValueAdded());
				}
			}
		});

		player.setOnReady(new Runnable() {
			public void run() {
				int seconds = (int) media.getDuration().toSeconds();
				setDuration(seconds);
				item.setDuration(FormatTime.parse(seconds));
			}
		});
	}

	public SoundFile(String url){
		File file = new File(url);
		setURL(file.toURI().toString());

		file = null;

		Media media        = new Media(getURL());
		MediaPlayer player = new MediaPlayer(media);

		media.getMetadata().addListener(new MapChangeListener<String, Object>() {
			@Override
			public void onChanged(Change<? extends String, ? extends Object> ch) {
				if (ch.wasAdded()) {
					handleMetadata(ch.getKey(), ch.getValueAdded());
				}
			}
		});

		player.setOnReady(new Runnable() {
			public void run() {
				int seconds = (int) media.getDuration().toSeconds();
				setDuration(seconds);
				item.setDuration(FormatTime.parse(seconds));
			}
		});
	}

	private void handleMetadata(String key, Object value) {
		if (key.equals("artist")) {
			setArtist(value.toString());
			item.setArtist(getArtist());
		} if (key.equals("title")) {
			setTitle(value.toString());
			setSEO(Str.slug(getTitle(), "-"));
			item.setTitle(getTitle());
		} if (key.equals("image")) {
			setThumbnail((Image) value);
			item.setThumb(getThumbnail());
		}
	}
}
