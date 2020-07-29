/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description: Controller for Product Functions
 */

package Controller;

import Model.EffortEstimation.FunctionPointCategory;
import Model.Product_F_D.ProductContentFactory;
import Model.Product_F_D.ProductFunction;

import java.util.ArrayList;
import java.util.List;

public class ProductFunctionController {

    private final Controller controller;

    public ProductFunctionController(Controller controller) {
        this.controller = controller;
    }

    public void createPF(String id) {
        //create new product function
        controller.model.getProductFunctionList().add(ProductContentFactory.createProductFunction(id));
        System.out.println("[ProductFunctionControler] New Product Function added: " + id);
    }

    public void savePF(String productFunctionID) {
        //save product function
        for (ProductFunction pf : controller.model.getProductFunctionList()) {
            if (pf.getId().equals(productFunctionID)) {
                pf.setFunction(controller.gui.getProductFunctionFunction());
                pf.setSource(controller.gui.getProductFunctionSource());
                pf.setProtagonist(controller.gui.getProductFunctionProtagonist());
                pf.setReference(controller.gui.getProductFunctionReference());
                pf.setDescription(controller.gui.getProductFunctionDescription());
                pf.setFpCategory(controller.gui.getProductFunctionCategory());

                try {
                    pf.setFpWeight(Integer.parseInt(controller.gui.getProductFunctionWeighting()));
                } catch (Exception exc) {
                    pf.setFpWeight(0);
                }

                try {

                    pf.setFpDet(Integer.parseInt(controller.gui.getProductFunctionDET()));
                } catch (Exception exc) {
                    pf.setFpDet(0);
                }

                try {
                    pf.setFpFtr(Integer.parseInt(controller.gui.getProductFunctionFTR()));
                } catch (Exception exc) {
                    pf.setFpFtr(0);
                }

                System.out.println("[ProductFunctionController] Saved new Productfunction:\n" +
                        getPFInfoString(productFunctionID) + "\n"
                );

                break;
            }
        }
    }

    public void deletePF(String id) {
        //delete product function
        for (ProductFunction pf : controller.model.getProductFunctionList()) {
            if (pf.getId().equals(id)) {
                controller.model.getProductFunctionList().remove(pf);
                System.out.println("[Model] Removed Productfunction " + id + " from Model");
                break;
            }
        }
    }

    public ProductFunction getPF(String id) {
        //get product function
        for (ProductFunction pf : controller.model.getProductFunctionList()) {
            if (pf.getId().equals(id)) {
                return pf;
            }
        }
        return null;
    }

    public String getPFInfoString(String id) {
        //get product function data
        for (ProductFunction pf : controller.model.getProductFunctionList()) {
            if (pf.getId().equals(id)) {
                return (pf.getId() + ": " +
                        pf.getFunction() + "; " +
                        pf.getSource() + "; " +
                        pf.getProtagonist() + "; " +
                        pf.getReference() + "; " +
                        pf.getDescription() + "; " +
                        pf.getFunctionPointWeight() + "; " +
                        pf.getFunctionPointCategory() + "; " +
                        pf.getFunctionPointFTR() + "; " +
                        pf.getFunctionPointDET()
                );
            }
        }
        return "[Error] No matching ID found";
    }

    public boolean ckeckPFId(String productFunctionID) {
        //check product function ID
        for (ProductFunction pf : controller.model.getProductFunctionList()) {
            if (pf.getId().equals(productFunctionID)) return true;
        }
        return false;
    }

    public void renamePF(String productFunctionID, String newProductFunctionID) {
        //rename product function in array list
        for (ProductFunction pf : controller.model.getProductFunctionList()) {
            if (pf.getId().equals(productFunctionID)) {
                pf.setId(newProductFunctionID);
            }
        }
    }

    public List<String> getProductFunctionIDs() {
        //get product function IDs
        List<String> ids = new ArrayList<>();

        for (ProductFunction pf : controller.model.getProductFunctionList()) {
            ids.add(pf.getId());
        }

        return ids;
    }

    public int countFunctionPointCategory(FunctionPointCategory category, int comparison, int value) {
        //count category of function point
        int counter = 0;

        switch (comparison) {
            case 1:
                for (ProductFunction pf : controller.model.getProductFunctionList()) {
                    if (pf.getFunctionPointCategory() == category & pf.getFunctionPointWeight() < value) {
                        counter++;
                    }
                }
                break;

            case 2:
                for (ProductFunction pf : controller.model.getProductFunctionList()) {
                    if (pf.getFunctionPointCategory() == category & pf.getFunctionPointWeight() == value) {
                        counter++;
                    }
                }
                break;

            case 3:
                for (ProductFunction pf : controller.model.getProductFunctionList()) {
                    if (pf.getFunctionPointCategory() == category & pf.getFunctionPointWeight() > value) {
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
