package ui.buildingmode;

import domain.Controller;
import ui.GameFrame;
import ui.NewGameMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;

public class BuildingModeFrame extends JFrame implements ActionListener, KeyListener {
    public ArrayList<Point> simplePoints = new ArrayList<>();
    public ArrayList<Point> firmPoints = new ArrayList<>();
    public ArrayList<Point> explosivePoints = new ArrayList<>();
    public ArrayList<Point> giftPoints = new ArrayList<>();
    Controller controller = new Controller();

    JPanel buttonPanel;
    EditingAreaPanel editingArea = new EditingAreaPanel(controller);
    int current = 0;
    JPanel currentPanel;
    JPanel selectedPanel;
    int selectedPanelGridNumber;
    Color defaultColor = Color.black;
    Color currentColor = Color.red;
    boolean isSelected = false;
    private JButton saveMapButton, backButton, startGameButton;

    public BuildingModeFrame() {

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(editingArea);

        editingArea.edit.addKeyListener(this);
        addButtonPanel();
        pack();
        obstacleTypeAndPoints(editingArea);
        updateSelectedLocation();
        setVisible(true);
        editingArea.edit.grabFocus();

    }

    private void obstacleTypeAndPoints(EditingAreaPanel editingArea) {
        obstacleTypeAndPointsSimple(editingArea);
        obstacleTypeAndPointsFirm(editingArea);
        obstacleTypeAndPointsExplosive(editingArea);
        obstacleTypeAndPointsGift(editingArea);
    }


    private void obstacleTypeAndPointsSimple(EditingAreaPanel e) {
        Collection<JPanel> valuesSimple = e.simpleObstacleList.values();
        ArrayList<JPanel> listOfSimple = new ArrayList<>(valuesSimple);
        for (JPanel jPanel : listOfSimple) {
            if (!simplePoints.contains(jPanel.getLocation())){
                simplePoints.add(jPanel.getLocation());
                System.out.println("new point");
                System.out.println(jPanel.getLocation());
            }
        }
    }

    private void obstacleTypeAndPointsFirm(EditingAreaPanel e) {
        Collection<JPanel> valuesFirm = e.firmObstacleList.values();
        ArrayList<JPanel> listOfFirm = new ArrayList<>(valuesFirm);
        for (JPanel jPanel : listOfFirm) {
            if (!firmPoints.contains(jPanel.getLocation()))
                firmPoints.add(jPanel.getLocation());
        }
    }

    private void obstacleTypeAndPointsExplosive(EditingAreaPanel e) {
        Collection<JPanel> valuesExplosive = e.explosiveObstacleList.values();
        ArrayList<JPanel> listOfExplosive = new ArrayList<>(valuesExplosive);
        for (JPanel jPanel : listOfExplosive) {
            if (!explosivePoints.contains(jPanel.getLocation()))
                explosivePoints.add(jPanel.getLocation());
        }
    }

    private void obstacleTypeAndPointsGift(EditingAreaPanel e) {
        Collection<JPanel> valuesGift = e.giftObstacleList.values();
        ArrayList<JPanel> listOfGift = new ArrayList<>(valuesGift);
        for (JPanel jPanel : listOfGift) {
            if (!giftPoints.contains(jPanel.getLocation()))
                giftPoints.add(jPanel.getLocation());
        }
    }

    private void addButtonPanel() {
        buttonPanel = new JPanel();

        saveMapButton = new JButton("Save Map");
        saveMapButton.addActionListener(this);
        buttonPanel.add(saveMapButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(this);
        buttonPanel.add(startGameButton);

        //buttonPanel.setBackground(Color.BLACK);
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

    private void startGame() {
        dispose();
        new GameFrame();
    }

    private void saveMap() {
        JPopupMenu pop = new JPopupMenu();
        //pop.setVisible(true);
        pop.setPreferredSize(new Dimension(200,200));
        pop.show(this, this.getWidth()/2,this.getY()/2);


    }

    private void goBackToNewGameMenu() {
        dispose();
        new NewGameMenu();
    }

    private void updateSelectedLocation() {
        currentPanel = editingArea.gridList.get(current);
        currentPanel.setBackground(currentColor);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!isSelected && editingArea.randomNumberExists.containsValue(current)) { //taşıncak obstacle seçilir
                isSelected = true;
                selectedPanel = currentPanel;
                selectedPanelGridNumber = current;
                currentColor = Color.green;
                currentPanel.setBackground(currentColor);
                System.out.println("seçildi");
            } else if (isSelected && !editingArea.randomNumberExists.containsValue(current)) { //taşıncak yer seçilir
                isSelected = false;
                currentColor = Color.red;
                updateSelectedLocation();

                if (simplePoints.contains(selectedPanel.getLocation())) {
                    editingArea.removeObstacle("simple", selectedPanelGridNumber);
                    editingArea.moveObstacle("simple", current);
                    obstacleTypeAndPointsSimple(editingArea);
                } else if (firmPoints.contains(selectedPanel.getLocation())) {
                    editingArea.removeObstacle("firm", selectedPanelGridNumber);
                    editingArea.moveObstacle("firm", current);
                    obstacleTypeAndPointsFirm(editingArea);
                } else if (explosivePoints.contains(selectedPanel.getLocation())) {
                    editingArea.removeObstacle("explosive", selectedPanelGridNumber);
                    editingArea.moveObstacle("explosive", current);
                    obstacleTypeAndPointsExplosive(editingArea);
                } else if (giftPoints.contains(selectedPanel.getLocation())) {
                    editingArea.removeObstacle("gift", selectedPanelGridNumber);
                    editingArea.moveObstacle("gift", current);
                    obstacleTypeAndPointsGift(editingArea);
                } else System.out.println("point not found");
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && current < 399) {
            currentPanel.setBackground(defaultColor);
            current++;
            updateSelectedLocation();
            if (isSelected && editingArea.randomNumberExists.containsValue(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && current > 0) {
            currentPanel.setBackground(defaultColor);
            current--;
            updateSelectedLocation();
            if (isSelected && editingArea.randomNumberExists.containsValue(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && current < 360) {
            currentPanel.setBackground(defaultColor);
            current = current + 40;
            updateSelectedLocation();
            if (isSelected && editingArea.randomNumberExists.containsValue(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && current >= 40) {
            currentPanel.setBackground(defaultColor);
            current = current - 40;
            updateSelectedLocation();
            if (isSelected && editingArea.randomNumberExists.containsValue(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
    }
}
