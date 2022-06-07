package net.ddns.celestgames.isometric_graphics.game;

import javafx.scene.image.Image;
import net.ddns.celestgames.isometric_graphics.Main;

public class Client {
    private final String name;
    private final Image skin;

    private Point3D selectedCube;

    public Client(String name) {
        this.name = name;
        this.skin = new Image(String.valueOf(Main.class.getResource("player.png")));

        this.selectedCube = new Point3D(0, 0, 0);
        this.resetSelectedCube();
    }

    public String getName() {
        return this.name;
    }

    public Image getSkin() {
        return this.skin;
    }

    public Point3D getSelectedCube() {
        return this.selectedCube;
    }

    public void setSelectedCube(Point3D selectedCube) {
        this.selectedCube = selectedCube;
    }

    public void resetSelectedCube() {
        this.selectedCube = new Point3D(-1, -1, -1);
    }
}
