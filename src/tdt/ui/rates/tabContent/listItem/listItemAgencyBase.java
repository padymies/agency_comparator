package tdt.ui.rates.tabContent.listItem;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public abstract class listItemAgencyBase extends AnchorPane {

    protected final HBox hBox;
    protected final Label textAgency;
    protected final TextField txtIncrement;
    protected final TextField txtDeliveryTime;
    protected final TextField txtMaxKilos;
    protected final Button btnEdit;
    protected final Button btnDelete;

    private int agencyId;

    private int zoneId;

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public HBox gethBox() {
        return hBox;
    }

    public Label getTextAgency() {
        return textAgency;
    }

    public TextField getTxtIncrement() {
        return txtIncrement;
    }

    public TextField getTxtMaxKilos() {
        return txtMaxKilos;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public TextField getTxtDeliveryTime() {
        return txtDeliveryTime;
    }

    public listItemAgencyBase(int agencyId, int zoneId) {

        this.agencyId = agencyId;
        this.zoneId = zoneId;

        hBox = new HBox();
        textAgency = new Label();
        txtIncrement = new TextField();
        txtDeliveryTime = new TextField();
        txtMaxKilos = new TextField();
        btnEdit = new Button();
        btnDelete = new Button();

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

        textAgency.setId("textAgencia");
        textAgency.setPrefHeight(17.0);
        textAgency.setPrefWidth(120.0);
        textAgency.setText("Agencia");
        HBox.setMargin(textAgency, new Insets(0.0, 0.0, 0.0, 25.0));

        txtIncrement.setId("txtIncremento");
        txtIncrement.setPrefHeight(25.0);
        txtIncrement.setPrefWidth(78.0);
        txtIncrement.setPromptText("Incremento");
        HBox.setMargin(txtIncrement, new Insets(0.0, 0.0, 0.0, 5.0));

        txtDeliveryTime.setId("txtIncremento");
        txtDeliveryTime.setLayoutX(186.0);
        txtDeliveryTime.setLayoutY(22.0);
        txtDeliveryTime.setPrefHeight(25.0);
        txtDeliveryTime.setPrefWidth(89.0);
        txtDeliveryTime.setPromptText("Plazo entrega");
        HBox.setMargin(txtDeliveryTime, new Insets(0.0, 0.0, 0.0, 5.0));

        txtMaxKilos.setId("txtMaxKilos");
        txtMaxKilos.setLayoutX(245.0);
        txtMaxKilos.setLayoutY(22.0);
        txtMaxKilos.setPrefHeight(25.0);
        txtMaxKilos.setPrefWidth(70.0);
        txtMaxKilos.setPromptText("Kilos Max");
        HBox.setMargin(txtMaxKilos, new Insets(0.0, 0.0, 0.0, 5.0));

        btnEdit.setId("btnEditar");
        btnEdit.setLayoutX(463.0);
        btnEdit.setLayoutY(22.0);
        btnEdit.setMnemonicParsing(false);
        btnEdit.setPrefHeight(25.0);
        btnEdit.setPrefWidth(60.0);
        btnEdit.setText("Editar");
        HBox.setMargin(btnEdit, new Insets(0.0, 0.0, 0.0, 20.0));

        btnDelete.setId("btnEliminar");
        btnDelete.setMnemonicParsing(false);
        btnDelete.setPrefHeight(25.0);
        btnDelete.setPrefWidth(60.0);
        btnDelete.setText("Eliminar");
        HBox.setMargin(btnDelete, new Insets(0.0, 0.0, 0.0, 10.0));

        hBox.getChildren().add(textAgency);
        hBox.getChildren().add(txtDeliveryTime);
        hBox.getChildren().add(txtIncrement);
        hBox.getChildren().add(txtMaxKilos);
        hBox.getChildren().add(btnEdit);
        hBox.getChildren().add(btnDelete);
        getChildren().add(hBox);

        btnDelete.setOnAction(event -> {
            deleteItemAgency(event);
        });
        btnEdit.setOnAction(event -> {
            editItemAgency(event);
        });

        txtIncrement.setDisable(true);

        txtDeliveryTime.setDisable(true);

        txtMaxKilos.setDisable(true);
    }

    protected abstract void deleteItemAgency(ActionEvent actionEvent);

    protected abstract void editItemAgency(ActionEvent actionEvent);

}
