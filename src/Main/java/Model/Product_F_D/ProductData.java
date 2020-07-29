/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  Model for Product Data
 */

package Model.Product_F_D;

import Model.EffortEstimation.FunctionPointCategory;

import java.util.ArrayList;

public class ProductData extends ProductContent {
    private String id = "";
    private String memoryContent = "";
    private String reference = "";
    private int fpWeight;
    private int fpDet;
    private int fpFtr;
    private FunctionPointCategory fpCategory;
    private ArrayList<ProductDataAttribute> attributeList = new ArrayList<>();

    public ProductData() {
    }

    public ProductData(String id, String memoryContent, String reference, int fpWeight, int fpDet, int fpFtr,
                       FunctionPointCategory fpCategory, ArrayList<ProductDataAttribute> attributeList) {
        this.id = id;
        this.memoryContent = memoryContent;
        this.reference = reference;
        this.fpWeight = fpWeight;
        this.fpDet = fpDet;
        this.fpFtr = fpFtr;
        this.fpCategory = fpCategory;
        this.attributeList = attributeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemoryContent() {
        return memoryContent;
    }

    public void setMemoryContent(String memoryContent) {
        this.memoryContent = memoryContent;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getFpWeight() {
        return fpWeight;
    }

    public void setFpWeight(int fpWeight) {
        this.fpWeight = fpWeight;
    }

    public int getFpDet() {
        return fpDet;
    }

    public void setFpDet(int fpDet) {
        this.fpDet = fpDet;
    }

    public int getFpFtr() {
        return fpFtr;
    }

    public void setFpFtr(int fpFtr) {
        this.fpFtr = fpFtr;
    }

    public FunctionPointCategory getFpCategory() {
        return fpCategory;
    }

    public void setFpCategory(FunctionPointCategory fpCategory) {
        this.fpCategory = fpCategory;
    }

    public ArrayList<ProductDataAttribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(ArrayList<ProductDataAttribute> attributeList) {
        this.attributeList = attributeList;
    }

    @Override
    public String toString() {
        String tmp = "";
        if (fpCategory == FunctionPointCategory.ILF) {
            tmp = "ILF";
        } else if (fpCategory == FunctionPointCategory.EIF) {
            tmp = "EIF";
        }
        return "ProductData{" +
                "id='" + id + '\'' +
                ", memory_content='" + memoryContent + '\'' +
                ", reference='" + reference + '\'' +
                ", fp_weighting=" + fpWeight +
                ", fp_det=" + fpDet +
                ", fp_ftr=" + fpFtr +
                ", fp_category='" + tmp + '\'' +
                ", attributes=" + attributeList +
                '}';
    }
}
