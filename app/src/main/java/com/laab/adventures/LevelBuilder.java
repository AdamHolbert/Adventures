package com.laab.adventures;

import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {

    public static ArrayList<Drawable> getWalls(int level, GameActivity_Layout game) {
        ArrayList<Drawable> walls = new ArrayList<Drawable>();

        if(level == 1) {
            walls.add(new Wall(0, 0, 900, 100,game));
            walls.add(new Wall(0, 0, 50, 1600, game));
            walls.add(new Wall(0, 300, 750, 500, game));
            walls.add(new Wall(850, 0, 900, 1600, game));
            walls.add(new Wall(150, 700, 900, 1000, game));
            walls.add(new Wall(0, 1300, 900, 1600, game));
        }
        if(level == 2) {
            //Top Wall
            walls.add(new Wall(0, 0, 900, 100,game));
            //Bottom Wall
            walls.add(new Wall(0, 1500, 900, 1600,game));
            //Huge bottom structure
            walls.add(new Wall(200, 1300, 850, 1500,game));
            //Above huge bottom structure
            walls.add(new Wall(150, 900, 850, 1100,game));
            //Above previous wall
            walls.add(new Wall(50, 600, 400, 700,game));
            //Above previous wall
            walls.add(new Wall(50, 300, 500, 400,game));
            //Right of previous wall
            walls.add(new Wall(500, 300, 750, 700,game));
            //Left Wall
            walls.add(new Wall(0, 100, 50, 1500, game));
            //Right Wall
            walls.add(new Wall(850, 100, 900, 1500, game));
        }
        if(level == 3) {
//            Outer walls
            walls.add(new Wall(0, 0, 900, 100,game));
            walls.add(new Wall(0, 0, 50, 1600, game));
            walls.add(new Wall(850, 0, 900, 1600, game));
            walls.add(new Wall(0, 1500, 900, 1600, game));

            walls.add(new Wall(200, 300, 900, 400, game));
            walls.add(new Wall(120, 350, 200, 400, game));
//            Above lower wall
            walls.add(new Wall(0, 650, 665, 750, game));
            walls.add(new Wall(665, 700, 780, 750, game));
//            Above bottom
            walls.add(new Wall(230, 1000, 900, 1100, game));
            walls.add(new Wall(120, 1050, 230, 1100, game));
//            Bottom
            walls.add(new Wall(600, 1300, 900, 1600, game));


        }
        return walls;
    }

    public static ArrayList<Player> getPlayers(int level, GameActivity_Layout game) {
        ArrayList<Player> players = new ArrayList<Player>();
        if(level == 1) {
            players.add(new Player(750, 1100, game, false, true));
        }
        if(level == 2) {
            players.add(new Player(700, 1100, game, false));
            players.add(new Player(750, 1100, game, true));
        }
        if(level == 3) {
            players.add(new Player(650, 1100, game, false));
            players.add(new Player(700, 1100, game, true));
            players.add(new Player(750, 1100, game, true));
            players.add(new Player(800, 1100, game, true));
        }
        return players;
    }

    public static ArrayList<Drawable> getSpikes(int level, GameActivity_Layout game) {
        ArrayList<Drawable> spikes = new ArrayList<Drawable>();
        if(level == 2) {
            spikes.add(new Spike(50, 1400, 200, 1500, game));
//            spikes.add(new Spike(125, 1430, game));
        }
        if(level == 3) {
//            Bottom
            spikes.add(new Spike(55, 1430, game));
            spikes.add(new Spike(130, 1430, game));
            spikes.add(new Spike(205, 1430, game));
            spikes.add(new Spike(280, 1430, game));
            spikes.add(new Spike(450, 1430, game));
            spikes.add(new Spike(525, 1430, game));
//            Above Bottom
            spikes.add(new Spike(250, 930, game));
            spikes.add(new Spike(325, 930, game));
            spikes.add(new Spike(400, 930, game));
            spikes.add(new Spike(475, 930, game));
            spikes.add(new Spike(550, 930, game));
            spikes.add(new Spike(625, 930, game));
            spikes.add(new Spike(700, 930, game));
            spikes.add(new Spike(775, 930, game));

//            Above lower spikes
            spikes.add(new Spike(55, 580, game));
            spikes.add(new Spike(130, 580, game));
            spikes.add(new Spike(205, 580, game));
            spikes.add(new Spike(280, 580, game));
            spikes.add(new Spike(355, 580, game));
            spikes.add(new Spike(430, 580, game));
            spikes.add(new Spike(505, 580, game));
            spikes.add(new Spike(580, 580, game));

            spikes.add(new Spike(210, 230, game));
            spikes.add(new Spike(285, 230, game));
            spikes.add(new Spike(360, 230, game));
            spikes.add(new Spike(435, 230, game));
            spikes.add(new Spike(510, 230, game));
            spikes.add(new Spike(585, 230, game));
            spikes.add(new Spike(660, 230, game));
        }
        return spikes;
    }

    public static ArrayList<Drawable> getFlags(int level, GameActivity_Layout game) {
        ArrayList<Drawable> flags = new ArrayList<Drawable>();
        if(level == 1) {
            flags.add(new Flag(70,225, game));
        }
        if(level == 2) {
            flags.add(new Flag(70,225, game));
        }
        if(level == 3) {
            flags.add(new Flag(800,220, game));
        }
        return flags;
    }

    public static ArrayList<Drawable> getPlates(int level, GameActivity_Layout game, List<Drawable> doors) {
        ArrayList<Drawable> plates = new ArrayList<Drawable>();
        if(level == 2) {
            plates.add(new Plate(50, 580, game, (Door)doors.get(0)));
        }
        if(level == 3) {
            plates.add(new Plate(370, 1490, game, (Door)doors.get(0)));
            plates.add(new Plate(150, 1040, game, (Door)doors.get(1)));
            plates.add(new Plate(685, 690, game, (Door)doors.get(2)));
        }
        return plates;
    }

    public static ArrayList<Drawable> getDoors(int level, GameActivity_Layout game) {
        ArrayList<Drawable> doors = new ArrayList<Drawable>();
        if(level == 2) {
            doors.add(new Door(750, 500, 850, 520, game));
        }
        if(level == 3) {
            doors.add(new Door(45, 1088, game));
            doors.add(new Door(775, 738, game));
            doors.add(new Door(45, 388, game));
        }
        return doors;
    }
}
