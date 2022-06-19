package fr.celestgames.isometric_graphics.ui;

import fr.celestgames.isometric_graphics.game.Cube;
import fr.celestgames.isometric_graphics.game.CubeType;
import fr.celestgames.isometric_graphics.game.Level;
import fr.celestgames.isometric_graphics.graphics.Tile;

import java.awt.*;
import java.util.HashMap;

import static fr.celestgames.isometric_graphics.io.Window.tileSize;

public class LevelScreen extends Screen {
    private final HashMap<String, Tile> tiles;
    private Level level;

    public LevelScreen() {
        super();
        tiles = new HashMap<>();
        level = new Level(10, 10, 10);

        tiles.put("active", new Tile("textures/cubes/active.png"));
        tiles.put("inactive", new Tile("textures/cubes/inactive.png"));
        tiles.put("locked", new Tile("textures/cubes/locked.png"));
        tiles.put("base", new Tile("textures/cubes/base.png"));

        tiles.put("player", new Tile("textures/player.png"));
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2) {
        update();

        g2.setColor(Color.BLACK);
        Cube [][][] cubes = level.getCubes();

        for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes[i].length; j++) {
                for (int k = 0; k < cubes[i][j].length; k++) {
                    Cube cube = cubes[i][j][k];
                    if (cube.getType() == CubeType.ACTIVE) {
                        g2.drawImage(tiles.get("active").getTile(), i * tileSize, i * tileSize, null);
                    } else if (cube.getType() == CubeType.INACTIVE) {
                        g2.drawImage(tiles.get("inactive").getTile(), i * tileSize, i * tileSize, null);
                    } else {
                        g2.drawImage(tiles.get("base").getTile(), i * tileSize, i * tileSize, null);
                    }
                }
            }
        }
    }
}
