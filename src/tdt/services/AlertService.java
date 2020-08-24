
package tdt.services;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class AlertService extends Alert {

    public AlertService(AlertType alertType, String title, String message, String detalle) {

        super(alertType);

        setTitle(title);

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        
        stage.getIcons().add(0, new Image("file:resources/img/tdt.jpg"));

        setHeaderText(null);

        Text text = new Text("\n"+ message);
       
        Text text2 = new Text("\n\n" + detalle);

        text.setStyle("-fx-font-size: 14px; -fx-fill: black;");
       
        text2.setStyle("-fx-font-size: 12px; -fx-fill: black;");

        TextFlow flow = new TextFlow(text, text2);

        getDialogPane().setContent(flow);
        
    }
    
    

}
