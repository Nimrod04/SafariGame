package model;

public class Coordinate {
    private int x, y;

    public Coordinate(int x, int y) { 
        this.x = x; 
        this.y = y; 
    }

    public int getPosX(){
        return x;
    }
    public int getPosY(){
        return y;
    }
}
