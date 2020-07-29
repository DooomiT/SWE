/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  XML Parser module
 * */

package XML;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XML_Parser {
    public static Element elementcreator(String text, String Elementname, Document xmlDoc) {
        Element element = xmlDoc.createElement(Elementname);
        Text eText = xmlDoc.createTextNode(text);
        element.appendChild(eText);
        return element;
    }

    public static HashMap<String, HashMap> parsetojava(File file) {

        return readXML(file);
    }

    private static HashMap<String, HashMap> readXML(File file) {
        try {
            String xmlfile = file.getPath();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlfile);

            /* Write ProjectInfo Import from XML Elements into Hashmap */
            HashMap<String, HashMap> importData = new HashMap<>();
            Node Projektinfos = doc.getElementsByTagName("Projektinfos").item(0);
            if (Projektinfos.getNodeType() == Node.ELEMENT_NODE) {
                Element infos = (Element) Projektinfos;
                NodeList nameList = infos.getChildNodes();
                HashMap<String, String> projectInfoMap = new HashMap<>();
                int bound = nameList.getLength();
                /* Iterate for all childs of "Projektinfos" */
                for (int i1 = 0; i1 < bound; i1++) {
                    Node n = nameList.item(i1);
                    if (n.getNodeType() == Node.ELEMENT_NODE) {
                        Element name = (Element) n;
                        projectInfoMap.put(name.getTagName(), name.getTextContent());
                    }
                }
                importData.put("Projektinfos", projectInfoMap);
                System.out.println("Added To Map:" + projectInfoMap.toString());
            }

            /* Write ProductFunktion Import from XML Elements into Hashmap */
            HashMap<String, HashMap<String, String>> productFunctionMap = new HashMap<>();
            NodeList pfIds = doc.getElementsByTagName("PF_ID");
            /* Iterate for all childs of "Produktfunktionen" */
            for (int i = 0; i < pfIds.getLength(); i++) {
                Node p = pfIds.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element pfId = (Element) p;
                    String id = pfId.getAttribute("ID");
                    NodeList nameList = pfId.getChildNodes();
                    HashMap<String, String> productFunction = new HashMap<>();
                    HashMap<String, String> functionPoint = new HashMap<>();
                    int bound = nameList.getLength();
                    /* Iterate for all childs of one "ProductFunction" */
                    for (int i1 = 0; i1 < bound; i1++) {
                        Node n = nameList.item(i1);
                        if (n.getNodeType() == Node.ELEMENT_NODE) {
                            Element name = (Element) n;
                            if (name.getTagName().equals("functionpoint")) {
                                NodeList fpList = name.getChildNodes();
                                int boundFp = fpList.getLength();
                                /* Iterate for all childs of "functionpoint" */
                                for (int i2 = 0; i2 < boundFp; i2++) {
                                    Node n1 = fpList.item(i2);
                                    if (n1.getNodeType() == Node.ELEMENT_NODE) {
                                        Element fp = (Element) n1;
                                        /* Append FunctionPoint data to functionPoint HashMap */
                                        functionPoint.put(fp.getTagName(), fp.getTextContent());
                                    }
                                }
                            } else {
                                /* Append productFunction data to productFunction HashMap */
                                productFunction.put(name.getTagName(), name.getTextContent());
                            }
                        }
                    }
                    /* Append FunctionPoint & productFunction to productFunctionMap HashMap */
                    productFunctionMap.put("pf" + id, productFunction);
                    productFunctionMap.put("fp" + id, functionPoint);
                }
            }
            importData.put("Produktfunktionen", productFunctionMap);
            System.out.println("Added To Map:" + productFunctionMap.toString());

            /* Write ProductData Import from XML Elements into Hashmap */
            HashMap<String, HashMap<String, String>> productDataMap = new HashMap<>();
            NodeList pdIds = doc.getElementsByTagName("PD_ID");
            /* Iterate for all childs of "Produktdaten" */
            for (int i = 0; i < pdIds.getLength(); i++) {
                Node p = pdIds.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element pdId = (Element) p;
                    String id = pdId.getAttribute("ID");
                    NodeList nameList = pdId.getChildNodes();
                    HashMap<String, String> productData = new HashMap<>();
                    HashMap<String, String> functionPoint = new HashMap<>();
                    HashMap<String, String> attribute = new HashMap<>();
                    int bound = nameList.getLength();
                    /* Iterate for all childs of one "ProductData" */
                    for (int i1 = 0; i1 < bound; i1++) {
                        Node n = nameList.item(i1);
                        if (n.getNodeType() == Node.ELEMENT_NODE) {
                            Element name = (Element) n;
                            if (name.getTagName().equals("functionpoint")) {
                                NodeList fpList = name.getChildNodes();
                                int boundFp = fpList.getLength();
                                /* Iterate for all childs of "functionpoint" */
                                for (int i2 = 0; i2 < boundFp; i2++) {
                                    Node n1 = fpList.item(i2);
                                    if (n1.getNodeType() == Node.ELEMENT_NODE) {
                                        Element fp = (Element) n1;
                                        /* Append FunctionPoint data to functionPoint HashMap */
                                        functionPoint.put(fp.getTagName(), fp.getTextContent());
                                    }
                                }
                            } else if (name.getTagName().equals("attribute")) {
                                NodeList atList = name.getChildNodes();
                                int boundFp = atList.getLength();
                                /* Iterate for all childs of "attribute" */
                                for (int i2 = 0; i2 < boundFp; i2++) {
                                    Node n1 = atList.item(i2);
                                    if (n1.getNodeType() == Node.ELEMENT_NODE) {
                                        Element at = (Element) n1;
                                        /* Append attribute data to attribute HashMap */
                                        attribute.put(at.getTagName(), at.getTextContent());
                                    }
                                }
                            } else {
                                /* Append productData data to productData HashMap */
                                productData.put(name.getTagName(), name.getTextContent());
                            }
                        }
                    }
                    /* Append FunctionPoint & attribute & productData to productFunctionMap HashMap */
                    productDataMap.put("pd" + i, productData);
                    productDataMap.put("fp" + i, functionPoint);
                    productDataMap.put("at" + i, attribute);
                }
            }
            importData.put("Produktdaten", productDataMap);
            System.out.println("Added To Map:" + productDataMap.toString());
            return importData;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void parsetoxml(HashMap<String, HashMap> exportData) {
        try {
            writeXML(exportData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeXML(HashMap<String, HashMap> exportData) throws ParserConfigurationException {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document xmlDoc = docBuilder.newDocument();

            /* Create XML rootelement */
            Element rootelement = xmlDoc.createElement("content");

            /* Create main XML Elements */
            Element mainElement1 = xmlDoc.createElement("Projektinfos");
            Element mainElement2 = xmlDoc.createElement("Produktfunktionen");
            Element mainElement3 = xmlDoc.createElement("Produktdaten");

            /* Write ProjectInfo Export from Hashmap into XML Elements */
            HashMap<String, String> ProjectInfo = exportData.get("Projektinfos");
            try {
                for (Map.Entry pi : ProjectInfo.entrySet()) {
                    Element temp = elementcreator(pi.getValue().toString(), pi.getKey().toString(), xmlDoc);
                    mainElement1.appendChild(temp);
                    mainElement1.appendChild(temp);
                }
            } catch (NullPointerException e) {
                System.out.println("No Projektinfo available " + e);
            }

            /* Write ProductFunctions Export from Hashmap into XML Elements */
            HashMap<String, HashMap<String, String>> productFunctions = exportData.get("Productfunktionen");
            try {
                for (int i = 0; i < productFunctions.size() / 2; i++) {
                    Element PF_ID = xmlDoc.createElement("PF_ID");
                    PF_ID.setAttribute("ID", Integer.toString(i));
                    for (Map.Entry pf : productFunctions.get("pf" + i).entrySet()) {
                        Element element = elementcreator(pf.getValue().toString(), pf.getKey().toString(), xmlDoc);
                        PF_ID.appendChild(element);
                    }
                    Element fp = xmlDoc.createElement("functionpoint");
                    for (Map.Entry pf : productFunctions.get("fp" + i).entrySet()) {
                        Element element = elementcreator(pf.getValue().toString(), pf.getKey().toString(), xmlDoc);
                        fp.appendChild(element);
                    }
                    PF_ID.appendChild(fp);
                    mainElement2.appendChild(PF_ID);
                }
            } catch (NullPointerException e) {
                System.out.println("No Productfunction available " + e);
            }

            /* Write ProductData Export from Hashmap into XML Elements */
            HashMap<String, HashMap<String, String>> productData = exportData.get("Produktdaten");
            try {
                for (int i = 0; i < productData.size() / 3; i++) {
                    Element PD_ID = xmlDoc.createElement("PD_ID");
                    PD_ID.setAttribute("ID", String.valueOf(i));
                    for (Map.Entry pd : productData.get("pd" + i).entrySet()) {
                        Element element = elementcreator(pd.getValue().toString(), pd.getKey().toString(), xmlDoc);
                        PD_ID.appendChild(element);
                    }
                    Element fp = xmlDoc.createElement("functionpoint");
                    for (Map.Entry pd : productData.get("fp" + i).entrySet()) {
                        Element element = elementcreator(pd.getValue().toString(), pd.getKey().toString(), xmlDoc);
                        fp.appendChild(element);
                    }
                    PD_ID.appendChild(fp);
                    Element attr = xmlDoc.createElement("attribute");
                    for (Map.Entry pd : productData.get("at" + i).entrySet()) {
                        Element attribute = elementcreator(pd.getValue().toString(), pd.getKey().toString(), xmlDoc);
                        if (pd.getKey().toString().equals("attibuteName" + i)) {
                        }
                        if (pd.getKey().toString().equals("attibuteContent" + i)) {
                        }
                        attr.appendChild(attribute);
                    }
                    PD_ID.appendChild(attr);
                    mainElement3.appendChild(PD_ID);
                }
            } catch (NullPointerException e) {
                System.out.println("No Productdata available " + e);
            }

            /* Append XML Elements to rootelement */
            rootelement.appendChild(mainElement1);
            rootelement.appendChild(mainElement2);
            rootelement.appendChild(mainElement3);
            xmlDoc.appendChild(rootelement);

            /* User Menu to choose a Filename and a storage location */
            JFileChooser chooser = new JFileChooser();
            int returnValue = chooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String pathName = chooser.getSelectedFile().getPath();
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(xmlDoc);
                StreamResult streamResult = new StreamResult(new File(pathName));
                transformer.transform(source, streamResult);
            } else {        //else printing an error
                System.out.println("Save File was aborted...");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}