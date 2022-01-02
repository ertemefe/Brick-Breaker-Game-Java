package ui;

import domain.GamePanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StatPanel extends JPanel {
    private static final StatPanel statPanel = new StatPanel();
    JLabel lives = new JLabel("Lives: 3");
    JLabel clock = new JLabel("Time: 0");
    JLabel score = new JLabel("Score: 0");


    private StatPanel() {
        setPreferredSize(new Dimension(1200, 30));
        setBorder(new LineBorder(Color.black));
        lives.setForeground(Color.black);
        lives.setVisible(true);
        add(lives, BorderLayout.WEST);
        lives.setForeground(Color.black);
        lives.setVisible(true);
        add(clock, BorderLayout.WEST);
        lives.setForeground(Color.black);
        lives.setVisible(true);
        add(score, BorderLayout.WEST);
        setVisible(true);

    }

    public static StatPanel getInstance() {
        return statPanel;
    }

    public void live(int remaining) {
        lives.setText("Lives: " + remaining);
    }

    public void setClock(int time) {
        clock.setText("Clock: " + time);
    }

    public void setScore(int score) {
        this.score.setText("Score: " + score);
    }

}
