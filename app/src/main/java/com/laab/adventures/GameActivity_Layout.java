package com.laab.adventures;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameActivity_Layout extends GameLoop_Layout {

    public static boolean beatLevel;
    GameActivity game;
    List<Drawable> walls;
    List<Player> players;
    List<Drawable> spikes;
    List<Drawable> plates;
    List<Drawable> doors;
    List<Drawable> flags;
    LevelsActivity levels;

    public GameActivity_Layout(Context context) {
        super(context);
        game = (GameActivity) context;
        walls = new ArrayList<Drawable>();
        players = new ArrayList<Player>();
        spikes = new ArrayList<Drawable>();
        plates = new ArrayList<Drawable>();
        doors = new ArrayList<Drawable>();
        flags = new ArrayList<Drawable>();

        getAssets();
    }

    private void getAssets(){
        LevelBuilder builder = new LevelBuilder();
        LevelsActivity levels = new LevelsActivity();
        if(levels.getLevel() == 1) {
            walls = builder.getWalls(1, this);
            players = builder.getPlayers(1, this);
            flags = builder.getFlags(1,this);
        }
        else if(levels.getLevel() == 2) {
            walls = builder.getWalls(2, this);
            players = builder.getPlayers(2, this);
            spikes = builder.getSpikes(2, this);
            flags = builder.getFlags(2,this);
            doors = builder.getDoors(2,this);
            plates = builder.getPlates(2, this, doors);
        }
        else if(levels.getLevel() == 3) {
            walls = builder.getWalls(3, this);
            players = builder.getPlayers(3, this);
        }
    }

    @Override
    void update(double delta_t) {
        List<Player> playersToBeDeleted = new ArrayList<Player>();
        for(Player player : players){
            boolean spikeCollision = false;
            List<Sides> collisions = new ArrayList<Sides>();
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
                collisions.add(player.AdvancedCollision(wall));
            }
            for(Drawable plate : plates){
                collisions.add(player.AdvancedCollision(plate));
                if(player.collidedWith(plate)) {
                    if(!((Plate)plate).getDoor().getIsOpen())
                        ((Plate)plate).getDoor().open();
                }
            }
            for(Drawable door : doors){
                if(((Door) door).getIsOpen())
                    System.out.println("******* Door Is Open ********");
                if (!((Door) door).getIsOpen()) {
                    collisions.add(player.AdvancedCollision(door));
                }
            }
            for(Drawable flag : flags){
                collisions.add(player.AdvancedCollision(flag));
                if(player.collidedWith(flag)){
                    player.atFlag();
                }
            }
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
            for(Sides collision : collisions) {
                if(collision != Sides.None){
                    if ((collision == Sides.Top && moveY > 0) || (collision == Sides.Bottom && moveY < 0)) {
                        moveY = 0;
                        Log.i("Y Movement", "Switched");
                    } else if ((collision == Sides.Left && moveX < 0) || (collision == Sides.Right && moveX > 0)) {
                        moveX = 0;
                        Log.i("X Movement", "Switched");
                    } else if (collision == Sides.TopLeft && (moveX < 0 || moveY < 0)) {
                        moveX = 0;
                        moveY = 0;
                    } else if (collision == Sides.TopRight && (moveX > 0 || moveY < 0)) {
                        moveX = 0;
                        moveY = 0;
                    } else if (collision == Sides.BottomLeft && (moveX < 0 || moveY > 0)) {
                        moveX = 0;
                        moveY = 0;
                    } else if (collision == Sides.BottomRight && (moveX > 0 || moveY > 0)) {
                        moveX = 0;
                        moveY = 0;
                    }
                }
            }
            player.move(moveX, moveY);
        }

        for(Player p : playersToBeDeleted){
            players.remove(p);
        }

        // Win condition stuff -------------------------------
        beatLevel = true;
        if(players.size() > 0) {
            for (Player p : players) {
                if (p.isAtFlag()) {
                    continue;
                }
                beatLevel = false;
            }

            if (beatLevel) {
                game.levelComplete();
                Log.i("Win", "YOU BEAT THE LEVEL");
                levels.beatCurrentLevel();
            }
        }
        else{
            game.gameOver();
            Log.i("Lose", "YOU LOST THE LEVEL");
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
