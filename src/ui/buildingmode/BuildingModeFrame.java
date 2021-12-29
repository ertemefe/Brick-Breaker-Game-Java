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

public class BuildingModeFrame extends JFrame implements ActionListener, KeyListener {
   Controller controller = Controller.getInstance();

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
        updateSelectedLocation();
        setVisible(true);
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

        buttonPanel.setVisible(true);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateSelectedLocation() {
        currentPanel = editingArea.gridList.get(current);
        currentPanel.setBackground(currentColor);
    }

    private void setObstacleCoordinates(Controller controller) {
        for (int i = 1; i < controller.spawnLocation.size(); i++) {
            controller.obstacles.get(controller.spawnLocation.get(i)).setCoordinates
                    (editingArea.gridList.get(controller.spawnLocation.get(i)).getLocation());
        }
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
            if (!isSelected && controller.spawnLocation.contains(current)) { //taşıncak obstacle seçilir
                isSelected = true;
                selectedPanel = currentPanel;
                System.out.println(selectedPanel.getLocation() + " seçildi");
                selectedPanelGridNumber = current;
                currentColor = Color.green;
                currentPanel.setBackground(currentColor);
            } else if (isSelected && !controller.spawnLocation.contains(current)) { //taşıncak yer seçilir
                isSelected = false;
                currentColor = Color.red;
                updateSelectedLocation();
                System.out.println(currentPanel.getLocation() + "'a taşındı");
                if (controller.spawnLocation.contains(selectedPanelGridNumber)) {
                    editingArea.changePosition(controller, current, selectedPanelGridNumber);
                } else System.out.println("point not found");
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && current < 399) {
            currentPanel.setBackground(defaultColor);
            current++;
            updateSelectedLocation();
            if (isSelected && controller.spawnLocation.contains(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && current > 0) {
            currentPanel.setBackground(defaultColor);
            current--;
            updateSelectedLocation();
            if (isSelected && controller.spawnLocation.contains(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && current < 360) {
            currentPanel.setBackground(defaultColor);
            current = current + 40;
            updateSelectedLocation();
            if (isSelected && controller.spawnLocation.contains(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && current >= 40) {
            currentPanel.setBackground(defaultColor);
            current = current - 40;
            updateSelectedLocation();
            if (isSelected && controller.spawnLocation.contains(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
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
        setObstacleCoordinates(controller);
        for (int i = 1; i < controller.spawnLocation.size(); i++)
            System.out.println(controller.obstacles.get(controller.spawnLocation.get(i)).getName() + " " + controller.obstacles.get(controller.spawnLocation.get(i)).getCoordinates() + " " + i);
    }

    private void goBackToNewGameMenu() {
        dispose();
        new NewGameMenu();
    }

}
