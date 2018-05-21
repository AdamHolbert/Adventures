package com.laab.adventures;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {

    private static final int levelMusic = R.raw.level;
    private static final int menuMusic = R.raw.background;
    private static int currentMusic;
    private static MediaPlayer music;
    static boolean muted;

    static void startMusic(){
        if(!muted){
            music.setLooping(true);
            music.start();
        }
    }

    static void stopMusic(){
        music.stop();
    }

    static void toggleMute(){
        muted = !muted;
    }

    static void levelMusic(Context context) {
        if(currentMusic != levelMusic || !music.isPlaying()) {
            changeMusic(context, levelMusic);
        }
    }

    static void menuMusic(Context context){
        if(currentMusic != menuMusic || !music.isPlaying()){
            changeMusic(context, menuMusic);
        }
    }

    private static void changeMusic(Context context, int newMusic){
        stopMusic();
        currentMusic = newMusic;
        music = MediaPlayer.create(context, currentMusic);
        startMusic();
    }
}
