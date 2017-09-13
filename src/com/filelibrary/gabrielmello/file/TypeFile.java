package com.filelibrary.gabrielmello.file;

import com.filelibrary.gabrielmello.csv.ManageCSV;
import com.filelibrary.gabrielmello.doc.ManageDOC;
import com.filelibrary.gabrielmello.json.ManageJSON;
import com.filelibrary.gabrielmello.pdf.ManagePDF;
import com.filelibrary.gabrielmello.txt.ManageTXT;
import com.filelibrary.gabrielmello.xls.ManageXLS;
import com.filelibrary.gabrielmello.xlsx.ManageXLSX;
import com.filelibrary.gabrielmello.xml.ManageXML;
import com.filelibrary.gabrielmello.xps.ManageXPS;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public enum TypeFile {
    XML(new ManageXML(), "*.xml"), XLS(new ManageXLS(), "*.xls"), XLSX(new ManageXLSX(), "*.xlsx"), 
    CSV(new ManageCSV(), "*.csv"), XPS(new ManageXPS(), "*.xps"), DOC(new ManageDOC(), "*.doc"), 
    PDF(new ManagePDF(), "*.pdf"), JSON(new ManageJSON(), "*.json"), TXT(new ManageTXT(), "*.txt");

    private final Object typeFile;
    private final String extension;
    
    TypeFile(ManageFile file, String extension) {
        this.typeFile = file;
        this.extension = extension;
    }

    public Object getTypeFile() {
        return typeFile;
    }
    
    public String getExtension() {
        return extension;
    }

}
