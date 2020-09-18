package tdt.ui.comparator_output;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class listItemBase extends HBox {

    public  Label label;
    public Label ref;
    public Label label0;
    public Label nom;
    public Label label1;
    public Label pais;
    public Label label2;
    public Label pobl;
    public Label label3;
    public Label cp;

    public listItemBase() {

        label = new Label();
        ref = new Label();
        label0 = new Label();
        nom = new Label();
        label1 = new Label();
        pais = new Label();
        label2 = new Label();
        pobl = new Label();
        label3 = new Label();
        cp = new Label();

        setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(30.0);
        setPrefWidth(850.0);

        label.setText("Ref:");
        HBox.setMargin(label, new Insets(0.0, 0.0, 0.0, 10.0));
        label.setFont(new Font("System Bold", 12.0));

        ref.setPrefHeight(17.0);
        ref.setPrefWidth(88.0);
        ref.setText("A2345");
        HBox.setMargin(ref, new Insets(0.0, 0.0, 0.0, 5.0));

        label0.setText("Nombre:");
        HBox.setMargin(label0, new Insets(0.0, 0.0, 0.0, 10.0));
        label0.setFont(new Font("System Bold", 12.0));

        nom.setPrefHeight(17.0);
        nom.setPrefWidth(170.0);
        nom.setText("Pedro Jimenez");
        HBox.setMargin(nom, new Insets(0.0, 0.0, 0.0, 5.0));

        label1.setText("Pais:");
        HBox.setMargin(label1, new Insets(0.0, 0.0, 0.0, 10.0));
        label1.setFont(new Font("System Bold", 12.0));

        pais.setPrefHeight(17.0);
        pais.setPrefWidth(76.0);
        pais.setText("España");
        HBox.setMargin(pais, new Insets(0.0, 0.0, 0.0, 5.0));

        label2.setText("Poblacion:");
        HBox.setMargin(label2, new Insets(0.0, 0.0, 0.0, 10.0));
        label2.setFont(new Font("System Bold", 12.0));

        pobl.setPrefHeight(17.0);
        pobl.setPrefWidth(150.0);
        pobl.setText("Malaga");
        HBox.setMargin(pobl, new Insets(0.0, 0.0, 0.0, 5.0));

        label3.setText("C.P:");
        HBox.setMargin(label3, new Insets(0.0, 0.0, 0.0, 10.0));
        label3.setFont(new Font("System Bold", 12.0));

        cp.setPrefHeight(17.0);
        cp.setPrefWidth(58.0);
        cp.setText("29010");

        getChildren().add(label);
        getChildren().add(ref);
        getChildren().add(label0);
        getChildren().add(nom);
        getChildren().add(label1);
        getChildren().add(pais);
        getChildren().add(label2);
        getChildren().add(pobl);
        getChildren().add(label3);
        getChildren().add(cp);

    }
}
