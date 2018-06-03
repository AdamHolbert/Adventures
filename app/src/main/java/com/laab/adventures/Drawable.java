package com.laab.adventures;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Drawable {


    protected int x1, y1, x2, y2;
    GameActivity_Layout layout;

    public Drawable(GameActivity_Layout layout){
        this.layout = layout;
    }

    boolean collidedWith(Drawable obj){
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
        boolean topLeft = isInside(thisObj, obj.GetXMin(), obj.GetYMin());
        boolean topRight = isInside(thisObj, obj.GetXMax(), obj.GetYMin());
        boolean bottomLeft = isInside(thisObj, obj.GetXMin(), obj.GetYMax());
        boolean bottomRight = isInside(thisObj, obj.GetXMax(), obj.GetYMax());


        //is the ______ corner of this inside of obj
        boolean topLeftO = isInside(thatObj, x1, y1);
        boolean topRightO = isInside(thatObj, x2, y1);
        boolean bottomLeftO = isInside(thatObj, x1, y2);
        boolean bottomRightO = isInside(thatObj, x2, y2);

        if(topLeft || topRight || bottomLeft || bottomRight || topLeftO || topRightO || bottomLeftO || bottomRightO){
            collision = true;
        }
        else{
            collision = false;
        }
        return collision;
    }

    Sides AdvancedCollision(Drawable obj){
        Sides side = null;

        Rect thisObj = new Rect();
        thisObj.set(x1, y1, x2, y2);

        Rect thatObj = new Rect();
        thatObj.set(obj.GetXMin(), obj.GetYMin(), obj.GetXMax(), obj.GetYMax());

        //is the ______ corner of obj inside of this
        boolean topLeft = isInside(thisObj, obj.GetXMin(), obj.GetYMin());
        boolean topRight = isInside(thisObj, obj.GetXMax(), obj.GetYMin());
        boolean bottomLeft = isInside(thisObj, obj.GetXMin(), obj.GetYMax());
        boolean bottomRight = isInside(thisObj, obj.GetXMax(), obj.GetYMax());


        //is the ______ corner of this inside of obj
        boolean topLeftO = isInside(thatObj, x1, y1);
        boolean topRightO = isInside(thatObj, x2, y1);
        boolean bottomLeftO = isInside(thatObj, x1, y2);
        boolean bottomRightO = isInside(thatObj, x2, y2);

        if(topLeft || topRight || bottomLeft || bottomRight || topLeftO || topRightO || bottomLeftO || bottomRightO){
            if((topLeft && topRight) || (bottomLeftO && bottomRightO)){
                side = Sides.Top;
            }
            else if((bottomLeft && bottomRight) || (topLeftO && topRightO)){
                side = Sides.Bottom;
            }
            else if((topLeft && bottomLeft) || (topRightO && bottomRightO)){
                side = Sides.Left;
            }
            else if((topRight && bottomRight) || (topLeftO && bottomLeftO)){
                side = Sides.Left;
            }
        }
        else{
            side = Sides.None;
        }

        return side;
    }

    private boolean isInside(Rect object, int x, int y){
        if(x >= object.left && x <= object.right && y >= object.top && y <= object.bottom){
            return true;
        }
        else{
            return false;
        }
    }


    int GetXMin(){return x1;}
    int GetYMin(){return y1;}
    int GetXMax(){return x2;}
    int GetYMax(){return y2;}

    protected abstract void draw(Canvas canvas);

    public void move(int x, int y){
        x = x > 0 ? 1 : x < 0 ? -1 : 0;
        y = y > 0 ? 1 : y < 0 ? -1 : 0;
        x1 += x;
        x2 += x;
        y1 += y;
        y2 += y;
    }
}
