package fr.celestgames.isometric_graphics.ui;

import fr.celestgames.isometric_graphics.io.Window;

import java.awt.*;

public abstract class Screen {
    protected Window window;
    public Screen() {
        this.window = Window.getInstance();
    }

    public abstract void update();
    public abstract void render(Graphics2D g2);
}
