package com.laab.adventures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LevelsActivity extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        back = (Button) findViewById(R.id.backbutton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    public void goBack() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void moveToLevel(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void moveToPhysics(View view) {
        Intent intent = new Intent(this, PhysicsTest.class);
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
