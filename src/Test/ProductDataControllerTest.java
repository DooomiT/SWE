/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Test function for estimation
 * Comment: no control flow graph, redundant for one function
 */

package Test;


import Controller.Controller;
import Controller.ProductDataController;
import Model.EffortEstimation.FunctionPointCategory;
import Model.Model;
import Model.Product_F_D.ProductData;
import Test.EquivalenceClasses.EQPD;
import View.View;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProductDataControllerTest {
    Model model = new Model();
    View view = new View(model);
    Controller controller = new Controller(view, model);
    ProductDataController pdc = controller.getProductDataController();

    /*  checkPDID -> ControllFlowGraph as Text
     *   ID not available in pdc     -> False
     *       ID was never created        -> Case 1
     *       ID was deleted              -> Case 2
     *   ID available in pdc         -> True
     *       ID was created              -> Case 3
     *   Is also checking if createPD and deletePD is working
     * */
    @org.junit.Test
    public void ckeckPDId() {
        /* Case 1 */
        assertFalse("Check empty Model for ProductDataID should return false.",
                pdc.ckeckPDId(""));

        /* Case 2 */
        pdc.createPD("TestID");
        assertTrue("Check Model for created ProductDataID should return true.",
                pdc.ckeckPDId("TestID"));
        assertFalse("Check Model for nonexistent ProductDataID should return false.",
                pdc.ckeckPDId("TestFail"));

        /* Case 3 */
        pdc.deletePD("TestID");
        assertFalse("Check Model for deleted ProductFunctionID should return false.",
                pdc.ckeckPDId("TestID"));
    }

    @org.junit.Test
    public void renamePD() {
        pdc.createPD("TestID");
        pdc.renamePD("TestID", "RenamedTestID");

        assertTrue("Check Module for renamed ProductFunctionID should return true.",
                pdc.ckeckPDId("RenamedTestID"));
    }

    //TODO: Mock gui and Add Test
    @org.junit.Test
    public void savePD() {
    }

    /*  checkPDAttribute -> ControllFlowGraph as Text
     *  PD available:
     *  PDAttributeName not available in pdc   -> False
     *      PDAttributeName was never created      -> Case 1
     *      PDAttributeName was deleted            -> Case 2
     *  PDAttributeName available in pdc       -> True
     *      PDAttributeName was created            -> Case 3
     *  PD not available                       -> False
     *      PDAttributeName was never created      -> Case 4
     *      PDAttributeName was deleted            -> Case 5
     *      PDAttributeName was created            -> Case 6
     *  Is also checking if createPDAttribute and deletePDAttribute is working
     * */
    @org.junit.Test
    public void ckeckPDAttribute() {
        pdc.createPD("TestID");
        /* Case 1 */
        assertFalse("Check empty Model for ProductDataAttribute should return false.",
                pdc.ckeckPDAttribute("", ""));

        /* Case 2 */
        pdc.createPDAttribute("TestID", "TestName");
        assertTrue("Check Model for created ProductDataAttribute should return true.",
                pdc.ckeckPDAttribute("TestID", "TestName"));
        assertFalse("Check Model for nonexistent ProductDataAttribute should return false.",
                pdc.ckeckPDAttribute("TestFail", "TestFailName"));

        /* Case 3 */
        pdc.deletePDAttribut("TestID", "TestName");
        assertFalse("Check Model for deleted ProductDataAttribute should return false.",
                pdc.ckeckPDAttribute("TestID", "TestName"));

        /* Case 4 */
        assertFalse("Check empty Model for ProductDataAttribute should return false.",
                pdc.ckeckPDAttribute("", ""));

        /* Case 5 */
        pdc.createPDAttribute("TestID", "TestName");
        assertFalse("Check Model for nonexistent ProductDataAttribute should return false.",
                pdc.ckeckPDAttribute("", "TestID"));

        /* Case 6 */
        pdc.deletePDAttribut("TestID", "TestName");
        assertFalse("Check Model for deleted ProductDataAttribute should return false.",
                pdc.ckeckPDAttribute("TestID", "TestName"));
    }

    //TODO: Mock gui and Add Test
    @org.junit.Test
    public void savePDAttribute() {
    }

    @org.junit.Test
    public void countFunctionPointCategory() {
        FunctionPointCategory ILF = FunctionPointCategory.ILF;
        FunctionPointCategory EIF = FunctionPointCategory.EIF;
        /* CASE 1 No FunctionPoints */
        EQPD[] eq1 = {};
        int sumFunc = 0;
        int sumAssert = eq1.length;
        sumFunc += pdc.countFunctionPointCategory(ILF, 1, 10);
        sumFunc += pdc.countFunctionPointCategory(ILF, 2, 10);
        sumFunc += pdc.countFunctionPointCategory(ILF, 3, 10);
        sumFunc += pdc.countFunctionPointCategory(EIF, 1, 7);
        sumFunc += pdc.countFunctionPointCategory(EIF, 2, 7);
        sumFunc += pdc.countFunctionPointCategory(EIF, 3, 7);
        assertEquals(sumAssert, sumFunc, 0);

        /* CASE 2 One FunctionPoint for all Types */
        EQPD[] eq2 = {
                new EQPD("x1", "LF10", "", 9, (int) (Math.random() * 10),
                        (int) (Math.random() * 10), ILF,
                        new ArrayList<>()),
                new EQPD("x2", "LF10", "", 10, (int) (Math.random() * 10),
                        (int) (Math.random() * 10), ILF,
                        new ArrayList<>()),
                new EQPD("x3", "LF10", "", 11, (int) (Math.random() * 10),
                        (int) (Math.random() * 10), ILF,
                        new ArrayList<>()),
                new EQPD("x4", "LF10", "", 6, (int) (Math.random() * 10),
                        (int) (Math.random() * 10), EIF,
                        new ArrayList<>()),
                new EQPD("x5", "LF10", "", 7, (int) (Math.random() * 10),
                        (int) (Math.random() * 10), EIF,
                        new ArrayList<>()),
                new EQPD("x6", "LF10", "", 8, (int) (Math.random() * 10),
                        (int) (Math.random() * 10), EIF,
                        new ArrayList<>()),
        };
        pdc.createPD("x1");
        pdc.createPD("x2");
        pdc.createPD("x3");
        pdc.createPD("x4");
        pdc.createPD("x5");
        pdc.createPD("x6");
        int i = 0;
        for (ProductData pd : model.getProductDataList()) {
            pd.setId(eq2[i].id);
            pd.setMemoryContent(eq2[i].memory_content);
            pd.setReference(eq2[i].reference);
            pd.setFpCategory(eq2[i].fp_category);
            pd.setFpWeight(eq2[i].fp_weighting);
            pd.setFpDet(eq2[i].fp_det);
            pd.setFpFtr(eq2[i].fp_ftr);
            pd.setFpFtr(0);
            i += 1;
        }
        sumFunc = 0;
        sumAssert = eq2.length;
        sumFunc += pdc.countFunctionPointCategory(ILF, 1, 10);
        sumFunc += pdc.countFunctionPointCategory(ILF, 2, 10);
        sumFunc += pdc.countFunctionPointCategory(ILF, 3, 10);
        sumFunc += pdc.countFunctionPointCategory(EIF, 1, 7);
        sumFunc += pdc.countFunctionPointCategory(EIF, 2, 7);
        sumFunc += pdc.countFunctionPointCategory(EIF, 3, 7);
        assertEquals(sumAssert, sumFunc, 0);
    }
}