package com.laab.adventures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

class Player extends Drawable {
    private Rect rectangle;
    private Bitmap image;
    private Paint display;

    public Player(int x1, int y1, GameActivity_Layout layout) {
        super(layout);
        this.x1 = x1;
        this.x2 = x1 +16;
        this.y1 = y1;
        this.y2 = y1 +16;
        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        image = BitmapFactory.decodeResource(layout.getResources(), R.drawable.objectboi);

        display = new Paint();
        display.setColor(Color.BLACK);
        display.setStyle(Paint.Style.FILL);

    }

    @Override
    void draw(Canvas canvas) {
        Log.i("Player", "Drawing!");
        if(image != null) {
            canvas.drawBitmap(image, layout.toPxs(x1), layout.toPxs(y1), null);
        }
//        canvas.drawRect(x1, y1, x2, y2, display);
    }

}
