package com.filelibrary.gabrielmello.file;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public interface ManageFile {
    
    public File getFile(Option option);
    
    public default File getChooseFile(TypeFile typeFile, Option option) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Files", typeFile.getExtension()));
        
        switch (option) {
            case EXPORT:
                return fc.showSaveDialog(new Stage());
            case IMPORT:
                return fc.showOpenDialog(new Stage());
            default:
                return null;
        }
        
    }
    
}
