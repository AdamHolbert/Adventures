package com.laab.adventures;

import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {

    public static ArrayList<Drawable> getWalls(int level, GameActivity_Layout game) {
        ArrayList<Drawable> walls = new ArrayList<Drawable>();

        if(level == 1) {
            walls.add(new Wall(0, 0, 900, 100,game));
            walls.add(new Wall(0, 0, 10, 2000, game));
            walls.add(new Wall(0, 300, 800, 500, game));
            walls.add(new Wall(890, 0, 900, 2000, game));
            walls.add(new Wall(100, 700, 900, 1000, game));
            walls.add(new Wall(0, 1300, 900, 2000, game));
        }
        if(level == 2) {
            //Top Wall
            walls.add(new Wall(0, 0, 900, 100,game));
            //Bottom Wall
            walls.add(new Wall(0, 1500, 900, 2000,game));
            //Huge bottom structure
            walls.add(new Wall(200, 1300, 900, 2000,game));
            //Above huge bottom structure
            walls.add(new Wall(100, 900, 900, 1100,game));
            //Above previous wall
            walls.add(new Wall(0, 600, 400, 700,game));
            //Above previous wall
            walls.add(new Wall(0, 300, 830, 400,game));
            //Right of previous wall
            walls.add(new Wall(500, 300, 830, 700,game));
            //Left Wall
            walls.add(new Wall(0, 0, 10, 2000, game));
            //Right Wall
            walls.add(new Wall(890, 0, 900, 2000, game));
        }
        if(level == 3) {
            walls.add(new Wall(0, 0, 900, 100,game));
            walls.add(new Wall(0, 0, 10, 2000, game));
            walls.add(new Wall(0, 300, 800, 500, game));
            walls.add(new Wall(890, 0, 900, 2000, game));
            walls.add(new Wall(100, 700, 900, 1000, game));
            walls.add(new Wall(0, 1300, 900, 2000, game));
        }
        return walls;
    }

    public static ArrayList<Player>getPlayers(int level, GameActivity_Layout game) {
        ArrayList<Player> players = new ArrayList<Player>();
        if(level == 1) {
            players.add(new Player(800, 1100, game));
        }
        if(level == 2) {
            players.add(new Player(800, 1100, game));
            players.add(new Player(750, 1100, game));
        }
        return players;
    }

    public static ArrayList<Drawable>getSpikes(int level, GameActivity_Layout game) {
        ArrayList<Drawable> spikes = new ArrayList<Drawable>();
        if(level == 2) {
            spikes.add(new Spike(20, 1420, game));
            spikes.add(new Spike(100, 1420, game));
        }
        return spikes;
    }

    public static ArrayList<Drawable>getFlags(int level, GameActivity_Layout game) {
        ArrayList<Drawable> flags = new ArrayList<Drawable>();
        if(level == 1) {
            flags.add(new Flag(30,220, game));
        }
        if(level == 2) {
            flags.add(new Flag(20,220, game));
        }
        return flags;
    }

    public static ArrayList<Drawable>getPlates(int level, GameActivity_Layout game, List<Drawable> doors) {
        ArrayList<Drawable> plates = new ArrayList<Drawable>();
        if(level == 2) {
            plates.add(new Plate(10, 570, game, (Door)doors.get(0)));
        }
        return plates;
    }

    public static ArrayList<Drawable>getDoors(int level, GameActivity_Layout game) {
        ArrayList<Drawable> doors = new ArrayList<Drawable>();
        if(level == 2) {
            doors.add(new Door(825, 500, game));
        }
        return doors;
    }

    public static ArrayList<Drawable> getButtons(GameActivity_Layout game){
        ArrayList<Drawable> buttons = new ArrayList<Drawable>();
        buttons.add(new Button(game,10, 10,  BitmapFactory.decodeResource(game.getResources(), R.drawable.flag)));
        buttons.add(new Button(game, 790, 10,  BitmapFactory.decodeResource(game.getResources(), R.drawable.plate)));
        return buttons;
    }
}
