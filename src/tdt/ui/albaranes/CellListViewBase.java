package tdt.ui.albaranes;

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
    protected final Label txtNombreDestino;
    protected final Label lbZona;
    protected final TextField txtPais;
    protected final Label txtPoblacion;
    protected final Label lbCP;
    protected final CheckBox chkAgencia;
    protected final ComboBox comboAgencia;
    protected final TextField txtBultos;
    protected final Button btnEditar;

    public CellListViewBase() {

        ref = new Label();
        txtNombreDestino = new Label();
        lbZona = new Label();
        txtPais = new TextField();
        txtPoblacion = new Label();
        lbCP = new Label();
        chkAgencia = new CheckBox();
        comboAgencia = new ComboBox();
        txtBultos = new TextField();
        btnEditar = new Button();
        setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        setPrefHeight(35.0);
        setPrefWidth(1000.0);

        ref.setPrefHeight(25.0);
        ref.setPrefWidth(91.0);
        ref.setText("numero ref");
        ref.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        ref.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
        HBox.setMargin(ref, new Insets(0.0, 0.0, 0.0, 10.0));

        txtNombreDestino.setPrefHeight(17.0);
        txtNombreDestino.setPrefWidth(166.0);
        txtNombreDestino.setText("Nombre Destino");
        txtNombreDestino.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
        HBox.setMargin(txtNombreDestino, new Insets(0.0, 0.0, 0.0, 10.0));

        lbZona.setId("lbZona");
        lbZona.setPrefHeight(17.0);
        lbZona.setPrefWidth(106.0);
        lbZona.setText("Zona");
        HBox.setMargin(lbZona, new Insets(0.0, 0.0, 0.0, 10.0));

        txtPais.setPrefHeight(25.0);
        txtPais.setPrefWidth(110.0);
        txtPais.setPromptText("Pais");
        txtPais.setText("txtPais");
        HBox.setMargin(txtPais, new Insets(0.0, 0.0, 0.0, 10.0));

        txtPoblacion.setLayoutX(143.0);
        txtPoblacion.setLayoutY(19.0);
        txtPoblacion.setPrefHeight(17.0);
        txtPoblacion.setPrefWidth(136.0);
        txtPoblacion.setText("Poblacion");
        txtPoblacion.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
        HBox.setMargin(txtPoblacion, new Insets(0.0, 0.0, 0.0, 10.0));

        lbCP.setId("lbCP");
        lbCP.setPrefHeight(17.0);
        lbCP.setPrefWidth(55.0);
        lbCP.setText("29003");
        HBox.setMargin(lbCP, new Insets(0.0, 0.0, 0.0, 10.0));

        chkAgencia.setId("chkAgencia");
        chkAgencia.setMnemonicParsing(false);
        HBox.setMargin(chkAgencia, new Insets(0.0, 0.0, 0.0, 10.0));

        comboAgencia.setDisable(true);
        comboAgencia.setId("comboAgencia");
        comboAgencia.setPrefWidth(150.0);
        comboAgencia.setPromptText("Forzar Agencia");
        HBox.setMargin(comboAgencia, new Insets(0.0, 0.0, 0.0, 2.0));

        txtBultos.setPrefHeight(25.0);
        txtBultos.setPrefWidth(59.0);
        txtBultos.setText("Bultos");
        HBox.setMargin(txtBultos, new Insets(0.0, 0.0, 0.0, 10.0));

        btnEditar.setAlignment(javafx.geometry.Pos.CENTER);
        btnEditar.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        btnEditar.setMnemonicParsing(false);
        btnEditar.setPrefHeight(26.0);
        btnEditar.setPrefWidth(63.0);
        btnEditar.setText("Editar");
        btnEditar.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        btnEditar.setFont(new Font(13.0));

        ConfigStage.setIcon(btnEditar, "edit.png", 14);

        HBox.setMargin(btnEditar, new Insets(0.0, 0.0, 0.0, 10.0));
        setOpaqueInsets(new Insets(0.0));
        setPadding(new Insets(0.0, 5.0, 0.0, 5.0));

        getChildren().add(ref);
        getChildren().add(txtNombreDestino);
        getChildren().add(lbZona);
        getChildren().add(txtPais);
        getChildren().add(txtPoblacion);
        getChildren().add(lbCP);
        getChildren().add(chkAgencia);
        getChildren().add(comboAgencia);
        getChildren().add(txtBultos);
        getChildren().add(btnEditar);

    }
}
