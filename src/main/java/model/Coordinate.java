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

    public void setPosX(int x) {
        this.x = x;
    }

    public void setPosY(int y) {
        this.y = y;
    }
}
