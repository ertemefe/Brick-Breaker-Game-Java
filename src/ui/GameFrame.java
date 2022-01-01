package ui;

import domain.Controller;
import domain.GamePanel;
import domain.objects.Paddle;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    Controller controller = Controller.getInstance();

    public GameFrame(){
        //setBounds(10,10,1200,500);
        setPreferredSize(new Dimension(1200,528));
        setTitle("Need For Spear");
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(controller.gamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
