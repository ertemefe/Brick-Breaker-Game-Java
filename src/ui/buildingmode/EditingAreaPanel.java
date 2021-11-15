package ui.buildingmode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditingAreaPanel extends JPanel {
    //JPanel edit = new JPanel(new GridBagLayout());
    private Container container;

    public EditingAreaPanel() {

        setSize(900, 750);
        setBackground(Color.BLUE);
        setLayout(new GridLayout(15,15));
        gr();
        setVisible(true);
    }

    private void gr() {
        //GridBagConstraints gbc = new GridBagConstraints();
        ArrayList<JPanel> gridList = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < 15; i++) {
            for (int k = 0; k < 15; k++) {
                JPanel n = new JPanel();
                n.setSize(50,50);
                n.setVisible(true);

                if (gridList.isEmpty()) {
                    n.setBackground(Color.LIGHT_GRAY);
                } else if (gridList.get(j - 1).getBackground().equals(Color.BLACK)) {
                    n.setBackground(Color.LIGHT_GRAY);
                } else {
                    n.setBackground(Color.BLACK);
                }
                j++;
                gridList.add(n);
                add(n);

            }
        }
    }

}
