/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  XML-XML.Export module
 */

package XML.Export;

public abstract class Export {
    private final String format;

    public Export(String format) {
        this.format = format;
    }

    public abstract void exportData();

}
