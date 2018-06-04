package com.laab.adventures;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class DraggingPoint extends Drawable {

    private MotionEvent event;
    private Paint green;
    private Paint red;
    private Player capturedPlayer;

    DraggingPoint(MotionEvent event, GameActivity_Layout layout){
        super(layout);
        this.event = event;
        green = new Paint();
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL);
        red = new Paint();
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);
    }


    int getX(){

        Log.w("dragging point", Float.toString(event.getX()/layout.toPxsWidth(1)));
        return (int) (event.getX()/layout.toPxsWidth(1));
    }
    int getY(){return (int) (event.getY()/layout.toPxsHeight(1));}

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
//        Log.w("dragging point", Integer.toString(GetXMin()));
//        canvas.drawRect(event.getX(), event.getY(),
//                event.getX() + 50, event.getY()+50, red);
//
//        canvas.drawRect(layout.toPxsWidth(GetXMin()), layout.toPxsHeight(GetYMin()),
//                layout.toPxsWidth(GetXMax()), layout.toPxsHeight(GetYMax()), green);
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
