package fr.celestgames.isometric_graphics.game;

public class Level {
    Cube[][][] cubes;

    private final int width, height, depth;

    public Level(int width, int height, int depth) {
        cubes = new Cube[height][width][depth];

        this.width = width;
        this.height = height;
        this.depth = depth;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int z = 0; z < depth; z++) {
                    int nWidth = width - z;
                    int nHeight = height - z;

                    if (x < (nWidth - y) || y < (nHeight - x)) {
                        cubes[y][x][z] = new Cube(CubeType.INACTIVE);
                    } else {
                        cubes[y][x][z] = new Cube(CubeType.VOID);
                    }
                }
            }
        }

    }

    public Cube getCube(int x, int y, int z) {
        return cubes[y][x][z];
    }

    public void setCube(int x, int y, int z, Cube cube) {
        cubes[y][x][z] = cube;
    }

    public Cube[][][] getCubes() {
        return cubes;
    }

    public boolean hasInactiveCube() {
        for (int y = 0; y < cubes.length; y++) {
            for (int x = 0; x < cubes[y].length; x++) {
                for (int z = 0; z < cubes[y][x].length; z++) {
                    if (cubes[y][x][z].getType() == CubeType.INACTIVE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }
}
