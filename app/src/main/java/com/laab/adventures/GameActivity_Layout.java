package com.laab.adventures;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class GameActivity_Layout extends GameLoop_Layout {

    List<Drawable> walls;
    List<Player> players;
    List<Drawable> spikes;
    List<Drawable>plates;

    public GameActivity_Layout(Context context) {
        super(context);
        walls = new ArrayList<Drawable>();
        players = new ArrayList<Player>();
        spikes = new ArrayList<Drawable>();
        plates = new ArrayList<Drawable>();


        players.add(new Player(0, 0, 50, 50, this));
        walls.add(new Wall(0, 0, 1250, 200, this));
        walls.add(new Wall(0, 1250, 1250, 1750, this));
        spikes.add(new Spike(300, 300, 310,310,this));

    }

    @Override
    void update(double delta_t) {
        List<Player> playersToBeDeleted = new ArrayList<Player>();
        for(Player player : players){
            boolean spikeCollision = false;
            Sides collision = Sides.None;
            int moveX = 1, moveY = 1;
            for(Drawable spike : spikes){
                if(player.collidedWith(spike)){
                    spikeCollision = true;
                  break;
                }
            }
            if(spikeCollision){
                playersToBeDeleted.add(player);
                continue;
            }
            for(Drawable wall : walls){
                collision = player.AdvancedCollision(wall);
                if(collision != Sides.None){
                    break;
                }
            }
            if(draggingPoint != null &&  draggingPoint.hasEvent()){
                if(!draggingPoint.hasPlayer() && player.collidedWith(draggingPoint)){
                    draggingPoint.setCapturedPlayer(player);
                    int xMove = ((player.x2-player.x1) + player.x1);
                    int yMove = ((player.y2-player.y1) + player.y1);
                    player.move(draggingPoint.getX()-xMove, draggingPoint.getY()-yMove);
                } else if(draggingPoint.hasPlayer() && player == draggingPoint.getCapturedPlayer()){
                    int xMove = ((player.x2-player.x1) + player.x1);
                    int yMove = ((player.y2-player.y1) + player.y1);
                    player.move(draggingPoint.getX()-xMove, draggingPoint.getY()-yMove);
                }
            }
            if(collision == Sides.Top || collision ==  Sides.Bottom){
                moveY *= -1;
                Log.i("Y Movement", "Switched");
            }
            else if(collision == Sides.Left || collision ==  Sides.Right){
                moveX *= -1;
                Log.i("X Movement", "Switched");
        }
            player.move(moveX, moveY);
        }
        for(Player p : playersToBeDeleted){
            players.remove(p);
        }
    }

    @Override
    void draw() {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawCircle(cwidth, cheight, toPxs(10), red_paintbrush_fill);
        canvas.drawRect(0,0,cwidth,cheight, gray_panitbrush_fill);
        //cannon ball
        for(Drawable wall : walls){
            wall.draw(canvas);
        }
        for(Drawable spike : spikes){
            spike.draw(canvas);
        }
        for(Drawable player : players){
            player.draw(canvas);
        }

        if(draggingPoint != null && draggingPoint.hasEvent()){
            draggingPoint.draw(canvas);
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
    }


    DraggingPoint draggingPoint;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                draggingPoint = new DraggingPoint(event, this);
                draggingPoint.setEvent(event);
                draggingPoint.setCapturedPlayer(null);
                break;
            default:
        }
        return false;
    }
    }
