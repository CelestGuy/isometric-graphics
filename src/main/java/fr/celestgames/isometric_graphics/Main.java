package fr.celestgames.isometric_graphics;

import fr.celestgames.isometric_graphics.game.Client;
import fr.celestgames.isometric_graphics.io.Window;

public class Main {
    public static void main(String[] args) {
        Client client = Client.getInstance();
        Window window = new Window(client);

        window.renderThread.start();
    }
}