package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseFrame extends JFrame implements ActionListener {
    StatPanel stats = StatPanel.getInstance();
    GamePanel game = GamePanel.getInstance();
    int width = 200;
    int height = 300;
    JPanel buttonsPanel = new JPanel();
    JButton quit;
    JButton continueGame;
    JButton loadGame;
    JButton saveGame;
    JButton help;

    public PauseFrame(){
        // Configure the JFrame

        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


        //Create Continue Game Button
        continueGame = new JButton("Continue");
        continueGame.addActionListener(this);
        buttonsPanel.add(continueGame);

        // Create the Create game button
        saveGame = new JButton("Save Game");
        saveGame.addActionListener(this);
        buttonsPanel.add(saveGame);


        // Create the Help button
        help = new JButton("    Help    ");
        help.addActionListener(this);
        buttonsPanel.add(help);


        add(buttonsPanel, BorderLayout.CENTER);

        // Create the quit button
        quit = new JButton("Quit Game");
        quit.addActionListener(this);
        add(quit, BorderLayout.SOUTH);

        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueGame) {
            game.continueGame();
            dispose();
        }
        if (e.getSource() == quit) {
            System.exit(0);
        }


    }
}
