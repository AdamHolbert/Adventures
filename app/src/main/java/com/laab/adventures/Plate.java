package com.laab.adventures;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Plate extends Drawable {

    private boolean pressed;
    private Rect rectangle;
    private Paint display;
    private Bitmap plateUp;
    private Bitmap plateDown;
    private Door door;


    public Plate (int x1, int y1, GameActivity_Layout layout, Door door){
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + 70;
        this.y2 = y1 + 20;

        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);


        plateUp = BitmapFactory.decodeResource(layout.getResources(), R.drawable.plate);
        plateDown = BitmapFactory.decodeResource(layout.getResources(), R.drawable.plate_down);


        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);

        this.door = door;
    }
    @Override
    public void draw(Canvas canvas) {
//        canvas.drawRect(layout.toPxsWidth(x1), layout.toPxsHeight(y1), layout.toPxsWidth(x2), layout.toPxsHeight(y2), display);

        if(!pressed){
            canvas.drawBitmap(plateUp, layout.toPxsWidth(x1), layout.toPxsHeight(y1), display);
        }
        else {
            canvas.drawBitmap(plateDown, layout.toPxsWidth(x1), layout.toPxsHeight(y1), display);
        }
    }

    public void press(){
        pressed = true;
    }

    public Door getDoor(){
        return door;
    }
}

