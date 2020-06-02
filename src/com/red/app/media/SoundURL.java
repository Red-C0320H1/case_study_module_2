package com.red.app.media;

import com.red.app.config.ConfigIO;
import com.red.app.helpers.Helpers;
import com.red.app.helpers.SoundIO;
import com.red.app.helpers.Str;
import javafx.scene.image.Image;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class SoundURL extends SoundInfo {
	private boolean isDownload = false;

	public void setIsDownload(boolean check) {
		isDownload = check;
	}

	public SoundURL(JSONObject data){
		try {
			setTitle(data.getString("title"));
			setID(data.getString("id"));
			setArtist(data.getString("artists_names"));
			setDuration(data.getInt("duration"));
			setSEO(Str.slug(getTitle(), "-"));

			Image thumb = new Image(Helpers.patternURL(data.getString("thumbnail")), true);
			setThumbnail(thumb);

		} catch (JSONException var2) {
		}
	}

	@Override
	public String getURL() {
		String url = super.getURL();
		if (url == null){
			String path;
			String name;
			String ext;
			if (isDownload){
				path = ConfigIO.PROFILE_PATH;
				name = getSEO();
				ext = ConfigIO.MP3_EXT;
			}else{
				path = ConfigIO.TEMP_PATH;
				name = getSEO();
				ext = ConfigIO.TEMP_EXT;
			}

			synchronized (SoundURL.class){
				SoundStream stream = new SoundStream(getID());

				String urlStream  = stream.getStream128();

				if (urlStream != null){
					SoundIO soundIO = SoundIO.getInstance();
					String URI = soundIO.download(urlStream, path, name, ext);

					setURL(URI);
				}else{
					setURL("-1");
				}
			}
		}

		try {
			File file = new File(new URI(super.getURL()));
			if (!file.exists()){
				setURL(null);
				return getURL();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return super.getURL();
	}
}
