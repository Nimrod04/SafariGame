/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.DifficultyLevel;
import model.Game;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

/**
 *
 * @author Felhasznalo
 */
public class MainWindow extends BaseWindow {
    
    public MainWindow(){
        JButton start = new JButton();
        start.setText("Start Game");
        start.addActionListener(getActionListener());

        JButton leaderboard = new JButton();
        leaderboard.setText("Leaderboard");
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1)); 
        
        JLabel text = new JLabel("Welcome to the game!");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel.add(text);
        panel.add(start);
        panel.add(leaderboard);
        
        add(panel);

        setLocationRelativeTo(null);
        
    }
    
    /**
     * start gomb megnyomásakor elkezdi a játékot
     * @return 
     */
    private ActionListener getActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Game(DifficultyLevel.EASY);
            }

        };
    }
            
}
