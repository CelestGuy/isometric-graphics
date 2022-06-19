package fr.celestgames.isometric_graphics.game;

public class Level {
    Cube[][][] cubes;

    public Level(int width, int height, int depth) {
        cubes = new Cube[width][height][depth];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    if (x > (width - z) / 2 || y > (height - z) / 2) {
                        cubes[x][y][z] = new Cube(CubeType.BASE);
                    } else {
                        cubes[x][y][z] = new Cube(CubeType.VOID);
                    }
                }
            }
        }

    }

    public Cube getCube(int x, int y, int z) {
        return cubes[x][y][z];
    }

    public void setCube(int x, int y, int z, Cube cube) {
        cubes[x][y][z] = cube;
    }

    public Cube[][][] getCubes() {
        return cubes;
    }
}
