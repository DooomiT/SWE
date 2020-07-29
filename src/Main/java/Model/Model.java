/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  Main Model of software
 */
package Model;

import Model.EffortEstimation.FunctionPointEstimation;
import Model.Product_F_D.ProductData;
import Model.Product_F_D.ProductFunction;
import Model.ProjectInformation.ProductEnvironment;
import Model.ProjectInformation.ProductUse;
import Model.ProjectInformation.TargetDefinition;

import java.util.ArrayList;

public class Model {
    private final ProductUse productUse = new ProductUse();
    private final TargetDefinition targetDefinition = new TargetDefinition();
    private final ProductEnvironment productEnvironment = new ProductEnvironment();
    private final FunctionPointEstimation fpEstimation = new FunctionPointEstimation(0, 0);
    private ArrayList<ProductData> productData = new ArrayList<>();
    private ArrayList<ProductFunction> productFunctions = new ArrayList<>();

    public ProductUse getProductUse() {
        return productUse;
    }

    public TargetDefinition getTargetDefinition() {
        return targetDefinition;
    }

    public ProductEnvironment getProductEnvironment() {
        return productEnvironment;
    }

    public ArrayList<ProductData> getProductDataList() {
        return productData;
    }

    public ArrayList<ProductFunction> getProductFunctionList() {
        return productFunctions;
    }

    public FunctionPointEstimation getFunctionPointEstimation() {
        return fpEstimation;
    }

    public void setProductData(ArrayList<ProductData> product_data) {
        this.productData = product_data;
    }

    public void setProductFunctions(ArrayList<ProductFunction> product_functions) {
        this.productFunctions = product_functions;
    }

    @Override
    public String toString() {
        return "Model{" +
                "productDataList=" + productData +
                ", productFunctionList=" + productFunctions +
                ", productUse=" + productUse +
                ", targetDefinition=" + targetDefinition +
                ", productEnvironment=" + productEnvironment +
                ", functionPointEstimation=" + fpEstimation +
                '}';
    }
}