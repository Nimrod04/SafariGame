/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.DifficultyLevel;
import model.Game;

/**
 * A Menu osztály a játék főmenüjét reprezentálja, ahol a játékos kiválaszthatja a nehézségi szintet, megadhatja a szafari nevét, vagy kiléphet a játékból.
 * Leszármazottja a JFrame osztálynak, így grafikus ablakos alkalmazásként jelenik meg.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 * 
 * @author nimro
 */
public class Menu extends javax.swing.JFrame {
    /** A szafari neve, amelyet a játékos ad meg. */
    private String safariName;

    /**
     * Létrehoz egy új Menu példányt, inicializálja a komponenseket.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     */
    public Menu() {
        initComponents();
    }

    /**
     * Inicializálja a grafikus komponenseket, gombokat, szövegmezőket, paneleket.
     * Ezt a metódust a Form Editor generálja, ne módosítsd kézzel!
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        /** Görgetősáv a szövegmezőhöz. */
        jScrollPane1 = new javax.swing.JScrollPane();
        /** Szövegmező, amelyben információk jelenhetnek meg. */
        jTextArea1 = new javax.swing.JTextArea();
        /** A menü címsorát megjelenítő címke. */
        jLabel1 = new javax.swing.JLabel();
        /** A fő panel, amely a menü elemeit tartalmazza. */
        jPanel1 = new javax.swing.JPanel();
        /** A szafari nevét bekérő szövegmező. */
        safariNameField = new javax.swing.JTextField();
        /** Könnyű nehézségi szintet választó gomb. */
        jButton1 = new javax.swing.JButton();
        /** Normál nehézségi szintet választó gomb. */
        jButton2 = new javax.swing.JButton();
        /** Nehéz nehézségi szintet választó gomb. */
        jButton3 = new javax.swing.JButton();
        /** Kilépés gomb. */
        jButton4 = new javax.swing.JButton();
        /** Kitöltő elem a panel elrendezéséhez. */
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Safari Game");
        setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Safari Game");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        safariNameField.setBackground(new java.awt.Color(153, 153, 153));
        safariNameField.setText("Enter your name");
        safariNameField.setToolTipText("Enter your name");

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setForeground(new java.awt.Color(0, 255, 0));
        jButton1.setText("Easy");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setForeground(new java.awt.Color(255, 255, 0));
        jButton2.setText("Normal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setForeground(new java.awt.Color(255, 0, 0));
        jButton3.setText("Hard");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setText("Quit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(87, 87, 87))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(safariNameField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(safariNameField)
                .addGap(31, 31, 31)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Az "Easy" gomb eseménykezelője, elindítja a játékot könnyű nehézségi szinten.
     * @param evt az esemény objektuma
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("Start /w easy");
        safariName = safariNameField.getText();
        System.out.println(safariName);
        Game game = new Game(DifficultyLevel.EASY, safariName);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * A "Normal" gomb eseménykezelője, elindítja a játékot normál nehézségi szinten.
     * @param evt az esemény objektuma
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println("Start /w normal");
        safariName = safariNameField.getText();
        Game game = new Game(DifficultyLevel.MEDIUM,safariName);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * A "Hard" gomb eseménykezelője, elindítja a játékot nehéz nehézségi szinten.
     * @param evt az esemény objektuma
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.out.println("Start /w hard");
        safariName = safariNameField.getText();
        Game game = new Game(DifficultyLevel.HARD,safariName);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * A "Quit" gomb eseménykezelője, bezárja az alkalmazást.
     * @param evt az esemény objektuma
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * A program belépési pontja, elindítja a menüt.
     * @param args parancssori argumentumok
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /** Kitöltő elem a panel elrendezéséhez. */
    private javax.swing.Box.Filler filler1;
    /** Könnyű nehézségi szintet választó gomb. */
    private javax.swing.JButton jButton1;
    /** Normál nehézségi szintet választó gomb. */
    private javax.swing.JButton jButton2;
    /** Nehéz nehézségi szintet választó gomb. */
    private javax.swing.JButton jButton3;
    /** Kilépés gomb. */
    private javax.swing.JButton jButton4;
    /** A menü címsorát megjelenítő címke. */
    private javax.swing.JLabel jLabel1;
    /** A fő panel, amely a menü elemeit tartalmazza. */
    private javax.swing.JPanel jPanel1;
    /** Görgetősáv a szövegmezőhöz. */
    private javax.swing.JScrollPane jScrollPane1;
    /** Szövegmező, amelyben információk jelenhetnek meg. */
    private javax.swing.JTextArea jTextArea1;
    /** A szafari nevét bekérő szövegmező. */
    private javax.swing.JTextField safariNameField;
    // End of variables declaration//GEN-END:variables
}
