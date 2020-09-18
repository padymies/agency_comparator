package tdt.ui.rates.tabContent.importForm;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import tdt.db.dao.IZoneDao;
import tdt.db.daoImpl.ZoneImpl;

public abstract class importForm extends AnchorPane {

    private IZoneDao zoneDao;

    protected final HBox hBox;
    protected final ComboBox importComboZone;
    protected final ComboBox importComboAgency;
    protected final Button importBtn;

    public HBox gethBox() {
        return hBox;
    }

    public ComboBox getImportComboZone() {
        return importComboZone;
    }

    public ComboBox getImportComboAgency() {
        return importComboAgency;
    }

    public Button getImportBtn() {
        return importBtn;
    }

    public importForm() {

        hBox = new HBox();
        importComboZone = new ComboBox();
        importComboAgency = new ComboBox();
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

        importComboZone.setId("cmbImportZona");
        importComboZone.setPrefHeight(25.0);
        importComboZone.setPrefWidth(188.0);
        importComboZone.setPromptText("Seleccione Zona");
        HBox.setMargin(importComboZone, new Insets(0.0, 0.0, 0.0, 20.0));

        importComboAgency.setId("cmbImportAgencia");
        importComboAgency.setPrefHeight(25.0);
        importComboAgency.setPrefWidth(217.0);
        importComboAgency.setPromptText("Seleccione Agencia");
        HBox.setMargin(importComboAgency, new Insets(0.0, 0.0, 0.0, 10.0));

        importBtn.setId("btnImport");
        importBtn.setMnemonicParsing(false);
        importBtn.setText("Importar Tarifa");
        HBox.setMargin(importBtn, new Insets(0.0, 0.0, 0.0, 30.0));

        hBox.getChildren().add(importComboZone);
        hBox.getChildren().add(importComboAgency);
        hBox.getChildren().add(importBtn);
        getChildren().add(hBox);

        importComboZone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                zoneChange(oldValue, newValue);
            }
        }
        );
        importComboAgency.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                agencyChange(oldValue, newValue);
            }
        }
        );

        importBtn.setOnAction(event -> {
            doImport(event);
        });

        importBtn.setDisable(true);
        importComboAgency.setDisable(true);
        zoneDao = new ZoneImpl();

        ObservableList<String> listaZonas = zoneDao.getZoneNames();

        importComboZone.setItems(listaZonas);

    }

    protected abstract void doImport(ActionEvent actionEvent);

    protected abstract void zoneChange(Object oldValue, Object newValue);

    protected abstract void agencyChange(Object oldValue, Object newValue);

}
