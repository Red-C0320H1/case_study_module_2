package com.red.app.media;

import com.red.app.config.ConfigIO;
import com.red.app.helpers.Helpers;
import com.red.app.helpers.SoundIO;
import com.red.app.helpers.Str;
import javafx.scene.image.Image;
import org.json.JSONException;
import org.json.JSONObject;

public class SoundURL extends SoundInfo {
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
			String path = ConfigIO.TEMP_PATH;
			String name = getSEO() + ConfigIO.TEMP_EXT;


			synchronized (SoundURL.class){
				SoundStream stream = new SoundStream(getID());

				String urlStream  = stream.getStream128();

				SoundIO soundIO = SoundIO.getInstance();
				String URI = soundIO.download(urlStream, path, name);

				System.out.println(URI);
				setURL(URI);
			}
		}
		return super.getURL();
	}
}
