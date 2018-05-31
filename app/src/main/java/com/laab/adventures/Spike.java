package com.laab.adventures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;

public class Spike extends Drawable {

    Rect rectangle;
    Paint display;

    public Spike(int coordinate, GameActivity_Layout layout){
        super(layout);
        this.x1 = coordinate - 15;
        this.y1 = coordinate + 15;
        this.x2 = coordinate + 15;
        this.y2 = coordinate - 15;

        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);
    }
    @Override
    void draw(Canvas canvas) {
        canvas.drawRect(x1, y1, x2, y2, display);
    }
}
