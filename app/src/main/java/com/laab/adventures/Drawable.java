package com.laab.adventures;

import android.graphics.Canvas;

public abstract class Drawable {


    int x1, y1, x2, y2;

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
        Sides side = Sides.None;
        if(this.GetXMax() >= obj.GetXMin()){ //right
            if(this.GetYMax() > obj.GetYMin()){ //bottom
                side = Sides.Bottom;
            }
            else if(this.GetYMin() > obj.GetYMax()){ //top
                side = Sides.Top;
            }
            else if(this.GetYMax() == obj.GetYMin() || this.GetYMin() == obj.GetYMax()){ //only right
                side = Sides.Right;
            }
        }
        else if(this.GetXMin() >= obj.GetXMax()){ //left
            if(this.GetYMax() > obj.GetYMin()){ //bottom
                side = Sides.Bottom;
            }
            else if(this.GetYMin() > obj.GetYMax()){ //top
                side = Sides.Top;
            }
            else if(this.GetYMax() == obj.GetYMin() || this.GetYMin() == obj.GetYMax()){ //only left
                side = Sides.Left;
            }
        }
        return side;
    }

    int GetXMin(){return x1;}
    int GetYMin(){return y1;}
    int GetXMax(){return x2;}
    int GetYMax(){return y2;}

    abstract void draw(Canvas canvas);

    public void move(int x, int y) {
        x1 += x;
        x2 += x;
        y1 += y;
        y2 += y;
    }
}
