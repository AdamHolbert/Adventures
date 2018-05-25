package com.laab.adventures;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

public class PhysicsTest extends Activity {

    Physics_Test_Layout physics_test_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        physics_test_layout = new Physics_Test_Layout(this);
        setContentView(physics_test_layout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        physics_test_layout.pause();
        Music.pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        physics_test_layout.resume();
        Music.startMusic();
    }
}
