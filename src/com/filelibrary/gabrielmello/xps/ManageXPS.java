package com.filelibrary.gabrielmello.xps;

import com.filelibrary.gabrielmello.file.ManageFile;
import com.filelibrary.gabrielmello.file.Option;
import com.filelibrary.gabrielmello.file.TypeFile;
import com.filelibrary.gabrielmello.pdf.ManagePDF;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class ManageXPS implements ManageFile {

    public <G> void generateXPSWithTable(List<G> list) {
        
        try {
            
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getFile(Option.EXPORT)));

            document.open();

            Class klass = list.get(0).getClass();

            Field[] fields = klass.getDeclaredFields();

            PdfPTable table = new PdfPTable(fields.length);
            
            for (Field column : fields) {
                table.addCell(column.getName());
            }
            
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    try {
                        table.addCell(fields[j].get(list.get(i)).toString());
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(ManagePDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            document.add(table);

            document.close();

        } catch (Exception ex) {
        }
        
    }

    @Override
    public File getFile(Option option) {
        return getChooseFile(TypeFile.XPS, option);
    }
    
}
