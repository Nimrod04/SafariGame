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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
