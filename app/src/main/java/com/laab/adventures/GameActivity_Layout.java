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

        players.add(new Player(100, 100, 150, 150, this));
        walls.add(new Wall(500,0,510, 1000, this));
        walls.add(new Wall(0,0,10, 1000, this));
        walls.add(new Wall(0,1000,510, 1010, this));
        walls.add(new Wall(0,0,510, 10, this));
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
                break;
            }
            for(Drawable wall : walls){
                collision = player.AdvancedCollision(wall);
                if(collision != Sides.None){
                    break;
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
