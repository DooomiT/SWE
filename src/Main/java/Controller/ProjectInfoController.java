/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Controller for Project Information
 */

package Controller;

import Model.ProjectInformation.ProductEnvironment;
import Model.ProjectInformation.ProductUse;
import Model.ProjectInformation.TargetDefinition;

public class ProjectInfoController {

    private final Controller controller;

    public ProjectInfoController(Controller controller) {
        this.controller = controller;
    }

    public ProductUse getProductUse() {
        return controller.model.getProductUse();
    }

    public ProductEnvironment getProductEnvironment() {
        return controller.model.getProductEnvironment();
    }

    public TargetDefinition getTargetDefinition() {
        return controller.model.getTargetDefinition();
    }

    public void save() {
        controller.model.getProductUse().setContent(controller.gui.getProductUseText());
        controller.model.getProductEnvironment().setContent(controller.gui.getEnvironmentText());
        controller.model.getTargetDefinition().setContent(controller.gui.getTargetDefinitionText());
        System.out.println("[ProjectInfoController] Neue Daten gesichert:\n" +
                controller.model.getTargetDefinition().toString() + "\n" +
                controller.model.getProductUse().toString() + "\n" +
                controller.model.getProductEnvironment().toString() + "\n"
        );
    }
}

