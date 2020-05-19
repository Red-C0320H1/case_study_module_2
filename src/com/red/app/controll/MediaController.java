package com.red.app.controll;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.MediaView;

public class MediaController {
    @FXML
    public Label songTime;

    @FXML
    public Label playingTime;

    @FXML
    public Label songName;

    public void initialize(){
        System.out.println("initialize controll");
    }
}
