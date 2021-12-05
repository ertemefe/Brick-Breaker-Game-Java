package ui;

import ui.buildingmode.BuildingModeFrame;

import javax.swing.*;
import java.sql.SQLException;

public class Main extends JFrame {


    public static void main(String[] args) throws SQLException {

        //new MainMenu();
        new BuildingModeFrame();
    }

}
