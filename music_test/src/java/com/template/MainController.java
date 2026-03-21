package com.template;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.*;


public class MainController extends var_manage
{
    @FXML private Slider time_slider;
    @FXML private Slider volume_control;
    @FXML private Label time_label;
    @FXML private Label volume_label;
    @FXML private Label time_label2;
    @FXML private Label title;
    @FXML private ImageView play_png;
    @FXML private ImageView prev_png;
    @FXML private ImageView next_png;
    @FXML private ProgressBar progressbar;
    @FXML private ProgressBar vol_progressbar;
    @FXML private VBox center_vbox;

    @FXML private void initialize()
    {

        //center_vbox.getChildren().add(title);


        System.out.println("FXML loaded successfully!");
        String path = "src/resourses/com/template/its_nothing.mp3";
        File file = new File(path);

        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        duration = mediaPlayer.getCurrentTime();
        mediaPlayer.setVolume(volume_control.getValue() / 100);

        media.durationProperty().addListener((obs,old,New)->{
            System.out.println("Duration "+ New.toSeconds() );
        });

        play_png.setImage(play);
        prev_png.setImage(prev);
        next_png.setImage(next);

        mediaPlayer.setOnReady(()->{

            time_slider.setMax( (int) mediaPlayer.getTotalDuration().toSeconds());
            max_time = mediaPlayer.getTotalDuration().toSeconds();

            minute_t = (int) max_time / 60;
            second_t = (int) max_time % 60;

            //if-else was causing problems, so I had to use ternary operator :)
            m = minute_t < 10 ? "0" + minute_t : "" + minute_t;
            s = second_t < 10 ? "0" + second_t : "" + second_t;

            time_label2.setText(m + ":" + s);
            title.setText("" + media.getMetadata().get("title"));

        });

        volume_control.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(volume_control.isValueChanging()){
                    mediaPlayer.setVolume(volume_control.getValue() / 100);
                    volume_label.setText("Volume: "+ (int)volume_control.getValue());
                }
            }
        });
        //progressbar behind volume slider
        volume_control.valueProperty().addListener((ob,old,ne)->{
            vol_progressbar.setProgress(ne.doubleValue() / volume_control.getMax());
        });

        time_label.setText("00:00");

        time_slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(time_slider.isValueChanging()){

                    mediaPlayer.seek(Duration.seconds(time_slider.getValue()));
                    if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                        whatever = false;
                        play_png.setImage(pause);
                        mediaPlayer.play();
                    }
                    else {
                        mediaPlayer.play();
                    }
                }
            }
        });
        //progressbar behind time slider
        time_slider.valueProperty().addListener((obs,oldVal,newVal) -> {
            progressbar.setProgress(newVal.doubleValue() / time_slider.getMax());
        });

        mediaPlayer.currentTimeProperty().addListener((obs,oldVal,newVal)->{
            time_slider.setValue(newVal.toSeconds());
            minute = (int)newVal.toSeconds() / 60;
            second = (int)newVal.toSeconds() % 60;

            minute_s = minute < 10 ? "0" + minute : "" + minute;
            second_s = second < 10 ? "0" + second : "" + second;

            time_label.setText(minute_s + ":" + second_s);
        });


        volume_label.setText("Volume: "+ (int)volume_control.getValue());


    }
    public void play_func() {

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

    public void next_func() {}

    public void prev_func() {}

    public void add_fold() {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Add Music");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Music", "*.mp3*"));

        List <File> list = chooser.showOpenMultipleDialog(null);

        if (list != null) {
            for (File f: list){
                System.out.println(f.getAbsolutePath());
            }
        }
        else {
            System.out.println("Nope");
        }
    }

}