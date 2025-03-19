/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;


public class BaseWindow extends JFrame {
    
    public BaseWindow(){
        setTitle("Maze");
        setSize(300,210);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                showExitConfirmation();
            }
        });
    }
    /**
     * Felugró ablakban rákérdez az ablak bezárására, majd bezárja azt.
     * @return
     */
    private void showExitConfirmation(){
        int n = JOptionPane.showConfirmDialog(this, "Do you really want to close this window?", 
                "Confirm",JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }
    /**
     * Bezárja az ablakot  
     */
    protected void doUponExit(){
       this.dispose();
    }
}
