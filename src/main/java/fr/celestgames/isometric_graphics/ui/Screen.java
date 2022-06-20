package fr.celestgames.isometric_graphics.ui;

import fr.celestgames.isometric_graphics.io.Window;

import java.awt.*;

public abstract class Screen {
    protected Window window;
    protected int cameraX, cameraY;
    public Screen(Window window) {
        this.window = window;
        this.cameraX = window.getWidth() / 2;
        this.cameraY = window.getHeight() / 2;
    }

    public abstract void update();
    public abstract void render(Graphics2D g2);
}
