package com.filelibrary.gabrielmello.csv;

import com.filelibrary.gabrielmello.file.ManageFile;
import com.filelibrary.gabrielmello.file.Option;
import com.filelibrary.gabrielmello.file.TypeFile;
import com.filelibrary.gabrielmello.util.CreateObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class ManageCSV implements ManageFile {

    public <G> void toCSV(List<G> list) {

        try (FileWriter fileWriter = new FileWriter(getFile(Option.EXPORT))) {

            PrintWriter saveFile = new PrintWriter(fileWriter);

            String line = "";

            Class klass = list.get(0).getClass();

            Field[] fields = klass.getDeclaredFields();

            for (Field column : fields) {
                line += column.getName() + ";";
            }

            String finalLine = line.substring(0, line.length() - 1);
            System.out.println(finalLine);

            saveFile.println(finalLine);

            line = "";

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    try {
                        line += fields[j].get(list.get(i)).toString() + ";";
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        Logger.getLogger(ManageCSV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println(line.substring(0, line.length() - 1));
                saveFile.println(line.substring(0, line.length() - 1));
                line = "";
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException ex) {
        }

    }

    public Object toObject(Class klass) {

        List<Object> list = new ArrayList();

        try (BufferedReader StrR = new BufferedReader(new FileReader(getFile(Option.IMPORT)))) {

            String Str;
            String[] TableLine;

            int i = 0;

            Field[] fields = klass.getDeclaredFields();

            while ((Str = StrR.readLine()) != null) {

                TableLine = Str.split(";");

                if (i != 0) {

                    try {
                        
                        list.add(CreateObject.getInstance(klass, TableLine));
                          
                    } catch (Exception ex) {
                        Logger.getLogger(ManageCSV.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                    i++;

                }

            }

            StrR.close();

        } catch (IOException e) {
        }

        return list;

    }
    
    @Override
    public File getFile(Option option) {
        return getChooseFile(TypeFile.CSV, option);
    }

}
