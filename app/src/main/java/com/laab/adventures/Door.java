package com.laab.adventures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;

public class Door extends Drawable {
    private boolean isOpen;
    private Rect rectangle;
    private Paint display;
    Bitmap closed;
    Bitmap open;

    public Door(int x1, int y1, GameActivity_Layout layout) {
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + 16;
        this.y2 = y1 + 16;

        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        closed = BitmapFactory.decodeResource(layout.getResources(), R.drawable.door);
        open = BitmapFactory.decodeResource(layout.getResources(), R.drawable.flag);

        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);
    }

    @Override
    void draw(Canvas canvas) {
        if(isOpen){
            canvas.drawBitmap(open, layout.toPxs(x1), layout.toPxs(y1), display);
        } else {
            canvas.drawBitmap(closed, layout.toPxs(x1), layout.toPxs(y1), display);
        }
    }

    public boolean getIsOpen(){
        return isOpen;
    }

    public void open(){
        isOpen = true;
    }
    public void close(){
        isOpen = false;
    }
}
