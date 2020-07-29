/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  Model for Product Functions
 */

package Model.Product_F_D;

import Model.EffortEstimation.FunctionPointCategory;

public class ProductFunction extends ProductContent {
    private String id = "";
    private String function = "";
    private String source = "";
    private String reference = "";
    private String protagonist = "";
    private String description = "";
    private int fpWeight;
    private FunctionPointCategory fpCategory;
    private int fpDet;
    private int fpFtr;

    public ProductFunction() {
    }

    public ProductFunction(String id, String function, String source, String reference, String protagonist,
                           String description, int fpWeight, FunctionPointCategory fpCategory, int fpDet, int fpFtr) {
        this.id = id;
        this.function = function;
        this.source = source;
        this.reference = reference;
        this.protagonist = protagonist;
        this.description = description;
        this.fpWeight = fpWeight;
        this.fpCategory = fpCategory;
        this.fpDet = fpDet;
        this.fpFtr = fpFtr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(String protagonist) {
        this.protagonist = protagonist;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFunctionPointWeight() {
        return fpWeight;
    }

    public void setFpWeight(int fpWeight) {
        this.fpWeight = fpWeight;
    }

    public FunctionPointCategory getFunctionPointCategory() {
        return fpCategory;
    }

    public void setFpCategory(FunctionPointCategory fpCategory) {
        this.fpCategory = fpCategory;
    }

    public int getFunctionPointDET() {
        return fpDet;
    }

    public void setFpDet(int fpDet) {
        this.fpDet = fpDet;
    }

    public int getFunctionPointFTR() {
        return fpFtr;
    }

    public void setFpFtr(int fpFtr) {
        this.fpFtr = fpFtr;
    }
}
