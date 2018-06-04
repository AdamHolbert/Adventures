package com.laab.adventures;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.laab.adventures.GameActivity_Layout.beatLevel;


public class GameActivity extends AppCompatActivity {

    private GameActivity_Layout gameActivity_layout;

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

    public void levelComplete() {
        Intent intent = new Intent(this, LevelComplete_Activity.class);
        startActivity(intent);
    }

    public void gameOver() {
        Intent intent = new Intent(this, GameOver_Activity.class);
        startActivity(intent);
    }
}
