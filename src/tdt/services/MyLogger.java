package tdt.services;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MyLogger {

    public static enum Severity {
        SUCCESS,
        INFO,
        WARNING,
        ERROR
    }

    private static TextFlow log;
    
    private static Text text;

    public static void writeLog(String message, Severity severity) {
       
        text = new Text(message + "\n");
        
        System.out.println(text.getStyle());
        
        log.getChildren().add(generateStyle(severity));

    }

    public MyLogger(TextFlow log) {
        MyLogger.log = log;
    }

    private static Text generateStyle(Severity severity) {
        
        switch (severity) {
        
            case SUCCESS:
            
                text.setFill(Color.GREENYELLOW);
                
                text.setFont(Font.font("System", 14));
                
                break;
            
            case INFO:
            
                text.setFill(Color.ALICEBLUE);
                
                text.setFont(Font.font("System", 14));
                
                break;
            
            case WARNING:
            
                text.setFill(Color.YELLOW);
                
                text.setFont(Font.font("System", 14));
                
                break;
            
            default:
            
                text.setFill(Color.valueOf("#f5aebb"));
                
                text.setFont(Font.font("System", 14));
                
                break;
        }
        return text;
    }
}
