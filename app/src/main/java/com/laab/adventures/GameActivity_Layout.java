package com.laab.adventures;

import android.content.Context;
import android.util.Log;

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

        players.add(new Player(0, 0, 5, 5));
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
            if(collided){
                player.move(1);
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
//        canvas.drawCircle(x , y, toPxs(3), red_paintbrush_fill);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}
