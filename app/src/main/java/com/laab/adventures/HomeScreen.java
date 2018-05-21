package com.laab.adventures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    Button levels;
    Button instructions;
    Button settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        levels = (Button) findViewById(R.id.levelsbutton);
        instructions = (Button) findViewById(R.id.instructionsbutton);
        settings = (Button) findViewById(R.id.settingsbutton);

        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLevels();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstructions();
            }
        });

//        Music.menuMusic(this);
    }

    public void openLevels() {
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }

    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openInstructions() {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }
}
