package tdt.ui.mapFile;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import tdt.db.dao.IVariableArchivoDao;
import tdt.db.daoImpl.VariableArchivoImpl;
import tdt.model.VariableArchivo;

public class MapFileController implements Initializable {

    @FXML
    private TableView<VariableArchivo> mapFileTable;

    @FXML
    private TableColumn<VariableArchivo, String> key;

    @FXML
    private TableColumn<VariableArchivo, Integer> start;

    @FXML
    private TableColumn<VariableArchivo, Integer> end;

    private IVariableArchivoDao variableDao;

    private ObservableList<VariableArchivo> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        key.setCellValueFactory(new PropertyValueFactory<>("key"));

        start.setCellValueFactory(new PropertyValueFactory<>("start"));

        end.setCellValueFactory(new PropertyValueFactory<>("end"));

        variableDao = new VariableArchivoImpl();

        list = variableDao.ObservableVariableArchivo();

        mapFileTable.setItems(list);

        mapFileTable.setEditable(true);

        start.setEditable(true);

        start.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        end.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    @FXML
    private void saveStart(TableColumn.CellEditEvent<VariableArchivo, Integer> event) {

        VariableArchivo newValue = event.getRowValue();

        newValue.setStart(event.getNewValue());

        variableDao.actualizarVariableArchivo(newValue);
    }

    @FXML
    private void saveEnd(TableColumn.CellEditEvent<VariableArchivo, Integer> event) {

        VariableArchivo newValue = event.getRowValue();

        newValue.setEnd(event.getNewValue());

        variableDao.actualizarVariableArchivo(newValue);
    }

    @FXML
    private void iniStartEdit(TableColumn.CellEditEvent<VariableArchivo, Integer> event) {

    }
}
