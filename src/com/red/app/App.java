package com.red.app;

import com.red.app.config.ENV;
import com.red.app.config.Resources;
import com.red.app.controll.HomeController;
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Resources.HOME));
		root              = loader.load();
		homeController    = loader.getController();
		homeController.setActiveNodeLoad(true);

		primaryStage.setTitle(ENV.APP_TITLE);

		scene = new Scene(root);

		primaryStage.getIcons().add(new Image(getClass().getResource(ENV.APP_ICON).toString()));
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent t) {
				// Handle when closing the program.
				// Delete the temporary file of the media player
				Platform.exit();
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
