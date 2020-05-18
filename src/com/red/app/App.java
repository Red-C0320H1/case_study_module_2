package com.red.app;

import com.red.app.helpers.Helpers;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/layout/home.fxml"));
		AnchorPane root = loader.load();

		primaryStage.setTitle("ZingMp3 - C0320H1 CodeGym");
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		//launch(args);
		Helpers helpers = new Helpers();
		String test = helpers.grab_content("https://m.zingmp3.vn/xhr/media/get-source?type=audio&key=LHJHyZGNEpipdpSyGyDGLmTLpJiZLRmSv");
		System.out.println(test);
	}
}
