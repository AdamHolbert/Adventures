package com.laab.adventures;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Wall extends Drawable {

    private Rect rectangle;
    private Paint display;

    public Wall(int x1, int y1, int x2, int y2, GameActivity_Layout layout){
        super(layout);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        display = new Paint();
        display.setColor(Color.LTGRAY);
        display.setStyle(Paint.Style.FILL);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(layout.toPxs(x1), layout.toPxs(y1), layout.toPxs(x2), layout.toPxs(y2), display);
    }

    @Override
    public void move(int x, int y) {
        //Walls don't move
    }
}
