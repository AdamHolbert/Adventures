package com.laab.adventures.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.laab.adventures.GameActivity_Layout;
import com.laab.adventures.R;

public class Player extends Drawable {
    private Bitmap img;
    private boolean atFlag;
    private boolean isGoon;
    private boolean dragTag;
    private Bitmap dragImg;
    private boolean everDragged;

    public Player(int x1, int y1, GameActivity_Layout layout, boolean isGoon) {
        this(x1, y1, x1+32, y1+32, layout, isGoon);
    }

    public Player(int x1, int y1, int x2, int y2, GameActivity_Layout layout, boolean isGoon) {
        super(layout);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.isGoon = isGoon;
        atFlag = false;
        this.dragTag = false;
        this.everDragged = false;

        img = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(layout.getResources(), isGoon ? R.drawable.goonboi : R.drawable.objectboi)
                , (int)layout.toPxsWidth(x2-x1), (int)layout.toPxsHeight(y2-y1), false);
        this.dragImg = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(layout.getResources(), R.drawable.drag)
                , (int)layout.toPxsWidth(100), (int)layout.toPxsHeight(40), false);
    }

    public void atFlag(){
        atFlag = true;
    }

    public void setDragTag(boolean dragTag) {
        this.dragTag = dragTag;
    }

    public boolean isAtFlag(){
        return atFlag;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, layout.toPxsWidth(x1), layout.toPxsHeight(y1), null);
        if(dragTag){
            canvas.drawBitmap(dragImg, layout.toPxsWidth(GetXMin()+((GetXMax()-GetXMin())/2)-(100/2)), layout.toPxsHeight(GetYMin() - 45), null);
        }
    }

    public boolean isGoon() {
        return isGoon;
    }

    public boolean hasEverDragged() {
        return everDragged;
    }

    public void dragging() {
        this.everDragged = true;
    }

    public float xCenter(){
        return ((GetXMax()-GetXMin())/2 + GetXMin());
    }

    public float yCenter(){
        return ((GetYMax()-GetYMin())/2 + GetYMin());
    }
}
