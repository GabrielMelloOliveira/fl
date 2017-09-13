package com.filelibrary.gabrielmello.txt;

import com.filelibrary.gabrielmello.file.ManageFile;
import com.filelibrary.gabrielmello.file.Option;
import com.filelibrary.gabrielmello.file.TypeFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class ManageTXT implements ManageFile {

    public void toTXT(String text) {

        try (FileWriter fileWrite = new FileWriter(getFile(Option.EXPORT))) {

            PrintWriter saveFile = new PrintWriter(fileWrite);

            BufferedReader br = new BufferedReader(new StringReader(text));

            String text2;
            while ((text2 = br.readLine()) != null) {
                saveFile.println(text2);
            }

        } catch (IOException ex) {
        }

    }

    @Override
    public String toString() {
        String text = new String();

        try {

            FileReader fileReader = new FileReader(getFile(Option.IMPORT));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String string = bufferedReader.readLine();

            while (string != null) {
                text += string;
                string = bufferedReader.readLine();
            }

        } catch (IOException ex) {
        }

        return text;
    }

    @Override
    public File getFile(Option option) {
        return getChooseFile(TypeFile.TXT, option);
    }

}
