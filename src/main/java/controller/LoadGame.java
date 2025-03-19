package controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
import model.Game;

public class LoadGame {

    public static final String PLAYER_ATLAS = "Player.png";
    public static final String LEVEL_ATLAS = "tilemap_packed.png";
    public static final String SKELETON_ATLAS = "Skeleton.png";
    public static final String HEART_IMG = "heart.png";
    
    public static final int SKELETON_NUM = 3; 

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;

        InputStream is = LoadGame.class.getResourceAsStream("/images/" + fileName);

        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    /**
     * példányosítja a skeletonokat és elhelyezi őket a pályán
     * @return 
     */
    public static void GetSkeletons() {

        /*ArrayList<Skeleton> list = new ArrayList<>();

        for (int i = 0; i < SKELETON_NUM; i++) {
            int x;
            int y;
            do {
                x = random.nextInt(Game.TILES_IN_HEIGHT);//14
                y = random.nextInt(Game.TILES_IN_WIDTH);//20
            } while (maze[x][y] == 0 || (x == startX || y == startY));

            list.add(new Skeleton(y * Game.TILES_SIZE, x * Game.TILES_SIZE));
        }
        return list;*/
    }

    private static int rows;
    private static int cols;
    private static final int WALL = 0;
    private static final int PATH = 1;

    public static int[][] maze;
    private static Random random = new Random();
    
    private static int startX =-1;
    private static int startY =-1;
    
    /**
     * labirintust generál
     * @param rows
     * @param cols
     * @return 
     */
    public static int[][] generateMaze(int rows, int cols) {

        int WALL = 0;
        int PATH = 1;

        maze = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(maze[i], WALL);
        }

        startX = random.nextInt(rows / 2) * 2 + 1;
        startY = random.nextInt(cols / 2) * 2 + 1;

        maze[startX][startY] = 1;
        kill(startX, startY, rows, cols);

        while (hunt(rows, cols)) {

        }
        dropHearts(cols,rows);
        setExit(cols, rows);

        return maze;

    }
    /**
     * generál egy kijáratot random pozícióval
     * @param cols
     * @param rows 
     */
    private static void setExit(int cols, int rows) {
        int playerX;
        int playerY;

        do {
            playerX = random.nextInt(rows-1);//14
            playerY = random.nextInt(cols);//20

        } while (playerX ==0 || maze[playerX][playerY] != WALL || maze[playerX+1][playerY] != PATH);

        //14x21
        maze[playerX][playerY] = 2;

    }
    /**
     * 3 szívet random helyen elhelyez a pályán
     * @param cols
     * @param rows 
     */
    private static void dropHearts(int cols, int rows) {
        for (int i = 0; i < 3; i++) {
            int x;
            int y;
            do {
                x = random.nextInt(Game.TILES_IN_HEIGHT);//14
                y = random.nextInt(Game.TILES_IN_WIDTH);//20
            } while (maze[x][y] == 0 || (x == startX && y == startY));

            maze[x][y] = 3;
        }
        
        
    }
    /**
     * egy random irányba a szomszédot járható úttá alakítja
     * @param x
     * @param y
     * @param rows
     * @param cols 
     */
    private static void kill(int x, int y, int rows, int cols) {
        int[][] directions = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
        shuffleArray(directions);

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (isValid(nx, ny, rows, cols)) {
                maze[nx][ny] = 1;
                maze[x + dir[0] / 2][y + dir[1] / 2] = 1; // Köztes fal eltávolítása
                kill(nx, ny, rows, cols); // Rekurzió
            }
        }
    }
    /**
     * sorban halad a mátrix elemein és ha talál olyan falat ami mellett van út,
     * azt hozzá köti az úthos
     * ez után innen folytatja a kill-t
     * @param rows
     * @param cols
     * @return 
     */
    private static boolean hunt(int rows, int cols) {
        for (int i = 1; i < rows; i += 2) {
            for (int j = 1; j < cols; j += 2) {
                if (maze[i][j] == 0 && hasAdjacentPath(i, j, rows, cols)) {
                    maze[i][j] = 1;
                    kill(i, j, rows, cols);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * megnézi hogy a szomszédai között van-e út
     * @param x
     * @param y
     * @param rows
     * @param cols
     * @return 
     */
    private static boolean hasAdjacentPath(int x, int y, int rows, int cols) {
        int[][] directions = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx > 0 && nx < rows && ny > 0 && ny < cols && maze[nx][ny] == 1) {
                return true;
            }
        }
        return false;
    }
    /**
     * megnézi, hogy valós pozíció-e
     * @param x
     * @param y
     * @param rows
     * @param cols
     * @return 
     */
    private static boolean isValid(int x, int y, int rows, int cols) {
        return x > 0 && x < rows && y > 0 && y < cols && maze[x][y] == 0;
    }
    /**
     * irányok megkeverése
     * @param array 
     */
    private static void shuffleArray(int[][] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int[] temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
