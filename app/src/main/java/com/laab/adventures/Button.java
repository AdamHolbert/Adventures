package com.laab.adventures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Button extends Drawable {

    private Bitmap img;
    private Rect rectangle;
    private Paint display;


    public Button(GameActivity_Layout layout, int x1, int y1, Bitmap img) {
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1+100;
        this.y2 = y1+100;

        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);

        this.img = img;

        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void draw(Canvas canvas) {
//        canvas.drawRect(layout.toPxsWidth(x1), layout.toPxsWidth(y1), layout.toPxsWidth(x2), layout.toPxsWidth(y2), display);
        canvas.drawBitmap(img, layout.toPxsWidth(x1), layout.toPxsHeight(y1), display);
    }
}
