package ui;

import domain.objects.Paddle;

import javax.swing.*;

public class GameFrame extends JFrame{

    Paddle gamePlay = new Paddle();
    JFrame obj = new JFrame();

    public GameFrame(){
        obj.setBounds(10,10,1200,500);
        obj.setTitle("Need For Spear");
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
        obj.setBounds(10,10,1200,500);
        obj.setVisible(true);

    }

}
