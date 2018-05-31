package com.laab.adventures;

import android.content.Context;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {
    Paint paint;
    public static List<Drawable> getWalls(int level, GameActivity_Layout game) {
        List<Drawable> walls = null;
        if(level == 0) {
            walls.add(new Wall(0, 0, 420, 100,game));
            walls.add(new Wall(0, 0, 10, 700, game));
            walls.add(new Wall(0, 200, 350, 250, game));
            walls.add(new Wall(400, 0, 420, 700, game));
            walls.add(new Wall(50, 300, 420, 450, game));
            walls.add(new Wall(0, 500, 420, 700, game));
        }
        return walls;
    }

    public static List<Player>getPlayers(int level, GameActivity_Layout game) {
        List<Player> players = null;
        if(level == 0) {
            players.add(new Player(0, 0, 50, 50, game));
        }
        return players;
    }
}
