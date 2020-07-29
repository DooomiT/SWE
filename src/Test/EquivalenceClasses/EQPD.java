/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Equivalenceclass for ProductData
 * Comment: no control flow graph, redundant for EQ-class
 */
package Test.EquivalenceClasses;

import Model.EffortEstimation.FunctionPointCategory;
import Model.Product_F_D.ProductDataAttribute;

import java.util.ArrayList;

public class EQPD {
    public String id = "";
    public String memory_content = "";
    public String reference = "";
    public int fp_weighting;
    public int fp_det;
    public int fp_ftr;
    public FunctionPointCategory fp_category;
    public ArrayList<ProductDataAttribute> attributes = new ArrayList<>();

    public EQPD(String id, String memory_content, String reference, int fp_weighting, int fp_det, int fp_ftr, FunctionPointCategory fp_category, ArrayList<ProductDataAttribute> attributes) {
        this.id = id;
        this.memory_content = memory_content;
        this.reference = reference;
        this.fp_weighting = fp_weighting;
        this.fp_det = fp_det;
        this.fp_ftr = fp_ftr;
        this.fp_category = fp_category;
        this.attributes = attributes;
    }
}

