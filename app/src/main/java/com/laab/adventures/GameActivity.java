package com.laab.adventures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jawnnypoo.physicslayout.PhysicsLinearLayout;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        PhysicsLinearLayout physicsLinearLayout = (PhysicsLinearLayout) findViewById(R.id.physics_layout);
        physicsLinearLayout.getPhysics().enableFling();

        Music.levelMusic(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        Music.startMusic();
    }

    @Override
    public void onPause(){
        super.onPause();
        Music.pauseMusic();

    }
}
