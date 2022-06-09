package net.ddns.celestgames.isometric_graphics;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import net.ddns.celestgames.isometric_graphics.game.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    Pane pane;

    private Client client;
    private Scene scene;
    private double mapScale = 2;
    private int cubeSize = (int) (32 * mapScale);

    final Point3D noSelection = new Point3D(-1, -1, -1);

    private final Map map = new Map(10, 10, 10);
    private ArrayList<Point2D> blocksPositions = new ArrayList<>();
    private ArrayList<Point3D> blocksIndexes = new ArrayList<>();

    private int cameraX, cameraY, lastMousePosX, lastMousePosY;

    private final Image selected1 = new Image(String.valueOf(this.getClass().getResource("cubes/x32/selected1.png")));
    private final Image selected_top = new Image(String.valueOf(this.getClass().getResource("cubes/x32/selected_top.png")));
    private final Image mapBase = new Image(String.valueOf(this.getClass().getResource("cubes/x32/map_base.png")));
    private final Image stone = new Image(String.valueOf(this.getClass().getResource("cubes/x32/stone.png")));

    public MainController(Client client) {
        this.client = client;
        this.blocksPositions = new ArrayList<>();
        this.blocksIndexes = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraX = 600;
        cameraY = 350;
        lastMousePosX = 0;
        lastMousePosY = 0;

        pane.setOnMouseMoved(this::onMouseMoved);
        pane.setOnMouseDragged(this::onMouseDragged);
        pane.setOnScroll(this::onScroll);

        graphicThread.start();
    }

    private void printMap() {
        pane.getChildren().clear();
        blocksPositions = new ArrayList<>();
        blocksIndexes = new ArrayList<>();

        int mapHeight = map.getHeight();
        int mapLength = map.getLength();
        int mapWidth = map.getWidth();

        Point3D selectedCube = client.getSelectedCube();

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapLength; j++) {
                for (int k = 0; k < mapHeight; k++) {
                    Cube cube = map.getCube(i, j, k);
                    int x = (int) ((j * cubeSize) - (j * cubeSize / 2.0) - (i * cubeSize / 2.0) + cameraX);
                    int y = (int) ((i * cubeSize) - (k * cubeSize / 2.0) - (i * cubeSize / 4.0 * 3) + (j * cubeSize / 4.0) + cameraY);

                    if (cube.isTransparent() && k == 0) {
                        ImageView imageView = new ImageView(mapBase);
                        imageView.setX(x);
                        imageView.setY(y);

                        imageView.setFitWidth(cubeSize);
                        imageView.setFitHeight(cubeSize);

                        pane.getChildren().add(imageView);
                    } else if (!cube.isTransparent()) {
                        ImageView imageView = new ImageView(stone);

                        imageView.setX(x);
                        imageView.setY(y);

                        imageView.setFitWidth(cubeSize);
                        imageView.setFitHeight(cubeSize);

                        imageView.setOnMouseEntered(this::onMouseHover);
                        blocksPositions.add(new Point2D(x, y));
                        blocksIndexes.add(new Point3D(i, j, k));

                        pane.getChildren().add(imageView);

                        if (client.getSelectedCube().equals(new Point3D(i, j, k))) {
                            ImageView selectionView = new ImageView(selected1);

                            selectionView.setX((j * cubeSize) - (j * cubeSize / 2.0) - (i * cubeSize / 2.0) + cameraX);
                            selectionView.setY((i * cubeSize) - (k * cubeSize / 2.0) - (i * cubeSize / 4.0 * 3) + (j * cubeSize / 4.0) + cameraY);

                            selectionView.setFitWidth(cubeSize);
                            selectionView.setFitHeight(cubeSize);

                            pane.getChildren().add(selectionView);
                        }
                        //k = -1;
                    }
                }
            }
        }

        int i = selectedCube.getX();
        int j = selectedCube.getY();
        int k = selectedCube.getZ();

        if (!client.getSelectedCube().equals(noSelection) && (k == map.getHeight() - 1 || map.getCube(i, j, k + 1).isTransparent())) {
            ImageView imageView = new ImageView(selected_top);

            imageView.setX((j * cubeSize) - (j * cubeSize / 2.0) - (i * cubeSize / 2.0) + cameraX);
            imageView.setY((i * cubeSize) - (k * cubeSize / 2.0) - (i * cubeSize / 4.0 * 3) + (j * cubeSize / 4.0) + cameraY);

            imageView.setFitWidth(cubeSize);
            imageView.setFitHeight(cubeSize);

            pane.getChildren().add(imageView);
        }
    }

    private void onMouseHover(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int i;
        int j;
        int k;

        for (int index = 0; index < blocksPositions.size(); index++) {
            Point2D point2D = blocksPositions.get(index);

            if (x >= point2D.getX() && x <= point2D.getX() + cubeSize && y >= point2D.getY() && y <= point2D.getY() + cubeSize) {
                i = (int) blocksIndexes.get(index).getX();
                j = (int) blocksIndexes.get(index).getY();
                k = blocksIndexes.get(index).getZ();

                client.setSelectedCube(new Point3D(i, j, k));
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

    public void onMouseClicked(MouseEvent event) {
        if (event.isPrimaryButtonDown() || event.getButton().name().equals("PRIMARY")) {
            Point3D selectedCube = client.getSelectedCube();
            map.setCube(selectedCube.getX(), selectedCube.getY(), selectedCube.getZ(), new Cube("air", true));
        } else if (event.isSecondaryButtonDown() || event.getButton().name().equals("SECONDARY")) {
            Point3D selectedCube = client.getSelectedCube();
            map.setCube(selectedCube.getX(), selectedCube.getY(), selectedCube.getZ(), new Cube("stone"));
        }
    }

    Thread graphicThread = new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                cubeSize = (int) (32 * mapScale);
                printMap();
            });
        }
    });

    public void setScene(Scene scene) {
        this.scene = scene;

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, this::onMouseClicked);
    }
}