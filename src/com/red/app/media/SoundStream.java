package com.red.app.media;

import com.red.app.helpers.Helpers;
import com.red.app.helpers.Request;
import com.red.app.zingmp3.ZingAPI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import com.red.app.zingmp3.ZingInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class SoundStream {
	private String id;
	private String stream128;

	public SoundStream(String id) {
		this.id = id;
	}

	public String getStream128() {
		if (stream128 == null) {
			downloadDataStream();
		}
		return stream128;
	}

	private void downloadDataStream() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		Request request = new Request();
		try {
			String urlData  = ZingAPI.buildAPIURL(ZingInfo.URL_STREAMMING, param);
			String content  = request.grab_content(urlData);
			JSONObject json = new JSONObject(content);
			int status = json.getInt("err");
			if (status == 0) {
				JSONObject data        = json.getJSONObject("data");
				JSONObject defaultData = data.getJSONObject("default");
				stream128 = Helpers.patternURL(defaultData.getString("128"));
			} else {
				downloadDataStream();
			}
		} catch (NullPointerException | InvalidKeyException | SignatureException | JSONException | NoSuchAlgorithmException var8) {
		}
	}
}
