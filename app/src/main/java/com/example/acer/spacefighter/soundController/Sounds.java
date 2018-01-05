package com.example.acer.spacefighter.soundController;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.acer.spacefighter.R;

/**
 * Created by acer on 05/01/2018.
 */

public class Sounds {
    //the mediaplayer objects to configure the background music
    static MediaPlayer gameOnsound;
    private MediaPlayer killedEnemysound;
    private MediaPlayer gameOversound;

    private Context context;
    public Sounds () {
        //initializing the media players for the game sounds
    }

    private static Sounds instance = null;

    public static Sounds getInstance() {
        if(null == instance)
            instance = new Sounds();
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
        //initializing the media players for the game sounds
        gameOnsound = MediaPlayer.create(context, R.raw.gameon);
        killedEnemysound = MediaPlayer.create(context,R.raw.killedenemy);
        gameOversound = MediaPlayer.create(context,R.raw.gameover);
    }

    public void  startOnGameSound() {
        gameOnsound.start();
    }

    public void startKilledSound() {
        killedEnemysound.start();
    }

    public void startGameOverSound() {
        gameOversound.start();
    }

    public void stopMusic() {
        gameOnsound.stop();
    }
}
