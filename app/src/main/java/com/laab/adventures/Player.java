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
    private Bitmap img;
    private Paint display;
    private boolean atFlag;

    public Player(int x1, int y1, GameActivity_Layout layout) {
        super(layout);
        this.x1 = x1;
        this.x2 = x1 +35;
        this.y1 = y1;
        this.y2 = y1 +35;
        rectangle = new Rect();
        rectangle.set(x1, y1, x2, y2);
        atFlag = false;

        img = BitmapFactory.decodeResource(layout.getResources(), R.drawable.objectboi);


        display = new Paint();
        display.setColor(Color.BLACK);
        display.setStyle(Paint.Style.FILL);

    }

    public void atFlag(){
        atFlag = true;
    }

    public boolean isAtFlag(){
        return atFlag;
    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawRect(layout.toPxsWidth(x1), layout.toPxsHeight(y1), layout.toPxsWidth(x2), layout.toPxsHeight(y2), display);
        if(img != null) {
            canvas.drawBitmap(img, layout.toPxsWidth(x1), layout.toPxsHeight(y1), null);
        }
    }

}
