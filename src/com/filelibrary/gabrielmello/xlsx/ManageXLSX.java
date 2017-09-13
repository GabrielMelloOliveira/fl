package com.filelibrary.gabrielmello.xlsx;

import com.filelibrary.gabrielmello.file.ManageFile;
import com.filelibrary.gabrielmello.file.Option;
import com.filelibrary.gabrielmello.file.TypeFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class ManageXLSX implements ManageFile {

    public <G> void toXLSX(List<G> list) {

        try (FileOutputStream out = new FileOutputStream(getFile(Option.EXPORT))) {

            Workbook wb = new XSSFWorkbook();

            Sheet s = wb.getSheetAt(0);

            Row r;

            Cell c;

            r = s.createRow(0);

            Class klass = list.get(0).getClass();

            Field[] fields = klass.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                c = r.createCell(i);
                c.setCellValue(fields[i].getName());
            }

            for (int i = 0; i < list.size(); i++) {
                r = s.createRow(i + 1);
                klass = list.get(i).getClass();
                fields = klass.getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    c = r.createCell(j);
                    fields[j].setAccessible(true);
                    try {
                        c.setCellValue(fields[j].get(list.get(i)).toString());
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(ManageXLSX.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            wb.write(out);

            out.close();

        } catch (IOException ex) {
            Logger.getLogger(ManageXLSX.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Object toObject() {

        try {

            FileOutputStream out = new FileOutputStream(getFile(Option.IMPORT));
            
            Workbook wb = new HSSFWorkbook();

            Sheet s = wb.createSheet();

            CellReference cellReference = new CellReference("A1");
            Row row = s.getRow(cellReference.getRow());
            Cell cell = row.getCell(cellReference.getCol());
            System.out.println(cell.getStringCellValue());

            for (Row rowFor : s) {
                for (Cell cellFor : rowFor) {
                    System.out.print(cellFor.getStringCellValue());
                }
            }

            out.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ManageXLSX.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public File getFile(Option option) {
        return getChooseFile(TypeFile.XLSX, option);
    }

}
