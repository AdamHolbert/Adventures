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

    Player draggingPlayer = null;
    boolean dragging = false;
    float xDrag, yDrag = 0;

    public GameActivity_Layout(Context context) {
        super(context);
        walls = new ArrayList<Drawable>();
        players = new ArrayList<Player>();
        spikes = new ArrayList<Drawable>();

        players.add(new Player(0, 0, 50, 50));

        // Adding Walls
        walls.add(new Wall(500,0,510, 1000));
        walls.add(new Wall(350,2000,360,1250));
        walls.add(new Wall(650,2000,660,1500));
        walls.add(new Wall(1500,650 ,1700 ,660));

        // Adding Spikes
        spikes.add(new Spike(300));

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
                break;
            }
            for(Drawable wall : walls){
                if(player.collidedWith(wall)){
                    collided = true;
                    break;
                }
            }
            if(!collided){
                player.move(5, 5);
            }
        }
        for(Player p : playersToBeDeleted){
            players.remove(p);
        }
    }

    @Override
    void draw() {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawCircle(cwidth, cheight, toPxs(10), red_paintbrush_fill);
        canvas.drawRect(0,0,cwidth,cheight,green_paintbrush_fill);
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

        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dragStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                dragMove(x, y);
                break;
            case MotionEvent.ACTION_UP:
                dragEnd();
                break;
            default:
        }
        return false;
    }

    private void dragMove(float x, float y) {
        dragging = true;
        xDrag = x;
        yDrag = y;
    }

    private void dragEnd() {
        dragging = false;
        draggingPlayer = null;
        xDrag = 0;
        yDrag = 0;
    }

    private void dragStart(float x, float y) {
        xDrag = x;
        yDrag = y;
    }
}
