/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Test function for XML XML.Import/XML.Export
 *
 * Comment: not possible due to not finished XML.Import/XML.Export function
 */

package Test;

import Controller.Controller;
import Model.Model;
import View.View;

public class XMLTest {
    Model model = new Model();
    View view = new View(model);
    Controller controller = new Controller(view, model);

    @org.junit.Test
    public void ImportTest() {
        //TODO: Fake XML HashMap For Test & Add Test
    }

    @org.junit.Test
    public void ExportTest() {
        //TODO: Fake XML HashMap For Test & Add Test
    }

}