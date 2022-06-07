package net.ddns.celestgames.isometric_graphics.game;

public class Point3D {
    private int x;
    private int y;
    private int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean equals(Point3D point) {
        return this.x == point.getX() && this.y == point.getY() && this.z == point.getZ();
    }
}
