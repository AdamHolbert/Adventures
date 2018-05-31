package com.laab.adventures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class Spike extends Drawable {

    private Rect rectangle;
    private Paint display;
    BitmapFactory img;
    GameActivity_Layout layout;

    public Spike(int coordinate){
        this.x1 = coordinate - 15;
        this.y1 = coordinate + 15;
        this.x2 = coordinate + 15;
        this.y2 = coordinate - 15;

        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        // img = BitmapFactory.decodeResource(layout.getResources(), R.drawable.spikes);

        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);


    }
    @Override
    void draw(Canvas canvas) {
        canvas.drawRect(x1, y1, x2, y2, display);
    }
}
