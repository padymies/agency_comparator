
package tdt.services;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import tdt.model.Note;


public class ValidatorService {

    public static boolean doubleValidate(TextField control) {

        boolean result = true;

        if (!control.getText().matches("[+-]?\\d*\\.?\\d+") || control.getText().isEmpty()) {

            control.getStyleClass().add("invalid");

            result = false;

        } else {

            control.getStyleClass().remove("invalid");

        }
        return result;
    }

    public static boolean integerValidate(TextField control) {

        boolean result = true;

        if (!control.getText().matches("^\\d+$") || control.getText().isEmpty()) {

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

    public static boolean noteValidator(Note note) {
        boolean result = (note.getZone() != null || note.getBEST_AGENCY() != null) && !note.getDestinationCity().isEmpty() && !note.getCountry().isEmpty() && !note.getDestinationName().isEmpty()
                && !note.getRef().isEmpty() && !note.getDestinationAddress().isEmpty() && !note.getEmail().isEmpty() && !note.getDestinationPostalCode().isEmpty()
                && !note.getDestinationPhone().isEmpty() && !note.getWeight().isEmpty();
        return result;
    }
}
