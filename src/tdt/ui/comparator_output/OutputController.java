
package tdt.ui.comparator_output;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import tdt.model.Note;

public class OutputController implements Initializable {

    @FXML
    private Accordion accordion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void loadData(Map<String, List<Note>> data) {
        for (String key : data.keySet()) {

            List<Note> list = data.get(key);
            
            ObservableList<Note> notes = FXCollections.observableArrayList();
            
            notes.addAll(list);

            paneBase pane = new paneBase(key, notes);

            accordion.getPanes().add(pane);
        }
    }

}
