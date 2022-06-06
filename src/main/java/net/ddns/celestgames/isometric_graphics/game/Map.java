package net.ddns.celestgames.isometric_graphics.game;

import java.util.ArrayList;

public class Map {
    private final Cube[][][] map;

    private final int length;
    private final int width;
    private final int height;

    public Map(int width, int length, int height) {
        this.map = new Cube[width][length][height];
        this.length = length;
        this.width = width;
        this.height = height;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < height; k++) {
                    String id = "air";

                    if (k == 0) {
                        id = "stone";
                    }

                    map[i][j][k] = new Cube(id);
                }
            }
        }
    }

    public Cube[][][] getMap() {
        return map;
    }

    public void setCube(int x, int y, int z, Cube cube) {
        map[x][y][z] = cube;
    }

    public Cube getCube(int x, int y, int z) {
        return map[x][y][z];
    }

    public int getWidth() {
        return this.width;
    }

    public int getLength() {
        return this.length;
    }

    public int getHeight() {
        return this.height;
    }
}
