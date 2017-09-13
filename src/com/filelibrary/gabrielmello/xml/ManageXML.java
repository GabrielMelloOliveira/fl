package com.filelibrary.gabrielmello.xml;

import com.filelibrary.gabrielmello.file.ManageFile;
import com.filelibrary.gabrielmello.file.Option;
import com.filelibrary.gabrielmello.file.TypeFile;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class ManageXML implements ManageFile {

    public <G> String toXML(List<G> object) {
        XStream xstream = new XStream();
        xstream.alias(object.getClass().getSimpleName(), object.getClass());
        return xstream.toXML(object);
    }

    public <G> String toXML(G object) {
        XStream xstream = new XStream();
        xstream.alias(object.getClass().getSimpleName(), object.getClass());
        return xstream.toXML(object);
    }

    public <G> void toXMLandSave(G object) {

        try (FileWriter fileWriter = new FileWriter(getFile(Option.EXPORT))) {

            PrintWriter saveFile = new PrintWriter(fileWriter);

            XStream xstream = new XStream();

            xstream.alias(object.getClass().getSimpleName(), object.getClass());
            saveFile.println(xstream.toXML(object));

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException ex) {
        }

    }
    
    public <G> void toXMLandSave(List<G> object) {

        try (FileWriter fileWriter = new FileWriter(getFile(Option.EXPORT))) {

            PrintWriter saveFile = new PrintWriter(fileWriter);

            XStream xstream = new XStream();

            xstream.alias(object.getClass().getSimpleName(), object.getClass());
            saveFile.println(xstream.toXML(object));

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException ex) {
        }

    }
    
    public void saveXML(String xml) {
        
        try (FileWriter fileWriter = new FileWriter(getFile(Option.EXPORT))) {

            PrintWriter saveFile = new PrintWriter(fileWriter);

            saveFile.println(xml);

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException ex) {
        }
        
    }

    public Object toObject() {
        
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
        
        XStream xstream = new XStream();
        return xstream.fromXML(text);
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
        return getChooseFile(TypeFile.XML, option);
    }

}
