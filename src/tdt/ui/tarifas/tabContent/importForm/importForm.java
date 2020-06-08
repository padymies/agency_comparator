package tdt.ui.tarifas.tabContent.importForm;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import tdt.db.dao.IZonaDao;
import tdt.db.daoImpl.ZonaImpl;

public abstract class importForm extends AnchorPane {

    private IZonaDao zonaDao;

    protected final HBox hBox;
    protected final ComboBox importComboZona;
    protected final ComboBox importComboAgencia;
    protected final Button importBtn;

    public HBox gethBox() {
        return hBox;
    }

    public ComboBox getImportComboZona() {
        return importComboZona;
    }

    public ComboBox getImportComboAgencia() {
        return importComboAgencia;
    }

    public Button getImportBtn() {
        return importBtn;
    }

    public importForm() {

        hBox = new HBox();
        importComboZona = new ComboBox();
        importComboAgencia = new ComboBox();
        importBtn = new Button();

        setId("AnchorPane");
        setPrefHeight(42.0);
        setPrefWidth(579.0);

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        hBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hBox.setId("hBox");
        hBox.setLayoutY(-50.0);
        hBox.setPrefHeight(42.0);
        hBox.setPrefWidth(600.0);

        importComboZona.setId("cmbImportZona");
        importComboZona.setPrefHeight(25.0);
        importComboZona.setPrefWidth(188.0);
        importComboZona.setPromptText("Seleccione Zona");
        HBox.setMargin(importComboZona, new Insets(0.0, 0.0, 0.0, 20.0));

        importComboAgencia.setId("cmbImportAgencia");
        importComboAgencia.setPrefHeight(25.0);
        importComboAgencia.setPrefWidth(217.0);
        importComboAgencia.setPromptText("Seleccione Agencia");
        HBox.setMargin(importComboAgencia, new Insets(0.0, 0.0, 0.0, 10.0));

        importBtn.setId("btnImport");
        importBtn.setMnemonicParsing(false);
        importBtn.setText("Importar Tarifa");
        HBox.setMargin(importBtn, new Insets(0.0, 0.0, 0.0, 30.0));

        hBox.getChildren().add(importComboZona);
        hBox.getChildren().add(importComboAgencia);
        hBox.getChildren().add(importBtn);
        getChildren().add(hBox);

        importComboZona.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                zonaChange(oldValue, newValue);
            }
        }
        );
        importComboAgencia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                agenciaChange(oldValue, newValue);
            }
        }
        );

        importBtn.setOnAction(event -> {
            importar(event);
        });

        importBtn.setDisable(true);
        importComboAgencia.setDisable(true);
        zonaDao = new ZonaImpl();

        ObservableList<String> listaZonas = zonaDao.obtenerNombresZonas();

        importComboZona.setItems(listaZonas);

    }

    protected abstract void importar(ActionEvent actionEvent);

    protected abstract void zonaChange(Object oldValue, Object newValue);

    protected abstract void agenciaChange(Object oldValue, Object newValue);

}
