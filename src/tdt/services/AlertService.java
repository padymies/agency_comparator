
package tdt.services;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class AlertService extends Alert {

    public AlertService(AlertType alertType, String title, String message, String detail) {

        super(alertType);

        setTitle(title);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        
        stage.getIcons().add(0, new Image("file:resources/img/tdt.jpg"));

        setHeaderText(null);

        Text text = new Text("\n"+ message);
       
        Text textDetail = new Text("\n\n" + detail);

        text.setStyle("-fx-font-size: 14px; -fx-fill: black;");
       
        textDetail.setStyle("-fx-font-size: 12px; -fx-fill: black;");

        TextFlow flow = new TextFlow(text, textDetail);

        getDialogPane().setContent(flow);
        
    }
    
    

}
