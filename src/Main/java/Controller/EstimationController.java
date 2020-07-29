/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Controller for Estimation
 */

package Controller;

import Model.EffortEstimation.FunctionPointCategory;
import Model.EffortEstimation.FunctionPointEstimation;

public class EstimationController {

    private final Controller controller;

    public EstimationController(Controller controller) {
        this.controller = controller;

    }

    public void calculateOverviewValues() {
        FunctionPointCategory EI = FunctionPointCategory.EI;
        FunctionPointCategory EO = FunctionPointCategory.EO;
        FunctionPointCategory EQ = FunctionPointCategory.EQ;
        FunctionPointCategory ILF = FunctionPointCategory.ILF;
        FunctionPointCategory EIF = FunctionPointCategory.EIF;
        FunctionPointEstimation fpe = controller.model.getFunctionPointEstimation();
        ProductFunctionController pf_controller = controller.getProductFunctionController();
        ProductDataController pd_controller = controller.getProductDataController();

        fpe.getInput().setEasy(pf_controller.countFunctionPointCategory(EI, 1, 4));
        fpe.getInput().setMiddle(pf_controller.countFunctionPointCategory(EI, 2, 4));
        fpe.getInput().setComplex(pf_controller.countFunctionPointCategory(EI, 3, 4));

        fpe.getOutput().setEasy(pf_controller.countFunctionPointCategory(EO, 1, 5));
        fpe.getOutput().setMiddle(pf_controller.countFunctionPointCategory(EO, 2, 5));
        fpe.getOutput().setComplex(pf_controller.countFunctionPointCategory(EO, 3, 5));

        fpe.getQuery().setEasy(pf_controller.countFunctionPointCategory(EQ, 1, 4));
        fpe.getQuery().setMiddle(pf_controller.countFunctionPointCategory(EQ, 2, 4));
        fpe.getQuery().setComplex(pf_controller.countFunctionPointCategory(EQ, 3, 4));

        fpe.getIntern().setEasy(pd_controller.countFunctionPointCategory(ILF, 1, 10));
        fpe.getIntern().setMiddle(pd_controller.countFunctionPointCategory(ILF, 2, 10));
        fpe.getIntern().setComplex(pd_controller.countFunctionPointCategory(ILF, 3, 10));

        fpe.getExtern().setEasy(pd_controller.countFunctionPointCategory(EIF, 1, 7));
        fpe.getExtern().setMiddle(pd_controller.countFunctionPointCategory(EIF, 2, 7));
        fpe.getExtern().setComplex(pd_controller.countFunctionPointCategory(EIF, 3, 7));

        fpe.setE1Sum(getInputEasySum() + getInputMiddleSum() + getInputComplexSum() +
                getOutputEasySum() + getOutputMiddleSum() + getOutputComplexSum() +
                getQueryEasySum() + getQueryMiddleSum() + getQueryComplexSum() +
                getInternEasySum() + getInternMiddleSum() + getInternComplexSum() +
                getExternEasySum() + getExternMiddleSum() + getExternComplexSum());

    }

    public int getInputEasySum() {
        return controller.model.getFunctionPointEstimation().getInput().getEasy() * 3;
    }

    public int getInputMiddleSum() {
        return controller.model.getFunctionPointEstimation().getInput().getMiddle() * 4;
    }

    public int getInputComplexSum() {
        return controller.model.getFunctionPointEstimation().getInput().getComplex() * 6;
    }

    public int getOutputEasySum() {
        return controller.model.getFunctionPointEstimation().getOutput().getEasy() * 4;
    }

    public int getOutputMiddleSum() {
        return controller.model.getFunctionPointEstimation().getOutput().getMiddle() * 5;
    }

    public int getOutputComplexSum() {
        return controller.model.getFunctionPointEstimation().getOutput().getComplex() * 7;
    }

    public int getQueryEasySum() {
        return controller.model.getFunctionPointEstimation().getQuery().getEasy() * 3;
    }

    public int getQueryMiddleSum() {
        return controller.model.getFunctionPointEstimation().getQuery().getMiddle() * 4;
    }

    public int getQueryComplexSum() {
        return controller.model.getFunctionPointEstimation().getQuery().getComplex() * 6;
    }

    public int getInternEasySum() {
        return controller.model.getFunctionPointEstimation().getIntern().getEasy() * 7;
    }

    public int getInternMiddleSum() {
        return controller.model.getFunctionPointEstimation().getIntern().getMiddle() * 10;
    }

    public int getInternComplexSum() {
        return controller.model.getFunctionPointEstimation().getIntern().getComplex() * 15;
    }

    public int getExternEasySum() {
        return controller.model.getFunctionPointEstimation().getExtern().getEasy() * 5;
    }

    public int getExternMiddleSum() {
        return controller.model.getFunctionPointEstimation().getExtern().getMiddle() * 7;
    }

    public int getExternComplexSum() {
        return controller.model.getFunctionPointEstimation().getExtern().getComplex() * 10;
    }

    public void calculateCorrectionValues(int actualPersonMonths) {
        //optimize function
        controller.model.getFunctionPointEstimation().setCorrectionFactor(Math.log(actualPersonMonths
                / controller.model.getFunctionPointEstimation().getSetpoint()) + 1);
    }
}
