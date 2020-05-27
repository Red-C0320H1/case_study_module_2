package com.red.app.media;

import com.red.app.helpers.Str;
import javafx.collections.MapChangeListener;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import java.io.File;

public class SoundFile extends SoundInfo {
	public SoundFile(String url){
		File file = new File(url);
		setURL(file.toURI().toString());
		Media media = new Media(getURL());
		media.getMetadata().addListener(new MapChangeListener<String, Object>() {
			@Override
			public void onChanged(Change<? extends String, ? extends Object> ch) {
				if (ch.wasAdded()) {
					handleMetadata(ch.getKey(), ch.getValueAdded());
				}
			}
		});

		setDuration((int) media.getDuration().toSeconds());
	}

	private void handleMetadata(String key, Object value) {
		if (key.equals("artist")) {
			setArtist(value.toString());
		} if (key.equals("title")) {
			setTitle(value.toString());
			setSEO(Str.slug(getTitle(), "-"));
		} if (key.equals("image")) {
			setThumbnail((Image) value);
		}
	}
}
