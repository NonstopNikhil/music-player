package com.template;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.Objects;

public class var_manage {

    double max_time;
    int minute,second,minute_t,second_t;

    String minute_s,second_s;
    String m = "" , s = "";

    protected File file;

    protected MediaPlayer mediaPlayer;
    protected Media media;

    Duration duration;
    boolean whatever = true;

    protected Image pause = new Image(Objects.requireNonNull(getClass().getResourceAsStream("pause.png")));
    protected Image play = new Image(Objects.requireNonNull(getClass().getResourceAsStream("play.png")));
    protected Image prev = new Image(Objects.requireNonNull(getClass().getResourceAsStream("prev.png")));
    protected Image next = new Image(Objects.requireNonNull(getClass().getResourceAsStream("next.png")));


}
