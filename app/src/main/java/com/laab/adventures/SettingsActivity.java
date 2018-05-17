package com.laab.adventures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button credits;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        credits = (Button) findViewById(R.id.creditsbutton);
        back = (Button) findViewById(R.id.backbutton);

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCredits();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    public void openCredits() {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

    public void goBack() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}
