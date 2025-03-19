
package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


public class MouseInputs implements MouseListener{
    
    private JPanel gamePanel;
    
    public MouseInputs(JPanel gamePanel){
        this.gamePanel = gamePanel;
        
    }
    /**
     * bal egérgomb esetén támad a játékos
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getButton() == MouseEvent.BUTTON1) {
            //gamePanel.getGame().getPlaying().getPlayer().setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
