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
    private final double mapScale = 2;
    private final int cubeSize = (int) (32 * mapScale);

    private final Map map = new Map(10, 10, 10);

    private int cameraX;
    private int cameraY;

    private Image air = new Image(String.valueOf(this.getClass().getResource("cubes/x32/selected.png")));
    private Image stone = new Image(String.valueOf(this.getClass().getResource("cubes/x32/stone.png")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraX = 900;
        cameraY = 500;

        printMap();
    }

    private void printMap() {
        int mapHeight = map.getHeight();
        int mapLength = map.getLength();
        int mapWidth = map.getWidth();

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapLength; j++) {
                for (int k = 0; k < mapHeight; k++) {
                    Cube cube = map.getCube(i, j, k);

                    ImageView imageView = new ImageView(air);

                    if (cube.getID().equals("stone")) {
                        imageView = new ImageView(stone);
                    }

                    imageView.setX((j * cubeSize) - (j * cubeSize / 2.0) - (i * cubeSize / 2.0) + cameraX);
                    imageView.setY((i * cubeSize) - (k * cubeSize / 2.0) - (i * cubeSize / 4.0 * 3) + (j * cubeSize / 4.0) + cameraY);

                    imageView.setFitWidth(cubeSize);
                    imageView.setFitHeight(cubeSize);

                    pane.getChildren().add(imageView);
                }
            }
        }
    }
}