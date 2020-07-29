/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Test function for estimation
 *
 * Comment: no control flow graph, redundant for one function
 */

package Test;


import Controller.Controller;
import Controller.EstimationController;
import Model.EffortEstimation.FunctionPointCategory;
import Model.EffortEstimation.FunctionPointEstimation;
import Model.Model;
import Model.Product_F_D.ProductData;
import Model.Product_F_D.ProductDataAttribute;
import Model.Product_F_D.ProductFunction;
import Test.EquivalenceClasses.EQECTCCV;
import Test.EquivalenceClasses.EQECTCOV;
import View.View;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EstimationControllerTest {
    Model model = new Model();
    View view = new View(model);
    Controller controller = new Controller(view, model);
    EstimationController ec = controller.getEstimationController();
    FunctionPointEstimation fpe = model.getFunctionPointEstimation();

    /*  calculateOverviewValues -> ControllFlowGraph as Text
     *      fill pfc and pdc with FunctionPointCategories
     *      e1sum >= 0 else -> overflow
     *      e1sum = sum(allFPs)
     * */
    @org.junit.Test
    public void calculateOverviewValues() {
        //ProductFunctionController pfc = controller.getProductFunctionController();
        //ProductDataController pdc = controller.getProductDataController();
        EQECTCOV[] eqsCOV = {getEQECTCOV(0), getEQECTCOV(1)};
        for (EQECTCOV eq : eqsCOV) {
            model.setProductFunctions(eq.product_functions);
            model.setProductFunctions(eq.product_functions);
            ec.calculateOverviewValues();
            assertEquals("Should return " + Double.toString(eq.e1Sum),
                    eq.e1Sum, fpe.getE1Sum(), 0);
        }

    }

    /*  calculateCorrectionValues -> ControllFlowGraph as Text
     *   setpoint = actualPersonMonths  -> Case 1
     *   setpoint < actualPersonMonths  -> Case 2
     *   setpoint > actualPersonMonths  -> Case 3
     * */
    @org.junit.Test
    public void calculateCorrectionValues() {

        /* Fill fpe with setpoint, actualPersonMonths*/
        EQECTCCV[] eqsCCV = {new EQECTCCV(100, 100),
                new EQECTCCV(101, 100),
                new EQECTCCV(100, 101)};

        for (EQECTCCV eq : eqsCCV) {
            fpe.setSetpoint(eq.setpoint);
            fpe.setActualValue(eq.actualPersonMonths);
            ec.calculateCorrectionValues((int) fpe.getActualValue());
            assertEquals("Should return " + eq.correctionFactor,
                    eq.correctionFactor, fpe.getCorrectionFactor(), 0);
        }
    }

    private EQECTCOV getEQECTCOV(int index) {
        FunctionPointCategory EI = FunctionPointCategory.EI;
        FunctionPointCategory EO = FunctionPointCategory.EO;
        FunctionPointCategory EQ = FunctionPointCategory.EQ;
        FunctionPointCategory ILF = FunctionPointCategory.ILF;
        FunctionPointCategory EIF = FunctionPointCategory.EIF;
        /* none pfc and pdc */
        if (index == 0) {
            return new EQECTCOV(new ArrayList<ProductData>(), new ArrayList<ProductFunction>(), 0);
        }
        /* Fill pfc with EI, EO, EQ & pdc with ILF, EIF (Easy Middle, Complex) */
        if (index == 1) {
            ArrayList<ProductFunction> tmp_pf_list = new ArrayList<>();
            tmp_pf_list.add(new ProductFunction("x1", "x1", "x1", "x1", "x1",
                    "x1", 3, EI, 3, 3));
            tmp_pf_list.add(new ProductFunction("x2", "x1", "x1", "x1", "x1",
                    "x1", 4, EO, 4, 4));
            tmp_pf_list.add(new ProductFunction("x3", "x1", "x1", "x1", "x1",
                    "x1", 3, EQ, 3, 3));
            tmp_pf_list.add(new ProductFunction("x4", "x1", "x1", "x1", "x1",
                    "x1", 4, EI, 4, 4));
            tmp_pf_list.add(new ProductFunction("x5", "x1", "x1", "x1", "x1",
                    "x1", 5, EO, 5, 5));
            tmp_pf_list.add(new ProductFunction("x6", "x1", "x1", "x1", "x1",
                    "x1", 4, EQ, 4, 4));
            tmp_pf_list.add(new ProductFunction("x7", "x1", "x1", "x1", "x1",
                    "x1", 5, EI, 5, 5));
            tmp_pf_list.add(new ProductFunction("x8", "x1", "x1", "x1", "x1",
                    "x1", 6, EO, 6, 6));
            tmp_pf_list.add(new ProductFunction("x9", "x1", "x1", "x1", "x1",
                    "x1", 5, EQ, 5, 5));

            ArrayList<ProductDataAttribute> attributes = new ArrayList<>();
            attributes.add(new ProductDataAttribute("x1", "x1"));
            attributes.add(new ProductDataAttribute("x2", "x2"));

            ArrayList<ProductData> tmp_pd_list = new ArrayList<>();
            tmp_pd_list.add(new ProductData("x1", "x1", "x1", 6, 6, 6,
                    EIF, attributes));
            tmp_pd_list.add(new ProductData("x2", "x1", "x1", 9, 9, 9,
                    ILF, attributes));
            tmp_pd_list.add(new ProductData("x3", "x1", "x1", 7, 7, 7,
                    EIF, attributes));
            tmp_pd_list.add(new ProductData("x4", "x1", "x1", 10, 10, 10,
                    ILF, attributes));
            tmp_pd_list.add(new ProductData("x5", "x1", "x1", 8, 8, 8,
                    EIF, attributes));
            tmp_pd_list.add(new ProductData("x6", "x1", "x1", 11, 11, 11,
                    ILF, attributes));
            return new EQECTCOV(tmp_pd_list, tmp_pf_list, 42);
        }
        return null;
    }
}