/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.main.albaranes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import tdt.main.albaranes.form.AlbaranFormController;
import tdt.model.Albaran;
import tdt.services.ConfigStage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class AlbaranesController implements Initializable {

    @FXML
    private ListView<Albaran> listView;
    @FXML
    private Button btnComparar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void trannsferLista(ObservableList<Albaran> albaranes) {
        if (albaranes.size() > 0) {

            listView.setCellFactory(new Callback<ListView<Albaran>, ListCell<Albaran>>() {

                @Override
                public ListCell<Albaran> call(ListView<Albaran> param) {
                    return new AlbaranCell();
                }
            });

            listView.setItems(albaranes);

        }

    }

    @FXML
    private void iniciarComparacion(ActionEvent event) {
        System.out.println(listView.getItems());
    }

    public class AlbaranCell extends ListCell<Albaran> {

        @Override
        public void updateItem(Albaran albaran, boolean empty) {
            super.updateItem(albaran, empty);
            if (albaran != null) {

                CellListViewBase cell = new CellListViewBase();

                cell.ref.setText(albaran.getRef());

                cell.cliente.setText(albaran.getCliente());

                cell.editButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        showAlbaranForm(albaran);
                    }
                });

                setGraphic(cell);
            }
        }

    }

    private void showAlbaranForm(Albaran albaran) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form/albaranForm.fxml"));

            Parent albaranRoot = (Parent) fxmlLoader.load();

            Stage stage = new Stage();

            ConfigStage.configStage(stage, "Albaran: " + albaran.getRef() , Modality.APPLICATION_MODAL);

            AlbaranFormController albaranFormController = fxmlLoader.getController();

            albaranFormController.transferAlbaran(albaran);

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(new Scene(albaranRoot));

            stage.show();

        } catch (IOException e) {
        }
    }

    public class CellListViewBase extends HBox {

        protected final Label label;
        protected final Label ref;
        protected final Label label0;
        protected final Label cliente;
        protected final Button editButton;

        public CellListViewBase() {

            label = new Label();
            ref = new Label();
            label0 = new Label();
            cliente = new Label();
            editButton = new Button();

            setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            setPrefHeight(35.0);
            setPrefWidth(550.0);

            label.setAlignment(javafx.geometry.Pos.CENTER);
            label.setPrefHeight(35.0);
            label.setPrefWidth(100.0);
            label.setText("Referencia nº");
            label.setFont(new Font("System Bold", 14.0));

            ref.setPrefHeight(25.0);
            ref.setPrefWidth(150.0);
            ref.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            HBox.setMargin(ref, new Insets(0.0, 5.0, 0.0, 5.0));

            label0.setAlignment(javafx.geometry.Pos.CENTER);
            label0.setPrefHeight(20.0);
            label0.setPrefWidth(53.0);
            label0.setText("Cliente");
            label0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            label0.setFont(new Font("System Bold", 14.0));

            cliente.setPrefHeight(25.0);
            cliente.setPrefWidth(150.0);
            cliente.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);

            editButton.setAlignment(javafx.geometry.Pos.CENTER);
            editButton.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
            editButton.setMnemonicParsing(false);
            editButton.setPrefHeight(26.0);
            editButton.setPrefWidth(63.0);
            editButton.setText("Editar");
            editButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            editButton.setFont(new Font(13.0));

            getChildren().add(label);
            getChildren().add(ref);
            getChildren().add(label0);
            getChildren().add(cliente);
            getChildren().add(editButton);

        }
    }
}
