package tdt.ui.salidaComparacion;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import tdt.model.Albaran;

public class paneBase extends TitledPane {

    protected final AnchorPane anchorPane;
    protected ListView<Albaran> list;

    public paneBase(String text, ObservableList<Albaran> data) {

        setStyle("-fx-font-size: 14");
        anchorPane = new AnchorPane();
        list = new ListView();
        list.setCellFactory((ListView<Albaran> param) -> new ItemCell());
        list.setItems(data);
        setAnimated(false);
        setPrefHeight(150.0);
        setPrefWidth(850.0);
        setText(text);

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(150.0);
        anchorPane.setPrefWidth(200.0);

        AnchorPane.setBottomAnchor(list, 0.0);
        AnchorPane.setLeftAnchor(list, 0.0);
        AnchorPane.setRightAnchor(list, 0.0);
        AnchorPane.setTopAnchor(list, 0.0);
        list.setPrefHeight(150.0);
        list.setPrefWidth(850.0);
      
        setContent(anchorPane);
        anchorPane.getChildren().add(list);

        expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//              
            }
        });

    }

    public class ItemCell extends ListCell<Albaran> {

        @Override
        public void updateItem(Albaran albaran, boolean empty) {

            super.updateItem(albaran, empty);

            if (albaran != null) {
                listItemBase cell = new listItemBase();
                cell.ref.setText(albaran.getRef());
                cell.nom.setText(albaran.getNombreDestino());
                cell.pais.setText(albaran.getPais());
                cell.pobl.setText(albaran.getPoblaDestino());
                cell.cp.setText(albaran.getPostalDestino());

                setGraphic(cell);
            } else {
                setGraphic(null);

            }
        }
    }
}
