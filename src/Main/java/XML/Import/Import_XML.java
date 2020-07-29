/*
 * Main: This file is part of CASE-
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  XML-XML.Import module
 * */

package XML.Import;

import Controller.Controller;
import Controller.ProductDataController;
import Controller.ProductFunctionController;
import Controller.ProjectInfoController;
import Model.EffortEstimation.FunctionPointCategory;
import Model.Product_F_D.ProductData;
import Model.Product_F_D.ProductDataAttribute;
import Model.Product_F_D.ProductFunction;
import XML.XML_Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Import_XML extends Import {
    private final XML_Parser xmlParser = new XML_Parser();
    private final Controller controller;

    public Import_XML(Controller controller) {
        super("XML");
        this.controller = controller;
    }

    @Override
    public HashMap<String, HashMap> importData(File file) {
        System.out.println("XML to Java parsing...");
        return XML_Parser.parsetojava(file);
    }

    public void receiveImportData(File file) {
        controller.emptyData();
        ProjectInfoController pic = controller.getProjectInfoController();
        ProductFunctionController pfc = controller.getProductFunctionController();
        ProductDataController pdc = controller.getProductDataController();
        HashMap<String, HashMap> importedData = importData(file);
        System.out.println("Recieved Map: " + importedData.toString());
        /* Get ProjectInfo */
        HashMap<String, String> projectInfoMap = importedData.get("Projektinfos");
        pic.getTargetDefinition().setContent(projectInfoMap.get("zielbestimmung"));
        pic.getProductUse().setContent(projectInfoMap.get("produkteinsatz"));
        pic.getProductEnvironment().setContent(projectInfoMap.get("umgebung"));
        /* Get ProductFunction */
        HashMap<String, HashMap<String, String>> productFunctionMap = importedData.get("Produktfunktionen");
        for (int i = 0; i < productFunctionMap.size() / 2; i++) {
            HashMap<String, String> productFunction = productFunctionMap.get("pf" + i);
            HashMap<String, String> functionPoint = productFunctionMap.get("fp" + i);
            pfc.createPF(productFunction.get("id"));
            ProductFunction pf = pfc.getPF(productFunction.get("id"));
            pf.setFunction(productFunction.get("funktion"));
            pf.setSource(productFunction.get("quelle"));
            pf.setReference(productFunction.get("verweis"));
            pf.setProtagonist(productFunction.get("akteuer"));
            pf.setDescription(productFunction.get("beschreibung"));
            pf.setFpWeight(Integer.parseInt(functionPoint.get("gewicht")));
            pf.setFpDet(Integer.parseInt(functionPoint.get("DET")));
            pf.setFpFtr(Integer.parseInt(functionPoint.get("FTR")));
            String cat = functionPoint.get("Kategorie");
            if (cat.equals("EI")) {
                pf.setFpCategory(FunctionPointCategory.EI);
            } else if (cat.equals("EO")) {
                pf.setFpCategory(FunctionPointCategory.EO);

            } else if (cat.equals("EQ")) {
                pf.setFpCategory(FunctionPointCategory.EQ);

            } else {
                System.out.println("ERROR getting FunctionPointCategory");
            }
        }
        try {

            /* Get ProductData */
            HashMap<String, HashMap<String, String>> productDataMap = importedData.get("Produktdaten");
            for (int i = 0; i < productDataMap.size(); i++) {
                HashMap<String, String> productData = productDataMap.get("pd" + i);
                HashMap<String, String> functionPoint = productDataMap.get("fp" + i);
                HashMap<String, String> attribute = productDataMap.get("at" + i);
                pdc.createPD(productData.get("id"));
                ProductData pd = pdc.getPD(productData.get("id"));
                pd.setMemoryContent(productData.get("speicherinhalt"));
                pd.setReference(productData.get("verweis"));
                pd.setFpWeight(Integer.parseInt(functionPoint.get("gewicht")));
                pd.setFpDet(Integer.parseInt(functionPoint.get("DET")));
                pd.setFpFtr(Integer.parseInt(functionPoint.get("FTR")));
                String cat = functionPoint.get("Kategorie");
                if (cat.equals("EIF")) {
                    pd.setFpCategory(FunctionPointCategory.EIF);
                } else if (cat.equals("ILF")) {
                    pd.setFpCategory(FunctionPointCategory.ILF);
                } else {
                    System.out.println("ERROR getting FunctionPointCategory");
                }
                ArrayList<ProductDataAttribute> pda_list = new ArrayList<>();
                for (int j = 0; j < attribute.size(); j++) {
                    String name = attribute.get("attributeName" + j);
                    String content = attribute.get("attributeContent" + j);
                    ProductDataAttribute tmpA = new ProductDataAttribute(name, content);
                    pda_list.add(tmpA);
                }
                pd.setAttributeList(pda_list);
            }
            System.out.println("XML to Java completed...");
        } catch (NullPointerException e) {
        }
    }
}
