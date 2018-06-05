package com.laab.adventures;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

class Player extends Drawable {
    private Bitmap img;
    private boolean atFlag;

    public Player(int x1, int y1, GameActivity_Layout layout) {
        super(layout);
        this.x1 = x1;
        this.x2 = x1 + 32;
        this.y1 = y1;
        this.y2 = y1 + 32;
        atFlag = false;

        img = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(layout.getResources(), R.drawable.objectboi)
                , (int)layout.toPxsWidth(x2-x1), (int)layout.toPxsHeight(y2-y1), false);
    }

    public void atFlag(){
        atFlag = true;
    }

    public boolean isAtFlag(){
        return atFlag;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, layout.toPxsWidth(x1), layout.toPxsHeight(y1), null);
    }

}
