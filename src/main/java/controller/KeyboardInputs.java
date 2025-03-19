package controller;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardInputs implements KeyListener {
    
    private JPanel gamePanel;
    
    public KeyboardInputs(JPanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*gamePanel.getGame().getPlaying().keyPressed(e);*/
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /*gamePanel.getGame().getPlaying().keyReleased(e); */
    }

}
