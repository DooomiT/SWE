/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  Model for Function Point Estimation
 */

package Model.EffortEstimation;


public class FunctionPointEstimation extends Estimation {
    private Functiontype input = new Functiontype();
    private Functiontype output = new Functiontype();
    private Functiontype query = new Functiontype();
    private Functiontype intern = new Functiontype();
    private Functiontype extern = new Functiontype();
    private int factor1 = 0;
    private int factor2 = 0;
    private int factor3 = 0;
    private int factor4a = 0;
    private int factor4b = 0;
    private int factor4c = 0;
    private int factor4d = 0;
    private int factor5 = 0;
    private int factor6 = 0;
    private int factor7 = 0;
    private int e1Sum = 0;
    private int e2Sum = 0;
    private double e3Sum = 0;
    private double jonesDuration = 0;
    private int jonesPersons = 0;
    private double jonesPersonMoths = 0;

    public FunctionPointEstimation(double actualValue, double setpoint) {
        super(actualValue, setpoint);
    }

    public Functiontype getInput() {
        return input;
    }

    public void setInput(Functiontype input) {
        this.input = input;
    }

    public Functiontype getOutput() {
        return output;
    }

    public void setOutput(Functiontype output) {
        this.output = output;
    }

    public Functiontype getQuery() {
        return query;
    }

    public void setQuery(Functiontype query) {
        this.query = query;
    }

    public Functiontype getIntern() {
        return intern;
    }

    public void setIntern(Functiontype intern) {
        this.intern = intern;
    }

    public Functiontype getExtern() {
        return extern;
    }

    public void setExtern(Functiontype extern) {
        this.extern = extern;
    }

    public int getFactor1() {
        return factor1;
    }

    public void setFactor1(int factor1) {
        this.factor1 = factor1;
    }

    public int getFactor2() {
        return factor2;
    }

    public void setFactor2(int factor2) {
        this.factor2 = factor2;
    }

    public int getFactor3() {
        return factor3;
    }

    public void setFactor3(int factor3) {
        this.factor3 = factor3;
    }

    public int getFactor4a() {
        return factor4a;
    }

    public void setFactor4a(int factor4a) {
        this.factor4a = factor4a;
    }

    public int getFactor4b() {
        return factor4b;
    }

    public void setFactor4b(int factor4b) {
        this.factor4b = factor4b;
    }

    public int getFactor4c() {
        return factor4c;
    }

    public void setFactor4c(int factor4c) {
        this.factor4c = factor4c;
    }

    public int getFactor4d() {
        return factor4d;
    }

    public void setFactor4d(int factor4d) {
        this.factor4d = factor4d;
    }

    public int getFactor5() {
        return factor5;
    }

    public void setFactor5(int factor5) {
        this.factor5 = factor5;
    }

    public int getFactor6() {
        return factor6;
    }

    public void setFactor6(int factor6) {
        this.factor6 = factor6;
    }

    public int getFactor7() {
        return factor7;
    }

    public void setFactor7(int factor7) {
        this.factor7 = factor7;
    }

    public int getE1Sum() {
        return e1Sum;
    }

    public void setE1Sum(int e1Sum) {
        this.e1Sum = e1Sum;
    }

    public int getE2Sum() {
        return e2Sum;
    }

    public void setE2Sum(int e2Sum) {
        this.e2Sum = e2Sum;
    }

    public double getE3Sum() {
        return e3Sum;
    }

    public void setE3Sum(double e3Sum) {
        this.e3Sum = e3Sum;
    }

    public double getJonesDuration() {
        return jonesDuration;
    }

    public void setJonesDuration(double jonesDuration) {
        this.jonesDuration = jonesDuration;
    }

    public int getJonesPersons() {
        return jonesPersons;
    }

    public void setJonesPersons(int jonesPersons) {
        this.jonesPersons = jonesPersons;
    }

    public double getJonesPersonMoths() {
        return jonesPersonMoths;
    }

    public void setJonesPersonMoths(double jonesPersonMoths) {
        this.jonesPersonMoths = jonesPersonMoths;
    }

    @Override
    public void estimate() {

    }

    /* FunctionPoint Estimation Calc Functions  */

    public void calcE2Sum() {
        //Calculation for E2
        e2Sum = factor1 + factor2 + factor3 + factor4a + factor4b + factor4c + factor4d + factor5 + factor6 + factor7;
    }

    public void calcE3() {
        //Calculation for E3
        e3Sum = (float) e2Sum / 100 + 0.7;
    }

    public void calcFunctionPointResult() {
        //Calculation for Function Point Result
        super.setSetpoint(e1Sum * e3Sum);
    }

    public void updateValues() {
        //Update Calculation functions
        calcE2Sum();
        calcE3();
        calcFunctionPointResult();
        calcJonesEstimation();
    }

    private void calcJonesEstimation() {
        //Calculation for Jones Estimation
        jonesDuration = Math.pow(super.getSetpoint(), 0.4);

        jonesPersons = (int) Math.round(super.getSetpoint() / 150);
        if (jonesPersons < 1 & super.getSetpoint() != 0) jonesPersons = 1;

        jonesPersonMoths = jonesDuration * jonesPersons;
    }
}
