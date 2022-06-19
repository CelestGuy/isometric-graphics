package fr.celestgames.isometric_graphics;

import fr.celestgames.isometric_graphics.game.Client;
import fr.celestgames.isometric_graphics.game.Cube;
import fr.celestgames.isometric_graphics.game.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController{
    /*

    private Client client;
    private Scene scene;
    private double mapScale = 2;
    private int cubeSize = (int) (32 * mapScale);

    private int cameraX, cameraY, lastMousePosX, lastMousePosY;

    public MainController(Client client) {
        this.client = client;
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

    private void printMapSE(int mapWidth, int mapLength, int mapHeight) {
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapLength; j++) {
                for (int k = 0; k < mapHeight; k++) {
                    Cube cube = level.getCube(i, j, k);
                    int x = (int) ((j * cubeSize) - (j * cubeSize / 2.0) - (i * cubeSize / 2.0) + cameraX);
                    int y = (int) ((i * cubeSize) - (k * cubeSize / 2.0) - (i * cubeSize / 4.0 * 3) + (j * cubeSize / 4.0) + cameraY);

                    ImageView imageView = new ImageView();
                    imageView.setX(x);
                    imageView.setY(y);

                    imageView.setFitWidth(cubeSize);
                    imageView.setFitHeight(cubeSize);

                    pane.getChildren().add(imageView);
                }
            }
        }
    }

    private void printMap() {
        pane.getChildren().clear();

        blocksPositions = new ArrayList<>();
        blocksIndexes = new ArrayList<>();

        int mapHeight = map.getHeight();
        int mapLength = map.getLength();
        int mapWidth = map.getWidth();

        Point3D selectedCube = client.getSelectedCube();
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
                i = blocksIndexes.get(index).getX();
                j = blocksIndexes.get(index).getY();
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

    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.Q) {
            client.setView(1);
        }
        if (event.getCode() == KeyCode.D) {
            client.setView(0);
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
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::onKeyPressed);
    }
    */
}