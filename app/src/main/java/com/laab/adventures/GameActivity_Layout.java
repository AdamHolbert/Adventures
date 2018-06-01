package com.laab.adventures;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import com.laab.adventures.LevelBuilder;

public class GameActivity_Layout extends GameLoop_Layout {

    List<Drawable> walls;
    List<Player> players;
    List<Drawable> spikes;
    List<Drawable> plates;
    List<Drawable> doors;

    public GameActivity_Layout(Context context) {
        super(context);
//          setLevel(1);
        walls = new ArrayList<Drawable>();
        players = new ArrayList<Player>();
        spikes = new ArrayList<Drawable>();
        players.add(new Player(0, 0, 50, 50, this));
        walls.add(new Wall(0, 0, 420, 100, this));
        walls.add(new Wall(0, 0, 10, 700, this));
        walls.add(new Wall(0, 200, 350, 250, this));
        walls.add(new Wall(400, 0, 420, 700, this));
        walls.add(new Wall(50, 300, 420, 450, this));
        walls.add(new Wall(0, 500, 420, 700, this));
        spikes.add(new Spike(300, this));
        plates = new ArrayList<Drawable>();
        doors = new ArrayList<Drawable>();
    }

    @Override
    void update(double delta_t) {
        List<Player> playersToBeDeleted = new ArrayList<Player>();
        for(Player player : players){
            boolean spikeCollision = false;
            Sides collision = Sides.None;
            int moveX = 0, moveY = 1;
            boolean collidedWithDoor = false;
            boolean collidedWithPlate = false;
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
            for(Drawable plate : plates){
                collision = player.AdvancedCollision(plate);
                if(collision != Sides.None) {
                    if(!((Plate)plate).getDoor().getIsOpen())
                        ((Plate)plate).getDoor().open();
                }else{
                    ((Plate)plate).getDoor().close();
                }
            }
            for(Drawable door : doors){

                if(!((Door) door).getIsOpen())
                    System.out.println("******* Door Is Not Open ********");
                if(((Door) door).getIsOpen())
                    System.out.println("******* Door Is Open ********");

               if (!((Door) door).getIsOpen()) {
                    collision = player.AdvancedCollision(door);
                    if(collision != Sides.None) {
                        break;
                    }
                }
            }
            if(draggingPoint != null &&  draggingPoint.hasEvent()){
                if(!draggingPoint.hasPlayer() && player.collidedWith(draggingPoint)){
                    draggingPoint.setCapturedPlayer(player);
                    int xMove = ((player.x2-player.x1) + player.x1);
                    int yMove = ((player.y2-player.y1) + player.y1);
                    player.move(draggingPoint.getX()-xMove, draggingPoint.getY()-yMove);
                } else if(draggingPoint.hasPlayer() && player == draggingPoint.getCapturedPlayer()){
                    int xMove = draggingPoint.getX() - ((player.x2-player.x1)/2 + player.x1);
                    int yMove = draggingPoint.getY() - ((player.y2-player.y1)/2 + player.y1);
                    moveX = xMove;
                    moveY = yMove;
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
//            player.move(moveX, moveY);
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

    public void setLevel(int level) {
        walls = LevelBuilder.getWalls(level, this);
        players = LevelBuilder.getPlayers(level, this);
    }
}
