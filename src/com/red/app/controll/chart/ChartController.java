package com.red.app.controll.chart;

import com.red.app.App;
import com.red.app.config.Resources;
import com.red.app.controll.HomeController;
import com.red.app.controll.playlist.ItemPlaylistController;
import com.red.app.helpers.Helpers;
import com.red.app.helpers.Request;
import com.red.app.media.SoundInfo;
import com.red.app.zingmp3.ZingAPI;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import com.red.app.zingmp3.ZingInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChartController {
    public static String dataChart;
    @FXML VBox content;

    public void getData() {
        new Thread() {
            public void run() {
                boolean check = getDataChart();

                try {
                    sleep(50L);
                } catch (InterruptedException var3) {
                    var3.printStackTrace();
                }

                if (!check) {
                    getData();
                } else {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            showPage();
                        }
                    });
                }
            }
        }.start();
    }

    public boolean getDataChart() {
        Map<String, String> paramChart = new HashMap<String, String>();
        paramChart.put("type", "song");
        paramChart.put("time", "-1");
        paramChart.put("count", "100");
        String urlData = null;

        try {
            urlData = ZingAPI.buildAPIURL(ZingInfo.URL_DETAIL, paramChart);
            Request request = new Request();
            dataChart = request.grab_content(urlData);
            return true;
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException var4) {
            return false;
        }
    }

    public void initialize() {
        getData();
    }

    public void showPage() {
        if (dataChart == null) {
            getData();
        } else {
            JSONObject json = null;

            try {
                json = new JSONObject(dataChart);
                int status = json.getInt("err");
                if (status != 0) {
                    getData();
                    return;
                }

                JSONObject data = json.getJSONObject("data");
                JSONObject promote = data.getJSONObject("promote");
                JSONArray items = data.getJSONArray("items");
                JSONObject chart = data.getJSONObject("chart");
                this.showList(items);
            } catch (JSONException var7) {
                getData();
            }
        }
    }

    private void showList(JSONArray items) {
        Helpers helpers = new Helpers();
        HomeController homeController = App.homeController;
        boolean loadPlaylist = false;
        if (homeController.getPlaylistContent().getChildren().size() == 0) {
            loadPlaylist = true;
        }

        try {
            for(int i = 0; i < items.length(); ++i) {
                JSONObject item = items.getJSONObject(i);
                SoundInfo sound = new SoundInfo(item);
                FXMLLoader itemPrefab = new FXMLLoader(App.getResource(Resources.BODY_CHART_ITEM));
                HBox itemNode = itemPrefab.load();
                ChartItemController itemController = itemPrefab.getController();
                itemController.setSound(i + 1, sound);
                this.content.getChildren().add(itemNode);
                helpers.setAnchorNodeFull(itemNode, 0.0D, 0.0D, 0.0D, 0.0D);
                if (loadPlaylist) {
                    FXMLLoader itemPlaylistXML = new FXMLLoader(App.getResource(Resources.PLAYLIST_ITEM));
                    AnchorPane itemPl = itemPlaylistXML.load();
                    helpers.setAnchorNodeFull(itemPl, 0.0D, 0.0D, 0.0D, 0.0D);
                    ItemPlaylistController itemPlaylist = itemPlaylistXML.getController();
                    itemPlaylist.setSound(sound);
                    homeController.getPlaylistContent().getChildren().add(itemPl);
                    homeController.getPlayList().add(sound);
                }

                if (i < items.length() - 1) {
                    itemNode.getStyleClass().add("border_bottom");
                }
            }
        } catch (IOException | JSONException var14) {
            var14.printStackTrace();
        }

        homeController.setActiveNodeLoad(false);
    }
}
