package ui;

import domain.Controller;
import domain.GamePanel;
import domain.objects.Paddle;

import javax.swing.*;

public class GameFrame extends JFrame{

    JFrame obj = new JFrame();
    Controller controller = Controller.getInstance();

    public GameFrame(){
        obj.setBounds(10,10,1200,500);
        obj.setTitle("Need For Spear");
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(controller.gamePanel());
        obj.setBounds(10,10,1200,500);
        obj.setVisible(true);

    }

}
