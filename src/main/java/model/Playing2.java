/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.*;

import controller.LevelManager;
//import levels.LevelManager;
//import static pkg3bead_vid.LoadGame.maze;


/**
 *
 * @author Felhasznalo
 */
public class Playing2 {
    //private Player player;
    private LevelManager levelManager;

    private JPanel board;
    
    private static Random random = new Random();
    private boolean gameOver;
    private boolean levelOver;
    private int seconds;
    private int levelComp;
    public Timer timer;
    public Game game;
    
    public Playing2(Game game) {

        this.game= game;
        //this.board = game.getGamePanel();
        initClasses();

        levelComp=0;
        
    }
    
    

    public Game getGame() {
        return game;
    }
    
    /**
     * a játékhoz szükséges osztályok létrehozása
     * játékos kezdőpozíciójának meghatározása
     * játékos kirajzolása
     */
    private void initClasses(){
        /*levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        int[] indexes = playerStartPoint(Game.TILES_IN_WIDTH, Game.TILES_IN_HEIGHT);
        player = new Player(indexes[1]*64 + 20,indexes[0]*64 + 20,64,64,this,enemyManager);
        player.loadLvlData(levelManager.getActualLevel().getLevelData());
        */
    }

    
    /**
     * a player megáll, ha kilépünk az ablakból
     */
    public void windowFocusLost(){
        //player.resetDirBooleans();
    }
    
    /**
     * generál egy kezdő pozíciót a játékosnak
     * @param cols
     * @param rows
     * @return 
     */
    /*
    private static int[] playerStartPoint(int cols, int rows){
        int playerX;
        int playerY;
        do {
            playerX = random.nextInt(rows);
            playerY = random.nextInt(cols);

        } while (maze[playerX][playerY] != 1 );
        int[] out = {playerX,playerY};
        return out;

    }*/
    

    /**
     * úl pálya kezdéséhez mindent alap helyzetbe állít
     */
    public void resetAll(){
        //gameOver = false;
        //levelOver = false;
        //player.resetAll();
        //enemyManager.ResetAllEnemies();
    }
    /**
     * ha vége a szintnek, új pályát generál
     * ha vége a játéknak, feldobja amentés lehetőségét, majd új pályát generál
     * egyébként pedig folytatódik a játék
     */
    public void update() {
        /*
        if(levelOver){
            levelComp++;
            resetAll();
            initClasses();
            //seconds=0;
            timer.start();
        } else if (gameOver){
            gameOver();
            resetAll();
            initClasses();
            levelComp =0;
            seconds=0;
            timer.start();
        } else if (!gameOver) {
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getActualLevel().getLevelData(),player);
        }
         */
    }
    /**
     * rajzol..
     * @param g 
     */
    public void draw(Graphics g) {

        //player.render(g);
        //enemyManager.draw(g);

        if (gameOver) {
            //gameOver();
            gameOver = false;
        }
        if (levelOver) {
            levelOver = false;
        }

        //levelManager.draw(g);

        
    }
    /**
     * a játék végén feldobja a a mentés lehetőségét 
     */
    public void gameOver (){
        /*timer.stop();
        String name = JOptionPane.showInputDialog("Game Over! Enter your name:");

    }
    /**
     * a WASD gombok szerint beállítja a játékos irányát
     * @param e 
     */
    /*public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                //player.setUp(true);
                break;
            case KeyEvent.VK_A:
                //player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                //player.setDown(true);
                break;
            case KeyEvent.VK_D:
                //player.setRight(true);
                break;
        }
    }*/
    /**
     * a WASD gombok szerint "törli" a játékos irányát
     * @param e
     */
    /*public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                //player.setUp(false);
                break;
            case KeyEvent.VK_A:
                //player.setLeft(false);
                break;
            case KeyEvent.VK_S:
                //player.setDown(false);
                break;
            case KeyEvent.VK_D:
                //player.setRight(false);
                break;
        }
    }*/

    /*
    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }
    
    public void setLevelOver(boolean gameOver){
        this.levelOver = gameOver;
    }*/

    }
}
