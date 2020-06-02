package com.red.app.controll.search;

import com.red.app.App;
import com.red.app.config.Resources;
import com.red.app.helpers.Helpers;
import com.red.app.media.Sound;
import com.red.app.media.SoundURL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SearchControll {
	private int            kmess;
	private int            page;

	@FXML
	private VBox content;

	@FXML
	private ScrollPane scrollPane;

	public void initialize() {
		scrollPane.vvalueProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
			{
				if(newValue.doubleValue() == 1){
					int page = Search.getInstance().getPage();
					page++;
					if (Search.getInstance().getPageCount() > page)
						Search.getInstance().setPage(page);
				}
			}
		});
	}

	public void setData(String key, int page, JSONArray items){
		content.getChildren().clear();
		addItems(items);
	}

	public void setData(JSONArray items){
		content.getChildren().clear();
		addItems(items);
	}

	public void addItems(JSONArray items){
		try {
			for(int i = 0; i < items.length(); i++){
				JSONObject jsonItem = items.getJSONObject(i);
				Sound sound = new SoundURL(jsonItem);
				FXMLLoader itemPrefab = new FXMLLoader(App.getResource(Resources.BODY_SEARCH_ITEM));
				HBox itemNode = null;
				try {
					itemNode = itemPrefab.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				SearchItem itemController = itemPrefab.getController();
				itemController.setSound(sound);
				content.getChildren().add(itemNode);
				Helpers.getInstance().setAnchorNodeFull(itemNode, 0.0D, 0.0D, 0.0D, 0.0D);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public VBox getContent() {
		return content;
	}
}
