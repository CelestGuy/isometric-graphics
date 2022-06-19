package fr.celestgames.isometric_graphics.graphics;

import fr.celestgames.isometric_graphics.utils.SpiteSheet;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage [] tiles;
    private int cursor;

    public Tile(String path) {
        tiles = SpiteSheet.loadSprite(path);
        cursor = 0;
    }

    public BufferedImage getTile() {
        BufferedImage tile = tiles[cursor];
        cursor++;
        if (cursor >= tiles.length) {
            cursor = 0;
        }
        return tile;
    }

    public void reset() {
        cursor = 0;
    }

    public void changeTiles(String path) {
        tiles = SpiteSheet.loadSprite(path);
        cursor = 0;
    }
}
