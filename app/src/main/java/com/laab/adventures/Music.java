package com.laab.adventures;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class Music {

    private static final int levelMusic = R.raw.level;
    private static final int menuMusic = R.raw.background;
    private static int currentMusic = 0;
    private static MediaPlayer music = new MediaPlayer();
    private static boolean muted = false;
    private static Context lastContext = null;

    static void startMusic(){
        if(!muted){
            if(!music.isPlaying()) {
                music.start();
            }
        }
    }

    static void pauseMusic(){
        if(music.isPlaying()) {
            music.pause();
        }
    }

    static void toggleMute(){
        muted = !muted;
        if(muted){
            pauseMusic();
        }
        else{
            changeMusic(lastContext, currentMusic);
        }
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
        lastContext = context;
        if(music.isPlaying()) {
            pauseMusic();
        }
        currentMusic = newMusic;
        music = MediaPlayer.create(context, currentMusic);
        music.setLooping(true);
        startMusic();
    }

}
