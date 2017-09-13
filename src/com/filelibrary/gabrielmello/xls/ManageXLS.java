package com.filelibrary.gabrielmello.xls;

import com.filelibrary.gabrielmello.file.ManageFile;
import com.filelibrary.gabrielmello.file.Option;
import com.filelibrary.gabrielmello.file.TypeFile;
import com.filelibrary.gabrielmello.util.CreateObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class ManageXLS implements ManageFile {

    public <G> void toXLS(List<G> list) {

        try (FileOutputStream out = new FileOutputStream(getFile(Option.EXPORT))) {

            Workbook wb = new HSSFWorkbook();

            Sheet s = wb.createSheet();

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
                        Logger.getLogger(ManageXLS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            wb.write(out);

            out.close();

        } catch (IOException ex) {
            Logger.getLogger(ManageXLS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Object toObject(Class klass) {

        List<Object> list = new ArrayList();

        try (FileInputStream out = new FileInputStream(getFile(Option.IMPORT))) {

            HSSFWorkbook wb = new HSSFWorkbook(out);

            HSSFSheet sheet = wb.getSheetAt(0);

            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

            for (int ix = 1; ix < sheet.getPhysicalNumberOfRows(); ix++) {

                String[] infos = new String[sheet.getRow(ix).getPhysicalNumberOfCells()];

                for (int x = 0; x < sheet.getRow(ix).getPhysicalNumberOfCells(); x++) {

                    String info = null;

                    switch (evaluator.evaluateInCell(sheet.getRow(ix).getCell(x)).getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            info = String.valueOf(sheet.getRow(ix).getCell(x).getNumericCellValue());
                            System.out.print(sheet.getRow(ix).getCell(x).getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            info = sheet.getRow(ix).getCell(x).getStringCellValue();
                            System.out.print(sheet.getRow(ix).getCell(x).getStringCellValue() + "\t\t");
                    }

                    infos[x] = info;

                }

                try {

                    list.add(CreateObject.getInstance(klass, infos));

                    System.out.println("");
                    
                } catch (Exception ex) {
                    Logger.getLogger(ManageXLS.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(ManageXLS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public File getFile(Option option) {
        return getChooseFile(TypeFile.XLS, option);
    }

}
