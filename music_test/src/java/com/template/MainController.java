package com.template;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.*;

import javax.swing.*;


public class MainController extends var_manage
{
    @FXML
    private Slider time_slider;
    @FXML
    private Slider volume_control;
    @FXML
    private Label time_label;
    @FXML
    private Label volume_label;
    @FXML
    private Label time_label2;
    @FXML
    private Label title;
    @FXML
    private ImageView play_png;
    @FXML
    private ImageView prev_png;
    @FXML
    private ImageView next_png;
    @FXML
    private ProgressBar progressbar;
    @FXML
    private ProgressBar vol_progressbar;
    @FXML
    private void initialize()
    {

        System.out.println("FXML loaded successfully!");
        String path = "src/resourses/com/template/its_nothing.mp3";
        File file = new File(path);

        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        duration = mediaPlayer.getCurrentTime();
        mediaPlayer.setVolume(volume_control.getValue() / 100);
        play_png.setImage(play);
        prev_png.setImage(prev);
        next_png.setImage(next);


        volume_label.setText("Volume: "+ (int)volume_control.getValue());


    }

    public void play() {

        if (whatever) {
            play_png.setImage(pause);
            mediaPlayer.setStartTime(duration);
            mediaPlayer.play();
            whatever=false;
        }
        else {
            play_png.setImage(play);
            duration = mediaPlayer.getCurrentTime();
            mediaPlayer.pause();

            whatever=true;
        }


    }

}