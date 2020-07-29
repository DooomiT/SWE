/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Controller for Product Data
 */

package Controller;

import Model.EffortEstimation.FunctionPointCategory;
import Model.Product_F_D.ProductContentFactory;
import Model.Product_F_D.ProductData;
import Model.Product_F_D.ProductDataAttribute;

import java.util.ArrayList;
import java.util.List;

public class ProductDataController {

    private final Controller controller;

    public ProductDataController(Controller controller) {
        this.controller = controller;
    }

    public void createPD(String id) {
        //create new product data
        controller.model.getProductDataList().add(ProductContentFactory.createProductData(id));
        System.out.println("[ProductDataController] New Product Data added: " + id);
    }

    public void savePD(String productDataID) {
        //save product data
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(productDataID)) {
                pd.setReference(controller.gui.getProductDataRefernce());
                pd.setMemoryContent(controller.gui.getProductDataMemoryContent());
                pd.setFpCategory(controller.gui.getProductDataCategory());

                try {
                    pd.setFpWeight(Integer.parseInt(controller.gui.getProductDataWeighting()));
                } catch (Exception exc) {
                    pd.setFpWeight(0);
                }

                try {
                    pd.setFpDet(Integer.parseInt(controller.gui.getProductDataDET()));
                } catch (Exception exc) {
                    pd.setFpDet(0);
                }

                try {
                    pd.setFpFtr(Integer.parseInt(controller.gui.getProductDataFTR()));
                } catch (Exception exc) {
                    pd.setFpFtr(0);
                }
                System.out.println("[ProductDataController] Saved new Productdata:\n" +
                        getPDInfoString(productDataID) + "\n"
                );
                break;
            }
        }
    }

    public void deletePD(String id) {
        //delete product data
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(id)) {
                controller.model.getProductDataList().remove(pd);
                System.out.println("[Model] Removed Productdata " + id + " from Model");
                break;
            }
        }
    }

    public ProductData getPD(String id) {
        //get product data
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(id)) {
                return pd;
            }
        }
        return null;
    }

    public String getPDInfoString(String id) {
        //get product data information
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(id)) {
                StringBuilder infoString = new StringBuilder();
                String temp = pd.getId() + ": " + pd.getReference() + "; " + pd.getMemoryContent() + "; "
                        + pd.getFpCategory();
                infoString.append(temp);
                for (String attributeName : getAttributeNames(id)) {
                    temp = attributeName + ", ";
                    infoString.append(temp);
                }
                return infoString.toString();
            }
        }
        return "[Error] No matching ID found";
    }

    public boolean ckeckPDId(String productDataID) {
        //check product data ID
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(productDataID)) return true;
        }
        return false;
    }

    public void renamePD(String productDataID, String newProductDataID) {
        ////rename product data ID in array list
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(productDataID)) {
                pd.setId(newProductDataID);
            }
        }
    }

    public boolean ckeckPDAttribute(String productDataID, String productDataAttributeName) {
        //check product data attribute
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(productDataID)) {
                for (ProductDataAttribute pda : pd.getAttributeList()) {
                    if (pda.getName().equals(productDataAttributeName)) return true;
                }
            }
        }
        return false;
    }

    public ProductDataAttribute getPDAttribute(String productDataID, String productDataAttributeName) {
        //get product data attribute
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(productDataID)) {
                for (ProductDataAttribute pda : pd.getAttributeList()) {
                    if (pda.getName().equals(productDataAttributeName)) return pda;
                }
            }
        }
        return null;
    }

    public void createPDAttribute(String productDataID, String productDataAttributeName) {
        //create new product data attribute
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(productDataID)) {
                pd.getAttributeList().add(ProductContentFactory.createProductDataAttribute(productDataAttributeName));
                System.out.println("[ProductDataControler] New Product Data Attribute added: " + productDataAttributeName);
                break;
            }
        }

    }

    public List<String> getAttributeNames(String selectedProductData) {
        //get attribute names
        List<String> names = new ArrayList<>();

        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(selectedProductData)) {
                for (ProductDataAttribute pda : pd.getAttributeList()) {
                    names.add(pda.getName());
                }
            }
        }

        return names;
    }

    public void deletePDAttribut(String productDataID, String productDataAttributeName) {
        //delete product data attribute
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(productDataID)) {
                for (ProductDataAttribute pda : pd.getAttributeList()) {
                    if (pda.getName().equals(productDataAttributeName)) {
                        pd.getAttributeList().remove(pda);
                        System.out.println("[Model] Removed Attribute " + productDataAttributeName + " from Model");
                        break;
                    }
                }
            }
        }
    }

    public void savePDAttribute(String productDataID, String productDataAttributeName) {
        //save product data attribute
        for (ProductData pd : controller.model.getProductDataList()) {
            if (pd.getId().equals(productDataID)) {
                for (ProductDataAttribute pda : pd.getAttributeList()) {
                    if (pda.getName().equals(productDataAttributeName)) {
                        pda.setContent(controller.gui.getProductDataAttributeContent());

                        System.out.println("[ProductDataController] Saved new Attribute: " +
                                productDataAttributeName
                        );

                        break;
                    }
                }
            }
        }
    }

    public List<String> getProductDataIDs() {
        //get product data IDs
        List<String> ids = new ArrayList<>();

        for (ProductData pd : controller.model.getProductDataList()) {
            ids.add(pd.getId());
        }

        return ids;
    }

    public int countFunctionPointCategory(FunctionPointCategory category, int comparison, int value) {
        //count category of Function Point
        int counter = 0;

        switch (comparison) {
            case 1:
                for (ProductData pd : controller.model.getProductDataList()) {
                    if (pd.getFpCategory() == category & pd.getFpWeight() < value) {
                        counter++;
                    }
                }
                break;

            case 2:
                for (ProductData pd : controller.model.getProductDataList()) {
                    if (pd.getFpCategory() == category & pd.getFpWeight() == value) {
                        counter++;
                    }
                }
                break;

            case 3:
                for (ProductData pd : controller.model.getProductDataList()) {
                    if (pd.getFpCategory() == category & pd.getFpWeight() > value) {
                        counter++;
                    }
                }
                break;

            default:
                System.out.println("[ERROR] countFunctionPointCategory: Wrong comparison-mode given.");
                break;
        }
        return counter;
    }
}
