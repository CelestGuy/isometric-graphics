package net.ddns.celestgames.isometric_graphics.game;

public class Cube {
    private final String id;
    private final boolean transparent;

    public Cube(String id) {
        this.id = id;
        this.transparent = false;
    }

    public Cube(String id, boolean isTransparent) {
        this.id = id;
        this.transparent = isTransparent;
    }

    public String getID() {
        return id;
    }

    public boolean isTransparent() {
        return this.transparent;
    }
}
