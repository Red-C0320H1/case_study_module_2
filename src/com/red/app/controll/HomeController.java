package com.red.app.controll;

import com.red.app.App;
import com.red.app.config.Resources;
import com.red.app.helpers.FormatTime;
import com.red.app.helpers.Helpers;
import com.red.app.media.Sound;
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
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
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


    private ArrayList<Sound> playList;
    private Sound   sound;
    private Thread      threadTemp;
    private SoundPlayer soundPlayer;
    private Duration    duration;


    private Image ICON_PLAY;
    private Image ICON_PAUSE;

    private int indexPlayList  = 0;
    private double volume      = 1.0D;

    private int repeat            = -1;
    private boolean stopRequested = false;
    private boolean atEndOfMedia  = false;
    private boolean isPlay        = false;

    public void initialize() throws IOException {
        this.playList = new ArrayList<Sound>();

        FXMLLoader chartXML = new FXMLLoader(App.getResource(Resources.BODY_CHART));
        AnchorPane chart = chartXML.load();
        this.body.getChildren().add(chart);


        ICON_PLAY  = new Image(Resources.IMAGE_MEDIA_PLAY, true);
        ICON_PAUSE = new Image(Resources.IMAGE_MEDIA_PAUSE, true);

        Helpers helpers = new Helpers();

        helpers.setAnchorNodeFull(chart, 0.0D, 0.0D, 0.0D, 0.0D);
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

            indexPlayList = playList.indexOf(sound);
        }
    }

    private void loadMediaPlayer() {
        if (soundPlayer != null) {
            soundPlayer.destroy();
        }
        soundPlayer = new SoundPlayer(sound);
        mediaView.setMediaPlayer(soundPlayer.getMediaPlayer());
        soundPlayer.resetSeek();
        soundPlayer.getMediaPlayer().setVolume(volume);
        soundPlayer.play();
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

    public ArrayList<Sound> getPlayList() {
        return playList;
    }

    public Image getICON_PAUSE() {
        return ICON_PAUSE;
    }

    public Image getICON_PLAY() {
        return ICON_PLAY;
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

                    btnPlay.setImage(ICON_PAUSE);
                } else {
                    mediaPlayer.pause();
                    isPlay = false;
                    btnPlay.setImage(ICON_PLAY);
                }
            }else {
                isPlay = true;
                btnPlay.setImage(ICON_PAUSE);
                loadMediaPlayer();
            }
        }
    }

    @FXML
    public void onClickPrevious(){
        indexPlayList--;
        if (indexPlayList < 0) indexPlayList = playList.size()-1;
        try {
            Sound soundPre = playList.get(indexPlayList);
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
        indexPlayList++;
        if (indexPlayList >= playList.size()) indexPlayList = 0;
        try {
            Sound soundNext = playList.get(indexPlayList);
            setSoundPane(soundNext);
        }catch (IndexOutOfBoundsException e){
        }
    }
}
