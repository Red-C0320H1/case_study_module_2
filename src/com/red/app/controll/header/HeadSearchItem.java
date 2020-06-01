package com.red.app.controll.header;

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
		System.out.println("click: " + text.getText());
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
