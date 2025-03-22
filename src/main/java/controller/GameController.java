package controller;

import model.GameMap;
import view.GamePanel;

import javax.swing.*;

public class GameController {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameMap gameMap = new GameMap(20, 15);
            GamePanel gamePanel = new GamePanel(gameMap);

            JFrame frame = new JFrame("Safari Park");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gamePanel);
            frame.setSize(640, 480);
            frame.setVisible(true);
            frame.pack(); // Ez biztosítja, hogy a JPanel megkapja a megfelelő méretet
            frame.setVisible(true);



        });
    }


}
