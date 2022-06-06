package net.ddns.celestgames.isometric_graphics;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import net.ddns.celestgames.isometric_graphics.game.Cube;
import net.ddns.celestgames.isometric_graphics.game.Map;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML Pane pane;
    final double mapScale = 1.5;
    final int cubeSize = (int) (32 * mapScale);

    private final Map map = new Map(10, 10, 3);

    private int cameraX;
    private int cameraY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraX = 0;
        cameraY = 0;

        printMap();
    }

    private void printMap() {
        int mapHeight = map.getHeight();
        int mapLength = map.getLength();
        int mapWidth = map.getWidth();

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapLength; j++) {
                /*for (int k = 0; k < mapHeight; k++) {

                }*/
            }
        }
    }
}