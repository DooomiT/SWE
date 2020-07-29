/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  Model for Product Content Factory
 */

package Model.Product_F_D;

public class ProductContentFactory {
    public static ProductData createProductData(String id) {
        ProductData productData = new ProductData();
        productData.setId(id);


        return productData;
    }

    public static ProductFunction createProductFunction(String id) {
        ProductFunction productFunction = new ProductFunction();
        productFunction.setId(id);


        return productFunction;
    }

    public static ProductDataAttribute createProductDataAttribute(String productDataAttributeName) {
        ProductDataAttribute productDataAttribute = new ProductDataAttribute();
        productDataAttribute.setName(productDataAttributeName);


        return productDataAttribute;
    }
}
