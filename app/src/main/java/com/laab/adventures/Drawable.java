package com.laab.adventures;

import android.graphics.Canvas;

public abstract class Drawable {


    int x1, y1, x2, y2;
    GameActivity_Layout layout;

    public Drawable(GameActivity_Layout layout){
        this.layout = layout;
    }

    boolean collidedWith(Drawable obj){
        boolean collision = false;
        if(this.GetXMax() >= obj.GetXMin()){ //right
            if(this.GetYMax() >= obj.GetYMin()){ //bottom
                collision = true;
            }
            else if(this.GetYMin() >= obj.GetYMax()){ //top
                collision = true;
            }
        }
        else if(this.GetXMin() >= obj.GetXMax()){ //left
            if(this.GetYMax() >= obj.GetYMin()){ //bottom
                collision = true;
            }
            else if(this.GetYMin() >= obj.GetYMax()){ //top
                collision = true;
            }
        }
        return collision;
    }

    Sides AdvancedCollision(Drawable obj){
        Sides side = null;

        side = Sides.None;

        return side;
    }

    int GetXMin(){return x1;}
    int GetYMin(){return y1;}
    int GetXMax(){return x2;}
    int GetYMax(){return y2;}

    abstract void draw(Canvas canvas);

    public void move(int x, int y){
        x1 += x;
        x2 += x;
        y1 += y;
        y2 += y;
    }
}
