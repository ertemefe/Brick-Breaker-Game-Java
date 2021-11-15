package ui.buildingmode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationPanel extends JPanel implements ActionListener {
    JButton startGameButton;
    JButton backButton;
    JButton saveMapButton;
    int minNumOfSimple = 75;
    int minNumOfFirm = 10;
    int minNumOfExplosive = 5;
    int minNumOfGift = 10;

    public CreationPanel() {

        setSize(300, 750);
        setBackground(Color.RED);
        addObstaclePanel();
        addButtonPanel();
        setVisible(true);
    }

    private void addObstaclePanel() {
        JPanel obstaclePanel = new JPanel(new GridBagLayout());

        //1. column
        JPanel simpleObstacleImage = new JPanel();
        simpleObstacleImage.setSize(24, 20);
        simpleObstacleImage.setBackground(Color.WHITE);
        JPanel firmObstacleImage = new JPanel();
        firmObstacleImage.setSize(24, 20);
        firmObstacleImage.setBackground(Color.DARK_GRAY);
        JPanel explosiveObstacleImage = new JPanel();
        explosiveObstacleImage.setSize(24, 20);
        explosiveObstacleImage.setBackground(Color.MAGENTA);
        JPanel giftObstacleImage = new JPanel();
        giftObstacleImage.setSize(24, 20);
        giftObstacleImage.setBackground(Color.CYAN);

        //2.column
        JLabel numberOfSimpleLabel = new JLabel("Number of Simple Obstacles");
        JLabel numberOfFirmLabel = new JLabel("Number of Firm Obstacles");
        JLabel numberOfExplosiveLabel = new JLabel("Number of Explosive Obstacles");
        JLabel numberOfGiftLabel = new JLabel("Number of Gift Obstacles");
        //JLabel numberOfTotalLabel = new JLabel("Total number of obstacles : ");

        //3.column
        JLabel minNumSimpleLabel = new JLabel(minNumOfSimple + " +");
        JLabel minNumFirmLabel = new JLabel(minNumOfFirm + " +");
        JLabel minNumExplosiveLabel = new JLabel(minNumOfExplosive + " +");
        JLabel minNumGiftLabel = new JLabel(minNumOfGift + " +");

        //4.column
        JTextField numberOfSimpleField = new JTextField(3);
        JTextField numberOfFirmField = new JTextField(3);
        JTextField numberOfExplosiveField = new JTextField(3);
        JTextField numberOfGiftField = new JTextField(3);
        //JLabel numberOfTotalField = new JLabel(totalNumber(numberOfSimpleField, numberOfFirmField, numberOfExplosiveField, numberOfGiftField));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        obstaclePanel.add(simpleObstacleImage, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        obstaclePanel.add(numberOfSimpleLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        obstaclePanel.add(numberOfSimpleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        obstaclePanel.add(firmObstacleImage, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        obstaclePanel.add(numberOfFirmLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        obstaclePanel.add(numberOfFirmField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        obstaclePanel.add(explosiveObstacleImage, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        obstaclePanel.add(numberOfExplosiveLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        obstaclePanel.add(numberOfExplosiveField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        obstaclePanel.add(giftObstacleImage, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        obstaclePanel.add(numberOfGiftLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        obstaclePanel.add(numberOfGiftField, gbc);

        /*
        gbc.gridx = 1;
        gbc.gridy = 4;
        obstaclePanel.add(numberOfTotalLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        obstaclePanel.add(numberOfTotalField, gbc);
        */

        obstaclePanel.setBackground(Color.ORANGE);
        obstaclePanel.setVisible(true);
        add(obstaclePanel, BorderLayout.NORTH);
    }


    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();

        saveMapButton = new JButton("Save Map");
        saveMapButton.addActionListener(this);
        buttonPanel.add(saveMapButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(this);
        buttonPanel.add(startGameButton);

        buttonPanel.setBackground(Color.GREEN);
        buttonPanel.setVisible(true);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == backButton) {
            goBackToNewGameMenu();
        }
        if (evt.getSource() == saveMapButton) {
            saveMap();
        }
        if (evt.getSource() == startGameButton) {
            startGame();
        }
    }

    //başta null olcağı için error verip çöküyor if falan kullanılabilir sonra bak çok mühim değil
    private String totalNumber(JTextField simple, JTextField firm, JTextField explosive, JTextField gift) {
        int simpleNum, firmNum, explosiveNum, giftNum, total;

        simpleNum = Integer.parseInt(simple.getText());
        firmNum = Integer.parseInt(firm.getText());
        explosiveNum = Integer.parseInt(explosive.getText());
        giftNum = Integer.parseInt(gift.getText());
        total = minNumOfSimple + minNumOfFirm + minNumOfGift + minNumOfExplosive + simpleNum + firmNum + explosiveNum + giftNum;
        return String.valueOf(total);
    }

    //burdaki dispose sıkıntısını çöz !
    private void startGame() {
    }

    private void saveMap() {
    }

    private void goBackToNewGameMenu() {
    }

}