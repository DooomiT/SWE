/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  Main View of software
 */

package View;

import Controller.Controller;
import Model.EffortEstimation.FunctionPointCategory;
import Model.EffortEstimation.FunctionPointEstimation;
import Model.Model;
import XML.Export.Export_XML;
import XML.Import.Import_XML;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class View extends JFrame {
    private static final DecimalFormat df2 = new DecimalFormat("#.##");
    private final Model model;
    public JLabel estimationInputEasyNumber;
    public JLabel estimationInputMiddleNumber;
    public JLabel estimationInputComplexNumber;
    private JList productFunctionList;
    private Controller controller;
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;
    private JPanel projectInfoTab;
    private JLabel productUseTitelLabel;
    private JTextArea productUseTextarea;
    private JLabel environmentTitelLabel;
    private JTextArea environmentTextarea;
    private JLabel targetDefinitionTitelLabel;
    private JButton saveProjectInfoButton;
    private JTextArea targetDefinitionTextarea;
    private JPanel productFunctionsTab;
    private JLabel productFunctionIDTitleLabel;
    private JLabel productFunctionFunctionLabel;
    private JLabel productFunctionSourceLabel;
    private JLabel productFunctionReferenceLabel;
    private JLabel productFunctionProtagonistLabel;
    private JLabel productFunctionDescriptionLabel;
    private JTextField productFunctionFunctionField;
    private JTextField productFunctionSourceField;
    private JTextField productFunctionReferenceField;
    private JTextField productFunctionProtagonistField;
    private JButton saveProductFunctionButton;
    private JButton deleteProductFunctionButton;
    private JButton newProductFunctionButton;
    private JScrollPane productFunctionTableScrollPane;
    private JTable productFunctionTable;
    private JLabel productFunctionIDLabel;
    private JButton changeProductFunctionIDButton;
    private JTextArea productFunctionDescriptionField;
    private JLabel productFunctionComplexityTitleLabel;
    private JLabel productFunctionCategoryTitleLabel;
    private JLabel productFunctionDETTitleLabel;
    private JLabel productFunctionFTRTitleLabel;
    private JSpinner productFunctionWeightingComboBox;
    private JComboBox<String> productFunctionCategoryComboBox;
    private JSpinner productFunctionDETSpinner;
    private JSpinner productFunctionFTRSpinner;
    private JLabel productFunctionFunctionPointLabel;
    private JPanel productDataTab;
    private JLabel productDataIDTitleLabel;
    private JLabel productDataMemoryContentTitleLabel;
    private JLabel productDataReferenceTitleLabel;
    private JLabel productDataAttributeTitleLabel;
    private JButton saveProductDataButton;
    private JTextField productDataMemoryContentField;
    private JTextField productDataReferenceField;
    private JButton deleteProductDataButton;
    private JButton newProductDataButton;
    private JLabel productDataIDLabel;
    private JScrollPane productDataTableScrollPane;
    private JTable productDataTable;
    private JScrollPane productDataAttributeTableScrollPane;
    private JTable productDataAttributeTable;
    private JLabel productDataAttributeNameTitleLabel;
    private JLabel productDataAttributeContentTitleLabel;
    private JTextField productDataAttributeContentLabel;
    private JButton changeProductDataIDButton;
    private JLabel productDataAttributeNameLabel;
    private JButton newProductDataAttributeButton;
    private JButton deleteProductDataAttributeButton;
    private JButton saveProductDataAttributeButton;
    private JLabel productDataWeightingTitleLabel;
    private JLabel productDataDETTitleLabel;
    private JLabel productDataFTRTitleLabel;
    private JSpinner productDataWeightingSpinner;
    private JSpinner productDataDETSpinner;
    private JSpinner productDataFTRSpinner;
    private JLabel productDataCategoryTitleLabel;
    private JComboBox<String> productDataCategoryComboBox;
    private JPanel estimationTab;
    private JTabbedPane estimationPane;
    private JPanel e1Overview;
    private JLabel estimationInputEasySum;
    private JLabel estimationInputMiddleSum;
    private JLabel estimationInputComplexSum;
    private JLabel estimationOutputEasyNumber;
    private JLabel estimationOutputMiddleNumber;
    private JLabel estimationOutputComplexNumber;
    private JLabel estimationOutputEasySum;
    private JLabel estimationOutputMiddleSum;
    private JLabel estimationOutputComplexSum;
    private JLabel estimationQueryEasyNumber;
    private JLabel estimationQueryMiddleNumber;
    private JLabel estimationQueryComplexNumber;
    private JLabel estimationQueryEasySum;
    private JLabel estimationQueryMiddleSum;
    private JLabel estimationQueryComplexSum;
    private JLabel estimationInternEasyNumber;
    private JLabel estimationInternMiddleNumber;
    private JLabel estimationInternComplexNumber;
    private JLabel estimationInternEasySum;
    private JLabel estimationInternMiddleSum;
    private JLabel estimationInternComplexSum;
    private JLabel estimationExternEasyNumber;
    private JLabel estimationExternMiddleNumber;
    private JLabel estimationExternComplexNumber;
    private JLabel estimationExternEasySum;
    private JLabel estimationExternMiddleSum;
    private JLabel estimationExternComplexSum;
    private JLabel estimationE1Sum;
    private JPanel estimationFactors;
    private JLabel factor1;
    private JLabel factor2;
    private JLabel factor3;
    private JLabel factor4a;
    private JLabel factor4b;
    private JLabel factor4c;
    private JLabel factor4d;
    private JLabel factor5;
    private JLabel factor6;
    private JLabel factor7;
    private JSlider factor1Slider;
    private JSlider factor2Slider;
    private JSlider factor3Slider;
    private JSlider factor4aSlider;
    private JSlider factor4bSlider;
    private JSlider factor4cSlider;
    private JSlider factor4dSlider;
    private JSlider factor5Slider;
    private JSlider factor6Slider;
    private JSlider factor7Slider;
    private JLabel factorE2Sum;
    private JLabel factorE3;
    private JLabel functionPointResult;
    private JLabel jonesEstimationDurationLabel;
    private JLabel jonesEstimationPersonsLabel;
    private JLabel jonesEstimationPersonMonthsLabel;
    private JLabel correctionFactorLabel;
    private JLabel correctionCalculationLabel;
    private JLabel correctionSetpointLabel;
    private JSpinner correctionActualValueSpinner;
    private JButton newCorrectionFactorButton;
    private JButton refreshEstimationViewButton;
    private JPanel xmlPanel;
    private JButton exportXMLButton;
    private JButton importXMLButton;


    public View(Model model) {
        this.model = model;

        add(rootPanel);
        setTitle("SE III");
        setSize(700, 700);

        //Init
        model.getTargetDefinition().setTitle(targetDefinitionTitelLabel.getText());
        model.getProductUse().setTitle(productUseTitelLabel.getText());
        model.getProductEnvironment().setTitle(environmentTitelLabel.getText());
        //TODO Table Coloring | Here ScrollPane Background
        productFunctionTableScrollPane.setBackground(Color.DARK_GRAY);
        rootPanel.setBackground(Color.DARK_GRAY);
        tabbedPane.setBackground(Color.DARK_GRAY);
        projectInfoTab.setBackground(Color.DARK_GRAY);
        productFunctionsTab.setBackground(Color.DARK_GRAY);
        productDataTab.setBackground(Color.DARK_GRAY);
        estimationTab.setBackground(Color.DARK_GRAY);
        JSpinner.DefaultEditor editor_pFWCB = (JSpinner.DefaultEditor) productFunctionWeightingComboBox.getEditor();
        editor_pFWCB.getTextField().setBackground(Color.DARK_GRAY);
        editor_pFWCB.getTextField().setForeground(Color.LIGHT_GRAY);
        productFunctionCategoryComboBox.setEditable(true);
        BasicComboBoxEditor editor_pFCCB = (BasicComboBoxEditor) productFunctionCategoryComboBox.getEditor();
        editor_pFCCB.getEditorComponent().setBackground(Color.DARK_GRAY);
        editor_pFCCB.getEditorComponent().setForeground(Color.LIGHT_GRAY);
        JSpinner.DefaultEditor editor_pFDETS = (JSpinner.DefaultEditor) productFunctionDETSpinner.getEditor();
        editor_pFDETS.getTextField().setBackground(Color.DARK_GRAY);
        editor_pFDETS.getTextField().setForeground(Color.LIGHT_GRAY);
        JSpinner.DefaultEditor editor_pFFTRS = (JSpinner.DefaultEditor) productFunctionFTRSpinner.getEditor();
        editor_pFFTRS.getTextField().setBackground(Color.DARK_GRAY);
        editor_pFFTRS.getTextField().setForeground(Color.LIGHT_GRAY);
        productDataIDLabel.setBackground(Color.DARK_GRAY);
        productDataIDLabel.setForeground(Color.LIGHT_GRAY);
        productDataReferenceField.setBackground(Color.DARK_GRAY);
        productDataReferenceField.setForeground(Color.LIGHT_GRAY);
        productDataMemoryContentTitleLabel.setBackground(Color.DARK_GRAY);
        productDataMemoryContentTitleLabel.setForeground(Color.LIGHT_GRAY);
        productDataMemoryContentField.setBackground(Color.DARK_GRAY);
        productDataMemoryContentField.setForeground(Color.LIGHT_GRAY);
        productDataReferenceTitleLabel.setBackground(Color.DARK_GRAY);
        productDataReferenceTitleLabel.setForeground(Color.LIGHT_GRAY);
        saveProductDataButton.setBackground(Color.DARK_GRAY);
        saveProductDataButton.setForeground(Color.LIGHT_GRAY);
        deleteProductDataButton.setBackground(Color.DARK_GRAY);
        deleteProductDataButton.setForeground(Color.LIGHT_GRAY);
        newProductDataButton.setBackground(Color.DARK_GRAY);
        newProductDataButton.setForeground(Color.LIGHT_GRAY);
        productDataWeightingTitleLabel.setBackground(Color.DARK_GRAY);
        productDataWeightingTitleLabel.setForeground(Color.LIGHT_GRAY);
        productDataDETTitleLabel.setBackground(Color.DARK_GRAY);
        productDataDETTitleLabel.setForeground(Color.LIGHT_GRAY);
        productDataFTRTitleLabel.setBackground(Color.DARK_GRAY);
        productDataFTRTitleLabel.setForeground(Color.LIGHT_GRAY);
        productDataCategoryTitleLabel.setBackground(Color.DARK_GRAY);
        productDataCategoryTitleLabel.setForeground(Color.LIGHT_GRAY);
        JSpinner.DefaultEditor editor_pDWS = (JSpinner.DefaultEditor) productDataWeightingSpinner.getEditor();
        editor_pDWS.getTextField().setBackground(Color.DARK_GRAY);
        editor_pDWS.getTextField().setForeground(Color.LIGHT_GRAY);
        JSpinner.DefaultEditor editor_pDDETS = (JSpinner.DefaultEditor) productDataDETSpinner.getEditor();
        editor_pDDETS.getTextField().setBackground(Color.DARK_GRAY);
        editor_pDDETS.getTextField().setForeground(Color.LIGHT_GRAY);
        JSpinner.DefaultEditor editor_pDFTRS = (JSpinner.DefaultEditor) productDataFTRSpinner.getEditor();
        editor_pDFTRS.getTextField().setBackground(Color.DARK_GRAY);
        editor_pDFTRS.getTextField().setForeground(Color.LIGHT_GRAY);
        productDataCategoryComboBox.setEditable(true);
        BasicComboBoxEditor editor_pDCCB = (BasicComboBoxEditor) productDataCategoryComboBox.getEditor();
        editor_pDCCB.getEditorComponent().setBackground(Color.DARK_GRAY);
        editor_pDCCB.getEditorComponent().setForeground(Color.LIGHT_GRAY);
        productDataAttributeNameLabel.setBackground(Color.DARK_GRAY);
        productDataAttributeNameLabel.setForeground(Color.LIGHT_GRAY);
        newProductDataAttributeButton.setBackground(Color.DARK_GRAY);
        newProductDataAttributeButton.setForeground(Color.LIGHT_GRAY);
        deleteProductDataAttributeButton.setBackground(Color.DARK_GRAY);
        deleteProductDataAttributeButton.setForeground(Color.LIGHT_GRAY);
        saveProductDataAttributeButton.setBackground(Color.DARK_GRAY);
        saveProductDataAttributeButton.setForeground(Color.LIGHT_GRAY);
        productDataAttributeNameTitleLabel.setBackground(Color.DARK_GRAY);
        productDataAttributeNameTitleLabel.setForeground(Color.LIGHT_GRAY);
        productDataAttributeContentTitleLabel.setBackground(Color.DARK_GRAY);
        productDataAttributeContentTitleLabel.setForeground(Color.LIGHT_GRAY);
        productDataAttributeContentLabel.setBackground(Color.DARK_GRAY);
        productDataAttributeContentLabel.setForeground(Color.LIGHT_GRAY);
        changeProductDataIDButton.setBackground(Color.DARK_GRAY);
        changeProductDataIDButton.setForeground(Color.LIGHT_GRAY);
        refreshEstimationViewButton.setBackground(Color.DARK_GRAY);
        refreshEstimationViewButton.setForeground(Color.LIGHT_GRAY);
        estimationPane.setBackground(Color.DARK_GRAY);
        estimationPane.setForeground(Color.LIGHT_GRAY);
        e1Overview.setBackground(Color.DARK_GRAY);
        e1Overview.setForeground(Color.LIGHT_GRAY);
        JSpinner.DefaultEditor editor_cAVL = (JSpinner.DefaultEditor) correctionActualValueSpinner.getEditor();
        editor_cAVL.getTextField().setBackground(Color.DARK_GRAY);
        editor_cAVL.getTextField().setForeground(Color.LIGHT_GRAY);

        setProductFunctionMaskEnabled(false);
        setProductDataMaskEnabled(false);
        setProductDataAttributeMaskEnabled(false);

        //productFunctionCategoryComboBox
        productFunctionCategoryComboBox.addItem("Eingabe (EI)");
        productFunctionCategoryComboBox.addItem("Ausgabe (EO)");
        productFunctionCategoryComboBox.addItem("Abfrage (EQ)");

        //productDataCategoryComboBox
        productDataCategoryComboBox.addItem("Interne Datenbestände (ILF)");
        productDataCategoryComboBox.addItem("Externe Datenbestände (EIF)");

        //productFunctionTable
        //productFunctionTable: Selection but no editing of Cells
        JTextField productFunctionTableTextField = new JTextField();
        //TODO Table Coloring | Here Textfield and Function Table Background
        productFunctionTableTextField.setBackground(Color.DARK_GRAY);
        productFunctionTable.setBackground(Color.DARK_GRAY);
        productFunctionTable.setForeground(Color.WHITE);
        productFunctionTableScrollPane.setBackground(Color.DARK_GRAY);
        productFunctionTableScrollPane.setForeground(Color.DARK_GRAY);

        productFunctionTableTextField.setEditable(false);
        DefaultCellEditor editorPF = new DefaultCellEditor(productFunctionTableTextField);
        productFunctionTable.setDefaultEditor(Object.class, editorPF);

        //productFunctionTable: Get selected Cells
        productFunctionTable.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModelPF = productFunctionTable.getSelectionModel();
        cellSelectionModelPF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModelPF.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {

                String selectedProductFunction = getSelectedCellFromTable(productFunctionTable);

                //System.out.println("[View] Selected ProductFunction: " + selectedProductFunction);

                if (controller.getProductFunctionController().ckeckPFId(selectedProductFunction)) {
                    productFunctionIDLabel.setText(selectedProductFunction);
                    productFunctionFunctionField.setText(controller.getProductFunctionController().getPF(selectedProductFunction).getFunction());
                    productFunctionSourceField.setText(controller.getProductFunctionController().getPF(selectedProductFunction).getSource());
                    productFunctionProtagonistField.setText(controller.getProductFunctionController().getPF(selectedProductFunction).getProtagonist());
                    productFunctionReferenceField.setText(controller.getProductFunctionController().getPF(selectedProductFunction).getReference());
                    productFunctionDescriptionField.setText(controller.getProductFunctionController().getPF(selectedProductFunction).getDescription());
                    productFunctionWeightingComboBox.setValue(controller.getProductFunctionController().getPF(selectedProductFunction).getFunctionPointWeight());
                    productFunctionCategoryComboBox.setSelectedItem(controller.getProductFunctionController().getPF(selectedProductFunction).getFunctionPointCategory());
                    productFunctionFTRSpinner.setValue(controller.getProductFunctionController().getPF(selectedProductFunction).getFunctionPointFTR());
                    productFunctionDETSpinner.setValue(controller.getProductFunctionController().getPF(selectedProductFunction).getFunctionPointDET());

                    setProductFunctionMaskEnabled(true);
                }
            }
        });

        DefaultTableModel productFunctionTableModel = new DefaultTableModel();
        productFunctionTable.setModel(productFunctionTableModel);
        productFunctionTableModel.addColumn("Produktfunktionen");
        /*
        TODO Table Coloring | Here Column Background with Renderer
        DefaultTableCellRenderer rendar1 = new DefaultTableCellRenderer();
        rendar1.setBackground(Color.DARK_GRAY);
        productFunctionTable.getColumnModel().getColumn(0).setCellRenderer(rendar1);
         */
        productDataTable.setBackground(Color.DARK_GRAY);
        productDataTable.setForeground(Color.WHITE);
        productDataTableScrollPane.setBackground(Color.DARK_GRAY);
        productDataTableScrollPane.setForeground(Color.DARK_GRAY);
        productDataAttributeTable.setBackground(Color.DARK_GRAY);
        productDataAttributeTable.setForeground(Color.WHITE);
        productDataAttributeTableScrollPane.setBackground(Color.DARK_GRAY);
        productDataAttributeTableScrollPane.setForeground(Color.DARK_GRAY);

        //productDataAttributeTable
        //productDataAttributeTable: Selection but no editing of Cells
        JTextField productDataAttributeTableTextField = new JTextField();
        productDataAttributeTableTextField.setEditable(false);
        DefaultCellEditor editorPDAttribute = new DefaultCellEditor(productDataAttributeTableTextField);
        productDataAttributeTable.setDefaultEditor(Object.class, editorPDAttribute);

        //productDataAttributeTable: Get selected Cells
        productDataAttributeTable.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModelPDAttribute = productDataAttributeTable.getSelectionModel();
        cellSelectionModelPDAttribute.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModelPDAttribute.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {

                String selectedProductDataAttribute = getSelectedCellFromTable(productDataAttributeTable);

                //System.out.println("[View] Selected ProductDataAttribute: " + selectedProductDataAttribute);

                if (controller.getProductDataController().ckeckPDAttribute(productDataIDLabel.getText(), selectedProductDataAttribute)) {
                    productDataAttributeNameLabel.setText(selectedProductDataAttribute);
                    productDataAttributeContentLabel.setText(controller.getProductDataController().getPDAttribute(productDataIDLabel.getText(), selectedProductDataAttribute).getContent());
                    setProductDataAttributeMaskEnabled(true);
                }
            }
        });

        DefaultTableModel productDataAttributeTableModel = new DefaultTableModel();
        productDataAttributeTable.setModel(productDataAttributeTableModel);
        productDataAttributeTableModel.addColumn("Attribute");


        //productDataTable
        //productDataTable: Selection but no editing of Cells
        JTextField productDataTableTextField = new JTextField();
        productDataTableTextField.setEditable(false);
        DefaultCellEditor editorPD = new DefaultCellEditor(productDataTableTextField);
        productDataTable.setDefaultEditor(Object.class, editorPD);

        //productDataTable: Get selected Cells
        productDataTable.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModelPD = productDataTable.getSelectionModel();
        cellSelectionModelPD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModelPD.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {

                String selectedProductData = getSelectedCellFromTable(productDataTable);

                //System.out.println("[View] Selected ProductData: " + selectedProductData);

                if (controller.getProductDataController().ckeckPDId(selectedProductData)) {
                    productDataIDLabel.setText(selectedProductData);
                    productDataMemoryContentField.setText(controller.getProductDataController().getPD(selectedProductData).getMemoryContent());
                    productDataReferenceField.setText(controller.getProductDataController().getPD(selectedProductData).getReference());
                    productDataWeightingSpinner.setValue(controller.getProductDataController().getPD(selectedProductData).getFpWeight());
                    productDataFTRSpinner.setValue(controller.getProductDataController().getPD(selectedProductData).getFpFtr());
                    productDataDETSpinner.setValue(controller.getProductDataController().getPD(selectedProductData).getFpDet());
                    productDataCategoryComboBox.setSelectedItem(controller.getProductDataController().getPD(selectedProductData).getFpCategory());


                    setProductDataMaskEnabled(true);

                    clearProductDataAttributeView();
                    clearProductDataAttributeTable();

                    for (String attributeName : controller.getProductDataController().getAttributeNames(selectedProductData)) {
                        productDataAttributeTableModel.addRow(new Object[]{attributeName});
                    }
                }
            }
        });

        DefaultTableModel productDataTableModel = new DefaultTableModel();
        productDataTable.setModel(productDataTableModel);
        productDataTableModel.addColumn("Produktdaten");


        saveProjectInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.getProjectInfoController().save();

                    JOptionPane.showMessageDialog(null,
                            "Projektinfos wurden gespeichert!",
                            "Info",
                            JOptionPane.PLAIN_MESSAGE);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null,
                            "Speichern fehlgeschlagen:\n" + exc.getMessage(),
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        newProductFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productFunctionID = "";
                do {
                    productFunctionID = JOptionPane.showInputDialog(null, "ID der neuen Produktfunktion:",
                            "Neue Produktfunktion",
                            JOptionPane.PLAIN_MESSAGE);
                } while (productFunctionID.equals(""));

                controller.getProductFunctionController().createPF(productFunctionID);

                String fp_category = productFunctionCategoryComboBox.getItemAt(0);
                String ei = "Eingabe (EI)";
                String eo = "Ausgabe (EO)";
                String eq = "Abfrage (EQ)";

                String pf_category = productFunctionCategoryComboBox.getSelectedItem().toString();
                if (pf_category.equals(ei)) {
                    controller.getProductFunctionController().getPF(productFunctionID).setFpCategory(FunctionPointCategory.EI);
                } else if (pf_category.equals(eo)) {
                    controller.getProductFunctionController().getPF(productFunctionID).setFpCategory(FunctionPointCategory.EO);
                } else if (pf_category.equals(eq)) {
                    controller.getProductFunctionController().getPF(productFunctionID).setFpCategory(FunctionPointCategory.EQ);
                }

                productFunctionTableModel.addRow(new Object[]{productFunctionID});

                clearProductFunctionView();
                productFunctionIDLabel.setText(productFunctionID);
                setProductFunctionMaskEnabled(true);
            }
        });

        saveProductFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productFunctionIDLabel.getText().equals("-")) {
                    showNothingHereError("Produktfunktion");
                } else {
                    try {
                        controller.getProductFunctionController().savePF(productFunctionIDLabel.getText());

                        JOptionPane.showMessageDialog(null,
                                "Produktfunktion wurde gespeichert!",
                                "Info",
                                JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null,
                                "Speichern fehlgeschlagen:\n" + exc.getMessage(),
                                "Fehler",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        deleteProductFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productFunctionIDLabel.getText().equals("-")) {
                    showNothingHereError("Produktfunktion");
                } else {
                    try {

                        controller.getProductFunctionController().deletePF(productFunctionIDLabel.getText());

                        //Delete Table Entry
                        for (int i = 0; i <= productFunctionTable.getRowCount() - 1; i++) {
                            if (productFunctionTable.getValueAt(i, 0).equals(productFunctionIDLabel.getText())) {
                                productFunctionTableModel.removeRow(i);
                            }
                        }

                        clearProductFunctionView();
                        setProductFunctionMaskEnabled(false);

                        JOptionPane.showMessageDialog(null,
                                "Produktfunktion wurde gelöscht!",
                                "Info",
                                JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null,
                                "Löschen fehlgeschlagen:\n" + exc.getMessage(),
                                "Fehler",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        changeProductFunctionIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productFunctionIDLabel.getText().equals("-")) {
                    showNothingHereError("Produktfunktion");
                } else {
                    String productFunctionID = productFunctionIDLabel.getText();
                    String newProductFunctionID;
                    do {
                        newProductFunctionID = JOptionPane.showInputDialog(null, "Neue ID der Produktfunktion " + productFunctionID + ":",
                                "Produktfunktion umbenennen",
                                JOptionPane.PLAIN_MESSAGE);
                    } while (newProductFunctionID.equals(""));
                    System.out.println("Neue ID: '" + newProductFunctionID + "'");


                    controller.getProductFunctionController().renamePF(productFunctionID, newProductFunctionID);

                    for (int i = 0; i <= productFunctionTable.getRowCount() - 1; i++) {
                        if (productFunctionTable.getValueAt(i, 0).equals(productFunctionID)) {
                            productFunctionTableModel.setValueAt(newProductFunctionID, i, 0);
                        }
                    }

                    clearProductFunctionView();
                }
            }
        });

        newProductDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productDataID;
                do {
                    productDataID = JOptionPane.showInputDialog(null, "ID der neuen Produktdaten:",
                            "Neue Produktdaten",
                            JOptionPane.PLAIN_MESSAGE);
                } while (productDataID.equals(""));

                controller.getProductDataController().createPD(productDataID);

                String fp_category = productDataCategoryComboBox.getItemAt(0);
                String ilf = "Interne Datenbestände (ILF)";
                String eif = "Externe Datenbestände (EIF)";


                String pf_category = productFunctionCategoryComboBox.getSelectedItem().toString();
                if (pf_category.equals(ilf)) {
                    controller.getProductDataController().getPD(productDataID).setFpCategory(FunctionPointCategory.ILF);
                } else if (pf_category.equals(eif)) {
                    controller.getProductDataController().getPD(productDataID).setFpCategory(FunctionPointCategory.ILF);
                }

                productDataTableModel.addRow(new Object[]{productDataID});

                clearProductDataView();
                clearProductDataAttributeTable();
                productDataIDLabel.setText(productDataID);
                setProductDataMaskEnabled(true);
            }
        });

        deleteProductDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productDataIDLabel.getText().equals("-")) {
                    showNothingHereError("Produktdaten");
                } else {
                    try {
                        controller.getProductDataController().deletePD(productDataIDLabel.getText());

                        //Delete Table Entry
                        for (int i = 0; i <= productDataTable.getRowCount() - 1; i++) {
                            if (productDataTable.getValueAt(i, 0).equals(productDataIDLabel.getText())) {
                                productDataTableModel.removeRow(i);
                            }
                        }

                        clearProductDataView();
                        clearProductDataAttributeView();
                        clearProductDataAttributeTable();
                        setProductDataMaskEnabled(false);
                        setProductDataAttributeMaskEnabled(false);


                        JOptionPane.showMessageDialog(null,
                                "Produktdaten wurde gelöscht!",
                                "Info",
                                JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null,
                                "Löschen fehlgeschlagen:\n" + exc.getMessage(),
                                "Fehler",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        saveProductDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productDataIDLabel.getText().equals("-")) {
                    showNothingHereError("Produktdaten");
                } else {
                    try {
                        controller.getProductDataController().savePD(productDataIDLabel.getText());

                        JOptionPane.showMessageDialog(null,
                                "Produktdaten wurde gespeichert!",
                                "Info",
                                JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null,
                                "Speichern fehlgeschlagen:\n" + exc.getMessage(),
                                "Fehler",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        changeProductDataIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productDataIDLabel.getText().equals("-")) {
                    showNothingHereError("Produktdaten");
                } else {
                    String productDataID = productDataIDLabel.getText();
                    String newProductDataID;
                    do {
                        newProductDataID = JOptionPane.showInputDialog(null, "Neue ID der Produktdaten " + productDataID + ":",
                                "Produktdaten umbenennen",
                                JOptionPane.PLAIN_MESSAGE);
                    } while (newProductDataID.equals(""));
                    System.out.println("Neue ID: '" + newProductDataID + "'");


                    controller.getProductDataController().renamePD(productDataID, newProductDataID);

                    for (int i = 0; i <= productDataTable.getRowCount() - 1; i++) {
                        if (productDataTable.getValueAt(i, 0).equals(productDataID)) {
                            productDataTableModel.setValueAt(newProductDataID, i, 0);
                        }
                    }

                    clearProductDataView();
                }

            }
        });

        newProductDataAttributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productDataIDLabel.getText().equals("-")) {
                    showNothingHereError("Produktdaten");
                } else {
                    String productDataAttributeName;
                    do {
                        productDataAttributeName = JOptionPane.showInputDialog(null, "Name des neuen Attributes:",
                                "Neues Attribut",
                                JOptionPane.PLAIN_MESSAGE);
                    } while (productDataAttributeName.equals(""));

                    if (controller.getProductDataController().ckeckPDId(productDataIDLabel.getText())) {
                        controller.getProductDataController().createPDAttribute(productDataIDLabel.getText(), productDataAttributeName);
                        productDataAttributeTableModel.addRow(new Object[]{productDataAttributeName});

                        clearProductDataAttributeView();
                        productDataAttributeNameLabel.setText(productDataAttributeName);
                        setProductDataAttributeMaskEnabled(true);
                    }
                }
            }
        });

        deleteProductDataAttributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productDataAttributeNameLabel.getText().equals("-")) {
                    showNothingHereError("Attribut");
                } else {
                    try {

                        controller.getProductDataController().deletePDAttribut(productDataIDLabel.getText(), productDataAttributeNameLabel.getText());

                        //Delete Table Entry
                        for (int i = 0; i <= productDataAttributeTable.getRowCount() - 1; i++) {
                            if (productDataAttributeTable.getValueAt(i, 0).equals(productDataAttributeNameLabel.getText())) {
                                productDataAttributeTableModel.removeRow(i);
                            }
                        }

                        clearProductDataAttributeView();
                        setProductDataAttributeMaskEnabled(false);

                        JOptionPane.showMessageDialog(null,
                                "Attribut wurde gelöscht!",
                                "Info",
                                JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null,
                                "Löschen fehlgeschlagen:\n" + exc.getMessage(),
                                "Fehler",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        saveProductDataAttributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productDataAttributeNameLabel.getText().equals("-")) {
                    System.out.println("DEBUG1");
                    showNothingHereError("Attribut");
                } else {
                    try {
                        controller.getProductDataController().savePDAttribute(productDataIDLabel.getText(), productDataAttributeNameLabel.getText());

                        JOptionPane.showMessageDialog(null,
                                "Attribut wurde gespeichert!",
                                "Info",
                                JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null,
                                "Speichern fehlgeschlagen:\n" + exc.getMessage(),
                                "Fehler",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exportXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("XML-Dateien", "xml");
                    chooser.setFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                    Export_XML export = new Export_XML(controller);
                    export.exportData();
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null,
                            "XML.Export fehlgeschlagen:\n" + exc.getMessage(),
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        importXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("XML-Dateien", "xml");
                    chooser.setFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                    chooser.setMultiSelectionEnabled(false);

                    int returnVal = chooser.showOpenDialog(null);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        System.out.println("Ausgewählte Datei: " +
                                chooser.getSelectedFile().getName());

                        Import_XML importXml = new Import_XML(controller);
                        importXml.receiveImportData(chooser.getSelectedFile());

                        System.out.println("DBG :" + controller.model.getProductDataList().toString());
                        System.out.println("DBG :" + controller.model.getProductFunctionList().toString());
                        loadProjectInfo();
                        loadProductFunctions();
                        loadProductData();
                        loadEstimation();
                        System.out.println("GUI received Data...");
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null,
                            "XML.Import fehlgeschlagen:\n" + exc.getMessage(),
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        refreshEstimationViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FunctionPointEstimation fpe = model.getFunctionPointEstimation();
                controller.getEstimationController().calculateOverviewValues();

                //Input
                estimationInputEasyNumber.setText(Integer.toString(fpe.getInput().getEasy()));
                estimationInputMiddleNumber.setText(Integer.toString(fpe.getInput().getMiddle()));
                estimationInputComplexNumber.setText(Integer.toString(fpe.getInput().getComplex()));

                estimationInputEasySum.setText(Integer.toString(controller.getEstimationController().getInputEasySum()));
                estimationInputMiddleSum.setText(Integer.toString(controller.getEstimationController().getInputMiddleSum()));
                estimationInputComplexSum.setText(Integer.toString(controller.getEstimationController().getInputComplexSum()));


                //Output
                estimationOutputEasyNumber.setText(Integer.toString(fpe.getOutput().getEasy()));
                estimationOutputMiddleNumber.setText(Integer.toString(fpe.getOutput().getMiddle()));
                estimationOutputComplexNumber.setText(Integer.toString(fpe.getOutput().getComplex()));

                estimationOutputEasySum.setText(Integer.toString(controller.getEstimationController().getOutputEasySum()));
                estimationOutputMiddleSum.setText(Integer.toString(controller.getEstimationController().getOutputMiddleSum()));
                estimationOutputComplexSum.setText(Integer.toString(controller.getEstimationController().getOutputComplexSum()));


                //Query
                estimationQueryEasyNumber.setText(Integer.toString(fpe.getQuery().getEasy()));
                estimationQueryMiddleNumber.setText(Integer.toString(fpe.getQuery().getMiddle()));
                estimationQueryComplexNumber.setText(Integer.toString(fpe.getQuery().getComplex()));

                estimationQueryEasySum.setText(Integer.toString(controller.getEstimationController().getQueryEasySum()));
                estimationQueryMiddleSum.setText(Integer.toString(controller.getEstimationController().getQueryMiddleSum()));
                estimationQueryComplexSum.setText(Integer.toString(controller.getEstimationController().getQueryComplexSum()));


                //Intern Data
                estimationInternEasyNumber.setText(Integer.toString(fpe.getIntern().getEasy()));
                estimationInternMiddleNumber.setText(Integer.toString(fpe.getIntern().getMiddle()));
                estimationInternComplexNumber.setText(Integer.toString(fpe.getIntern().getComplex()));

                estimationInternEasySum.setText(Integer.toString(controller.getEstimationController().getInternEasySum()));
                estimationInternMiddleSum.setText(Integer.toString(controller.getEstimationController().getInternMiddleSum()));
                estimationInternComplexSum.setText(Integer.toString(controller.getEstimationController().getInternComplexSum()));


                //Extern Data
                estimationExternEasyNumber.setText(Integer.toString(fpe.getExtern().getEasy()));
                estimationExternMiddleNumber.setText(Integer.toString(fpe.getExtern().getMiddle()));
                estimationExternComplexNumber.setText(Integer.toString(fpe.getExtern().getComplex()));

                estimationExternEasySum.setText(Integer.toString(controller.getEstimationController().getExternEasySum()));
                estimationExternMiddleSum.setText(Integer.toString(controller.getEstimationController().getExternMiddleSum()));
                estimationExternComplexSum.setText(Integer.toString(controller.getEstimationController().getExternComplexSum()));

                //e1Sum
                estimationE1Sum.setText(Integer.toString(model.getFunctionPointEstimation().getE1Sum()));

                updateEstimationView();
            }
        });

        factor1Slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f1 = factor1Slider.getValue();
                factor1.setText(Integer.toString(f1));
                model.getFunctionPointEstimation().setFactor1(f1);

                updateEstimationView();
            }
        });

        factor2Slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f2 = factor2Slider.getValue();
                factor2.setText(Integer.toString(f2));
                model.getFunctionPointEstimation().setFactor2(f2);

                updateEstimationView();
            }
        });

        factor3Slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f3 = factor3Slider.getValue();
                factor3.setText(Integer.toString(f3));
                model.getFunctionPointEstimation().setFactor3(f3);

                updateEstimationView();
            }
        });

        factor4aSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f4a = factor4aSlider.getValue();
                factor4a.setText(Integer.toString(f4a));
                model.getFunctionPointEstimation().setFactor4a(f4a);

                updateEstimationView();
            }
        });

        factor4bSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f4b = factor4bSlider.getValue();
                factor4b.setText(Integer.toString(f4b));
                model.getFunctionPointEstimation().setFactor4b(f4b);

                updateEstimationView();
            }
        });

        factor4cSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f4c = factor4cSlider.getValue();
                factor4c.setText(Integer.toString(f4c));
                model.getFunctionPointEstimation().setFactor4c(f4c);

                updateEstimationView();
            }
        });

        factor4dSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f4d = factor4dSlider.getValue();
                factor4d.setText(Integer.toString(f4d));
                model.getFunctionPointEstimation().setFactor4d(f4d);

                updateEstimationView();
            }
        });

        factor5Slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f5 = factor5Slider.getValue();
                factor5.setText(Integer.toString(f5));
                model.getFunctionPointEstimation().setFactor5(f5);

                updateEstimationView();
            }
        });

        factor6Slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f6 = factor6Slider.getValue();
                factor6.setText(Integer.toString(f6));
                model.getFunctionPointEstimation().setFactor6(f6);

                updateEstimationView();
            }
        });

        factor7Slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int f7 = factor7Slider.getValue();
                factor7.setText(Integer.toString(f7));
                model.getFunctionPointEstimation().setFactor7(f7);

                updateEstimationView();
            }
        });


        newCorrectionFactorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.getEstimationController().calculateCorrectionValues(Integer.parseInt(correctionActualValueSpinner.getValue().toString()));
                    correctionFactorLabel.setText(df2.format(model.getFunctionPointEstimation().getCorrectionFactor()));
                    correctionCalculationLabel.setText(df2.format(model.getFunctionPointEstimation().getSetpoint()) + " * e ^ "
                            + df2.format(model.getFunctionPointEstimation().getCorrectionFactor()) + " = "
                            + Integer.parseInt(correctionActualValueSpinner.getValue().toString()));
                } catch (Exception exc) {
                    System.out.println("Error in Event newCorrectionFactorButton: " + e);
                }
            }
        });
    }

    private void updateEstimationView() {
        model.getFunctionPointEstimation().updateValues();

        factorE2Sum.setText(Integer.toString(model.getFunctionPointEstimation().getE2Sum()));
        factorE3.setText(Double.toString(model.getFunctionPointEstimation().getE3Sum()));
        functionPointResult.setText(Double.toString(model.getFunctionPointEstimation().getSetpoint()));

        jonesEstimationDurationLabel.setText(model.getFunctionPointEstimation().getSetpoint() + "^(0.4) = "
                + df2.format(model.getFunctionPointEstimation().getJonesDuration()));
        jonesEstimationPersonsLabel.setText(model.getFunctionPointEstimation().getSetpoint() + " / 150 -> "
                + model.getFunctionPointEstimation().getJonesDuration());
        jonesEstimationPersonMonthsLabel.setText(df2.format(model.getFunctionPointEstimation().getJonesDuration())
                + " * " + model.getFunctionPointEstimation().getJonesDuration() + " = "
                + df2.format(model.getFunctionPointEstimation().getJonesPersonMoths()));

        correctionSetpointLabel.setText(df2.format(model.getFunctionPointEstimation().getSetpoint()));
        correctionFactorLabel.setText(Double.toString(model.getFunctionPointEstimation().getCorrectionFactor()));
        correctionCalculationLabel.setText(df2.format(model.getFunctionPointEstimation().getSetpoint()) + " * e ^ "
                + df2.format(model.getFunctionPointEstimation().getCorrectionFactor()) + " = "
                + Integer.parseInt(correctionActualValueSpinner.getValue().toString()));
    }

    private void loadEstimation() {
        //TODO: Laden der Aufwandsabschätzung! Einfach gegebene Werte mit .setText() anzeigen lassen.
    }

    private void loadProductData() {
        DefaultTableModel productDataTableModel = (DefaultTableModel) productDataTable.getModel();
        while (productDataTableModel.getRowCount() > 0) {
            productDataTableModel.removeRow(0);
        }
        for (String productDataID : controller.getProductDataController().getProductDataIDs()) {
            productDataTableModel.addRow(new Object[]{productDataID});
        }
    }


    private void loadProductFunctions() {
        DefaultTableModel productFunctionTableModel = (DefaultTableModel) productFunctionTable.getModel();
        while (productFunctionTableModel.getRowCount() > 0) {
            productFunctionTableModel.removeRow(0);
        }
        for (String productFunctionID : controller.getProductFunctionController().getProductFunctionIDs()) {
            productFunctionTableModel.addRow(new Object[]{productFunctionID});
        }
    }


    private void loadProjectInfo() {
        targetDefinitionTextarea.setText(model.getTargetDefinition().getContent());
        productUseTextarea.setText(model.getProductUse().getContent());
        environmentTextarea.setText(model.getProductEnvironment().getContent());
    }

    private void setProductDataAttributeMaskEnabled(boolean bool) {
        deleteProductDataAttributeButton.setEnabled(bool);
        saveProductDataAttributeButton.setEnabled(bool);
        productDataAttributeContentLabel.setEnabled(bool);
    }

    private void setProductDataMaskEnabled(boolean bool) {
        productDataMemoryContentField.setEnabled(bool);
        productDataReferenceField.setEnabled(bool);
        productDataWeightingSpinner.setEnabled(bool);
        productDataDETSpinner.setEnabled(bool);
        productDataFTRSpinner.setEnabled(bool);
        newProductDataAttributeButton.setEnabled(bool);
        productDataCategoryComboBox.setEnabled(bool);
    }

    private void setProductFunctionMaskEnabled(Boolean bool) {
        productFunctionFunctionField.setEnabled(bool);
        productFunctionSourceField.setEnabled(bool);
        productFunctionReferenceField.setEnabled(bool);
        productFunctionProtagonistField.setEnabled(bool);
        productFunctionDescriptionField.setEnabled(bool);
        productFunctionWeightingComboBox.setEnabled(bool);
        productFunctionCategoryComboBox.setEnabled(bool);
        productFunctionDETSpinner.setEnabled(bool);
        productFunctionFTRSpinner.setEnabled(bool);
    }

    private void clearProductDataAttributeTable() {
        DefaultTableModel productDataAttributeTableModel = (DefaultTableModel) productDataAttributeTable.getModel();
        int rowCount = productDataAttributeTableModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            productDataAttributeTableModel.removeRow(i);
        }
    }

    private void showNothingHereError(String element) {
        JOptionPane.showMessageDialog(null,
                "Zuerst " + element + " erstellen!",
                "Fehler",
                JOptionPane.ERROR_MESSAGE);
    }

    private String getSelectedCellFromTable(JTable table) {
        int[] selectedRow = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();

        for (int i = 0; i < selectedRow.length; i++) {
            for (int j = 0; j < selectedColumns.length; j++) {
                return (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
            }
        }
        return null;
    }

    public void clearProductFunctionView() {
        productFunctionTable.clearSelection();

        productFunctionIDLabel.setText("-");
        productFunctionFunctionField.setText("");
        productFunctionSourceField.setText("");
        productFunctionProtagonistField.setText("");
        productFunctionReferenceField.setText("");
        productFunctionDescriptionField.setText("");
        productFunctionWeightingComboBox.setValue(0);
        productFunctionCategoryComboBox.setSelectedIndex(0);
        productFunctionFTRSpinner.setValue(0);
        productFunctionDETSpinner.setValue(0);
    }

    private void clearProductDataAttributeView() {
        productDataAttributeTable.clearSelection();

        productDataAttributeNameLabel.setText("-");
        productDataAttributeContentLabel.setText("");
    }

    public void clearProductDataView() {
        productDataTable.clearSelection();

        productDataIDLabel.setText("-");
        productDataReferenceField.setText("");
        productDataMemoryContentField.setText("");
        productDataFTRSpinner.setValue(0);
        productDataDETSpinner.setValue(0);
        productDataWeightingSpinner.setValue(0);
        productFunctionCategoryComboBox.setSelectedIndex(0);


        clearProductDataAttributeView();
    }

    public void setControl(Controller controller) {
        this.controller = controller;
    }

    public String getTargetDefinitionText() {
        return targetDefinitionTextarea.getText();
    }

    public String getProductUseText() {
        return productUseTextarea.getText();
    }

    public String getEnvironmentText() {
        return environmentTextarea.getText();
    }

    public String getProductFunctionFunction() {
        return productFunctionFunctionField.getText();
    }

    public String getProductFunctionSource() {
        return productFunctionSourceField.getText();
    }

    public String getProductFunctionReference() {
        return productFunctionReferenceField.getText();
    }

    public String getProductFunctionProtagonist() {
        return productFunctionProtagonistField.getText();
    }

    public String getProductFunctionDescription() {
        return productFunctionDescriptionField.getText();
    }

    public String getProductFunctionWeighting() {
        return productFunctionWeightingComboBox.getValue().toString();
    }

    public FunctionPointCategory getProductFunctionCategory() {
        String ei = "Eingabe (EI)";
        String eo = "Ausgabe (EO)";
        String eq = "Abfrage (EQ)";
        try {
            String pf_category = productFunctionCategoryComboBox.getSelectedItem().toString();
            if (pf_category.equals(ei)) {
                return FunctionPointCategory.EI;
            } else if (pf_category.equals(eo)) {
                return FunctionPointCategory.EO;
            } else if (pf_category.equals(eq)) {
                return FunctionPointCategory.EQ;
            }
        } catch (NullPointerException e) {
            System.out.println("Error in getProductFunctionCategory: " + e);
            return null;
        }
        return null;
    }

    public String getProductFunctionDET() {
        return productFunctionDETSpinner.getValue().toString();
    }

    public String getProductFunctionFTR() {
        return productFunctionFTRSpinner.getValue().toString();
    }

    public String getProductDataMemoryContent() {
        return productDataMemoryContentField.getText();
    }

    public String getProductDataRefernce() {
        return productDataReferenceField.getText();
    }

    public String getProductDataAttributeContent() {
        return productDataAttributeContentLabel.getText();
    }

    public String getProductDataWeighting() {
        return productDataWeightingSpinner.getValue().toString();
    }

    public String getProductDataDET() {
        return productDataDETSpinner.getValue().toString();
    }

    public String getProductDataFTR() {
        return productDataFTRSpinner.getValue().toString();
    }

    public FunctionPointCategory getProductDataCategory() {
        String ilf = "Interne Datenbestände (ILF)";
        String eif = "Externe Datenbestände (EIF)";
        try {
            String pf_category = productDataCategoryComboBox.getSelectedItem().toString();
            if (pf_category.equals(ilf)) {
                return FunctionPointCategory.ILF;
            } else if (pf_category.equals(eif)) {
                return FunctionPointCategory.EIF;
            }
        } catch (NullPointerException e) {
            System.out.println("Error in getProductDataCategory: " + e);
            return null;
        }
        return null;
    }
}
