package com.laab.adventures.models;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.laab.adventures.GameActivity_Layout;
import com.laab.adventures.R;

public class Plate extends Drawable {

    private boolean pressed;
    private Bitmap plateUp;
    private Bitmap plateDown;
    private Door door;
    private boolean yBump;


    public Plate (int x1, int y1, GameActivity_Layout layout, Door door){
        this(x1, y1, x1+64, y1 + 20, layout, door);
    }
    public Plate (int x1, int y1, int x2, int y2, GameActivity_Layout layout, Door door){
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        yBump = false;

        plateUp = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(layout.getResources(), R.drawable.plate),
                (int)layout.toPxsWidth(x2-x1), (int)layout.toPxsHeight(y2-y1), false);

        plateDown = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(layout.getResources(), R.drawable.plate_down),
                (int)layout.toPxsWidth(x2-x1), (int)layout.toPxsHeight((y2-y1)/2), false);

        this.door = door;
    }
    @Override
    public void draw(Canvas canvas) {
//        canvas.drawRect(layout.toPxsWidth(x1), layout.toPxsHeight(y1), layout.toPxsWidth(x2), layout.toPxsHeight(y2), display);

        if(!pressed){
            canvas.drawBitmap(plateUp, layout.toPxsWidth(GetXMin()), layout.toPxsHeight(GetYMin()), null);
        }
        else {
            canvas.drawBitmap(plateDown, layout.toPxsWidth(GetXMin()), layout.toPxsHeight(GetYMin()), null);
        }
    }

    @Override
    public int GetYMin() {
        int y1 = super.GetYMin() + (pressed ? (y2-this.y1)/2 : 0);
        return yBump ? y1 - 1 : y1;
    }

    public void turnOnYBump(){
        yBump = true;
    }

    public void turnOffYBump(){
        yBump = false;
    }

    public void press(){
//        y1 += layout.toPxsHeight(1);
//        y2 += layout.toPxsHeight(1);
//        rectangle.set(x1, y1, x2, y2);
        pressed = true;
    }

    public Door getDoor(){
        return door;
    }

    public void unpress() {
        pressed = false;
    }
}

