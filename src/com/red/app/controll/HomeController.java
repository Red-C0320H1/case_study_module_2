package com.red.app.controll;

import com.red.app.App;
import com.red.app.config.Resources;
import com.red.app.controll.header.FindBox;
import com.red.app.controll.playlist.PlaylistController;
import com.red.app.controll.search.Search;
import com.red.app.helpers.FormatTime;
import com.red.app.helpers.Helpers;
import com.red.app.media.Sound;
import com.red.app.media.SoundPlayer;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class HomeController {
    @FXML
    private AnchorPane master;

    @FXML
    private AnchorPane nodeLoad;

    @FXML
    private AnchorPane body;

    @FXML
    private VBox findBox;

    @FXML
    private VBox findDiv;

    @FXML
    private VBox headFindContent;
    //

    @FXML
    private TextField inputText;

    @FXML
    private HBox nodeCloseFind;

    @FXML
    private Pane playlistNode;

    @FXML
    private Pane paneVolume;

    @FXML
    private VBox playlistContent;

    @FXML
    private Label playlistCount1;

    @FXML
    private Label playlistCount2;

    // Controll media
    @FXML
    private ImageView btnPlay;

    // Info media
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


    private Sound            sound;
    private SoundPlayer      soundPlayer;
    private Duration         duration;

    private int indexPlayList  = 0;
    private double volume      = 1.0D;

    private int repeat            = -1;
    private boolean stopRequested = false;
    private boolean atEndOfMedia  = false;
    private boolean isPlay        = false;

    public void initialize() throws IOException {
        FXMLLoader chartXML = new FXMLLoader(App.getResource(Resources.BODY_CHART));
        AnchorPane chart = chartXML.load();
        this.body.getChildren().add(chart);

        Helpers helpers = Helpers.getInstance();

        helpers.setAnchorNodeFull(chart, 0.0D, 0.0D, 0.0D, 0.0D);

        // text search focus
        inputText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue) {
                    FindBox.getInstance().onFocus();
                }
            }
        });

        timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                timeSlider.setValueChanging(true);
                double value = event.getX() / timeSlider.getWidth() * timeSlider.getMax();
                timeSlider.setValue(value);
                timeSlider.setValueChanging(false);
            }
        });
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging() && soundPlayer != null) {
                    soundPlayer.getMediaPlayer().seek(duration.multiply(timeSlider.getValue() / 100.0D));
                }
            }
        });

        // volume
        volumeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                volumeSlider.setValueChanging(true);
                double value = Math.abs((event.getY() / volumeSlider.getHeight()) - 1) * volumeSlider.getMax();
                volumeSlider.setValue(value);
                volumeSlider.setValueChanging(false);
            }
        });

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging() && soundPlayer != null) {
                    volume = volumeSlider.getValue() / 100.0;
                    soundPlayer.getMediaPlayer().setVolume(volume);
                }
            }
        });
    }

    public void setActiveNodeLoad(boolean active) {
        this.nodeLoad.setVisible(active);
    }

    public void setSoundPane(Sound sound) {
        if (this.sound != null && this.sound == sound) {
            if (soundPlayer != null)
                soundPlayer.seek(duration.multiply(0.0D));
        } else {
            if (soundPlayer != null) {
                soundPlayer.destroy();
                soundPlayer = null;
            }
            this.sound = sound;
            songTime.setText(FormatTime.parse(sound.getSeconds()));
            songName.setText(sound.getTitle());
            artists.setText(sound.getArtist());
            thumb.setImage(sound.getThumbnail());
            if (isPlay) loadMediaPlayer();

            PlaylistController playList = PlaylistController.getInstance();

            indexPlayList = playList.getPlayList().indexOf(sound);
        }
    }

    private void loadMediaPlayer() {
        if (soundPlayer != null) {
            soundPlayer.destroy();
        }

        String URL = sound.getURL();
        if (URL.equals("-1")){
            System.out.println("Not URL Stream");
        }else{
            soundPlayer = new SoundPlayer(sound);
            mediaView.setMediaPlayer(soundPlayer.getMediaPlayer());
            soundPlayer.resetSeek();
            soundPlayer.getMediaPlayer().setVolume(volume);
            soundPlayer.play();
        }
    }

    public void PlaySound(Sound sound){
        setSoundPane(sound);
        if (isPlay == false) onClickButtonPlay();
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

    public ImageView getBtnPlay() {
        return btnPlay;
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

    public Sound getSound() {
        return sound;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setIndexPlayList(int indexPlayList) {
        this.indexPlayList = indexPlayList;
    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    //
    @FXML
    public void onClickButtonPlay(){
        if (sound != null){
            if (soundPlayer != null) {
                MediaPlayer mediaPlayer = soundPlayer.getMediaPlayer();
                Status status = mediaPlayer.getStatus();
                if (status == Status.UNKNOWN || status == Status.HALTED) {
                    return;
                }
                if (status == Status.PAUSED || status == Status.READY || status == Status.STOPPED)
                {
                    mediaPlayer.play();
                    isPlay = true;

                    btnPlay.setImage(Resources.ICON_PAUSE);
                } else {
                    mediaPlayer.pause();
                    isPlay = false;
                    btnPlay.setImage(Resources.ICON_PLAY);
                }
            }else {
                isPlay = true;
                btnPlay.setImage(Resources.ICON_PAUSE);
                loadMediaPlayer();
            }
        }
    }

    @FXML
    public void onClickPrevious(){
        PlaylistController playList = PlaylistController.getInstance();
        indexPlayList--;
        if (indexPlayList < 0) indexPlayList = playList.getPlayList().size()-1;
        try {
            Sound soundPre = playList.getPlayList().get(indexPlayList);
            setSoundPane(soundPre);
        }catch (IndexOutOfBoundsException e){
        }
    }

    @FXML
    public void onShowVolume(){
        paneVolume.setVisible(true);
    }

    @FXML
    public void onHidenVolume(){
        paneVolume.setVisible(false);
    }

    @FXML
    public void onTogglePlaylist(){
        if (playlistNode.isVisible()){
            playlistNode.setVisible(false);
        }else{
            playlistNode.setVisible(true);
        }
    }

    @FXML
    public void onClickNext(){
        PlaylistController playList = PlaylistController.getInstance();
        indexPlayList++;
        if (indexPlayList >= playList.getPlayList().size()) indexPlayList = 0;
        try {
            Sound soundNext = playList.getPlayList().get(indexPlayList);
            setSoundPane(soundNext);
        }catch (IndexOutOfBoundsException e){
        }
    }

    @FXML
    public void findOnReleased(){
        FindBox.getInstance().search(inputText.getText());
    }

    @FXML
    public void findOnPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER) {
            Search.getInstance().search(inputText.getText());
        } else if(e.getCode() == KeyCode.ESCAPE) {
            FindBox.getInstance().hidden();
        }
    }

    @FXML
    public void hiddenSearch(){
        FindBox.getInstance().hidden();
    }

    public VBox getHeadFindContent() {
        return headFindContent;
    }

    public VBox getFindBox() {
        return findBox;
    }

    public VBox getFindDiv() {
        return findDiv;
    }

    public AnchorPane getMaster() {
        return master;
    }

    public AnchorPane getBody() {
        return body;
    }

    public TextField getInputText() {
        return inputText;
    }

    public Label getPlaylistCount1() {
        return playlistCount1;
    }

    public Label getPlaylistCount2() {
        return playlistCount2;
    }

    @FXML
    public void onClickGoToZingChart(){
        body.getChildren().clear();
        FXMLLoader chartXML = new FXMLLoader(App.getResource(Resources.BODY_CHART));
        AnchorPane chart = null;
        try {
            chart = chartXML.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        body.getChildren().add(chart);

        Helpers helpers = Helpers.getInstance();

        helpers.setAnchorNodeFull(chart, 0.0D, 0.0D, 0.0D, 0.0D);

        Search.getInstance().setControll(null);
    }
}
