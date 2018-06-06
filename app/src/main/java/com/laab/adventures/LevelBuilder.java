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
            players.add(new Player(750, 1100, game, false));
        }
        if(level == 2) {
            players.add(new Player(700, 1100, game, true));
            players.add(new Player(750, 1100, game, false));
        }
        if(level == 3) {
            players.add(new Player(650, 1100, game, true));
            players.add(new Player(700, 1100, game, true));
            players.add(new Player(750, 1100, game, true));
            players.add(new Player(800, 1100, game, false));
        }
        return players;
    }

    public static ArrayList<Drawable> getSpikes(int level, GameActivity_Layout game) {
        ArrayList<Drawable> spikes = new ArrayList<Drawable>();
        if(level == 2) {
            spikes.add(new Spike(50, 1430, game));
            spikes.add(new Spike(125, 1430, game));
        }
        if(level == 3) {
//            Bottom
            spikes.add(new Spike(50, 1410, 100, 1500, game));
            spikes.add(new Spike(100, 1410, 150, 1500, game));
            spikes.add(new Spike(150, 1410, 200, 1500, game));
            spikes.add(new Spike(200, 1410, 250, 1500, game));
            spikes.add(new Spike(250, 1410, 300, 1500, game));
            spikes.add(new Spike(300, 1410, 350, 1500, game));
            spikes.add(new Spike(350, 1410, 400, 1500, game));
            spikes.add(new Spike(450, 1410, 500, 1500, game));
            spikes.add(new Spike(500, 1410, 550, 1500, game));
            spikes.add(new Spike(550, 1410, 600, 1500, game));
//            Above Bottom
            for(int i = 250; i < 850; i += 50){
                spikes.add(new Spike(i, 930, i + 50, 1000, game));
            }

//            Above lower spikes
            for(int i = 50; i < 650; i += 50){
                spikes.add(new Spike(i, 585, i + 50, 650, game));
            }


//          Top spikes
            for(int i = 200; i < 750; i += 50){
                spikes.add(new Spike(i, 250, i + 50, 300, game));
            }
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
            flags.add(new Flag(775,200, 850, 300, game));
        }
        return flags;
    }

    public static ArrayList<Drawable> getPlates(int level, GameActivity_Layout game, List<Drawable> doors) {
        ArrayList<Drawable> plates = new ArrayList<Drawable>();
        if(level == 2) {
            plates.add(new Plate(50, 580, game, (Door)doors.get(0)));
        }
        if(level == 3) {
            plates.add(new Plate(400, 1480, 450, 1500, game, (Door)doors.get(0)));
            plates.add(new Plate(130, 1030, 200, 1050, game, (Door)doors.get(1)));
            plates.add(new Plate(690, 680, 760, 700, game, (Door)doors.get(2)));
        }
        return plates;
    }

    public static ArrayList<Drawable> getDoors(int level, GameActivity_Layout game) {
        ArrayList<Drawable> doors = new ArrayList<Drawable>();
        if(level == 2) {
            doors.add(new Door(750, 500, 850, 520, game));
        }
        if(level == 3) {
            doors.add(new Door(50, 1080, 120, 1100, game));
            doors.add(new Door(780, 730, 850, 750, game));
            doors.add(new Door(50, 380, 120, 400, game));
        }
        return doors;
    }
}
