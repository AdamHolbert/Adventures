package com.laab.adventures.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.laab.adventures.GameActivity_Layout;
import com.laab.adventures.models.Drawable;
import com.laab.adventures.models.Player;

public class DraggingPoint extends Drawable {

    private MotionEvent event;
    private Paint green;
    private Paint red;
    private Player capturedPlayer;

    public DraggingPoint(MotionEvent event, GameActivity_Layout layout){
        super(layout);
        this.event = event;
        green = new Paint();
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL);
        green.setStrokeWidth(7);
        red = new Paint();
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);
        red.setStrokeWidth(7);
    }


    public int getX(){return (int) (event.getX()/layout.toPxsWidth(1));}
    public int getY(){return (int) (event.getY()/layout.toPxsHeight(1)) - 15;}

    @Override
    public int GetXMin(){return getX() - 25;}
    @Override
    public int GetYMin(){return getY() - 25;}
    @Override
    public int GetXMax(){return getX() + 25;}
    @Override
    public int GetYMax(){return getY() + 25;}

    @Override
    public void draw(Canvas canvas) {
//        Log.w("dragging point", Integer.toString(GetXMin()));
//        canvas.drawRect(event.getX(), event.getY(),
//                event.getX() + 50, event.getY()+50, red);
//
//        canvas.drawRect(layout.toPxsWidth(GetXMin()), layout.toPxsHeight(GetYMin()),
//                layout.toPxsWidth(GetXMax()), layout.toPxsHeight(GetYMax()), green);
        if (hasPlayer()){
            canvas.drawLine(layout.toPxsWidth(getX()), layout.toPxsHeight(getY()),
                    layout.toPxsWidth(capturedPlayer.xCenter()), layout.toPxsHeight(capturedPlayer.yCenter()), capturedPlayer.isGoon() ? green : red);
        }
    }

    public void setEvent(MotionEvent event) {
        this.event = event;
    }

    public boolean hasEvent(){
        boolean hasEvent = event != null && event.getAction() != MotionEvent.ACTION_UP && event.getAction() != 1;
        if(!hasEvent){
            capturedPlayer = null;
        }
        return hasEvent;
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
