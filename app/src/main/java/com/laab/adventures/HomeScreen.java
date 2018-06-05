package com.laab.adventures;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    private Button levels;
    private Button instructions;
    private Button settings;
    public static File path;
    static boolean loadedOnce = false;

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
        Music.menuMusic(this);

        if(!loadedOnce) {
            path = getFilesDir();
            path.mkdirs();
            loadConfig();
            loadedOnce = true;
        }
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


    public static void saveConfig() {
        File file = new File(path + "/savedFile.txt");
        ArrayList<String> contents = new ArrayList<>();
        contents.add("Level:" + LevelsActivity.getHighestLevel());
        contents.add("Music:" + (Music.isMuted() ? "False" : "True"));
        Save(file, contents.toArray(new String[contents.size()]));
    }

    public void loadConfig() {
        File file = new File(path + "/savedFile.txt");
        String [] settings = Load(file);

        if(settings.length > 0){
            // Level:1
            LevelsActivity.beatLevel(Integer.parseInt(settings[0].split(":")[1]));

            //Music:True
            if(settings[1].split(":")[1].equalsIgnoreCase("true")){
                Music.setMute(false);
            } else {
                Music.setMute(true);
            }
            Toast.makeText(getApplicationContext(), "Settings loaded", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "No settings found", Toast.LENGTH_LONG).show();
            saveConfig();
        }
    }

    private static void Save(File file, String[] data) {
        try(FileOutputStream fos = new FileOutputStream(file)){
            for (int i = 0; i<data.length; i++)
            {
                fos.write(data[i].getBytes());
                if (i < data.length-1)
                {
                    fos.write("\n".getBytes());
                }
            }
        }catch (FileNotFoundException e) {
            Log.i("","");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String[] Load(File file) {
        ArrayList<String> words = new ArrayList<>();
        try
        {
            FileInputStream fis = new FileInputStream(file);
            try(InputStreamReader isr = new InputStreamReader(fis)){
                try(BufferedReader br = new BufferedReader(isr)){
                    try {
                        String contents = "";
                        while ((contents = br.readLine()) != null) {
                            words.add(contents);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words.toArray(new String[words.size()]);
    }
}
