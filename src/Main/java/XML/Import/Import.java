/*
 * Main: This file is part of CASE-Tool
 *
 * With the CASE-Tool it is possible to create feasibility studies
 * and save them. The software is based on a student project.
 *
 * Description:  XML-XML.Import module
 */

package XML.Import;

import java.io.File;
import java.util.HashMap;

public abstract class Import {
    public String format;

    public Import(String format) {
        this.format = format;
    }

    public abstract HashMap<String, HashMap> importData(File file);
}
