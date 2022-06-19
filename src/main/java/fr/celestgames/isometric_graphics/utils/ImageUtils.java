package fr.celestgames.isometric_graphics.utils;

import fr.celestgames.isometric_graphics.io.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static fr.celestgames.isometric_graphics.io.Window.tileSize;

public class ImageUtils {
    public static BufferedImage readImage(String inputPath) {
        URL pathURL = ImageUtils.class.getResource("/" + inputPath);

        if (pathURL != null) {
            try {
                return ImageIO.read(pathURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return placeHolder();
    }

    public static void writeImage(String outputPath, Object content) {
        if (content instanceof BufferedImage) {
            try {
                ImageIO.write((BufferedImage) content, "png", new File(String.valueOf(Window.class.getResourceAsStream(outputPath))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Unknown or unsupported content format.");
        }
    }

    public static BufferedImage placeHolder() {
        BufferedImage img = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2 = img.createGraphics();

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, tileSize / 2, tileSize / 2);
        g2.fillRect(tileSize / 2, tileSize / 2, tileSize / 2, tileSize / 2);

        g2.setColor(Color.RED);
        g2.fillRect(tileSize / 2, 0, tileSize / 2, tileSize / 2);
        g2.fillRect(0, tileSize / 2, tileSize / 2, tileSize / 2);

        return img;
    }
}
