package com.laab.adventures;

import android.media.MediaPlayer;

public class Music {

    private static int curretMusic = 0;
    private static MediaPlayer music;
    static boolean muted;

    static private void startMusic(){
        if(!muted){
            music.setLooping(true);
            music.start();
        }
    }

    static void levelMusic() {
        startMusic();
    }

    static void menuMusic(){

    }
}
