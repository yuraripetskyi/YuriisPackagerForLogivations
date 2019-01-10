package models;

public class Product {
    private long id;
    private int sizeX;
    private int sizeY;
    private int sizeZ;

    public Product(long id, int sizeX, int sizeY, int sizeZ) {
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    public long getId() {
        return id;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeZ() {
        return sizeZ;
    }

    public void setSizeZ(int sizeZ) {
        this.sizeZ = sizeZ;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", sizeZ=" + sizeZ +
                '}';
    }
}
