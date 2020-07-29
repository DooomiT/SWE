/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  XML-XML.Export module
 */

package XML.Export;

import Controller.Controller;
import Controller.ProductDataController;
import Controller.ProductFunctionController;
import Controller.ProjectInfoController;
import Model.Product_F_D.ProductData;
import Model.Product_F_D.ProductDataAttribute;
import Model.Product_F_D.ProductFunction;
import XML.XML_Parser;

import java.util.HashMap;

public class Export_XML extends Export {
    private final XML_Parser xmlParser = new XML_Parser();
    private final Controller controller;

    public Export_XML(Controller controller) {
        super("XML");
        this.controller = controller;
    }

    public HashMap<String, HashMap> createExportData(Controller controller) {
        ProjectInfoController pic = controller.getProjectInfoController();
        ProductFunctionController pfc = controller.getProductFunctionController();
        ProductDataController pdc = controller.getProductDataController();
        HashMap<String, HashMap> exportData = new HashMap<>();
        /* Get ProjectInfo */
        HashMap<String, String> projectInfoMap = new HashMap<>();
        projectInfoMap.put("zielbestimmung", pic.getTargetDefinition().getContent());
        projectInfoMap.put("produkteinsatz", pic.getProductUse().getContent());
        projectInfoMap.put("umgebung", pic.getProductEnvironment().getContent());
        exportData.put("Projektinfos", projectInfoMap);
        System.out.println("Added To Map:" + projectInfoMap.toString());
        /* Get ProductFunction */
        HashMap<String, HashMap<String, String>> productFunctionMap = new HashMap<>();
        int count = 0;
        for (String pfId : pfc.getProductFunctionIDs()) {
            HashMap<String, String> productFunction = new HashMap<>();
            HashMap<String, String> functionPoint = new HashMap<>();
            ProductFunction pf = pfc.getPF(pfId);
            productFunction.put("id", pf.getId());
            productFunction.put("funktion", pf.getId());
            productFunction.put("quelle", pf.getSource());
            productFunction.put("verweis", pf.getReference());
            productFunction.put("akteuer", pf.getProtagonist());
            productFunction.put("beschreibung", pf.getDescription());
            functionPoint.put("gewicht", Integer.toString(pf.getFunctionPointWeight()));
            functionPoint.put("DET", Integer.toString(pf.getFunctionPointDET()));
            functionPoint.put("FTR", Integer.toString(pf.getFunctionPointFTR()));
            functionPoint.put("Kategorie", pf.getFunctionPointCategory().toString());
            productFunctionMap.put("pf" + count, productFunction);
            productFunctionMap.put("fp" + count, functionPoint);
            count++;
        }
        exportData.put("Productfunktionen", productFunctionMap);
        System.out.println("Added To Map:" + productFunctionMap.toString());
        /* Get ProductData */
        HashMap<String, HashMap<String, String>> productDataMap = new HashMap<>();
        count = 0;
        for (String pdId : pdc.getProductDataIDs()) {
            HashMap<String, String> productData = new HashMap<>();
            HashMap<String, String> functionPoint = new HashMap<>();
            HashMap<String, String> attribute = new HashMap<>();
            ProductData pd = pdc.getPD(pdId);
            productData.put("id", pd.getId());
            productData.put("speicherinhalt", pd.getMemoryContent());
            productData.put("verweis", pd.getReference());
            functionPoint.put("gewicht", Integer.toString(pd.getFpWeight()));
            functionPoint.put("DET", Integer.toString(pd.getFpDet()));
            functionPoint.put("FTR", Integer.toString(pd.getFpFtr()));
            functionPoint.put("Kategorie", pd.getFpCategory().toString());
            int j = 0;

            for (ProductDataAttribute pda : pd.getAttributeList()) {
                attribute.put("attributeName" + j, pda.getName());
                attribute.put("attributeContent" + j, pda.getContent());
                j++;
            }
            productDataMap.put("pd" + count, productData);
            productDataMap.put("fp" + count, functionPoint);
            productDataMap.put("at" + count, attribute);
            count++;
        }
        exportData.put("Produktdaten", productDataMap);
        System.out.println("Added To Map:" + productDataMap.toString());

        return exportData;
    }

    @Override
    public void exportData() {
        System.out.println("Java to XML parsing...");
        XML_Parser.parsetoxml(createExportData(this.controller));
        System.out.println("Java to XML completed...");
        System.out.println("XML File created...");
    }
}
