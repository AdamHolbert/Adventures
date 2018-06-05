package com.laab.adventures;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private Button save, load;

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        editText = (EditText) findViewById(R.id.edittext);
        textView = (TextView) findViewById(R.id.loadtext);
        save = (Button) findViewById(R.id.savebutton);
        load = (Button) findViewById(R.id.loadbutton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSave(v);
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLoad(v);
            }
        });

        File dir = new File(path);
        dir.mkdirs();
    }

    public void buttonSave(View view) {
        File file = new File(path + "/savedFile.txt");
        String [] saveText = editText.getText().toString().split(System.getProperty("line.separator"));
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        Save(file, saveText);
    }

    public void buttonLoad(View view) {
        File file = new File(path + "/savedFile.txt");
        String [] loadText = Load(file);

        String finalString = "";

        for (int i = 0; i < loadText.length; i++) {
            finalString += loadText[i] + System.getProperty("line.seperator");
        }
        textView.setText(finalString);

    }

    private void Save(File file, String[] data) {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    if(fos != null){
                        fos.write(data[i].getBytes());
                        if (i < data.length-1)
                        {
                            fos.write("\n".getBytes());
                        }
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                if(fos != null) {
                    fos.close();
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }

    private String[] Load(File file) {
        String[] array = new String[0];
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        if(fis != null) {
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String test;
            int words = 0;
            try {
                while ((test = br.readLine()) != null) {
                    words++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis.getChannel().position(0);
            } catch (IOException e) {
                e.printStackTrace();
            }

            array = new String[words];

            String line;
            int i = 0;
            try {
                while ((line = br.readLine()) != null) {
                    array[i] = line;
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return array;
    }
}
