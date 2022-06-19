package fr.celestgames.isometric_graphics.io;

import fr.celestgames.isometric_graphics.game.Client;
import fr.celestgames.isometric_graphics.ui.Screen;

import javax.swing.*;
import java.awt.*;

import static fr.celestgames.isometric_graphics.utils.ImageUtils.readImage;

public class Window extends JPanel implements Runnable {
    private static Window instance = null;
    public static final int tileSize = 16;
    public final Thread renderThread = new Thread(this, "RENDER_THREAD");
    public final Keyboard keyboard = new Keyboard();
    private final Client client;
    private final int width;
    private final int height;
    private final JFrame window = new JFrame();
    public int scale;

    private Screen screen;

    public Window(Client client) {
        this.client = client;

        window.setTitle("A Mazing temple - The Buggiest Game Ever Created");
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

        while (renderThread.isAlive()) {
            try {
                Thread.sleep(16, 666666);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();
            if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
                Toolkit.getDefaultToolkit().sync();
            }
        }
    }

    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        screen.update();

        graphics2D.dispose();
    }

    public static Window getInstance() {
        if (instance == null) {
            instance = new Window(Client.getInstance());
        }
        return instance;
    }
}
