package com.laab.adventures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreditsActivity extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        back = (Button) findViewById(R.id.backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        Music.menuMusic(this);
    }

    public void goBack() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
