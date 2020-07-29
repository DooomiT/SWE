/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Equivalenceclass for ProductFunctions
 * Comment: no control flow graph, redundant for EQ-class
 */

package Test.EquivalenceClasses;

import Model.EffortEstimation.FunctionPointCategory;

public class EQPF {
    public String id;
    public String source;
    public String reference;
    public String protagonist;
    public String description;
    public FunctionPointCategory fpCategory;
    public int weight;
    public int det;
    public int ftr;

    public EQPF(String id, String source, String reference, String protagonist, String description, FunctionPointCategory fpCategory, int weight, int det, int ftr) {
        this.id = id;
        this.source = source;
        this.reference = reference;
        this.protagonist = protagonist;
        this.description = description;
        this.fpCategory = fpCategory;
        this.weight = weight;
        this.det = det;
        this.ftr = ftr;
    }
}
