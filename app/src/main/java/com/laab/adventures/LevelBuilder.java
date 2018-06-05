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
//            walls.add(new Wall(450, 1500, 900, 1600,game));
//            walls.add(new Wall(0, 1500, 450, 1550,game));
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
            walls.add(new Wall(0, 0, 25, 1400, game));
            walls.add(new Wall(25, 0, 50, 1500, game));
            //Right Wall
            walls.add(new Wall(850, 0, 900, 1500, game));
        }
        if(level == 3) {
//            Outer walls
            walls.add(new Wall(0, 0, 900, 100,game));
            walls.add(new Wall(0, 0, 10, 2000, game));
            walls.add(new Wall(890, 0, 900, 2000, game));
            walls.add(new Wall(0, 1500, 900, 2000, game));

            walls.add(new Wall(200, 300, 900, 400, game));
            walls.add(new Wall(80, 350, 900, 400, game));
//            Above lower wall
            walls.add(new Wall(0, 675, 700, 750, game));
            walls.add(new Wall(0, 700, 820, 750, game));
//            Above bottom
            walls.add(new Wall(200, 1000, 900, 1100, game));
            walls.add(new Wall(80, 1050, 900, 1100, game));
//            Bottom
            walls.add(new Wall(600, 1300, 900, 2000, game));


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
        if(level == 3) {
            players.add(new Player(800, 1100, game));
            players.add(new Player(750, 1100, game));
            players.add(new Player(700, 1100, game));
        }
        return players;
    }

    public static ArrayList<Drawable>getSpikes(int level, GameActivity_Layout game) {
        ArrayList<Drawable> spikes = new ArrayList<Drawable>();
        if(level == 2) {
            spikes.add(new Spike(20, 1420, game));
            spikes.add(new Spike(100, 1420, game));
        }
        if(level == 3) {
//            Bottom
            spikes.add(new Spike(20, 1420, game));
            spikes.add(new Spike(100, 1420, game));
            spikes.add(new Spike(180, 1420, game));
            spikes.add(new Spike(260, 1420, game));
            spikes.add(new Spike(420, 1420, game));
            spikes.add(new Spike(500, 1420, game));
//            Above Bottom
            spikes.add(new Spike(200, 920, game));
            spikes.add(new Spike(280, 920, game));
            spikes.add(new Spike(360, 920, game));
            spikes.add(new Spike(440, 920, game));
            spikes.add(new Spike(520, 920, game));
            spikes.add(new Spike(600, 920, game));
            spikes.add(new Spike(680, 920, game));
            spikes.add(new Spike(760, 920, game));

//            Above lower spikes
            spikes.add(new Spike(10, 600, game));
            spikes.add(new Spike(90, 600, game));
            spikes.add(new Spike(170, 600, game));
            spikes.add(new Spike(250, 600, game));
            spikes.add(new Spike(340, 600, game));
            spikes.add(new Spike(420, 600, game));
            spikes.add(new Spike(500, 600, game));
            spikes.add(new Spike(580, 600, game));

            spikes.add(new Spike(200, 220, game));
            spikes.add(new Spike(280, 220, game));
            spikes.add(new Spike(360, 220, game));
            spikes.add(new Spike(440, 220, game));
            spikes.add(new Spike(520, 220, game));
            spikes.add(new Spike(600, 220, game));
            spikes.add(new Spike(680, 220, game));
        }
        return spikes;
    }

    public static ArrayList<Drawable>getFlags(int level, GameActivity_Layout game) {
        ArrayList<Drawable> flags = new ArrayList<Drawable>();
        if(level == 1) {
            flags.add(new Flag(30,225, game));
        }
        if(level == 2) {
            flags.add(new Flag(20,225, game));
        }
        if(level == 3) {
            flags.add(new Flag(800,220, game));
        }
        return flags;
    }

    public static ArrayList<Drawable>getPlates(int level, GameActivity_Layout game, List<Drawable> doors) {
        ArrayList<Drawable> plates = new ArrayList<Drawable>();
        if(level == 2) {
            plates.add(new Plate(10, 588, game, (Door)doors.get(0)));
        }
        if(level == 3) {
            plates.add(new Plate(335, 1480, game, (Door)doors.get(0)));
            plates.add(new Plate(100, 1025, game, (Door)doors.get(1)));
            plates.add(new Plate(730, 680, game, (Door)doors.get(2)));
        }
        return plates;
    }

    public static ArrayList<Drawable>getDoors(int level, GameActivity_Layout game) {
        ArrayList<Drawable> doors = new ArrayList<Drawable>();
        if(level == 2) {
            doors.add(new Door(825, 500, game));
        }
        if(level == 3) {
            doors.add(new Door(10, 1070, game));
            doors.add(new Door(820, 710, game));
            doors.add(new Door(10, 380, game));
        }
        return doors;
    }
}
