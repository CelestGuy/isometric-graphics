package net.ddns.celestgames.isometric_graphics.game;

public class Cube {
    private final String id;
    private final boolean transparent;
    private boolean selected;

    public Cube(String id) {
        this.id = id;
        this.transparent = false;
        this.selected = false;
    }

    public Cube(String id, boolean isTransparent) {
        this.id = id;
        this.transparent = isTransparent;
        this.selected = false;
    }

    public String getID() {
        return this.id;
    }

    public boolean isTransparent() {
        return this.transparent;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }
}
