package model;

public class Level {
    private int[][] lvlData;
    
    
    public Level(int[][]lvlData){
        this.lvlData = lvlData;
    }
    /**
     * adott cella típusát adja meg (fal, út, ajtó, stb.)
     * @param x
     * @param y
     * @return 
     */
    public int getSpriteIndex(int x, int y){
        return lvlData[x][y];
    }
    
    public int [][] getLevelData(){
        return lvlData;
    }
}
