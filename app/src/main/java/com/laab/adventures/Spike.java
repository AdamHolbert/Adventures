package com.laab.adventures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class Spike extends Drawable {

    private Rect rectangle;
    private Paint display;
    Bitmap img;

    public Spike(int x1, int y1, int x2, int y2, GameActivity_Layout layout){
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        img = BitmapFactory.decodeResource(layout.getResources(), R.drawable.spikes);

        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);


    }
    @Override
    void draw(Canvas canvas) {
        canvas.drawBitmap(img, layout.toPxs(x1), layout.toPxs(y1), display);
        }
    }
}
