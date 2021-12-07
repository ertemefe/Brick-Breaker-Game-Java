package ui.buildingmode;

import domain.Controller;
import domain.objects.obstacles.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class EditingAreaPanel extends JPanel implements ActionListener {
    ArrayList<JPanel> gridList = new ArrayList<>();
    HashMap<Integer, Integer> randomNumberExists = new HashMap<>();
    HashMap<Integer, JPanel> simpleObstacleList = new HashMap<>();
    HashMap<Integer, JPanel> firmObstacleList = new HashMap<>();
    HashMap<Integer, JPanel> explosiveObstacleList = new HashMap<>();
    HashMap<Integer, JPanel> giftObstacleList = new HashMap<>();
    HashMap<Integer, JPanel> allObstacleList = new HashMap<>();
    Random rand = new Random();
    int random_generate = -1;
    int row = 10;
    int column = 40;
    int width = 1200;
    int height = 500;
    int L = width / 10;
    JPanel create = new JPanel();
    JPanel edit = new JPanel();
    JPanel simpleObstacleImage;
    JPanel firmObstacleImage;
    JPanel explosiveObstacleImage;
    JPanel giftObstacleImage;
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
    int minNumOfSimple = 75;
    int minNumOfFirm = 10;
    int minNumOfExplosive = 5;
    int minNumOfGift = 10;
    int simpleNum;
    int firmNum;
    int explosiveNum;
    int giftNum;
    JLabel numberOfTotalField;

    public EditingAreaPanel(Controller controller) {
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerSize(0);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(EditPanel(controller));
        splitPane.setBottomComponent(CreationPanel());
        add(splitPane);
    }

    public JPanel CreationPanel() {
        create.setPreferredSize(new Dimension(width, 100));

        JPanel obstaclePanel = new JPanel(new GridBagLayout());
        obstaclePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //1. column
        /*Obstacle simpleObstacle = controller.obstacleSimple;
        Graphics graphicsSimple = controller.obstacleSimple.draw(getGraphics());
        Obstacle firmObstacle = controller.obstacleSimple;
        Graphics graphicsFirm = controller.obstacleSimple.draw(getGraphics());
        Obstacle explosiveObstacle = controller.obstacleSimple;
        Graphics graphicsExplosive = controller.obstacleSimple.draw(getGraphics());
        Obstacle giftObstacle = controller.obstacleSimple;
        Graphics graphicsGift = controller.obstacleSimple.draw(getGraphics());*/

        simpleObstacleImage = new JPanel();
        simpleObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
        simpleObstacleImage.setBackground(Color.WHITE);
        firmObstacleImage = new JPanel();
        firmObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
        firmObstacleImage.setBackground(Color.LIGHT_GRAY);
        explosiveObstacleImage = new JPanel();
        explosiveObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
        explosiveObstacleImage.setBackground(Color.MAGENTA);
        giftObstacleImage = new JPanel();
        giftObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
        giftObstacleImage.setBackground(Color.CYAN);

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
        obstaclePanel.add(simpleObstacleImage, gbc);
        //simpleObstacleImage.paint(graphicsSimple);
        gbc.gridx = 1;
        obstaclePanel.add(numberOfSimpleLabel, gbc);
        gbc.gridx = 2;
        obstaclePanel.add(minNumSimpleLabel, gbc);
        gbc.gridx = 3;
        obstaclePanel.add(numberOfSimpleField, gbc);

        gbc.gridx = 4;
        obstaclePanel.add(firmObstacleImage, gbc);
        gbc.gridx = 5;
        obstaclePanel.add(numberOfFirmLabel, gbc);
        gbc.gridx = 6;
        obstaclePanel.add(minNumFirmLabel, gbc);
        gbc.gridx = 7;
        obstaclePanel.add(numberOfFirmField, gbc);

        gbc.gridx = 8;
        obstaclePanel.add(explosiveObstacleImage, gbc);
        gbc.gridx = 9;
        obstaclePanel.add(numberOfExplosiveLabel, gbc);
        gbc.gridx = 10;
        obstaclePanel.add(minNumExplosiveLabel, gbc);
        gbc.gridx = 11;
        obstaclePanel.add(numberOfExplosiveField, gbc);

        gbc.gridx = 12;
        obstaclePanel.add(giftObstacleImage, gbc);
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

        //obstaclePanel.setBackground(Color.ORANGE);
        obstaclePanel.setVisible(true);
        create.add(obstaclePanel);
        create.setVisible(true);
        return create;
    }

    public JPanel EditPanel(Controller controller) {
        edit.setPreferredSize(new Dimension(width, height));
        edit.setLayout(new GridLayout(row * 2, column));
        griding(edit);
        randomNumberExists.put(-1, -1);
        initializeSpawnObstacle("simple", controller.minObstacleCountSimple);
        initializeSpawnObstacle("firm", controller.minObstacleCountFirm);
        initializeSpawnObstacle("explosive", controller.minObstacleCountExplosive);
        initializeSpawnObstacle("gift", controller.minObstacleCountGift);
        edit.setVisible(true);
        edit.requestFocusInWindow();
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

    public void initializeSpawnObstacle(String obstacleType, int obstacleAmount) {

        switch (obstacleType) {
            case "simple":
                for (int k = 0; k < obstacleAmount; k++) {
                    while (randomNumberExists.containsValue(random_generate))
                        random_generate = rand.nextInt(row * column);
                    randomNumberExists.put(random_generate, random_generate);
                    JPanel simpleObstacleImage = new JPanel();
                    simpleObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
                    simpleObstacleImage.setBackground(Color.WHITE);
                    simpleObstacleImage.setVisible(true);
                    gridList.get(random_generate).add(simpleObstacleImage, BorderLayout.CENTER);
                    simpleObstacleList.put(random_generate, gridList.get(random_generate));
                    allObstacleList.put(random_generate, gridList.get(random_generate));
                }
                break;

            case "firm":
                for (int k = 0; k < obstacleAmount; k++) {
                    while (randomNumberExists.containsValue(random_generate))
                        random_generate = rand.nextInt(row * column);
                    randomNumberExists.put(random_generate, random_generate);
                    JPanel firmObstacleImage = new JPanel();
                    firmObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
                    firmObstacleImage.setBackground(Color.LIGHT_GRAY);
                    gridList.get(random_generate).add(firmObstacleImage);
                    firmObstacleList.put(random_generate, gridList.get(random_generate));
                    allObstacleList.put(random_generate, gridList.get(random_generate));
                }
                break;
            case "explosive":
                for (int k = 0; k < obstacleAmount; k++) {
                    while (randomNumberExists.containsValue(random_generate))
                        random_generate = rand.nextInt(row * column);
                    randomNumberExists.put(random_generate, random_generate);
                    JPanel explosiveObstacleImage = new JPanel();
                    explosiveObstacleImage.paint(getGraphics());
                    explosiveObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
                    explosiveObstacleImage.setBackground(Color.MAGENTA);
                    gridList.get(random_generate).add(explosiveObstacleImage);
                    explosiveObstacleList.put(random_generate, gridList.get(random_generate));
                    allObstacleList.put(random_generate, gridList.get(random_generate));
                }
                break;
            case "gift":
                for (int k = 0; k < obstacleAmount; k++) {
                    while (randomNumberExists.containsValue(random_generate))
                        random_generate = rand.nextInt(row * column);
                    randomNumberExists.put(random_generate, random_generate);
                    JPanel giftObstacleImage = new JPanel();
                    giftObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
                    giftObstacleImage.setBackground(Color.CYAN);
                    gridList.get(random_generate).add(giftObstacleImage);
                    giftObstacleList.put(random_generate, gridList.get(random_generate));
                    allObstacleList.put(random_generate, gridList.get(random_generate));
                }
                break;
        }
    }

    public void removeObstacle(String str, int i) {
        switch (str) {
            case "simple" -> {
                simpleObstacleList.remove(i);
                randomNumberExists.remove(i);
                allObstacleList.remove(i);
                gridList.get(i).removeAll();
                updateUI();
            }
            case "firm" -> {
                firmObstacleList.remove(i);
                randomNumberExists.remove(i);
                allObstacleList.remove(i);
                gridList.get(i).removeAll();
                updateUI();
            }
            case "explosive" -> {
                explosiveObstacleList.remove(i);
                randomNumberExists.remove(i);
                allObstacleList.remove(i);
                gridList.get(i).removeAll();
                updateUI();
            }
            case "gift" -> {
                giftObstacleList.remove(i);
                randomNumberExists.remove(i);
                allObstacleList.remove(i);
                gridList.get(i).removeAll();
                updateUI();
            }
        }
        System.out.println(str + " silindi");
    }

    public void moveObstacle(String str, int i) {
        switch (str) {
            case "simple" -> {
                JPanel simpleObstacleImage = new JPanel();
                simpleObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
                simpleObstacleImage.setBackground(Color.WHITE);
                simpleObstacleImage.setVisible(true);
                randomNumberExists.put(i, i);
                simpleObstacleList.put(i, gridList.get(i));
                allObstacleList.put(i, gridList.get(i));
                gridList.get(i).add(simpleObstacleImage);
                updateUI();
            }
            case "firm" -> {
                JPanel firmObstacleImage = new JPanel();
                firmObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
                firmObstacleImage.setBackground(Color.LIGHT_GRAY);
                firmObstacleImage.setVisible(true);
                randomNumberExists.put(i, i);
                firmObstacleList.put(i, gridList.get(i));
                allObstacleList.put(i, gridList.get(i));
                gridList.get(i).add(firmObstacleImage);
                updateUI();
            }
            case "explosive" -> {
                JPanel explosiveObstacleImage = new JPanel();
                explosiveObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
                explosiveObstacleImage.setBackground(Color.MAGENTA);
                explosiveObstacleImage.setVisible(true);
                randomNumberExists.put(i, i);
                explosiveObstacleList.put(i, gridList.get(i));
                allObstacleList.put(i, gridList.get(i));
                gridList.get(i).add(explosiveObstacleImage);
                updateUI();
            }
            case "gift" -> {
                JPanel giftObstacleImage = new JPanel();
                giftObstacleImage.setPreferredSize(new Dimension(L / 5, 20));
                giftObstacleImage.setBackground(Color.CYAN);
                giftObstacleImage.setVisible(true);
                randomNumberExists.put(i, i);
                giftObstacleList.put(i, gridList.get(i));
                allObstacleList.put(i, gridList.get(i));
                gridList.get(i).add(giftObstacleImage);
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
    private void update() {
        if (!Objects.equals(numberOfSimpleField.getText(), "")) {
            if (simpleNum != Integer.parseInt(numberOfSimpleField.getText())){
                simpleNum = Integer.parseInt(numberOfSimpleField.getText());
                initializeSpawnObstacle("simple", simpleNum);
            }
        }
        if (!Objects.equals(numberOfFirmField.getText(), "")) {
            if (firmNum != Integer.parseInt(numberOfFirmField.getText())){
                firmNum = Integer.parseInt(numberOfFirmField.getText());
                initializeSpawnObstacle("firm", firmNum);
            }
        }
        if (!Objects.equals(numberOfExplosiveField.getText(), "")) {
            if (explosiveNum != Integer.parseInt(numberOfExplosiveField.getText())) {
                explosiveNum = Integer.parseInt(numberOfExplosiveField.getText());
                initializeSpawnObstacle("explosive", explosiveNum);
            }
        }
        if (!Objects.equals(numberOfGiftField.getText(), "")) {
            if (giftNum != Integer.parseInt(numberOfGiftField.getText())){
                giftNum = Integer.parseInt(numberOfGiftField.getText());
                initializeSpawnObstacle("gift", giftNum);
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
            update();
            print();
            numberOfTotalField.setText(totalNumber());
            this.updateUI();
        }
    }
}
