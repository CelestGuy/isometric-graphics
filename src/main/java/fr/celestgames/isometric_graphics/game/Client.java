package fr.celestgames.isometric_graphics.game;

import fr.celestgames.isometric_graphics.Main;

public class Client {

    private final String name;
    private int view;

    private static final Client instance = new Client("Theo");

    private Client(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public static Client getInstance() {
        return instance;
    }
}
