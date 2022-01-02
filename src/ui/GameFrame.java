package ui;

import domain.Controller;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    Controller controller = Controller.getInstance();
    StatPanel stats = StatPanel.getInstance();
    public GameFrame() {
        setTitle("Need For Spear");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(controller.gamePanel(), BorderLayout.SOUTH);
        add(stats, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
