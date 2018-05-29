package com.laab.adventures;

public class Wall implements Drawable {

    int x1,y1,x2,y2;

    public Wall(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }


    @Override
    public boolean CollidedWith(Drawable object) {
        if(this.GetXMin() >= object.GetXMax()){
            return true;
        }
        else if(this.GetXMax() >= object.GetXMin()){
            return true;
        }
        else if(this.GetYMin() >= object.GetYMax()){
            return true;
        }
        else if(this.GetYMax() >= object.GetYMin()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int GetXMin() {
        return x1;
    }

    @Override
    public int GetYMin() {
        return y1;
    }

    @Override
    public int GetXMax() {
        return x2;
    }

    @Override
    public int GetYMax() {
        return y2;
    }
}
