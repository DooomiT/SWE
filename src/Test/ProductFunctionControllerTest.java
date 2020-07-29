/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Testfunction for ProductFunctionController
 * Comment: no control flow graph, redundant for one Function
 */


package Test;

import Controller.Controller;
import Controller.ProductFunctionController;
import Model.EffortEstimation.FunctionPointCategory;
import Model.Model;
import Model.Product_F_D.ProductFunction;
import Test.EquivalenceClasses.EQPF;
import View.View;

import static org.junit.Assert.*;

public class ProductFunctionControllerTest {
    Model model = new Model();
    View view = new View(model);
    Controller controller = new Controller(view, model);
    ProductFunctionController pfc = new ProductFunctionController(controller);

    /*  checkFPID -> ControllFlowGraph as Text
     *   ID not available in pfc     -> False
     *       ID was never created        -> Case 1
     *       ID was deleted              -> Case 2
     *   ID available in pfc         -> True
     *       ID was created              -> Case 3
     *   Is also checking if createPF and deletePF is working
     * */
    @org.junit.Test
    public void ckeckPFId() {
        /* Case 1 */
        assertFalse("Check empty Model for ProductFunctionID should return false.", pfc.ckeckPFId(""));
        /* Case 2 */
        pfc.createPF("TestID");
        assertTrue("Check Model for created ProductFunctionID should return true.", pfc.ckeckPFId("TestID"));
        assertFalse("Check Model for nonexistent ProductFunctionID should return false.", pfc.ckeckPFId("TestFail"));
        /* Case 3 */
        pfc.deletePF("TestID");
        assertFalse("Check Model for deleted ProductFunctionID should return false.", pfc.ckeckPFId("TestID"));
    }

    @org.junit.Test
    public void renamePF() {
        pfc.createPF("TestID");

        pfc.renamePF("TestID", "RenamedTestID");
        assertTrue("Check Module for renamed ProductFunctionID should return true.", pfc.ckeckPFId("RenamedTestID"));
    }

    //TODO: Mock gui and Add Test
    @org.junit.Test
    public void savePF() {
        // Komplex
    }

    @org.junit.Test
    public void countFunctionPointCategory() {
        FunctionPointCategory EI = FunctionPointCategory.EI;
        FunctionPointCategory EO = FunctionPointCategory.EO;
        FunctionPointCategory EQ = FunctionPointCategory.EQ;
        /* CASE 1 No FunctionPoints */
        EQPF[] eq1 = {};
        int sumFunc = 0;
        int sumAssert = eq1.length;
        sumFunc += pfc.countFunctionPointCategory(EI, 1, 4);
        sumFunc += pfc.countFunctionPointCategory(EI, 2, 4);
        sumFunc += pfc.countFunctionPointCategory(EI, 3, 4);
        sumFunc += pfc.countFunctionPointCategory(EO, 1, 5);
        sumFunc += pfc.countFunctionPointCategory(EO, 2, 5);
        sumFunc += pfc.countFunctionPointCategory(EO, 3, 5);
        sumFunc += pfc.countFunctionPointCategory(EQ, 1, 4);
        sumFunc += pfc.countFunctionPointCategory(EQ, 2, 4);
        sumFunc += pfc.countFunctionPointCategory(EQ, 3, 4);
        assertEquals(sumAssert, sumFunc, 0);
        //EQPF(String function, String source, String reference, String protagonist, String description, FunctionPointCategory fp_category, int weight, int det, int ftr) {
        /* CASE 2 One FunctionPoint for all Types */
        EQPF[] eq2 = {
                new EQPF("x1", "LF10", "", "", "", EI, 3, (int) (Math.random() * 10),
                        (int) (Math.random() * 10)),
                new EQPF("x2", "LF10", "", "", "", EI, 4, (int) (Math.random() * 10),
                        (int) (Math.random() * 10)),
                new EQPF("x3", "LF10", "", "", "", EI, 5, (int) (Math.random() * 10),
                        (int) (Math.random() * 10)),
                new EQPF("x4", "LF10", "", "", "", EO, 4, (int) (Math.random() * 10),
                        (int) (Math.random() * 10)),
                new EQPF("x5", "LF10", "", "", "", EO, 5, (int) (Math.random() * 10),
                        (int) (Math.random() * 10)),
                new EQPF("x6", "LF10", "", "", "", EO, 6, (int) (Math.random() * 10),
                        (int) (Math.random() * 10)),
                new EQPF("x7", "LF10", "", "", "", EQ, 3, (int) (Math.random() * 10),
                        (int) (Math.random() * 10)),
                new EQPF("x8", "LF10", "", "", "", EQ, 4, (int) (Math.random() * 10),
                        (int) (Math.random() * 10)),
                new EQPF("x9", "LF10", "", "", "", EQ, 5, (int) (Math.random() * 10),
                        (int) (Math.random() * 10))
        };
        pfc.createPF("x1");
        pfc.createPF("x2");
        pfc.createPF("x3");
        pfc.createPF("x4");
        pfc.createPF("x5");
        pfc.createPF("x6");
        pfc.createPF("x7");
        pfc.createPF("x8");
        pfc.createPF("x9");
        int i = 0;
        for (ProductFunction pf : model.getProductFunctionList()) {
            pf.setId(eq2[i].id);
            pf.setSource(eq2[i].source);
            pf.setReference(eq2[i].reference);
            pf.setProtagonist(eq2[i].protagonist);
            pf.setDescription(eq2[i].description);
            pf.setFpCategory(eq2[i].fpCategory);
            pf.setFpWeight(eq2[i].weight);
            pf.setFpDet(eq2[i].det);
            pf.setFpCategory(eq2[i].fpCategory);
        }
        sumFunc = 0;
        sumAssert = eq2.length;
        sumFunc += pfc.countFunctionPointCategory(EI, 1, 4);
        sumFunc += pfc.countFunctionPointCategory(EI, 2, 4);
        sumFunc += pfc.countFunctionPointCategory(EI, 3, 4);
        sumFunc += pfc.countFunctionPointCategory(EO, 1, 5);
        sumFunc += pfc.countFunctionPointCategory(EO, 2, 5);
        sumFunc += pfc.countFunctionPointCategory(EO, 3, 5);
        sumFunc += pfc.countFunctionPointCategory(EQ, 1, 4);
        sumFunc += pfc.countFunctionPointCategory(EQ, 2, 4);
        sumFunc += pfc.countFunctionPointCategory(EQ, 3, 4);
        assertEquals(sumAssert, sumFunc, 0);
    }
}