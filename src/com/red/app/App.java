package com.red.app;

import com.red.app.controll.HomeController;
import com.red.app.helpers.Str;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;

public class App extends Application {
	public static AnchorPane root;
	public static Scene scene;
	public static HomeController homeController;

	public static URL getResource(String path){
		return App.class.getResource(path);
	}

	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/layout/home.fxml"));
		root              = loader.load();
		homeController    = loader.getController();
		homeController.setActiveNodeLoad(true);

		primaryStage.setTitle("ZingMp3 - C0320H1 CodeGym");

		scene = new Scene(root);

		primaryStage.getIcons().add(new Image(getClass().getResource("/res/images/Zing-MP3-favicon.png").toString()));
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
