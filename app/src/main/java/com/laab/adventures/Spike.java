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
    private Bitmap img;

    public Spike(int x1, int y1, GameActivity_Layout layout){
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 +70;
        this.y2 = y1 +70;

        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        img = BitmapFactory.decodeResource(layout.getResources(), R.drawable.spikes);

        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);


    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect( layout.toPxsWidth(x1), layout.toPxsHeight(y1), layout.toPxsWidth(x2), layout.toPxsHeight(y2),display);
        canvas.drawBitmap(img, layout.toPxsWidth(x1), layout.toPxsHeight(y1), display);
    }
}

