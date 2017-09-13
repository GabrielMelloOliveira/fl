package com.filelibrary.gabrielmello.json;

import com.filelibrary.gabrielmello.file.ManageFile;
import com.filelibrary.gabrielmello.file.Option;
import com.filelibrary.gabrielmello.file.TypeFile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class ManageJSON implements ManageFile {

    public <G> String toJSON(G obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }

    public <G> void toJSONandSave(G obj) {
        try (FileWriter writer = new FileWriter(getFile(Option.EXPORT))) {
            Gson gson = new Gson();
            String json = gson.toJson(obj);
            writer.write(json);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManageJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public <G> Collection<G> toObject(Class klass) {

        Object obj = null;

        Collection<G> ints2 = null;
        
        try {
            Gson gson = new Gson();

            BufferedReader br = new BufferedReader(new FileReader("file.json"));

            Type collectionType = new TypeToken<Collection<G>>(){}.getType();
            ints2 = gson.fromJson(br, collectionType);
            
            obj = gson.fromJson(br, klass.getClass());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManageJSON.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ints2;

    }

    @Override
    public File getFile(Option option) {
        return getChooseFile(TypeFile.JSON, option);
    }

}
