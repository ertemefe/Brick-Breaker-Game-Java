package ui;

import domain.database.adapter.LoadGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoadGameMenu extends JFrame implements ActionListener {

    JPanel load = new JPanel();
    JLabel username;
    JTextField input;
    JButton login;
    JButton back;
    int width = 1000;
    int height = 750;

    LoadGameMenu() {
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        username = new JLabel("Enter username: ");
        username.setBounds(20, 20, 20, 20);
        load.add(username);

        input = new JTextField(20);
        input.setBounds(50, 20, 20, 20);
        load.add(input);

        login = new JButton("Login");
        login.addActionListener(this);
        load.add(login);

        add(load, BorderLayout.CENTER);

        back = new JButton("Back");
        back.addActionListener(this);
        add(back, BorderLayout.SOUTH);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == back) {
           goBackToMainMenu();
        }
        if(evt.getSource() == login){
         loginAndContinueGame();
        }
    }

    private void goBackToMainMenu(){
        dispose();
        new MainMenu();
    }

    private void loginAndContinueGame(){
        LoadGame loadGame = new LoadGame();
        try {
            loadGame.getData(input.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //getData verileri ile runningModeFrame aç (kaldığı yerden başlayacak)
    }
}
