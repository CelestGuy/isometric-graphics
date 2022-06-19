package fr.celestgames.isometric_graphics.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.celestgames.isometric_graphics.io.Window.tileSize;

public class SpiteSheet extends ImageUtils {
    public static BufferedImage[] loadSprite(String path) {
        BufferedImage spriteSheet = readImage(path);
        BufferedImage[] sprites;

        if (spriteSheet.getWidth() % 16 == 0 && spriteSheet.getHeight() % 16 == 0) {
            sprites = new BufferedImage[1];
            BufferedImage tempImage = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2 = tempImage.createGraphics();

            g2.drawImage(spriteSheet, 0, 0, tileSize, tileSize, null);

            sprites[0] = tempImage;
            
        } else if (spriteSheet.getWidth() % 16 == 0 && spriteSheet.getHeight() % 16 == 0 && spriteSheet.getHeight() > spriteSheet.getWidth()) {
            int width, height, rows, cols;
            cols = spriteSheet.getWidth() / 16;
            rows = spriteSheet.getHeight() / 16;
            width = spriteSheet.getWidth() / cols;
            height = spriteSheet.getWidth() / rows;

            sprites = new BufferedImage[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sprites[(i * cols) + j] = spriteSheet.getSubimage(j * width, i * height, width, height);
                }
            }
        } else {
            sprites = new BufferedImage[1];
            sprites[0] = spriteSheet;
        }

        return sprites;
    }
}