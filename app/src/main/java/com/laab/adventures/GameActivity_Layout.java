package com.laab.adventures;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.MotionEvent;
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

    public GameActivity_Layout(Context context) {
        super(context);
        game = (GameActivity) context;
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
        restartButton = new Button(this,10, 10,
                BitmapFactory.decodeResource(game.getResources(), R.drawable.restart));
        mainMenuButton = new Button(this, 790, 10,
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
        for(Drawable door : doors) {
            ((Door) door).close();
        }
        for(Player player : players){
            boolean spikeCollision = false;
            List<Sides> collisions = new ArrayList<>();

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
                    collisions.addAll(player.AdvancedCollision(wall));
                }
            }
            for(Drawable plate : plates){
                collisions.addAll(player.AdvancedCollision(plate));
                if(player.collidedWith(plate)) {
                    ((Plate)plate).press();
                    if(!((Plate)plate).getDoor().getIsOpen())
                        ((Plate)plate).getDoor().open();
                }
            }
            for(Drawable door : doors){
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
                LevelsActivity.beatLevel();
            }
        }
        else{
            game.gameOver();
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
        mainMenuButton.draw(canvas);
        restartButton.draw(canvas);
        if(draggingPoint != null && draggingPoint.hasEvent()){
            draggingPoint.draw(canvas);
        }

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
