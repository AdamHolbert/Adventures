package com.laab.adventures;

import com.laab.adventures.Drawable;
import com.laab.adventures.GameActivity_Layout;
import com.laab.adventures.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Flag extends Drawable {

    private Rect rectangle;
    private Paint display;
    private Bitmap image;

    public Flag(int x1, int y1, GameActivity_Layout layout) {
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        x2 = x1 + 16;
        y2 = y1 + 16;

        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        image = BitmapFactory.decodeResource(layout.getResources(), R.drawable.flag);

        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, layout.toPxs(x1), layout.toPxs(y1), display);
    }
}
