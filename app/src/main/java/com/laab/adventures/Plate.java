package com.laab.adventures;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Plate extends Drawable {

    private Rect rectangle;
    private Paint display;
    private Bitmap img;
    private Door door;


    public Plate (int x1, int y1, GameActivity_Layout layout, Door door){
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + 40;
        this.y2 = y1 + 32;

        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);


        img = BitmapFactory.decodeResource(layout.getResources(), R.drawable.plate);


        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);

        this.door = door;
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(layout.toPxsWidth(x1), layout.toPxsHeight(y1), layout.toPxsWidth(x2), layout.toPxsHeight(y2), display);
        canvas.drawBitmap(img, layout.toPxsWidth(x1), layout.toPxsHeight(y1), display);
    }

    public Door getDoor(){
        return door;
    }
}

