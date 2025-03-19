/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import model.Game;
import model.Level;

//import static utilz.Constants.fieldTypes.*;



public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private BufferedImage heartSprite;
   
    private Level level;
    private static Random random = new Random();
    
    //private EnemyManager EM;
    
    public LevelManager(Game game){
        this.game = game;
        importLevelSprites();
        level = new Level(LoadGame.generateMaze(10, 15));
        //this.EM = enemyManager;
    }
    
    /**
     * kirajzolja a pályát
     * @param g 
     */
    public void draw(Graphics g){
        /*BufferedImage img = LoadGame.GetSpriteAtlas(LoadGame.HEART_IMG);
        heartSprite = img.getSubimage(0, 0, 32, 32);
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 20; j++) {
                int index = level.getSpriteIndex(i, j);
                
                if ( index == WALL) {
                    //fal
                    g.drawImage(levelSprite[40], j*64, i*64,64,64, null );
                } else if (index ==PATH || index == HEARTH_ON_MAP){
                    //ut
                    g.drawImage(levelSprite[48], j*64, i*64,64,64, null );
                }  else if (index ==DOOR){
                    //ut
                    
                        g.drawImage(levelSprite[45], j*64, i*64,64,64, null );
                   
                }
                
                if (index == HEARTH_ON_MAP ) {
                    g.drawImage(heartSprite, j*64+16, i*64+16,32,32, null);
                }
                
            }
        }        
        */
    }

    /**
     * a pálya elemeinek sprite-jait betölti egy egydimenziós tömbbe
     */
    private void importLevelSprites() {
        /*BufferedImage img = LoadGame.GetSpriteAtlas(LoadGame.LEVEL_ATLAS);
        levelSprite = new BufferedImage[132];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 12; j++) {
                int index = i*12 + j;
                levelSprite[index] = img.getSubimage(j*16, i*16, 16, 16);
            }
        }*/
    }
    
    public void update(){
        
    }
    public Level getActualLevel(){
        return level;
    }

    
}
