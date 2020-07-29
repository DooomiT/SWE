/**
 * Main: This file is part of CASE-Tool
 * <p>
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 * <p>
 * Description: Main function of the software, based on MVC architecture
 * -   initialises Mode, View and Controller
 */

import Controller.Controller;
import Model.Model;
import View.View;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        View gui = new View(model);
        Controller controller = new Controller(gui, model);
        gui.setVisible(true);
        gui.setControl(controller);
    }

}
