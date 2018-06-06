package com.laab.adventures;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.laab.adventures.models.Button;
import com.laab.adventures.models.Door;
import com.laab.adventures.models.DraggingPoint;
import com.laab.adventures.models.Drawable;
import com.laab.adventures.models.Plate;
import com.laab.adventures.models.Player;
import com.laab.adventures.models.Sides;

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
    Drawable mainMenuButton;
    Drawable restartButton;
    Bitmap grid;
    Paint transparent;

    public GameActivity_Layout(Context context) {
        super(context);
        game = (GameActivity) context;
        grid = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.grid),
                (int)toPxsWidth(900), (int)toPxsHeight(1600), false);
        transparent = new Paint();
        transparent.setAlpha(80);

        getAssets();
    }

    private void getAssets(){
        walls = new ArrayList<>();
        players = new ArrayList<>();
        spikes = new ArrayList<>();
        plates = new ArrayList<>();
        doors = new ArrayList<>();
        flags = new ArrayList<>();
        levels = new LevelsActivity();
        if(levels.getLevel() == 1) {
            walls = LevelBuilder.getWalls(1, this);
            players = LevelBuilder.getPlayers(1, this);
            flags = LevelBuilder.getFlags(1,this);
        }
        else if(levels.getLevel() == 2) {
            walls = LevelBuilder.getWalls(2, this);
            players = LevelBuilder.getPlayers(2, this);
            spikes = LevelBuilder.getSpikes(2, this);
            flags = LevelBuilder.getFlags(2,this);
            doors = LevelBuilder.getDoors(2,this);
            plates = LevelBuilder.getPlates(2, this, doors);
        }
        else if(levels.getLevel() == 3) {
            flags = LevelBuilder.getFlags(3,this);
            spikes = LevelBuilder.getSpikes(3, this);
            doors = LevelBuilder.getDoors(3,this);
            plates = LevelBuilder.getPlates(3, this, doors);
            walls = LevelBuilder.getWalls(3, this);
            players = LevelBuilder.getPlayers(3, this);
        }
        restartButton = new Button(this,50, 13,
                BitmapFactory.decodeResource(game.getResources(), R.drawable.restart));
        mainMenuButton = new Button(this, 775, 13,
                BitmapFactory.decodeResource(game.getResources(), R.drawable.back));

    }

    @Override
    void update(double delta_t) {
        List<Player> playersToBeDeleted = new ArrayList<>();

        if(draggingPoint != null && draggingPoint.hasEvent() && !draggingPoint.hasPlayer()){
            if(restartButton.collidedWith(draggingPoint)) {
                game.restart();
                return;
            }
            if(mainMenuButton.collidedWith(draggingPoint)){
                game.levelSelect();
                return;
            }
        }

        // Reset doors and plates
        for(Drawable door : doors){
            ((Door) door).close();
        }
        for(Drawable plate : plates){
            ((Plate) plate).unpress();
        }

        // Activate all doors and plates based off simple collision
        for(Drawable dPlate : plates){
            Plate plate = ((Plate)dPlate);
            plate.turnOnYBump();
            for(Player player : players){
                if(player.collidedWith(plate)){
                    plate.press();
                    plate.getDoor().open();
                }
            }
            plate.turnOffYBump();
        }


        for(Player player : players){
            List<Sides> collisions = new ArrayList<>();

            // get player direction
            int moveX = 0, moveY = 1;



            // if is object boi, and dragging isn't happening
            if(!player.hasEverDragged() &&(
                    draggingPoint == null ||
                    draggingPoint != null && !draggingPoint.hasEvent() ||
                    draggingPoint != null && draggingPoint.hasEvent() && !draggingPoint.hasPlayer() ||
                    draggingPoint != null && draggingPoint.hasEvent() && draggingPoint.hasPlayer() && draggingPoint.getCapturedPlayer() != player
                    )){
                player.setDragTag(true);
            } else {
                player.setDragTag(false);
            }

            if(draggingPoint != null &&  draggingPoint.hasEvent()){
                if(!draggingPoint.hasPlayer() && player.collidedWith(draggingPoint)){
                    draggingPoint.setCapturedPlayer(player);
                    player.dragging();
                }
                if(draggingPoint.hasPlayer() && player == draggingPoint.getCapturedPlayer()){
                    int xMove = draggingPoint.getX() - (int) player.xCenter();
                    int yMove = draggingPoint.getY() - (int) player.yCenter();
                    moveX = xMove;
                    moveY = yMove;
                }
            }
            // move that direction
            player.move(moveX, moveY);

            // Check spike collision
            boolean spikeCollision = false;
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

            // Check Wall collision
            for(Drawable wall : walls){
                if(player.collidedWith(wall)){
                    collisions.addAll(player.AdvancedCollision(wall));
                }
            }

            // Check Plate collision
            for(Drawable plate : plates){
                if(player.collidedWith(plate)){
                    collisions.addAll(player.AdvancedCollision(plate));
                }
            }

            // Check door collision
            for(Drawable door : doors){
                // If door is closed, add the collisions
                if (!((Door) door).getIsOpen()) {
                    if(player.collidedWith(door)){
                        collisions.addAll(player.AdvancedCollision(door));
                    }
                }
            }
            for(Drawable flag : flags){
                if(player.collidedWith(flag)){
                    player.atFlag();
                }
            }

            // Undo moves based off of the collisions
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


        } // End of all player collision checks



        // Win condition checks -------------------------------

        for(Player p : playersToBeDeleted){
            if(!p.isGoon()){
                game.gameOver();
                return;
            }
            players.remove(p);
        }

        if(players.size() > 0) {
            for (Player p : players) {
                if (p.isAtFlag() && !p.isGoon()) {
                    game.levelComplete();
                    LevelsActivity.beatLevel();
                    return;
                }
            }
        }
        // ----------------------------------------------------
    }

    @Override
    void draw() {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawRect(0,0,cwidth,cheight, backgroundPaint);
        //cannon ball
        for(Drawable wall : walls){
            wall.draw(canvas);
        }
        for(Drawable spike : spikes){
            spike.draw(canvas);
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
        for(Drawable player : players){
            player.draw(canvas);
        }
        mainMenuButton.draw(canvas);
        restartButton.draw(canvas);

        canvas.drawBitmap(grid, 0, 0, transparent);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }


    DraggingPoint draggingPoint;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
