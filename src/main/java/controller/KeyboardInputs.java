/*
package controller;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs extends JPanel implements KeyListener {

    private JPanel gamePanel;

    public KeyboardInputs(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A ->
                 = Math.max(0, cameraX - 1);
            case KeyEvent.VK_D ->
                cameraX = Math.min(gameMap.getWidth() - VIEWPORT_WIDTH, cameraX + 1);
            case KeyEvent.VK_W ->
                cameraY = Math.max(0, cameraY - 1);
            case KeyEvent.VK_S ->
                cameraY = Math.min(gameMap.getHeight() - VIEWPORT_HEIGHT, cameraY + 1);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gamePanel.getGame().getPlaying().keyReleased(e);
    }

}


*/