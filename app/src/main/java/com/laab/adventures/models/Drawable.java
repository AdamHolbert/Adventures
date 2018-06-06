package com.laab.adventures.models;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.laab.adventures.GameActivity_Layout;

import java.util.ArrayList;

public abstract class Drawable {


    protected int x1, y1, x2, y2;
    GameActivity_Layout layout;

    public Drawable(GameActivity_Layout layout){
        this.layout = layout;
    }

    public boolean collidedWith(Drawable obj){
        boolean collision = false;
//        if(this.GetXMax() >= obj.GetXMin()){ //right
//            if(this.GetYMax() >= obj.GetYMin()){ //bottom
//                collision = true;
//            }
//            else if(this.GetYMin() >= obj.GetYMax()){ //top
//                collision = true;
//            }
//        }
//        else if(this.GetXMin() >= obj.GetXMax()){ //left
//            if(this.GetYMax() >= obj.GetYMin()){ //bottom
//                collision = true;
//            }
//            else if(this.GetYMin() >= obj.GetYMax()){ //top
//                collision = true;
//            }
//        }
        Rect thisObj = new Rect();
        thisObj.set(x1, y1, x2, y2);

        Rect thatObj = new Rect();
        thatObj.set(obj.GetXMin(), obj.GetYMin(), obj.GetXMax(), obj.GetYMax());

        //is the ______ corner of obj inside of this
        boolean topLeft = coordInsideRect(thatObj.top, thatObj.left, thisObj);
        boolean topRight = coordInsideRect(thatObj.top, thatObj.right, thisObj);
        boolean bottomLeft = coordInsideRect(thatObj.bottom, thatObj.left, thisObj);
        boolean bottomRight = coordInsideRect(thatObj.bottom, thatObj.right, thisObj);


        //is the ______ corner of this inside of obj
        boolean topLeftO = coordInsideRect(thisObj.top, thisObj.left, thatObj);
        boolean topRightO = coordInsideRect(thisObj.top, thisObj.right, thatObj);
        boolean bottomLeftO = coordInsideRect(thisObj.bottom, thisObj.left, thatObj);
        boolean bottomRightO = coordInsideRect(thisObj.bottom, thisObj.right, thatObj);

        return topLeft || topRight || bottomLeft || bottomRight || topLeftO || topRightO || bottomLeftO || bottomRightO;
    }

    public ArrayList<Sides> AdvancedCollision(Drawable obj){
        ArrayList<Sides> sides = new ArrayList<Sides>();

        Rect thisObj = new Rect();
        thisObj.set(x1, y1, x2, y2);

        Rect thatObj = new Rect();
        thatObj.set(obj.GetXMin(), obj.GetYMin(), obj.GetXMax(), obj.GetYMax());

        //is the ______ corner of obj inside of this
        boolean topLeft = coordInsideRect(thisObj.top, thisObj.left, thatObj);
        boolean topRight = coordInsideRect(thisObj.top, thisObj.right, thatObj);
        boolean bottomLeft = coordInsideRect(thisObj.bottom, thisObj.left, thatObj);
        boolean bottomRight = coordInsideRect(thisObj.bottom, thisObj.right, thatObj);


        //is the ______ corner of this inside of obj
        boolean topLeftO = coordInsideRect(thatObj.top, thatObj.left, thisObj);
        boolean topRightO = coordInsideRect(thatObj.top, thatObj.right, thisObj);
        boolean bottomLeftO = coordInsideRect(thatObj.bottom, thatObj.left, thisObj);
        boolean bottomRightO = coordInsideRect(thatObj.bottom, thatObj.right, thisObj);


        if(topLeftO){
            bottomRight = true;
        }
        if(topRightO){
            bottomLeft = true;
        }
        if(bottomLeftO){
            topRight = true;
        }
        if(bottomRightO){
            topLeft = true;
        }

        if(topLeft && topRight){
            sides.add(Sides.Top);
        }
        if(topLeft && bottomLeft){
            sides.add(Sides.Left);
        }
        if(topRight && bottomRight){
            sides.add(Sides.Right);
        }
        if(bottomLeft && bottomRight){
            sides.add(Sides.Bottom);
        }

        if (topLeft && !(bottomLeft || topRight)) {
            int thing = cornerCheck(thisObj.left, thisObj.top, thatObj.right, thatObj.bottom);
            if (thing > 0) {
                sides.add(Sides.Top);
            } else if (thing < 0) {
                sides.add(Sides.Left);
            } else {
                sides.add(Sides.Top);
                sides.add(Sides.Left);
            }
        }
        if (topRight && !(topLeft || bottomRight)) {
            int thing = cornerCheck(thatObj.left, thisObj.top, thisObj.right, thatObj.bottom);
            if (thing > 0) {
                sides.add(Sides.Top);
            } else if (thing < 0) {
                sides.add(Sides.Right);
            } else {
                sides.add(Sides.Top);
                sides.add(Sides.Right);
            }
        }
        if (bottomLeft && !(bottomRight || topLeft)) {
            int thing = cornerCheck(thisObj.left, thatObj.top, thatObj.right, thisObj.bottom);
            if (thing > 0) {
                sides.add(Sides.Bottom);
            } else if (thing < 0) {
                sides.add(Sides.Left);
            } else {
                sides.add(Sides.Bottom);
                sides.add(Sides.Left);
            }
        }
        if (bottomRight && !(bottomLeft || topRight)) {
            int thing = cornerCheck(thatObj.left, thatObj.top, thisObj.right, thisObj.bottom);
            if (thing > 0) {
                sides.add(Sides.Bottom);
            } else if (thing < 0) {
                sides.add(Sides.Right);
            } else {
                sides.add(Sides.Bottom);
                sides.add(Sides.Right);
            }
        }
        return sides;
    }

    private boolean coordInsideRect(int topOrBottom, int leftOrRight, Rect obj){
        return (obj.left < leftOrRight && obj.right > leftOrRight
                && obj.top < topOrBottom && obj.bottom > topOrBottom);
    }

    private int cornerCheck(int x1, int y1, int x2, int y2){ //returns 0 is equal, + if wide, - if tall
        int width = Math.abs(x2-x1);
        int height = Math.abs(y2-y1);
        return width-height;
    }


    public int GetXMin(){return x1;}
    public int GetYMin(){return y1;}
    public int GetXMax(){return x2;}
    public int GetYMax(){return y2;}

    public abstract void draw(Canvas canvas);

    public void move(int x, int y){
        x = x > 0 ? 1 : x < 0 ? -1 : 0;
        y = y > 0 ? 1 : y < 0 ? -1 : 0;
        x1 += x;
        x2 += x;
        y1 += y;
        y2 += y;
    }
}
