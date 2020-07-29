/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Equivalenceclass for EstimationController calculateOverviewValues
 * Comment: no control flow graph, redundant for EQ-class
 */

package Test.EquivalenceClasses;

import Model.Product_F_D.ProductData;
import Model.Product_F_D.ProductFunction;

import java.util.ArrayList;

public class EQECTCOV {
    public ArrayList<ProductData> product_data = new ArrayList<>();
    public ArrayList<ProductFunction> product_functions = new ArrayList<>();
    public int e1Sum;

    public EQECTCOV(ArrayList<ProductData> product_data, ArrayList<ProductFunction> product_functions, int e1Sum) {
        this.product_data = product_data;
        this.product_functions = product_functions;
        this.e1Sum = e1Sum;
    }
}
