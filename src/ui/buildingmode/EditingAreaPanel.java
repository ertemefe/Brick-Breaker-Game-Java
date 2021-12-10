package ui.buildingmode;

import domain.Controller;
import domain.objects.obstacles.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class EditingAreaPanel extends JPanel implements ActionListener {
    ArrayList<JPanel> gridList = new ArrayList<>();
    int row = 10;
    int column = 40;
    int width = 1200;
    int height = 500;
    JPanel create = new JPanel();
    JPanel edit = new JPanel();
    JLabel numberOfSimpleLabel;
    JLabel numberOfFirmLabel;
    JLabel numberOfExplosiveLabel;
    JLabel numberOfGiftLabel;
    JLabel numberOfTotalLabel;
    JLabel minNumSimpleLabel;
    JLabel minNumFirmLabel;
    JLabel minNumExplosiveLabel;
    JLabel minNumGiftLabel;
    JTextField numberOfSimpleField;
    JTextField numberOfFirmField;
    JTextField numberOfExplosiveField;
    JTextField numberOfGiftField;
    JButton generateButton;
    JButton changeObstacleLoc;
    int minNumOfSimple = 75;
    int minNumOfFirm = 10;
    int minNumOfExplosive = 5;
    int minNumOfGift = 10;
    int simpleNum;
    int firmNum;
    int explosiveNum;
    int giftNum;
    JLabel numberOfTotalField;
    Controller controller;

    public EditingAreaPanel(Controller controller) {

        this.controller = controller;

        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerSize(0);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(EditPanel(controller));
        splitPane.setBottomComponent(CreationPanel(controller));
        add(splitPane);
    }

    public JPanel CreationPanel(Controller controller) {
        create.setPreferredSize(new Dimension(controller.screenWidth, 100));
        JPanel obstaclePanel = new JPanel(new GridBagLayout());

        //2.column
        numberOfSimpleLabel = new JLabel("Number of Simple Obstacles");
        numberOfFirmLabel = new JLabel("Number of Firm Obstacles");
        numberOfExplosiveLabel = new JLabel("Number of Explosive Obstacles");
        numberOfGiftLabel = new JLabel("Number of Gift Obstacles");
        numberOfTotalLabel = new JLabel("Total number of obstacles : ");

        //3.column
        minNumSimpleLabel = new JLabel(minNumOfSimple + "+");
        minNumFirmLabel = new JLabel(minNumOfFirm + "+");
        minNumExplosiveLabel = new JLabel(minNumOfExplosive + "+");
        minNumGiftLabel = new JLabel(minNumOfGift + "+");

        //4.column
        numberOfSimpleField = new JTextField(3);
        numberOfFirmField = new JTextField(3);
        numberOfExplosiveField = new JTextField(3);
        numberOfGiftField = new JTextField(3);
        numberOfTotalField = new JLabel(totalNumber());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridx = 0;
        gbc.gridy = 0;
        obstaclePanel.add(controller.addNewObstacle("simple").getImage(), gbc);
        gbc.gridx = 1;
        obstaclePanel.add(numberOfSimpleLabel, gbc);
        gbc.gridx = 2;
        obstaclePanel.add(minNumSimpleLabel, gbc);
        gbc.gridx = 3;
        obstaclePanel.add(numberOfSimpleField, gbc);

        gbc.gridx = 4;
        JPanel firmObstacleImage = controller.addNewObstacle("firm").getImage();
        firmObstacleImage.removeAll();
        obstaclePanel.add(firmObstacleImage, gbc);
        gbc.gridx = 5;
        obstaclePanel.add(numberOfFirmLabel, gbc);
        gbc.gridx = 6;
        obstaclePanel.add(minNumFirmLabel, gbc);
        gbc.gridx = 7;
        obstaclePanel.add(numberOfFirmField, gbc);

        gbc.gridx = 8;
        obstaclePanel.add(controller.addNewObstacle("explosive").getImage(), gbc);
        gbc.gridx = 9;
        obstaclePanel.add(numberOfExplosiveLabel, gbc);
        gbc.gridx = 10;
        obstaclePanel.add(minNumExplosiveLabel, gbc);
        gbc.gridx = 11;
        obstaclePanel.add(numberOfExplosiveField, gbc);

        gbc.gridx = 12;
        obstaclePanel.add(controller.addNewObstacle("gift").getImage(), gbc);
        gbc.gridx = 13;
        obstaclePanel.add(numberOfGiftLabel, gbc);
        gbc.gridx = 14;
        obstaclePanel.add(minNumGiftLabel, gbc);
        gbc.gridx = 15;
        obstaclePanel.add(numberOfGiftField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        obstaclePanel.add(numberOfTotalLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        obstaclePanel.add(numberOfTotalField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        generateButton = new JButton("Generate Obstacles");
        generateButton.addActionListener(this);
        generateButton.setVisible(true);
        obstaclePanel.add(generateButton, gbc);

        gbc.gridx = 5;
        gbc.gridy = 3;
        changeObstacleLoc = new JButton("Change Obstacle Location");
        changeObstacleLoc.addActionListener(this);
        changeObstacleLoc.setVisible(true);
        obstaclePanel.add(changeObstacleLoc, gbc);

        obstaclePanel.setVisible(true);
        create.add(obstaclePanel);
        create.setVisible(true);
        return create;
    }

    public JPanel EditPanel(Controller controller) {
        edit.setPreferredSize(new Dimension(width, height));
        edit.setLayout(new GridLayout(row * 2, column));
        griding(edit);
        initializeSpawnObstacle(controller, "simple", controller.minObstacleCountSimple);
        initializeSpawnObstacle(controller, "firm", controller.minObstacleCountFirm);
        initializeSpawnObstacle(controller, "explosive", controller.minObstacleCountExplosive);
        initializeSpawnObstacle(controller, "gift", controller.minObstacleCountGift);
        edit.setVisible(true);
        return edit;
    }

    private void griding(JPanel panel) {

        for (int k = 0; k < row * column; k++) {
            JPanel n = new JPanel();
            n.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            n.setBackground(Color.BLACK);
            n.setVisible(true);
            gridList.add(n);
            panel.add(n);
        }
        for (int k = 0; k < row * column; k++) {
            JPanel n = new JPanel();
            n.setBackground(Color.BLACK);
            n.setVisible(true);
            gridList.add(n);
            panel.add(n);
        }
    }

    public void initializeSpawnObstacle(Controller controller, String obstacleType, int obstacleAmount) {

        switch (obstacleType) {
            case "simple":
                for (int k = 0; k < obstacleAmount; k++) {
                    Obstacle simple = controller.addNewObstacle("simple");
                    gridList.get(simple.getInitialSpawnLocation()).add(simple.getImage());
                }
                break;
            case "firm":
                for (int k = 0; k < obstacleAmount; k++) {
                    Obstacle firm = controller.addNewObstacle("firm");
                    gridList.get(firm.getInitialSpawnLocation()).add(firm.getImage());
                }
                break;
            case "explosive":
                for (int k = 0; k < obstacleAmount; k++) {
                    Obstacle explosive = controller.addNewObstacle("explosive");
                    gridList.get(explosive.getInitialSpawnLocation()).add(explosive.getImage());
                }
                break;
            case "gift":
                for (int k = 0; k < obstacleAmount; k++) {
                    Obstacle gift = controller.addNewObstacle("gift");
                    gridList.get(gift.getInitialSpawnLocation()).add(gift.getImage());
                }
                break;
        }
    }

    public void removeObstacle(String str, int i) {
        switch (str) {
            case "simple" -> {
                controller.spawnLocation.remove(i);
                controller.simpleLoc.remove(i);
                gridList.get(i).removeAll();
                updateUI();
            }
            case "firm" -> {
                controller.spawnLocation.remove(i);
                controller.firmLoc.remove(i);
                gridList.get(i).removeAll();
                updateUI();
            }
            case "explosive" -> {
                controller.spawnLocation.remove(i);
                controller.explosiveLoc.remove(i);
                gridList.get(i).removeAll();
                updateUI();
            }
            case "gift" -> {
                controller.spawnLocation.remove(i);
                controller.giftLoc.remove(i);
                gridList.get(i).removeAll();
                updateUI();
            }
        }
        System.out.println(str + " silindi");
    }

    public void moveObstacle(Controller controller, String str, int i) {
        switch (str) {
            case "simple" -> {
                controller.spawnLocation.put(i, i);
                controller.simpleLoc.put(i, i);
                gridList.get(i).add(controller.addNewObstacle("simple").getImage());
                updateUI();
            }
            case "firm" -> {
                controller.spawnLocation.put(i, i);
                controller.firmLoc.put(i, i);
                gridList.get(i).add(controller.addNewObstacle("firm").getImage());
                updateUI();
            }
            case "explosive" -> {
                controller.spawnLocation.put(i, i);
                controller.explosiveLoc.put(i, i);
                gridList.get(i).add(controller.addNewObstacle("explosive").getImage());
                updateUI();
            }
            case "gift" -> {
                controller.spawnLocation.put(i, i);
                controller.giftLoc.put(i, i);
                gridList.get(i).add(controller.addNewObstacle("gift").getImage());
                updateUI();
            }
        }
        System.out.println(str + " eklendi");
    }

    private String totalNumber() {
        int total = minNumOfSimple + minNumOfFirm + minNumOfGift + minNumOfExplosive + simpleNum + firmNum + explosiveNum + giftNum;
        return String.valueOf(total);
    }

    //bi kere değer girildikten sonra silince sıfıra dönmesi gerekir mi gerekmez mi bilmiyorum,
    //sayı girmediğin sürece variable değişmiyor, çözülür şimdilik acelesi yok

    //entred value 300+ olduğunda çöküyo --çöz
    //ayrı ayrı toplamları da 300 olabilir tek seferde de bi boolean metodu yaz burda o metodu kontrol ettirir
    private void update(Controller controller) {
        if (!Objects.equals(numberOfSimpleField.getText(), "")) {
            if (simpleNum != Integer.parseInt(numberOfSimpleField.getText())) {
                minNumOfSimple += simpleNum;
                simpleNum = Integer.parseInt(numberOfSimpleField.getText());
                initializeSpawnObstacle(controller, "simple", simpleNum);
            }
        }
        if (!Objects.equals(numberOfFirmField.getText(), "")) {
            if (firmNum != Integer.parseInt(numberOfFirmField.getText())) {
                minNumOfFirm += firmNum;
                firmNum = Integer.parseInt(numberOfFirmField.getText());
                initializeSpawnObstacle(controller, "firm", firmNum);
            }
        }
        if (!Objects.equals(numberOfExplosiveField.getText(), "")) {
            if (explosiveNum != Integer.parseInt(numberOfExplosiveField.getText())) {
                minNumOfExplosive += explosiveNum;
                explosiveNum = Integer.parseInt(numberOfExplosiveField.getText());
                initializeSpawnObstacle(controller, "explosive", explosiveNum);
            }
        }
        if (!Objects.equals(numberOfGiftField.getText(), "")) {
            if (giftNum != Integer.parseInt(numberOfGiftField.getText())) {
                minNumOfGift += giftNum;
                giftNum = Integer.parseInt(numberOfGiftField.getText());
                initializeSpawnObstacle(controller, "gift", giftNum);
            }
        }
    }

    //kontrol etmek için
    private void print() {
        System.out.println("simple count: " + minNumOfSimple + "+" + simpleNum + "=" + (simpleNum + minNumOfSimple));
        System.out.println("firm count: " + minNumOfFirm + "+" + firmNum + "=" + (firmNum + minNumOfFirm));
        System.out.println("explosive count: " + minNumOfExplosive + "+" + explosiveNum + "=" + (explosiveNum + minNumOfExplosive));
        System.out.println("gift count: " + minNumOfGift + "+" + giftNum + "=" + (giftNum + minNumOfGift));
        System.out.println();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            if (Integer.parseInt(totalNumber()) < 400) {
                update(controller);
                print();
                numberOfTotalField.setText(totalNumber());
                this.updateUI();
            }
        }
        if (e.getSource() == changeObstacleLoc) {
            edit.grabFocus();
        }
    }
}
