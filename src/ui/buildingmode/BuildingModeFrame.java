package ui.buildingmode;

import javax.swing.*;

public class BuildingModeFrame extends JFrame {
    int width = 1200;
    int height = 750;

    public BuildingModeFrame() {

        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        EditingAreaPanel editingArea = new EditingAreaPanel();
        CreationPanel creationPanel = new CreationPanel();

        JSplitPane splitPane = new JSplitPane();
        splitPane.setSize(width, height);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(width*3/4);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(editingArea);
        splitPane.setRightComponent(creationPanel);

        add(splitPane);

        setVisible(true);
    }

}
