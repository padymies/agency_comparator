package tdt.ui.notes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import tdt.services.ConfigStage;

public class CellListViewBase extends HBox {

    protected final Label ref;
    protected final Label txtDestinationName;
    protected final Label lbZone;
    protected final TextField txtCountry;
    protected final Label txtCity;
    protected final Label lbCP;
    protected final CheckBox chkAgency;
    protected final ComboBox comboAgency;
    protected final TextField txtBundles;
    protected final Button btnEdit;

    public CellListViewBase() {

        ref = new Label();
        txtDestinationName = new Label();
        lbZone = new Label();
        txtCountry = new TextField();
        txtCity = new Label();
        lbCP = new Label();
        chkAgency = new CheckBox();
        comboAgency = new ComboBox();
        txtBundles = new TextField();
        btnEdit = new Button();
        setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        setPrefHeight(35.0);
        setPrefWidth(1000.0);

        ref.setPrefHeight(25.0);
        ref.setPrefWidth(91.0);
        ref.setText("numero ref");
        ref.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        ref.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
        HBox.setMargin(ref, new Insets(0.0, 0.0, 0.0, 10.0));

        txtDestinationName.setPrefHeight(17.0);
        txtDestinationName.setPrefWidth(166.0);
        txtDestinationName.setText("Nombre Destino");
        txtDestinationName.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
        HBox.setMargin(txtDestinationName, new Insets(0.0, 0.0, 0.0, 10.0));

        lbZone.setId("lbZona");
        lbZone.setPrefHeight(17.0);
        lbZone.setPrefWidth(106.0);
        lbZone.setText("Zona");
        HBox.setMargin(lbZone, new Insets(0.0, 0.0, 0.0, 10.0));

        txtCountry.setPrefHeight(25.0);
        txtCountry.setPrefWidth(110.0);
        txtCountry.setPromptText("Pais");
        txtCountry.setText("txtPais");
        HBox.setMargin(txtCountry, new Insets(0.0, 0.0, 0.0, 10.0));

        txtCity.setLayoutX(143.0);
        txtCity.setLayoutY(19.0);
        txtCity.setPrefHeight(17.0);
        txtCity.setPrefWidth(136.0);
        txtCity.setText("Poblacion");
        txtCity.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
        HBox.setMargin(txtCity, new Insets(0.0, 0.0, 0.0, 10.0));

        lbCP.setId("lbCP");
        lbCP.setPrefHeight(17.0);
        lbCP.setPrefWidth(55.0);
        lbCP.setText("29003");
        HBox.setMargin(lbCP, new Insets(0.0, 0.0, 0.0, 10.0));

        chkAgency.setId("chkAgencia");
        chkAgency.setMnemonicParsing(false);
        HBox.setMargin(chkAgency, new Insets(0.0, 0.0, 0.0, 10.0));

        comboAgency.setDisable(true);
        comboAgency.setId("comboAgencia");
        comboAgency.setPrefWidth(150.0);
        comboAgency.setPromptText("Forzar Agencia");
        HBox.setMargin(comboAgency, new Insets(0.0, 0.0, 0.0, 2.0));

        txtBundles.setPrefHeight(25.0);
        txtBundles.setPrefWidth(59.0);
        txtBundles.setText("Bultos");
        HBox.setMargin(txtBundles, new Insets(0.0, 0.0, 0.0, 10.0));

        btnEdit.setAlignment(javafx.geometry.Pos.CENTER);
        btnEdit.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        btnEdit.setMnemonicParsing(false);
        btnEdit.setPrefHeight(26.0);
        btnEdit.setPrefWidth(63.0);
        btnEdit.setText("Editar");
        btnEdit.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        btnEdit.setFont(new Font(13.0));

        ConfigStage.setIcon(btnEdit, "edit.png", 14);

        HBox.setMargin(btnEdit, new Insets(0.0, 0.0, 0.0, 10.0));
        setOpaqueInsets(new Insets(0.0));
        setPadding(new Insets(0.0, 5.0, 0.0, 5.0));

        getChildren().add(ref);
        getChildren().add(txtDestinationName);
        getChildren().add(lbZone);
        getChildren().add(txtCountry);
        getChildren().add(txtCity);
        getChildren().add(lbCP);
        getChildren().add(chkAgency);
        getChildren().add(comboAgency);
        getChildren().add(txtBundles);
        getChildren().add(btnEdit);

    }
}
