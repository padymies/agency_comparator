/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.ui.salidaComparacion;

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

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class SalidaController implements Initializable {

    @FXML
    private Accordion acordeon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void loadData(Map<String, List<Note>> data) {
        for (String key : data.keySet()) {

            List<Note> list = data.get(key);
            
            ObservableList<Note> albaranes = FXCollections.observableArrayList();
            
            albaranes.addAll(list);

            paneBase pane = new paneBase(key, albaranes);

            acordeon.getPanes().add(pane);
        }
    }

}
