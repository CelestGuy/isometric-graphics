package net.ddns.celestgames.isometric_graphics;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import net.ddns.celestgames.isometric_graphics.game.Cube;
import net.ddns.celestgames.isometric_graphics.game.Map;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML Pane pane;
    private double mapScale = 2;
    private int cubeSize = (int) (32 * mapScale);

    private final Map map = new Map(10, 10, 1);

    private int cameraX, cameraY, lastMousePosX, lastMousePosY;

    private Image selected = new Image(String.valueOf(this.getClass().getResource("cubes/x32/selected.png")));
    private Image stone = new Image(String.valueOf(this.getClass().getResource("cubes/x32/stone.png")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraX = 900;
        cameraY = 500;
        lastMousePosX = 0;
        lastMousePosY = 0;

        pane.setOnMouseMoved(this::onMouseMoved);
        pane.setOnMouseDragged(this::onMouseDragged);
        pane.setOnScroll(this::onScroll);

        graphicThread.start();
    }

    private void printMap() {
        pane.getChildren().clear();

        int mapHeight = map.getHeight();
        int mapLength = map.getLength();
        int mapWidth = map.getWidth();

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapLength; j++) {
                for (int k = 0; k < mapHeight; k++) {
                    Cube cube = map.getCube(i, j, k);
                    if (!cube.isTransparent()) {
                        ImageView imageView = new ImageView(stone);

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

    private void onScroll(ScrollEvent event) {
        if (this.mapScale > 1 || event.getDeltaY() > 0) {
            this.mapScale += (event.getDeltaY() / 100);
        }
    }

    private void onMouseMoved(MouseEvent event) {
        lastMousePosX = (int) event.getX();
        lastMousePosY = (int) event.getY();
    }

    private void onMouseDragged(MouseEvent event) {
        if (event.isMiddleButtonDown()) {
            int offsetX = lastMousePosX - (int) event.getX();
            int offsetY = lastMousePosY - (int) event.getY();

            cameraY -= offsetY;
            cameraX -= offsetX;

            lastMousePosX = (int) event.getX();
            lastMousePosY = (int) event.getY();
        }
    }

    Thread graphicThread = new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                cubeSize = (int) (32 * mapScale);
                printMap();
            });
        }
    });
}