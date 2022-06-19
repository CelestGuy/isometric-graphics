package fr.celestgames.isometric_graphics.game;

public class Cube {
    private final CubeType type;

    public Cube(CubeType type) {
        this.type = type;
    }

    public CubeType getType() {
        return type;
    }
}
