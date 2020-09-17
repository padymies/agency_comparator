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
import tdt.db.daoImpl.FileVariableImpl;
import tdt.model.FileVariable;
import tdt.db.dao.IFileVariableDao;

public class MapFileController implements Initializable {

    @FXML
    private TableView<FileVariable> mapFileTable;

    @FXML
    private TableColumn<FileVariable, String> key;

    @FXML
    private TableColumn<FileVariable, Integer> start;

    @FXML
    private TableColumn<FileVariable, Integer> end;

    private IFileVariableDao variableDao;

    private ObservableList<FileVariable> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        key.setCellValueFactory(new PropertyValueFactory<>("key"));

        start.setCellValueFactory(new PropertyValueFactory<>("start"));

        end.setCellValueFactory(new PropertyValueFactory<>("end"));

        variableDao = new FileVariableImpl();

        list = variableDao.observableFileVariable();

        mapFileTable.setItems(list);

        mapFileTable.setEditable(true);

        start.setEditable(true);

        start.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        end.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    @FXML
    private void saveStart(TableColumn.CellEditEvent<FileVariable, Integer> event) {

        FileVariable newValue = event.getRowValue();

        newValue.setStart(event.getNewValue());

        variableDao.updateFileVariable(newValue);
    }

    @FXML
    private void saveEnd(TableColumn.CellEditEvent<FileVariable, Integer> event) {

        FileVariable newValue = event.getRowValue();

        newValue.setEnd(event.getNewValue());

        variableDao.updateFileVariable(newValue);
    }

    @FXML
    private void iniStartEdit(TableColumn.CellEditEvent<FileVariable, Integer> event) {

    }
}
