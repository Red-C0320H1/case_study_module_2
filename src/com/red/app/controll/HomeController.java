package com.red.app.controll;

import com.red.app.App;
import com.red.app.config.Resources;
import com.red.app.helpers.FormatTime;
import com.red.app.helpers.Helpers;
import com.red.app.media.SoundInfo;
import com.red.app.media.SoundPlayer;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class HomeController {
    @FXML
    private AnchorPane nodeLoad;

    @FXML
    private AnchorPane body;

    @FXML
    private Pane playlistNode;

    @FXML
    private VBox playlistContent;
    @FXML
    private Label playlistCount1;
    @FXML
    private Label playlistCount2;
    @FXML
    private Label songTime;

    @FXML
    private ImageView thumb;
    @FXML
    private Label playTime;
    @FXML
    private Label songName;
    @FXML
    private Label artists;
    @FXML
    private MediaView mediaView;
    @FXML
    private Slider timeSlider;
    @FXML
    private Slider volumeSlider;


    private ArrayList<SoundInfo> playList;
    private SoundInfo sound;
    private Thread threadTemp;
    private SoundPlayer soundPlayer;
    private Duration duration;
    private int repeat;
    private boolean stopRequested;
    private boolean atEndOfMedia;

    public void initialize() throws IOException {
        this.playList = new ArrayList();
        this.repeat = -1;
        this.stopRequested = false;
        this.atEndOfMedia = false;
        FXMLLoader chartXML = new FXMLLoader(App.getResource(Resources.BODY_CHART));
        AnchorPane chart = chartXML.load();
        this.body.getChildren().add(chart);
        Helpers helpers = new Helpers();
        helpers.setAnchorNodeFull(chart, 0.0D, 0.0D, 0.0D, 0.0D);
        this.timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                timeSlider.setValueChanging(true);
                double value = event.getX() / timeSlider.getWidth() * timeSlider.getMax();
                timeSlider.setValue(value);
                timeSlider.setValueChanging(false);
            }
        });
        this.timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging() && soundPlayer != null) {
                    soundPlayer.getMediaPlayer().seek(duration.multiply(timeSlider.getValue() / 100.0D));
                }
            }
        });
    }

    public void setActiveNodeLoad(boolean active) {
        this.nodeLoad.setVisible(active);
    }

    public void setSoundPane(SoundInfo sound) {
        this.sound = sound;
        songTime.setText(FormatTime.parse(sound.getSeconds()));
        songName.setText(sound.getTitle());
        artists.setText(sound.getArtist());
        thumb.setImage(new Image(sound.getThumbnail(), true));
    }

    public void setPlaySound(SoundInfo sound) {
        if (threadTemp != null) {
            threadTemp.stop();
        }

        if (this.sound != null && this.sound.equals(sound)) {
            soundPlayer.getMediaPlayer().seek(duration.multiply(0.0D));
        } else {
            setSoundPane(sound);
            threadTemp = new Thread() {
                @Override
                public void run() {
                    if (soundPlayer != null) {
                        soundPlayer.stop();
                    }
                    soundPlayer = new SoundPlayer(sound);
                    mediaView.setMediaPlayer(soundPlayer.getMediaPlayer());
                    soundPlayer.play();
                }
            };
            threadTemp.start();
        }
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public Slider getTimeSlider() {
        return timeSlider;
    }

    public Label getPlayTime() {
        return playTime;
    }

    public VBox getPlaylistContent() {
        return playlistContent;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public ArrayList<SoundInfo> getPlayList() {
        return playList;
    }
}
