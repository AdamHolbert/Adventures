package com.laab.adventures;

import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {

    public static ArrayList<Drawable> getWalls(int level, GameActivity_Layout game) {
        ArrayList<Drawable> walls = new ArrayList<Drawable>();
        if(level == 1) {
            walls.add(new Wall(0, 0, 420, 100,game));
            walls.add(new Wall(0, 0, 10, 700, game));
            walls.add(new Wall(0, 200, 350, 250, game));
            walls.add(new Wall(400, 0, 420, 700, game));
            walls.add(new Wall(50, 300, 420, 450, game));
            walls.add(new Wall(0, 500, 420, 700, game));
        }
        if(level == 2) {
            //Top Wall
            walls.add(new Wall(0, 0, 420, 80,game));
            //Bottom Wall
            walls.add(new Wall(0, 650, 420, 700,game));
            //Huge bottom structure
            walls.add(new Wall(100, 550, 420, 700,game));
            //Above huge bottom structure
            walls.add(new Wall(50, 400, 420, 500,game));
            //Above previous wall
            walls.add(new Wall(0, 240, 200, 330,game));
            //Above previous wall
            walls.add(new Wall(0, 150, 370, 200,game));
            //Right of previous wall
            walls.add(new Wall(230, 150, 370, 330,game));
            //Left Wall
            walls.add(new Wall(0, 0, 10, 700, game));
            //Right Wall
            walls.add(new Wall(400, 0, 420, 700, game));
        }
        return walls;
    }

    public static ArrayList<Player>getPlayers(int level, GameActivity_Layout game) {
        ArrayList<Player> players = new ArrayList<Player>();
        if(level == 1) {
            players.add(new Player(370, 470, game));
        }
        if(level == 2) {
            players.add(new Player(370, 500, game));
        }
        return players;
    }

    public static ArrayList<Drawable>getSpikes(int level, GameActivity_Layout game) {
        ArrayList<Drawable> spikes = new ArrayList<Drawable>();
        if(level == 2) {
            spikes.add(new Spike(20, 617, game));
            spikes.add(new Spike(60, 617, game));
        }
        return spikes;
    }

    public static ArrayList<Drawable>getFlags(int level, GameActivity_Layout game) {
        ArrayList<Drawable> flags = new ArrayList<Drawable>();
        if(level == 1) {

        }
        return flags;
    }

    public static ArrayList<Drawable>getPlates(int level, GameActivity_Layout game) {
        ArrayList<Drawable> plates = new ArrayList<Drawable>();
        return plates;
    }

    public static ArrayList<Drawable>getDoors(int level, GameActivity_Layout game) {
        ArrayList<Drawable> doors = new ArrayList<Drawable>();
        return doors;
    }
}
