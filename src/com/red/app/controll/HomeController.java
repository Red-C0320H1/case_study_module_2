package com.red.app.controll;

import com.red.app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Label;


import java.io.IOException;

public class HomeController {

    @FXML public AnchorPane body;

    @FXML public Label      songTime;

    @FXML public Label      playingTime;

    @FXML public Label      songName;

    public void initialize() throws IOException {
        // tai zing chart

        FXMLLoader chartXML = new FXMLLoader(getClass().getResource("/res/layout/zingchart/chart.fxml"));
        AnchorPane chart    = chartXML.load();
        body.getChildren().addAll(chart);

        AnchorPane.setTopAnchor(chart,   0.0);
        AnchorPane.setLeftAnchor(chart,  0.0);
        AnchorPane.setBottomAnchor(chart,0.0);
        AnchorPane.setRightAnchor(chart, 0.0);

    }
}
