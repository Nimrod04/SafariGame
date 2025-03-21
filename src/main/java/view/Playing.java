/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;

/**
 *
 * @author nimro
 */
public class Playing extends javax.swing.JFrame {
    public boolean inSecurityShop = false;
    public boolean inAnimalShop = false;
    public boolean inPlantShop = false;
    public boolean inRoadShop = false;

    private void setSecurityButtonActions() {
        // Alapértelmezett gomb feliratokat és eseménykezelőket állítunk be
        buyButton_1.removeActionListener(buyButton_1.getActionListeners()[0]);
        buyButton_2.removeActionListener(buyButton_2.getActionListeners()[0]);
        buyButton_3.removeActionListener(buyButton_3.getActionListeners()[0]);
        buyButton_4.removeActionListener(buyButton_4.getActionListeners()[0]);
        
        buyButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Kamera");
            }
        });
        
        buyButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Töltőállomás");
            }
        });
        
        buyButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Drón");
            }
        });
        
        buyButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Léghajó");
            }
        });
    }
    
    private void setAnimalButtonActions() {
        // Régi eseménykezelők eltávolítása, ha léteznek
        if (buyButton_1.getActionListeners().length > 0) {
            buyButton_1.removeActionListener(buyButton_1.getActionListeners()[0]);
        }
        if (buyButton_2.getActionListeners().length > 0) {
            buyButton_2.removeActionListener(buyButton_2.getActionListeners()[0]);
        }
        if (buyButton_3.getActionListeners().length > 0) {
            buyButton_3.removeActionListener(buyButton_3.getActionListeners()[0]);
        }
        if (buyButton_4.getActionListeners().length > 0) {
            buyButton_4.removeActionListener(buyButton_4.getActionListeners()[0]);
        }
        
        // Új eseménykezelők hozzáadása
        buyButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Gazella");
            }
        });
        
        buyButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Elefánt");
            }
        });
        
        buyButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Oroszlán");
            }
        });
        
        buyButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Gepárd");
            }
        });
    }
    
    private void setPlantButtonActions() {
        // Régi eseménykezelők eltávolítása, ha léteznek
        if (buyButton_1.getActionListeners().length > 0) {
            buyButton_1.removeActionListener(buyButton_1.getActionListeners()[0]);
        }
        if (buyButton_2.getActionListeners().length > 0) {
            buyButton_2.removeActionListener(buyButton_2.getActionListeners()[0]);
        }
        if (buyButton_3.getActionListeners().length > 0) {
            buyButton_3.removeActionListener(buyButton_3.getActionListeners()[0]);
        }
        if (buyButton_4.getActionListeners().length > 0) {
            buyButton_4.removeActionListener(buyButton_4.getActionListeners()[0]);
        }
        
        // Új eseménykezelők hozzáadása
        buyButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fű");
            }
        });
        
        buyButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fa");
            }
        });
        
        buyButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tó");
            }
        });
        
        buyButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Semmi");
            }
        });
    }
    
    public JPanel getJPanel(){
        return jPanel1;
    }
    /**
     * Creates new form Playing
     */
    public Playing() {

        initComponents();
        shopPanel.setVisible(false);

        roundIconPanel2.setIconPath("visitor.png");
        roundIconPanel3.setIconPath("carni.png");
        roundIconPanel4.setIconPath("herbi.png");
        roundIconPanel5.setIconPath("m.png");

        hireButton.setIconPath("shop.png");
        shopButton.setIconPath("hire.png");
        roundButton2.setIconPath("play.png");
        roundButton3.setIconPath("dplay.png");
        roundButton4.setIconPath("tplay.png");
        roundButton5.setBorderThickness(0);
        roundButton5.setIconPath("exit.png");

        buyRoadButton.setIconPath("buyRoad.png");
        buySecurityButton.setIconPath("buyCamera.jpg");
        buyAnimalsButton.setIconPath("buyAnimal.png");
        buyPlantsButton.setIconPath("buyPlants.png");

        secondaryShopPanel.setVisible(false);

        visitorCount.setText("??/??");
        herbiCount.setText("??/??");
        carniCount.setText("??/??");
        moneyCount.setText("??/??");
        
        shopLabel1.setText("Út építés - 200$/db");
        shopLabel2.setText("Biztonság");
        shopLabel3.setText("Állatok");
        shopLabel4.setText("Környezet");


    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamePanel = new javax.swing.JPanel();
        roundIconPanel2 = new view.RoundIconPanel();
        roundIconPanel3 = new view.RoundIconPanel();
        roundIconPanel4 = new view.RoundIconPanel();
        roundIconPanel5 = new view.RoundIconPanel();
        roundButton5 = new view.RoundButton();
        shopPanel = new javax.swing.JPanel();
        buyRoadButton = new view.ShopButtons();
        buySecurityButton = new view.ShopButtons();
        buyAnimalsButton = new view.ShopButtons();
        buyPlantsButton = new view.ShopButtons();
        shopLabel1 = new javax.swing.JLabel();
        shopLabel2 = new javax.swing.JLabel();
        shopLabel3 = new javax.swing.JLabel();
        shopLabel4 = new javax.swing.JLabel();
        secondaryShopPanel = new javax.swing.JPanel();
        buyButton_1 = new view.ShopButtons();
        buyButton_2 = new view.ShopButtons();
        buyButton_3 = new view.ShopButtons();
        buyButton_4 = new view.ShopButtons();
        buyButton_1Label = new javax.swing.JLabel();
        buyButton_2Label = new javax.swing.JLabel();
        buyButton_3Label = new javax.swing.JLabel();
        buyButton_4Label = new javax.swing.JLabel();
        visitorCount = new javax.swing.JLabel();
        herbiCount = new javax.swing.JLabel();
        carniCount = new javax.swing.JLabel();
        moneyCount = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        shopButton = new view.RoundButton();
        hireButton = new view.RoundButton();
        moneyLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        roundButton2 = new view.RoundButton();
        roundButton3 = new view.RoundButton();
        roundButton4 = new view.RoundButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        miniMap = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Safari Game");
        setBounds(new java.awt.Rectangle(0, 0, 1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        gamePanel.setMaximumSize(new java.awt.Dimension(1270, 710));
        gamePanel.setMinimumSize(new java.awt.Dimension(1270, 710));
        gamePanel.setPreferredSize(new java.awt.Dimension(1270, 710));

        javax.swing.GroupLayout roundIconPanel2Layout = new javax.swing.GroupLayout(roundIconPanel2);
        roundIconPanel2.setLayout(roundIconPanel2Layout);
        roundIconPanel2Layout.setHorizontalGroup(
            roundIconPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        roundIconPanel2Layout.setVerticalGroup(
            roundIconPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundIconPanel3Layout = new javax.swing.GroupLayout(roundIconPanel3);
        roundIconPanel3.setLayout(roundIconPanel3Layout);
        roundIconPanel3Layout.setHorizontalGroup(
            roundIconPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        roundIconPanel3Layout.setVerticalGroup(
            roundIconPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundIconPanel4Layout = new javax.swing.GroupLayout(roundIconPanel4);
        roundIconPanel4.setLayout(roundIconPanel4Layout);
        roundIconPanel4Layout.setHorizontalGroup(
            roundIconPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        roundIconPanel4Layout.setVerticalGroup(
            roundIconPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundIconPanel5Layout = new javax.swing.GroupLayout(roundIconPanel5);
        roundIconPanel5.setLayout(roundIconPanel5Layout);
        roundIconPanel5Layout.setHorizontalGroup(
            roundIconPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        roundIconPanel5Layout.setVerticalGroup(
            roundIconPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        roundButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton5ActionPerformed(evt);
            }
        });

        shopPanel.setBackground(new java.awt.Color(255, 255, 255));
        shopPanel.setPreferredSize(new java.awt.Dimension(442, 116));

        buyRoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyRoadButtonActionPerformed(evt);
            }
        });

        buySecurityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buySecurityButtonActionPerformed(evt);
            }
        });

        buyAnimalsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyAnimalsButtonActionPerformed(evt);
            }
        });

        buyPlantsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyPlantsButtonActionPerformed(evt);
            }
        });

        shopLabel1.setText("jLabel4");

        shopLabel2.setText("jLabel3");

        shopLabel3.setText("jLabel2");

        shopLabel4.setText("jLabel1");

        javax.swing.GroupLayout shopPanelLayout = new javax.swing.GroupLayout(shopPanel);
        shopPanel.setLayout(shopPanelLayout);
        shopPanelLayout.setHorizontalGroup(
            shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shopLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buyRoadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shopLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buySecurityButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buyAnimalsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shopLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shopPanelLayout.createSequentialGroup()
                        .addComponent(buyPlantsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(shopLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        shopPanelLayout.setVerticalGroup(
            shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buyPlantsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buyAnimalsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buySecurityButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buyRoadButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shopLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shopLabel3)
                    .addComponent(shopLabel2)
                    .addComponent(shopLabel1))
                .addContainerGap())
        );

        secondaryShopPanel.setBackground(new java.awt.Color(255, 255, 255));

        buyButton_1.setForeground(new java.awt.Color(255, 255, 255));
        buyButton_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButton_1ActionPerformed(evt);
            }
        });

        buyButton_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButton_2ActionPerformed(evt);
            }
        });

        buyButton_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButton_3ActionPerformed(evt);
            }
        });

        buyButton_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButton_4ActionPerformed(evt);
            }
        });

        buyButton_1Label.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        buyButton_1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buyButton_2Label.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        buyButton_2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buyButton_3Label.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        buyButton_3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buyButton_4Label.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        buyButton_4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout secondaryShopPanelLayout = new javax.swing.GroupLayout(secondaryShopPanel);
        secondaryShopPanel.setLayout(secondaryShopPanelLayout);
        secondaryShopPanelLayout.setHorizontalGroup(
            secondaryShopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secondaryShopPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(secondaryShopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(secondaryShopPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(buyButton_1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buyButton_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(secondaryShopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(secondaryShopPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(buyButton_2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buyButton_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(secondaryShopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(secondaryShopPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(buyButton_3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buyButton_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(secondaryShopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(secondaryShopPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(buyButton_4Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buyButton_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        secondaryShopPanelLayout.setVerticalGroup(
            secondaryShopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secondaryShopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(secondaryShopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buyButton_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buyButton_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buyButton_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buyButton_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(secondaryShopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buyButton_1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buyButton_2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buyButton_3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buyButton_4Label, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        visitorCount.setText("jLabel2");

        herbiCount.setText("jLabel3");

        carniCount.setText("jLabel4");

        moneyCount.setText("jLabel5");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        shopButton.setBackground(new java.awt.Color(255, 255, 255));
        shopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shopButtonActionPerformed(evt);
            }
        });

        hireButton.setBackground(new java.awt.Color(255, 255, 255));
        hireButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hireButtonActionPerformed(evt);
            }
        });

        moneyLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        moneyLabel.setText("200.000$");
        moneyLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        dateLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dateLabel.setText("2025.03.19");
        dateLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        roundButton2.setBackground(new java.awt.Color(255, 255, 255));
        roundButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton2ActionPerformed(evt);
            }
        });

        roundButton3.setBackground(new java.awt.Color(255, 255, 255));
        roundButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton3ActionPerformed(evt);
            }
        });

        roundButton4.setBackground(new java.awt.Color(255, 255, 255));
        roundButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                .addComponent(moneyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(377, 377, 377)
                .addComponent(hireButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(590, Short.MAX_VALUE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(578, 578, 578)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(moneyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(roundButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hireButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(shopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(49, Short.MAX_VALUE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Safari Name");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jProgressBar1.setValue(40);

        miniMap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout miniMapLayout = new javax.swing.GroupLayout(miniMap);
        miniMap.setLayout(miniMapLayout);
        miniMapLayout.setHorizontalGroup(
            miniMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );
        miniMapLayout.setVerticalGroup(
            miniMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(secondaryShopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(roundIconPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(moneyCount, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(roundIconPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                        .addComponent(roundIconPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                                    .addComponent(roundIconPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(carniCount, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(visitorCount)
                                    .addComponent(herbiCount))))
                        .addGap(425, 477, Short.MAX_VALUE)
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(244, 244, 244)
                        .addComponent(miniMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(miniMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(secondaryShopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(shopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(visitorCount))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(roundIconPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(herbiCount)
                        .addGap(48, 48, 48)
                        .addComponent(carniCount))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundIconPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundIconPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundIconPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(moneyCount)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roundButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton2ActionPerformed
        System.out.println("1x speed");

    }//GEN-LAST:event_roundButton2ActionPerformed

    private void roundButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton3ActionPerformed
        System.out.println("2x speed");
    }//GEN-LAST:event_roundButton3ActionPerformed

    private void roundButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton4ActionPerformed
        System.out.println("3x speed");

    }//GEN-LAST:event_roundButton4ActionPerformed

    private void hireButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hireButtonActionPerformed
        System.out.println("Open shop");
        if (shopPanel.isVisible()) {
            shopPanel.setVisible(false);
            secondaryShopPanel.setVisible(false);
        } else {
            shopPanel.setVisible(true);            
            secondaryShopPanel.setVisible(false);

        }
    }//GEN-LAST:event_hireButtonActionPerformed

    private void roundButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_roundButton5ActionPerformed

    private void buyRoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyRoadButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Building roads!");
        
    }//GEN-LAST:event_buyRoadButtonActionPerformed

    private void buySecurityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buySecurityButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Building camera system!");
        if (secondaryShopPanel.isVisible()) {
            secondaryShopPanel.setVisible(false);
        } else {
            setSecurityButtonActions();
            buyButton_1Label.setText("Kamera - 200$");
            buyButton_2Label.setText("Töltő Állomás - 500$");
            buyButton_3Label.setText("Drón - 1000$");
            buyButton_4Label.setText("Léghajó - 4000$");
            
            buyButton_1.setIconPath("buySecurityCamera_Item.jpg");
            buyButton_2.setIconPath("buyChargingstation_Item.png");
            buyButton_3.setIconPath("buyDrone_Item.png");          
            buyButton_4.setIconPath("buyAirship_Item.png");
            secondaryShopPanel.setVisible(true);
        }
    }//GEN-LAST:event_buySecurityButtonActionPerformed

    private void buyAnimalsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyAnimalsButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Buying animals...");
        if (secondaryShopPanel.isVisible()) {
            secondaryShopPanel.setVisible(false);
        } else {
            setAnimalButtonActions();
            
            buyButton_1Label.setText("Gazella - 500$/db");
            buyButton_2Label.setText("Elepánt - 1000$/db");
            buyButton_3Label.setText("Oroszlán - 2000$/db");
            buyButton_4Label.setText("Gepárd - 2800$/db");
            
            buyButton_1.setIconPath("gazelle_Icon.png");
            buyButton_2.setIconPath("elephant_Icon.jpg");
            buyButton_3.setIconPath("lion_Icon.jpg");
            buyButton_4.setIconPath("cheetah_Icon.png");
            secondaryShopPanel.setVisible(true);
        }
    }//GEN-LAST:event_buyAnimalsButtonActionPerformed

    private void buyPlantsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyPlantsButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Planting...");
        if (secondaryShopPanel.isVisible()) {
            secondaryShopPanel.setVisible(false);
        } else {
            setPlantButtonActions();
            
            buyButton_1Label.setText("Legelő(Fű) - 500$");
            buyButton_2Label.setText("Pagony(Fa) - 1000$/db");
            buyButton_3Label.setText("Oázis(Tó) - 2000$/db");
            buyButton_4Label.setText("");
            
            buyButton_1.setIconPath("grass_Icon.png");
            buyButton_2.setIconPath("tree_Icon.png");
            buyButton_3.setIconPath("lake_Icon.png");
            buyButton_4.setIconPath("none.jpg");
            secondaryShopPanel.setVisible(true);
        }
    }//GEN-LAST:event_buyPlantsButtonActionPerformed

    private void buyButton_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButton_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buyButton_1ActionPerformed

    private void buyButton_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButton_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buyButton_4ActionPerformed

    private void buyButton_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButton_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buyButton_2ActionPerformed

    private void buyButton_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButton_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buyButton_3ActionPerformed

    private void shopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shopButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Hiring staff...");
    }//GEN-LAST:event_shopButtonActionPerformed

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(Playing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Playing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Playing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Playing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Playing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.ShopButtons buyAnimalsButton;
    private view.ShopButtons buyButton_1;
    private javax.swing.JLabel buyButton_1Label;
    private view.ShopButtons buyButton_2;
    private javax.swing.JLabel buyButton_2Label;
    private view.ShopButtons buyButton_3;
    private javax.swing.JLabel buyButton_3Label;
    private view.ShopButtons buyButton_4;
    private javax.swing.JLabel buyButton_4Label;
    private view.ShopButtons buyPlantsButton;
    private view.ShopButtons buyRoadButton;
    private view.ShopButtons buySecurityButton;
    private javax.swing.JLabel carniCount;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JLabel herbiCount;
    private view.RoundButton hireButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JPanel miniMap;
    private javax.swing.JLabel moneyCount;
    private javax.swing.JLabel moneyLabel;
    private view.RoundButton roundButton2;
    private view.RoundButton roundButton3;
    private view.RoundButton roundButton4;
    private view.RoundButton roundButton5;
    private view.RoundIconPanel roundIconPanel2;
    private view.RoundIconPanel roundIconPanel3;
    private view.RoundIconPanel roundIconPanel4;
    private view.RoundIconPanel roundIconPanel5;
    private javax.swing.JPanel secondaryShopPanel;
    private view.RoundButton shopButton;
    private javax.swing.JLabel shopLabel1;
    private javax.swing.JLabel shopLabel2;
    private javax.swing.JLabel shopLabel3;
    private javax.swing.JLabel shopLabel4;
    private javax.swing.JPanel shopPanel;
    private javax.swing.JLabel visitorCount;
    // End of variables declaration//GEN-END:variables
}
