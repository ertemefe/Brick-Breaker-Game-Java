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


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!isSelected && controller.spawnLocation.containsValue(current)) { //taşıncak obstacle seçilir
                isSelected = true;
                selectedPanel = currentPanel;
                System.out.println(selectedPanel.getLocation() + " seçildi");
                selectedPanelGridNumber = current;
                currentColor = Color.green;
                currentPanel.setBackground(currentColor);
            } else if (isSelected && !controller.spawnLocation.containsValue(current)) { //taşıncak yer seçilir
                isSelected = false;
                currentColor = Color.red;
                updateSelectedLocation();

                if (controller.simpleLoc.containsValue(selectedPanelGridNumber)) {
                    editingArea.removeObstacle("simple", selectedPanelGridNumber);
                    editingArea.moveObstacle(controller,"simple", current);
                } else if (controller.firmLoc.containsValue(selectedPanelGridNumber)) {
                    editingArea.removeObstacle("firm", selectedPanelGridNumber);
                    editingArea.moveObstacle(controller,"firm", current);
                } else if (controller.explosiveLoc.containsValue(selectedPanelGridNumber)) {
                    editingArea.removeObstacle("explosive", selectedPanelGridNumber);
                    editingArea.moveObstacle(controller,"explosive", current);
                } else if (controller.giftLoc.containsValue(selectedPanelGridNumber)) {
                    editingArea.removeObstacle("gift", selectedPanelGridNumber);
                    editingArea.moveObstacle(controller,"gift", current);
                } else System.out.println("point not found");
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && current < 399) {
            currentPanel.setBackground(defaultColor);
            current++;
            updateSelectedLocation();
            if (isSelected && controller.spawnLocation.containsValue(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && current > 0) {
            currentPanel.setBackground(defaultColor);
            current--;
            updateSelectedLocation();
            if (isSelected && controller.spawnLocation.containsValue(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && current < 360) {
            currentPanel.setBackground(defaultColor);
            current = current + 40;
            updateSelectedLocation();
            if (isSelected && controller.spawnLocation.containsValue(current)) {
                currentPanel.setBackground(Color.red);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && current >= 40) {
            currentPanel.setBackground(defaultColor);
            current = current - 40;
            updateSelectedLocation();
            if (isSelected && controller.spawnLocation.containsValue(current)) {
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
        JPopupMenu pop = new JPopupMenu();
        //pop.setVisible(true);
        pop.setPreferredSize(new Dimension(200,200));
        pop.show(this, this.getWidth()/2,this.getY()/2);


    }

    private void goBackToNewGameMenu() {
        dispose();
        new NewGameMenu();
    }

}
