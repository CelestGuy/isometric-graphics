package fr.celestgames.isometric_graphics.ui;

import fr.celestgames.isometric_graphics.game.Cube;
import fr.celestgames.isometric_graphics.game.CubeType;
import fr.celestgames.isometric_graphics.game.Level;
import fr.celestgames.isometric_graphics.graphics.Tile;
import fr.celestgames.isometric_graphics.io.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import static fr.celestgames.isometric_graphics.io.Window.tileSize;

public class LevelScreen extends Screen {
    private final HashMap<String, Tile> tiles;
    private Level level;

    private boolean isStanding = true;
    private boolean finished;

    public LevelScreen(Window window) {
        super(window);
        tiles = new HashMap<>();
        level = new Level(10, 10, 10);

        tiles.put("active", new Tile("textures/cubes/active.png"));
        tiles.put("inactive", new Tile("textures/cubes/inactive.png"));
        tiles.put("locked", new Tile("textures/cubes/locked.png"));
        tiles.put("base", new Tile("textures/cubes/base.png"));

        tiles.put("player", new Tile("textures/player.png"));

        window.client.x = 0;
        window.client.y = 0;
        window.client.z = 0;
    }

    @Override
    public void update() {
        if (level.hasInactiveCube()) {
            int x = window.client.x;
            int y = window.client.y;
            int z = window.client.z;

            if (z > 0 && z <= level.getDepth()) {
                if (z < level.getDepth() && level.getCube(x, y, z).getType() == CubeType.VOID) {
                    if (level.getCube(x, y, z - 1).getType() == CubeType.INACTIVE && !isStanding) {
                        level.setCube(x, y, z - 1, new Cube(CubeType.ACTIVE));
                        isStanding = true;
                    } else if (level.getCube(x, y, z - 1).getType() == CubeType.ACTIVE && !isStanding) {
                        level.setCube(x, y, z - 1, new Cube(CubeType.INACTIVE));
                        isStanding = true;
                    } else if (level.getCube(x, y, z - 1).getType() == CubeType.LOCKED && !isStanding) {
                        level.setCube(x, y, z - 1, new Cube(CubeType.INACTIVE));
                        isStanding = true;
                    } else if (level.getCube(x, y, z - 1).getType() == CubeType.VOID) {
                        window.client.z--;
                        isStanding = false;
                    }
                } else if (z == level.getDepth()) {
                    if (level.getCube(x, y, z - 1).getType() == CubeType.INACTIVE && !isStanding) {
                        level.setCube(x, y, z - 1, new Cube(CubeType.ACTIVE));
                        isStanding = true;
                    } else if (level.getCube(x, y, z - 1).getType() == CubeType.ACTIVE && !isStanding) {
                        level.setCube(x, y, z - 1, new Cube(CubeType.INACTIVE));
                        isStanding = true;
                    } else if (level.getCube(x, y, z - 1).getType() == CubeType.LOCKED && !isStanding) {
                        level.setCube(x, y, z - 1, new Cube(CubeType.INACTIVE));
                        isStanding = true;
                    } else if (level.getCube(x, y, z - 1).getType() == CubeType.VOID) {
                        window.client.z--;
                        isStanding = false;
                    }
                } else {
                    window.client.z++;
                }
            } else {
                window.client.z++;
            }

            if (isStanding) {
                if (window.keyboard.qKeyPressed && x > 0) {
                    window.client.x -= 1;
                    isStanding = false;
                } else if (window.keyboard.dKeyPressed && x < level.getWidth() - 1) {
                    window.client.x += 1;
                    isStanding = false;
                }
                if (window.keyboard.zKeyPressed && y > 0) {
                    window.client.y -= 1;
                    isStanding = false;
                } else if (window.keyboard.sKeyPressed && y < level.getHeight() - 1) {
                    window.client.y += 1;
                    isStanding = false;
                }
            }
        } else {
            this.finished = true;
        }
    }

    @Override
    public void render(Graphics2D g2) {
        update();

        Cube[][][] cubes = level.getCubes();

        for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes[i].length; j++) {
                for (int k = 0; k < cubes[i][j].length; k++) {
                    Cube cube = cubes[i][j][k];
                    int x = (int) ((j * tileSize) - (j * tileSize / 2.0) - (i * tileSize / 2.0) + cameraX);
                    int y = (int) ((i * tileSize) - (k * tileSize / 2.0) - (i * tileSize / 4.0 * 3) + (j * tileSize / 4.0) + cameraY);

                    if (cube.getType() == CubeType.ACTIVE) {
                        g2.drawImage(tiles.get("active").getTile(), x, y, null);
                    } else if (cube.getType() == CubeType.INACTIVE) {
                        g2.drawImage(tiles.get("inactive").getTile(), x, y, null);
                    } else if (cube.getType() == CubeType.LOCKED) {
                        g2.drawImage(tiles.get("locked").getTile(), x, y, null);
                    } else if (cube.getType() == CubeType.BASE) {
                        g2.drawImage(tiles.get("base").getTile(), x, y, null);
                    }
                }
            }
        }

        int i = window.client.y;
        int j = window.client.x;
        int k = window.client.z;

        System.out.println(i + " " + j + " " + k);

        double x = (j * tileSize) - (j * tileSize / 2.0) - (i * tileSize / 2.0) + cameraX;
        double y = (i * tileSize) - (k * tileSize / 2.0) - (i * tileSize / 4.0 * 3) + (j * tileSize / 4.0) + cameraY - (tileSize / 3.0);

        g2.drawImage(tiles.get("player").getTile(), (int) x, (int) y, null);

        if (this.finished) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, window.getWidth(), window.getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.drawString("You have finished the level!", window.getWidth() / 2 - 200, window.getHeight() / 2);
        }
    }
}
