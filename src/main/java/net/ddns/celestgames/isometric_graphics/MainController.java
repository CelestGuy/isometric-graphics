package net.ddns.celestgames.isometric_graphics;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import net.ddns.celestgames.isometric_graphics.game.Cube;
import net.ddns.celestgames.isometric_graphics.game.Map;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML Pane pane;

    private Scene scene;
    private double mapScale = 2;
    private int cubeSize = (int) (32 * mapScale);

    private final Map map = new Map(10, 10, 10);

    private int cameraX, cameraY, lastMousePosX, lastMousePosY;

    private Image selected1 = new Image(String.valueOf(this.getClass().getResource("cubes/x32/selected1.png")));
    private Image selected2 = new Image(String.valueOf(this.getClass().getResource("cubes/x32/selected2.png")));
    private Image stone = new Image(String.valueOf(this.getClass().getResource("cubes/x32/stone.png")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraX = 600;
        cameraY = 350;
        lastMousePosX = 0;
        lastMousePosY = 0;

        Cube air = new Cube("air", true);

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
                for (int k = mapHeight - 1; k >= 0; k--) {
                    Cube cube = map.getCube(i, j, k);
                    if (!cube.isTransparent()) {
                        ImageView imageView = new ImageView(stone);

                        imageView.setX((j * cubeSize) - (j * cubeSize / 2.0) - (i * cubeSize / 2.0) + cameraX);
                        imageView.setY((i * cubeSize) - (k * cubeSize / 2.0) - (i * cubeSize / 4.0 * 3) + (j * cubeSize / 4.0) + cameraY);

                        imageView.setFitWidth(cubeSize);
                        imageView.setFitHeight(cubeSize);

                        pane.getChildren().add(imageView);
                        k = -1;
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

    private void onKeyPressed(KeyEvent event) {
        System.out.println("d");
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
        int i = map.getWidth() - 1;
        int j = map.getLength() - 1;
        int k = map.getHeight() - 1;

        int dir = -1;

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            map.setCube(i, j, k, new Cube("air", true));

            j+=dir;
            if (j < 0 || j >= map.getLength()) {
                dir *= -1;
                j+=dir;
                i--;
                if (i < 0) {
                    i = map.getWidth() - 1;
                    k--;
                    if (k < 0) {
                        k = map.getHeight() - 1;
                    }
                }
            }

            Platform.runLater(() -> {
                cubeSize = (int) (32 * mapScale);
                printMap();
            });
        }
    });

    public void setScene(Scene scene) {
        this.scene = scene;

        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::onKeyPressed);
    }
}