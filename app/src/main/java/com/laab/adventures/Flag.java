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

    private Paint display;
    private Bitmap image;

    public Flag(int x1, int y1, GameActivity_Layout layout) {
        this(x1, y1, x1+60, y1+75, layout);
    }
    public Flag(int x1, int y1, int x2, int y2, GameActivity_Layout layout) {
        super(layout);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(layout.getResources(), R.drawable.flag),
                (int)layout.toPxsWidth(x2-x1), (int)layout.toPxsHeight(y2-y1), false);

        display = new Paint();
        display.setColor(Color.RED);
        display.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas){
//        canvas.drawRect(layout.toPxsWidth(x1), layout.toPxsHeight(y1), layout.toPxsWidth(x2), layout.toPxsHeight(y2), display);
        canvas.drawBitmap(image, layout.toPxsWidth(x1), layout.toPxsHeight(y1), display);
    }
}
