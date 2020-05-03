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
    
    private static Text t;

    public static void writeLog(String text, Severity severity) {
       
        t = new Text(text + "\n");
        
        System.out.println(t.getStyle());
        
        log.getChildren().add(generateStyle(severity));

    }

    public MyLogger(TextFlow log) {
        MyLogger.log = log;
    }

    private static Text generateStyle(Severity severity) {
        
        switch (severity) {
        
            case SUCCESS:
            
                t.setFill(Color.GREENYELLOW);
                
                t.setFont(Font.font("System", 14));
                
                break;
            
            case INFO:
            
                t.setFill(Color.ALICEBLUE);
                
                t.setFont(Font.font("System", 14));
                
                break;
            
            case WARNING:
            
                t.setFill(Color.YELLOW);
                
                t.setFont(Font.font("System", 14));
                
                break;
            
            default:
            
                t.setFill(Color.valueOf("#f5aebb"));
                
                t.setFont(Font.font("System", 14));
                
                break;
        }
        return t;
    }
}
