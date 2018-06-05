package com.laab.adventures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LevelsActivity extends AppCompatActivity {
    Button back, level1, level2, level3;
    private static int level = 0;
    private static int highestBeatLevel = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);


        back = (Button) findViewById(R.id.backbutton);
        level1 = (Button) findViewById(R.id.level1);
        level2 = (Button) findViewById(R.id.level2);
        level3 = (Button) findViewById(R.id.level3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });


        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = 1;
                openLevel();
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = 2;
                openLevel();
            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = 3;
                openLevel();
            }
        });

        if (highestBeatLevel < 1) {
            level2.setEnabled(false);
            level2.setText("Locked");
        }
        else{
            level2.setText("Level 2");
        }

        if (highestBeatLevel < 2) {
            level3.setEnabled(false);
            level3.setText("Locked");
        }
        else{
            level3.setText("Level 3");
        }
    }

    private void openLevel() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void goBack() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public static void beatLevel() {
        beatLevel(level);
    }

    public static void beatLevel(int level) {
        if(highestBeatLevel < level){
            highestBeatLevel = level;
            HomeScreen.saveConfig();
        }
    }

    @Override
    public void onResume () {
        super.onResume();
        Music.startMusic();
    }

    @Override
    public void onPause () {
        super.onPause();
        Music.pauseMusic();
    }

    public int getLevel () {
        return level;
    }

    public static int getHighestLevel(){
        return highestBeatLevel;
    }
}
