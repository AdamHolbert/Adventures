package com.laab.adventures;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private Button credits;
    private Button back;
    private Button mute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        credits = (Button) findViewById(R.id.creditsbutton);
        back = (Button) findViewById(R.id.backbutton);
        mute = (Button) findViewById(R.id.soundbutton);

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCredits();
            }
        });
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMute();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        Music.menuMusic(this);
        updateMuteButton();
    }

    private void updateMuteButton() {
        if(Music.isMuted()){
            mute.setBackgroundColor(Color.DKGRAY);
            mute.setText("SOUND - OFF");
        } else {
            mute.setBackgroundColor(Color.BLACK);
            mute.setText("SOUND - ON ");
        }
    }

    public void openCredits() {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

    public void toggleMute(){
        Music.toggleMute();
        updateMuteButton();
    }

    public void goBack() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
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
