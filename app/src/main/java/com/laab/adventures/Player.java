package com.laab.adventures;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

class Player extends Drawable {
    Rect rectangle;
    Paint display;

    public Player(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        display = new Paint();
        display.setColor(Color.BLACK);
        display.setStyle(Paint.Style.FILL);

    }

    @Override
    void draw(Canvas canvas) {
        canvas.drawRect(x1, y1, x2, y2, display);
    }

    @Override
    public void move(int x, int y) {
        x1 += x;
        x2 += x;
        y1 += y;
        y2 += y;
    }
}
