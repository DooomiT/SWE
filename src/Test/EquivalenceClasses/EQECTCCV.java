/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Equivalenceclass for EstimationController calculateCorrectionValue
 * Comment: no control flow graph, redundant for EQ-class
 */

package Test.EquivalenceClasses;

public class EQECTCCV {
    public int actualPersonMonths;
    public double setpoint;
    public double correctionFactor;

    public EQECTCCV(int actualPersonMonths, double setpoint) {
        this.actualPersonMonths = actualPersonMonths;
        this.setpoint = setpoint;
        this.correctionFactor = Math.log(actualPersonMonths / setpoint) + 1;
    }

}
