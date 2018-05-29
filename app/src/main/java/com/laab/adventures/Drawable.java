package com.laab.adventures;

public interface Drawable {

    boolean CollidedWith(Drawable object);
    int GetXMin();
    int GetYMin();
    int GetXMax();
    int GetYMax();


}
