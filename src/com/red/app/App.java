package com.red.app;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
	public static String APP_TITLE = "ZingMp3 - C0320H1 CodeGym";
	public static String URL_ICON  = "/res/images/Zing-MP3-Mod-Vip.png";
	public static AnchorPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/layout/home.fxml"));
		root   = loader.load();

		primaryStage.setTitle(APP_TITLE);
		Scene scene = new Scene(root);

		// Set Icon
		primaryStage.getIcons().add(new Image(getClass().getResource(URL_ICON).toString()));

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

		//Helpers helpers = new Helpers();
		//String test = helpers.grab_content("https://m.zingmp3.vn/xhr/media/get-source?type=audio&key=LHJHyZGNEpipdpSyGyDGLmTLpJiZLRmSv");
		//System.out.println(test);
	}
}
