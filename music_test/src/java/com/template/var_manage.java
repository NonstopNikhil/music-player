package com.template;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Objects;

public class var_manage {

    double max_time;
    int minute,second,minute_t,second_t;

    String minute_s,second_s;
    String m = "" , s = "";

    MediaPlayer mediaPlayer;
    Media media;

    Duration duration;
    boolean whatever = true;

    Image pause = new Image(Objects.requireNonNull(getClass().getResourceAsStream("pause.png")));
    Image play = new Image(Objects.requireNonNull(getClass().getResourceAsStream("play.png")));
    Image prev = new Image(Objects.requireNonNull(getClass().getResourceAsStream("prev.png")));
    Image next = new Image(Objects.requireNonNull(getClass().getResourceAsStream("next.png")));


}
