package com.laab.adventures.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.laab.adventures.GameActivity_Layout;

public class Button extends Drawable {

    private Bitmap img;


    public Button(GameActivity_Layout layout, int x1, int y1, Bitmap img) {
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1+74;
        this.y2 = y1+74;

        this.img = Bitmap.createScaledBitmap(img,
                (int)layout.toPxsWidth(x2-x1), (int)layout.toPxsHeight(y2-y1), false);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, layout.toPxsWidth(x1), layout.toPxsHeight(y1), null);
    }
}
