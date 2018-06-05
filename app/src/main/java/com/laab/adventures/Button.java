package com.laab.adventures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Button extends Drawable {

    private Bitmap img;


    public Button(GameActivity_Layout layout, int x1, int y1, Bitmap img) {
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1+100;
        this.y2 = y1+100;

        this.img = Bitmap.createScaledBitmap(img,
                (int)layout.toPxsWidth(x2-x1), (int)layout.toPxsHeight(y2-y1), false);
        ;

    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawBitmap(img, layout.toPxsWidth(x1), layout.toPxsHeight(y1), null);
    }
}
