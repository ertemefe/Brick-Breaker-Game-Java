package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    JPanel buttonsPanel = new JPanel();
    JButton quit;
    JButton createNewGame;
    JButton loadGame;
    int width = 1000;
    int height = 750;

    public MainMenu() {

        // Configure the JFrame
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the panel containing the controls

        // Create the Create game button
        createNewGame = new JButton("Create New Game");
        createNewGame.addActionListener(this);
        buttonsPanel.add(createNewGame);

        // Create the Load game button
        loadGame = new JButton("Load Game");
        loadGame.addActionListener(this);
        buttonsPanel.add(loadGame);

        add(buttonsPanel, BorderLayout.CENTER);

        // Create the quit button
        quit = new JButton("Quit Game");
        quit.addActionListener(this);
        add(quit, BorderLayout.SOUTH);

        setVisible(true);

    }

    public void displayLoadGameMenu() {
        dispose();
        new LoadGameMenu();
    }

    public void displayCreateGameMenu() {
        dispose();
        new NewGameMenu();
    }


    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == quit) {
            System.exit(0);
        }
        if (evt.getSource() == createNewGame) {
            displayCreateGameMenu();
        }
        if (evt.getSource() == loadGame) {
            displayLoadGameMenu();
        }
    }
}
