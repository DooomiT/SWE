/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  Model for Estimation
 */

package Model.EffortEstimation;

public abstract class Estimation {
    private double actualValue;
    private double setpoint;
    private double correctionFactor = 0;

    public Estimation(double actualValue, double set_point) {
        this.actualValue = actualValue;
        this.setpoint = set_point;
    }

    public double getActualValue() {
        return actualValue;
    }

    public void setActualValue(double actualValue) {
        this.actualValue = actualValue;
    }

    public double getSetpoint() {
        return setpoint;
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public double getCorrectionFactor() {
        return correctionFactor;
    }

    public void setCorrectionFactor(double correctionFactor) {
        this.correctionFactor = correctionFactor;
    }

    public abstract void estimate();
}

