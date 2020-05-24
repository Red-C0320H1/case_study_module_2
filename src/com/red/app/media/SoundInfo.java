package com.red.app.media;

import com.red.app.config.ConfigIO;
import com.red.app.helpers.Helpers;
import com.red.app.helpers.SoundIO;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public class SoundInfo {
	private boolean isProfile;
	private JSONObject data;
	private SoundStream stream;
	private String fileName;

	public SoundInfo(JSONObject data) {
		this.isProfile = false;
		this.data = data;

		try {
			this.stream = new SoundStream(data.getString("id"));
		} catch (JSONException var3) {
			var3.printStackTrace();
		}
	}

	public SoundInfo(String data) {
		this.isProfile = true;
	}

	public String getTitle() {
		try {
			return this.data.getString("title");
		} catch (JSONException var2) {
			return "";
		}
	}

	public String getArtist() {
		try {
			return this.data.getString("artists_names");
		} catch (JSONException var2) {
			return "";
		}
	}

	public String getThumbnail() {
		try {
			return Helpers.patternURL(this.data.getString("thumbnail"));
		} catch (JSONException var2) {
			return "";
		}
	}

	public int getSeconds(){
		try {
			return this.data.getInt("duration");
		} catch (JSONException var2) {
			return 0;
		}
	}

	public double getDuration() {
		try {
			return this.data.getDouble("duration");
		} catch (JSONException var2) {
			return 0.0D;
		}
	}

	public SoundStream getStream() {
		return this.stream;
	}

	private String getURITemp() {
		SoundIO soundIO = new SoundIO();
		String url = this.getStream().getStream128();
		return soundIO.loadTemp(url).toURI().toString();
	}

	private String getURIFileDownload() {
		File file = new File(ConfigIO.PROFILE_PATH + this.fileName);
		return !file.exists() ? null : file.toURI().toString();
	}

	public String getURI() {
		return !this.isProfile ? this.getURITemp() : this.getURIFileDownload();
	}
}
