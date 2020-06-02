package com.red.app.controll.header;

import com.red.app.controll.search.Search;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HeadSearchItem {
	@FXML
	private HBox item;

	@FXML
	private Label text;

	public void setText(String key){
		this.text.setText(key);
	}

	@FXML
	public void onClick(){
		Search.getInstance().search(text.getText());
		FindBox.getInstance().hidden();
	}

	@FXML
	public void onHover(){
		item.getStyleClass().add("itemHeadFindHover");
	}

	@FXML
	public void offHover(){
		item.getStyleClass().clear();
		item.setStyle(null);
	}
}
