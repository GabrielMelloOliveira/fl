package com.filelibrary.gabrielmello.validation;

import java.text.ParseException;
import javafx.scene.control.TextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class TextFieldFormatter {

    private final MaskFormatter maskFormatter;
    private TextField textField;
    private String validCharacters;
    private String mask;

    public TextFieldFormatter() {
        maskFormatter = new MaskFormatter();
    }

    public void formatter(TextField textField, String validCharacters, String mask) {
        try {
            maskFormatter.setMask(mask);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

        maskFormatter.setValidCharacters(validCharacters);
        maskFormatter.setValueContainsLiteralCharacters(false);
        String text = textField.getText().replaceAll("[\\W]", "");
        
        boolean repeat = true;
        while (repeat) {

            char lastCharacter;

            if (text.equals("")) {
                break;
            } else {
                lastCharacter = text.charAt(text.length() - 1);
            }

            try {
                text = maskFormatter.valueToString(text);
                repeat = false;
            } catch (ParseException ex) {
                text = text.replace(lastCharacter + "", "");
                repeat = true;
            }

        }

        textField.setText(text);

        if (!text.equals("")) {
            for (int i = 0; textField.getText().charAt(i) != ' ' && i < textField.getLength() - 1; i++) {
                textField.forward();
            }
        }
    }

    public void formatter(){
        formatter(this.textField, this.validCharacters, this.mask);
    }

    /**
     * @return the textField
     */
    public TextField getTextField() {
        return textField;
    }

    /**
     * @param textField the textField to set
     */
    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    /**
     * @return the validCharacters
     */
    public String getValidCharacters() {
        return validCharacters;
    }

    /**
     * @param validCharacters the validCharacters to set
     */
    public void setValidCharacters(String validCharacters) {
        this.validCharacters = validCharacters;
    }
    
    public String getMask() {
        return mask;
    }

    /**
     * @param mask the mask to set
     */
    public void setMask(String mask) {
        this.mask = mask;
    }
}
