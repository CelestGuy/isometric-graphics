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
    @FXML
    Pane pane;
    final double scale = 1.5;
    final int size = (int) (32 * scale);
    private Map map;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.map = new Map(10, 10, 3);
    }
}