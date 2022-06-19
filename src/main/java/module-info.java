module net.ddns.celestgames.isometric_graphics {
    requires org.lwjgl;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;
    requires org.lwjgl.stb;
    requires java.desktop;

    exports fr.celestgames.isometric_graphics;
    exports fr.celestgames.isometric_graphics.game;
}