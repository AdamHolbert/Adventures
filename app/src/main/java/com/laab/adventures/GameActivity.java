package com.laab.adventures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jawnnypoo.physicslayout.PhysicsLinearLayout;

public class GameActivity extends AppCompatActivity {

    GameActivity_Layout gameActivity_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameActivity_layout = new GameActivity_Layout(this);
        setContentView(gameActivity_layout);
        Music.levelMusic(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameActivity_layout.resume();
        Music.startMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameActivity_layout.pause();
        Music.pauseMusic();
    }
}
