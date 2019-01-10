package models;

import com.sun.javafx.beans.IDProperty;

import javax.annotation.Generated;

public class Case {

    private long id;
    private int sizeX;
    private int sizeY;
    private int sizeZ;

    public Case(long id, int sizeX, int sizeY, int sizeZ) {
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    public Case(int sizeX, int sizeY, int sizeZ) {
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
        return "Case{" +
                "id=" + id +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", sizeZ=" + sizeZ +
                '}';
    }
}

