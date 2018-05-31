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

    public GameActivity_Layout(Context context) {
        super(context);
        walls = new ArrayList<Drawable>();
        players = new ArrayList<Player>();
        spikes = new ArrayList<Drawable>();
        players.add(new Player(0, 0, 50, 50));
        walls.add(new Wall(500,0,510, 1000));
    }

    @Override
    void update(double delta_t) {
        List<Player> playersToBeDeleted = new ArrayList<Player>();
        for(Player player : players){
            boolean collided = false;
            for(Drawable spike : spikes){
                if(player.collidedWith(spike)){
                    collided = true;
                    break;
                }
            }
            if(collided){
                playersToBeDeleted.add(player);
                continue;
            }
            for(Drawable wall : walls){
                if(player.collidedWith(wall)){
                    collided = true;
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
            if(!collided){
                player.move(0, 5);
            }
        }
        for(Player p : playersToBeDeleted){
            players.remove(p);
        }
    }

    @Override
    void draw() {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawRect(0, 0, cwidth, cheight, green_paintbrush_fill);
        canvas.drawCircle(cwidth, cheight, toPxs(10), red_paintbrush_fill);

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
                draggingPoint = new DraggingPoint(event);
                draggingPoint.setEvent(event);
                draggingPoint.setCapturedPlayer(null);
                break;
            default:
        }
        return false;
    }
}
