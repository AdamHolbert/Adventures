package com.laab.adventures;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.lang.Object;

public class DraggingPoint extends Drawable {

    private MotionEvent event;
    private Paint paint;
    private Player capturedPlayer;

    DraggingPoint(MotionEvent event, GameActivity_Layout layout){
        super(layout);
        this.event = event;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }


    int getX(){
        return (int) (event.getX()/layout.toPxs(1));
    }
    int getY(){return (int) (event.getY()/layout.toPxs(1));}

    @Override
    int GetXMin(){return getX() - 10;}
    @Override
    int GetYMin(){return getY() - 10;}
    @Override
    int GetXMax(){return getX() + 10;}
    @Override
    int GetYMax(){return getY() + 10;}

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawRect(layout.toPxs(getX()), layout.toPxs(getY()),
//                layout.toPxs(getX() + 10), layout.toPxs(getY()+10), paint);

//        canvas.drawRect(layout.toPxs(GetXMin()), layout.toPxs(GetYMin()),
//                layout.toPxs(GetXMax()), layout.toPxs(GetYMax()), paint);
    }

    public void setEvent(MotionEvent event) {
        this.event = event;
    }

    public boolean hasEvent(){
        return event != null && event.getAction() != MotionEvent.ACTION_UP && event.getAction() != 1;
    }

    public boolean hasPlayer(){
        return capturedPlayer != null;
    }

    public Player getCapturedPlayer(){
        return capturedPlayer;
    }

    public void setCapturedPlayer(Player capturedPlayer) {
        this.capturedPlayer = capturedPlayer;
    }
}
