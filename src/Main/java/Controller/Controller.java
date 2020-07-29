/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Main controller of the software
 */

package Controller;

import Model.Model;
import View.View;

import java.util.ArrayList;

public class Controller {
    private final ProjectInfoController projectInfoController;
    private final ProductFunctionController productFunctionController;
    private final ProductDataController productDataController;
    private final EstimationController estimationController;
    public View gui;
    public Model model;

    public Controller(View gui, Model model) {
        this.gui = gui;
        this.model = model;

        projectInfoController = new ProjectInfoController(this);
        productFunctionController = new ProductFunctionController(this);
        productDataController = new ProductDataController(this);
        estimationController = new EstimationController(this);
    }

    public ProjectInfoController getProjectInfoController() {
        return projectInfoController;
    }

    public ProductFunctionController getProductFunctionController() {
        return productFunctionController;
    }

    public ProductDataController getProductDataController() {
        return productDataController;
    }

    public EstimationController getEstimationController() {
        return estimationController;
    }

    public void emptyData() {
        this.model.setProductData(new ArrayList<>());
        this.model.setProductFunctions(new ArrayList<>());
    }
}