package com.laab.adventures;


public class Object implements Drawable{

    private int x1, y1, x2, y2;
    private int size;
    private boolean isAlive;
    private boolean topCollision, bottomCollision, leftCollision, rightCollision;

    public Object(){
        x1 = 0;
        y1 = 0;
        x2 = 20;
        y2 = 20;
        size = 20;

        isAlive = true;
        topCollision = false;
        bottomCollision = false;
        leftCollision = false;
        rightCollision = false;
    }

    public Object(int x, int y, int size){
        x1 = x;
        y1 = y;
        x2 = x + size;
        y2 = y + size;
        this.size = size;

        isAlive = true;
        topCollision = false;
        bottomCollision = false;
        leftCollision = false;
        rightCollision = false;
    }

    public int[] GetPoints(){
        return new int[]{x1,y1,x2,y2};
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

    public int[] MoveTo(int x, int y){
        x1 = x - (size/2);
        y1 = y - (size/2);
        x2 = x + (size/2);
        y2 = y + (size/2);

        return GetPoints();
    }
}
