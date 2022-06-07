package net.ddns.celestgames.isometric_graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.ddns.celestgames.isometric_graphics.game.Client;

import java.io.IOException;

public class Main extends Application {
    public MainController controller;

    @Override
    public void start(Stage stage) throws IOException {
        Client client = new Client("theo");

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        controller = new MainController(client);
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        controller.setScene(scene);
        stage.setTitle("Welcome to the Isometric World !");
        stage.setMaximized(true);
        stage.requestFocus();
        stage.getIcons().add(new Image(String.valueOf(this.getClass().getResource("cubes/x128/stone.png"))));
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}