/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.services;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Usuario
 */
public class ValidatorService {

    public static boolean doubleValidate(TextField control) {
        
        boolean result = true;
        
        if (!control.getText().matches("[+-]?\\d*\\.?\\d+")) {
            
            control.getStyleClass().add("invalid");
            
            result = false;
        
        } else {
        
            control.getStyleClass().remove("invalid");
            
        }
        return result;
    }
    
    public static boolean integerValidate(TextField control) {
        
        boolean result = true;
        
        if (!control.getText().matches("^\\d+$")) {
            
            control.getStyleClass().add("invalid");
            
            result = false;
        
        } else {
        
            control.getStyleClass().remove("invalid");
            
        }
        return result;
    }
    
    public static boolean comboBoxValidate(ComboBox control) {
        
        boolean result = true;
        
        if (control.getSelectionModel().isEmpty()) {
            result = false;
            control.getStyleClass().add("invalid");
        } else {
             control.getStyleClass().remove("invalid");
        }
        return result;
    }
}