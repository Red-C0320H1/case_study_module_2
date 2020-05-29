package com.red.app.controll.home;

import com.red.app.helpers.Request;
import com.red.app.zingmp3.ZingAPI;
import com.red.app.zingmp3.ZingInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

public class FindBox {
	private JSONArray data;
	private static FindBox instance;

	public static FindBox getInstance(){
		if (instance == null){
			instance = new FindBox();
		}
		return instance;
	}
	public void search(String string) {
		string = string.trim();
		if (string.length() > 0){
			cloneSearch(string);
		}
	}
	private void cloneSearch(String string) {
	}

	public void showHotKey(){
		if (data == null){
			cloneHotKey();
		}
		try {
			for (int i = 0; i < data.length(); i++){
				String key = (String) data.get(i);
				System.out.println(key);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void cloneHotKey() {
		Map<String, String> paramChart = new HashMap<String, String>();
		String urlData = null;
		try {
			urlData = ZingAPI.buildAPIURL(ZingInfo.URL_SEARCH_HOT_KEY, paramChart);
			Request request = new Request();
			String dataChart = request.grab_content(urlData);

			JSONObject json = null;

			json = new JSONObject(dataChart);
			int status = json.getInt("err");
			if (status != 0) {
				cloneHotKey();
				return;
			}

			data = json.getJSONArray("data");
		} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException | JSONException var4) {
		}
	}
}
