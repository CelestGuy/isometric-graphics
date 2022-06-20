package fr.celestgames.isometric_graphics.io;

import fr.celestgames.isometric_graphics.game.Client;
import fr.celestgames.isometric_graphics.ui.LevelScreen;
import fr.celestgames.isometric_graphics.ui.Screen;

import javax.swing.*;
import java.awt.*;

import static fr.celestgames.isometric_graphics.utils.ImageUtils.readImage;

public class Window extends JPanel implements Runnable {
    public static final int tileSize = 32;
    public final Thread renderThread = new Thread(this, "RENDER_THREAD");
    public final Keyboard keyboard = new Keyboard();
    public final Client client;
    private final int width;
    private final int height;
    private final JFrame window = new JFrame();
    public int scale;

    public Screen screen;

    public Window(Client client) {
        this.client = client;

        window.setTitle("QBert Like - A little game by CelestGames");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        scale = 4;
        height = 480;
        width = 720;

        addKeyListener(keyboard);
        setDoubleBuffered(true);
        setFocusable(true);

        window.setIconImage(readImage("assets/textures/icon.png"));

        window.add(this);
        window.setSize(new Dimension(width, height));
        window.setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        window.setVisible(true);
        this.screen = new LevelScreen(this);

        while (renderThread.isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();
            if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
                Toolkit.getDefaultToolkit().sync();
            }
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.fillRect(0, 0, window.getWidth(), window.getHeight());

        if (screen != null) {
            screen.render(graphics2D);
        }

        graphics2D.dispose();
    }
}
