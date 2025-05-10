package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * A MouseInputs osztály a játék egérbemeneteit kezeli.
 * Implementálja a MouseListener interfészt, így képes reagálni az egér eseményeire.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class MouseInputs implements MouseListener{
    
    private JPanel gamePanel;
    
    /**
     * Létrehoz egy új MouseInputs példányt a megadott JPanel-hez.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     * @param gamePanel a játék panelje, amelyhez az egérbemenetek tartoznak
     */
    public MouseInputs(JPanel gamePanel){
        this.gamePanel = gamePanel;
    }

    /**
     * bal egérgomb esetén támad a játékos
     * Ez a metódus public, mert a MouseListener interfész része, így a rendszer hívja meg az egérkattintás eseményekhez.
     * @param e az egér esemény
     */
    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getButton() == MouseEvent.BUTTON1) {
            //gamePanel.getGame().getPlaying().getPlayer().setAttacking(true);
        }
    }

    /**
     * Az egérgomb lenyomásának eseményét kezeli.
     * Ez a metódus public, mert a MouseListener interfész része, így a rendszer hívja meg.
     * @param e az egér esemény
     */
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    /**
     * Az egérgomb felengedésének eseményét kezeli.
     * Ez a metódus public, mert a MouseListener interfész része, így a rendszer hívja meg.
     * @param e az egér esemény
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    /**
     * Az egér komponens fölé érkezésének eseményét kezeli.
     * Ez a metódus public, mert a MouseListener interfész része, így a rendszer hívja meg.
     * @param e az egér esemény
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    /**
     * Az egér komponensről való kilépésének eseményét kezeli.
     * Ez a metódus public, mert a MouseListener interfész része, így a rendszer hívja meg.
     * @param e az egér esemény
     */
    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
