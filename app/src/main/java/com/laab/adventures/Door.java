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
    private Bitmap closed;
    private Bitmap open;

    public Door(int x1, int y1, GameActivity_Layout layout) {
        this(x1, y1, x1 + 80, y1+13, layout);
    }
    public Door(int x1, int y1, int x2, int y2, GameActivity_Layout layout) {
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        closed = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(layout.getResources(), R.drawable.door),
                (int)layout.toPxsWidth(x2-x1), (int)layout.toPxsHeight(y2-y1), false);

        open = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(layout.getResources(), R.drawable.open_door),
                (int)layout.toPxsWidth(x2-x1), (int)layout.toPxsHeight(y2-y1), false);
    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawRect(layout.toPxsWidth(x1), layout.toPxsHeight(y1), layout.toPxsWidth(x2), layout.toPxsHeight(y2), display);
        if(isOpen){
            canvas.drawBitmap(open, layout.toPxsWidth(x1), layout.toPxsHeight(y1), null);
        } else {
            canvas.drawBitmap(closed, layout.toPxsWidth(x1), layout.toPxsHeight(y1), null);
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
