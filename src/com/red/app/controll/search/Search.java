package com.red.app.controll.search;

import com.red.app.App;
import com.red.app.config.Resources;
import com.red.app.controll.header.FindBox;
import com.red.app.helpers.Helpers;
import com.red.app.helpers.Log;
import com.red.app.helpers.Request;
import com.red.app.zingmp3.ZingAPI;
import com.red.app.zingmp3.ZingInfo;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

public class Search {
	private SearchControll controll;
	private static Search  instance;
	private String         url;
	private int            kmess;
	private int            page;
	private int            pageCount;
	private String         key;

	private Search(){
		kmess = 20;
		page  = 0;
	}

	public static Search getInstance(){
		if (instance == null){
			instance = new Search();
		}
		return instance;
	}

	public void search(String string) {
		App.getHome().getInputText().setText(string);

		page  = 0;
		key   = string;

		FindBox.getInstance().hidden();
		createURL();

		JSONObject json = getContent();

		if (json == null) return;

		JSONArray items = null;
		pageCount = 0;

		try {
			JSONObject data = json.getJSONObject("data");
			double total    = data.getDouble("total");
			items           = data.getJSONArray("items");

			pageCount = (int) Math.ceil(total/Search.getInstance().getKmess());
		} catch (JSONException e) {
		}

		if (controll == null){
			FXMLLoader loader = new FXMLLoader(App.getResource(Resources.BODY_SEARCH));
			try {
				AnchorPane searchNode = loader.load();
				controll    = loader.getController();

				App.getHome().getBody().getChildren().clear();
				App.getHome().getBody().getChildren().add(searchNode);

				Helpers.getInstance().setAnchorNodeFull(searchNode, 0,0,0,0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (items == null){
			return;
		}

		controll.setData(key, pageCount, items);
	}

	private void createURL() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("type", "song");
		param.put("count", "20");
		param.put("q", key);

		try {
			url = ZingAPI.buildAPIURL(ZingInfo.URL_SEARCH, param);
		} catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
			createURL();
		}
	}

	private JSONObject getContent(){
		StringBuilder urlData = new StringBuilder();
		urlData.append(url);
		int start = page*kmess;

		urlData.append("&start="+start);

		Request request  = new Request();
		String url       = urlData.toString();
		String dataChart = request.grab_content(url);

		try {
			JSONObject json = new JSONObject(dataChart);
			int status = json.getInt("err");
			if (status != 0) {
				return null;
			}
			return json;
		} catch (NullPointerException | JSONException var4) {
			Log.info(var4.toString());
		}
		return null;
	}

	public int getKmess() {
		return kmess;
	}

	public int getPage() {
		return page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPage(int page) {
		this.page = page;

		JSONObject json = getContent();
		JSONArray items = null;

		try {
			JSONObject data = json.getJSONObject("data");
			items           = data.getJSONArray("items");
		} catch (JSONException e) {
		}

		controll.addItems(items);
	}

	public void setControll(SearchControll controll) {
		this.controll = controll;
	}
}
