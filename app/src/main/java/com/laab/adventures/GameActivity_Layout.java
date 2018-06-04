package com.laab.adventures;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class GameActivity_Layout extends GameLoop_Layout {

    List<Drawable> walls;
    List<Player> players;
    List<Drawable> spikes;
    List<Drawable> plates;
    List<Drawable> doors;
    List<Drawable> flags;

    public GameActivity_Layout(Context context) {
        super(context);
        LevelBuilder builder = new LevelBuilder();
        LevelsActivity levels = new LevelsActivity();
        walls = new ArrayList<Drawable>();
        players = new ArrayList<Player>();
        spikes = new ArrayList<Drawable>();
        plates = new ArrayList<Drawable>();
        doors = new ArrayList<Drawable>();
        flags = new ArrayList<Drawable>();


        if(levels.getLevel() == "Level 1") {
            walls.addAll(builder.getWalls(1, this));
            players.addAll(builder.getPlayers(1, this));
            flags.addAll(builder.getFlags(1,this));
        }
        else if(levels.getLevel() == "Level 2") {
            walls.addAll(builder.getWalls(2, this));
            players.addAll(builder.getPlayers(2, this));
            spikes.addAll(builder.getSpikes(2, this));
            flags.addAll(builder.getFlags(2,this));
            doors.addAll(builder.getDoors(2,this));
            plates.addAll(builder.getPlates(2, this, new Door(500, 1000, this)));
        }
        else if(levels.getLevel() == "Level 3") {
            walls.addAll(builder.getWalls(3, this));
            players.addAll(builder.getPlayers(3, this));
        }
    }

    @Override
    void update(double delta_t) {
        List<Player> playersToBeDeleted = new ArrayList<Player>();
        for(Player player : players){
            boolean spikeCollision = false;
            List<Sides> collisions = new ArrayList<Sides>();

            // get player direction
            int moveX = 0, moveY = 1;
            if(draggingPoint != null &&  draggingPoint.hasEvent()){
                if(!draggingPoint.hasPlayer() && player.collidedWith(draggingPoint)){
                    draggingPoint.setCapturedPlayer(player);
                    int xMove = draggingPoint.getX() - ((player.x2-player.x1)/2 + player.x1);
                    int yMove = draggingPoint.getY() - ((player.y2-player.y1)/2 + player.y1);
                    moveX = xMove;
                    moveY = yMove;
                } else if(draggingPoint.hasPlayer() && player == draggingPoint.getCapturedPlayer()){
                    int xMove = draggingPoint.getX() - ((player.x2-player.x1)/2 + player.x1);
                    int yMove = draggingPoint.getY() - ((player.y2-player.y1)/2 + player.y1);
                    moveX = xMove;
                    moveY = yMove;
                }
            }
            // move that direction
            player.move(moveX, moveY);

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
                if(player.collidedWith(wall)){
                    for(Sides side : player.AdvancedCollision(wall)){
                        collisions.add(side);
                    }
                }
            }
            for(Drawable plate : plates){
                for(Sides side : player.AdvancedCollision(plate)){
                    collisions.add(side);
                }
                if(player.collidedWith(plate)) {
                    if(!((Plate)plate).getDoor().getIsOpen())
                        ((Plate)plate).getDoor().open();
                }
            }
            for(Drawable door : doors){
                if(((Door) door).getIsOpen())
                    System.out.println("******* Door Is Open ********");
                if (!((Door) door).getIsOpen()) {
                    if(player.collidedWith(door)){
                        for(Sides side : player.AdvancedCollision(door)){
                            collisions.add(side);
                        }
                    }
                }
            }
            for(Drawable flag : flags){
                if(player.collidedWith(flag)){
                    for(Sides side : player.AdvancedCollision(flag)){
                        collisions.add(side);
                    }
                    player.atFlag();
                }
            }

            boolean revertY = false;
            boolean revertX = false;
            for(Sides collision : collisions) {
                if ((collision == Sides.Top && moveY < 0) || (collision == Sides.Bottom && moveY > 0)) {
                    revertY = true;
                } else if ((collision == Sides.Left && moveX < 0) || (collision == Sides.Right && moveX > 0)) {
                    revertX = true;
                }
            }
            if(revertX){
                player.move(moveX*-1, 0);
            }
            if(revertY){
                player.move(0, moveY*-1);
            }
        }

        for(Player p : playersToBeDeleted){
            players.remove(p);
        }

        // Win condition stuff -------------------------------
        boolean beatLevel = true;
        for(Player p : players){
            if(p.isAtFlag()){
                continue;
            }
            beatLevel = false;
        }

        if(beatLevel){
            Log.i("Win","YOU BEAT THE LEVEL");
        }
        // ----------------------------------------------------
    }

    @Override
    void draw() {
        canvas = surfaceHolder.lockCanvas();
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
        for (Drawable plate : plates){
            plate.draw(canvas);
        }
        for(Drawable door : doors){
            door.draw(canvas);
        }
        for(Drawable flag : flags){
            flag.draw(canvas);
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
