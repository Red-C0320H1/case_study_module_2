package com.red.app.controll.header;

import com.red.app.App;
import com.red.app.config.Resources;
import com.red.app.controll.HomeController;
import com.red.app.helpers.Helpers;
import com.red.app.helpers.Log;
import com.red.app.helpers.Request;
import com.red.app.zingmp3.ZingAPI;
import com.red.app.zingmp3.ZingInfo;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FindBox {
	private boolean isLoadHotKey;
	private JSONArray data;
	private static FindBox instance;

	private FindBox(){
		isLoadHotKey = false;
	}

	public static FindBox getInstance(){
		if (instance == null){
			instance = new FindBox();
		}
		return instance;
	}

	private boolean checkLoadKey(){
		return isLoadHotKey;
	}

	public void hidden(){
		HomeController home = App.getHome();
		home.getFindBox().getStyleClass().clear();
		home.getFindBox().getStyleClass().add("findBox");
		home.getFindDiv().setVisible(false);
		home.getFindDiv().setPrefHeight(0);
		home.getMaster().requestFocus();
	}

	public void onFocus() {
		HomeController home = App.getHome();
		home.getFindBox().getStyleClass().add("focused");
		home.getFindDiv().setVisible(true);
		home.getFindDiv().setPrefHeight(Control.USE_COMPUTED_SIZE);
		showHotKey();
	}

	public void search(String string) {
		string = string.trim();
		if (string.length() > 0){
			cloneSearch(string);
		}
	}
	private void cloneSearch(String key) {
		try {
			String urlData = "https://ac.zingmp3.vn/suggestKeyword/desktop?num=10&query=" + URLEncoder.encode(key, "UTF-8");
			Request request  = new Request();
			String dataChart = request.grab_content(urlData);

			JSONObject json = null;

			json = new JSONObject(dataChart);
			boolean status = json.getBoolean("result");
			if (status == false) {
				return;
			}
			JSONArray data = json.getJSONArray("data");

			json = data.getJSONObject(0);

			data = json.getJSONArray("keyword");
			ArrayList<String> lists = new ArrayList<>();

			for (int i = 0; i < data.length(); i++){
				JSONObject obj = data.getJSONObject(i);
				lists.add(obj.getString("keyword"));
			}
			show(lists);
		} catch (UnsupportedEncodingException | JSONException e) {
			//e.printStackTrace();
		}
	}

	private void addItem(String key) {
		FXMLLoader loader = new FXMLLoader(App.getResource(Resources.HEAD_FIND_ITEM));
		try {
			HBox item = loader.load();
			HeadSearchItem controller = loader.getController();
			controller.setText(key);

			App.getHome().getHeadFindContent().getChildren().add(item);

			Helpers helpers = Helpers.getInstance();
			helpers.setAnchorNodeH(item, 0.0, 0.0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showHotKey(){
		if (checkLoadKey() == false){
			cloneHotKey();
			try {
				for (int i = 0; i < data.length(); i++){
					String key = (String) data.get(i);
					addItem(key);
				}
				isLoadHotKey = true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	public void show(ArrayList<String> lists){
		App.getHome().getHeadFindContent().getChildren().clear();
		for (String text: lists){
			addItem(text);
		}
	}
	private void cloneHotKey() {
		Map<String, String> paramChart = new HashMap<String, String>();
		try {
			String urlData = ZingAPI.buildAPIURL(ZingInfo.URL_SEARCH_HOT_KEY, paramChart);
			Request request = new Request();
			String dataChart = request.grab_content(urlData);

			JSONObject json = new JSONObject(dataChart);
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
