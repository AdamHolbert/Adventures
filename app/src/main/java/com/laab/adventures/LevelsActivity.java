package com.laab.adventures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LevelsActivity extends AppCompatActivity {
    Button back, level1, level2, level3;
    private static int level = 0;
    private static boolean level2Locked = true, level3Locked = true;
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
                goLevel1(v);
                level = 1;
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLevel2(v);
                level = 2;
            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLevel3(v);
                level = 3;
            }
        });

        if (level2Locked) {
            level2.setEnabled(false);
            level2.setText("Locked");
        }
        else{
            level2.setText("Level 2");
        }

        if (level3Locked) {
            level3.setEnabled(false);
            level3.setText("Locked");
        }
        else{
            level3.setText("Level 3");
        }
    }

        public void goBack() {
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }

        public void goFile() {
            Intent intent = new Intent(this, FileActivity.class);
            startActivity(intent);
        }

        public void goLevel1(View view){
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }

        public void goLevel2 (View view){
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }

        public void goLevel3 (View view){
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }

        public void beatCurrentLevel () {
            switch (level) {
                case 0:
                    break;
                case 1:
                    level2Locked = false;
                    break;
                case 2:
                    level3Locked = false;
                    break;
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
    }
