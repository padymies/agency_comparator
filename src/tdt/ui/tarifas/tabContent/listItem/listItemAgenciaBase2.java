package tdt.ui.tarifas.tabContent.listItem;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public abstract class listItemAgenciaBase2 extends AnchorPane {

    protected final HBox hBox;
    protected final Label textAgencia;
    protected final TextField txtIncremento;
    protected final TextField txtPlazoEntrega;
    protected final TextField txtMaxKilos;
    protected final Button btnEditar;
    protected final Button btnEliminar;

    public listItemAgenciaBase2() {

        hBox = new HBox();
        textAgencia = new Label();
        txtIncremento = new TextField();
        txtPlazoEntrega = new TextField();
        txtMaxKilos = new TextField();
        btnEditar = new Button();
        btnEliminar = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(48.0);
        setPrefWidth(750.0);

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        hBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hBox.setId("hBox");
        hBox.setLayoutX(99.0);
        hBox.setLayoutY(77.0);
        hBox.setPrefHeight(48.0);
        hBox.setPrefWidth(700.0);

        textAgencia.setId("textAgencia");
        textAgencia.setPrefHeight(17.0);
        textAgencia.setPrefWidth(146.0);
        textAgencia.setText("Agencia");
        HBox.setMargin(textAgencia, new Insets(0.0, 0.0, 0.0, 25.0));

        txtIncremento.setEditable(false);
        txtIncremento.setId("txtIncremento");
        txtIncremento.setPrefHeight(25.0);
        txtIncremento.setPrefWidth(78.0);
        txtIncremento.setPromptText("Incremento");
        HBox.setMargin(txtIncremento, new Insets(0.0, 0.0, 0.0, 5.0));

        txtPlazoEntrega.setEditable(false);
        txtPlazoEntrega.setId("txtIncremento");
        txtPlazoEntrega.setLayoutX(186.0);
        txtPlazoEntrega.setLayoutY(22.0);
        txtPlazoEntrega.setPrefHeight(25.0);
        txtPlazoEntrega.setPrefWidth(89.0);
        txtPlazoEntrega.setPromptText("Plazo entrega");
        HBox.setMargin(txtPlazoEntrega, new Insets(0.0, 0.0, 0.0, 5.0));

        txtMaxKilos.setEditable(false);
        txtMaxKilos.setId("txtMaxKilos");
        txtMaxKilos.setLayoutX(245.0);
        txtMaxKilos.setLayoutY(22.0);
        txtMaxKilos.setPrefHeight(25.0);
        txtMaxKilos.setPrefWidth(97.0);
        txtMaxKilos.setPromptText("Maximo Kilos");
        HBox.setMargin(txtMaxKilos, new Insets(0.0, 0.0, 0.0, 5.0));

        btnEditar.setId("btnEditar");
        btnEditar.setLayoutX(463.0);
        btnEditar.setLayoutY(22.0);
        btnEditar.setMnemonicParsing(false);
        btnEditar.setPrefHeight(25.0);
        btnEditar.setPrefWidth(79.0);
        btnEditar.setText("Editar");
        HBox.setMargin(btnEditar, new Insets(0.0, 0.0, 0.0, 20.0));

        btnEliminar.setId("btnEliminar");
        btnEliminar.setMnemonicParsing(false);
        btnEliminar.setPrefHeight(25.0);
        btnEliminar.setPrefWidth(84.0);
        btnEliminar.setText("Eliminar");
        HBox.setMargin(btnEliminar, new Insets(0.0, 0.0, 0.0, 10.0));

        hBox.getChildren().add(textAgencia);
        hBox.getChildren().add(txtIncremento);
        hBox.getChildren().add(txtPlazoEntrega);
        hBox.getChildren().add(txtMaxKilos);
        hBox.getChildren().add(btnEditar);
        hBox.getChildren().add(btnEliminar);
        getChildren().add(hBox);

    }
}
