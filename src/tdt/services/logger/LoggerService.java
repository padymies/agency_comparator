package tdt.services;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import java.util.logging.Logger;


public class LoggerService {

    private static final Logger logger = Logger.getLogger("");

    private static FileHandler handler;

    private final String fileName;


    public LoggerService(String fileName) {
         
        
        this.fileName = fileName;
    }

    public void writeLog(String level, String message, Exception ex) {
        try {

            String desktopPath = System.getProperty("user.home") + "\\Desktop";
            
            new File(desktopPath, "Log TDT Envios").mkdir();
            
            handler = new FileHandler(desktopPath + "\\Log TDT Envios\\" + fileName + ".log", 200000, 1, true);
            
            logger.addHandler(handler);
            
            LogFormatter formatter = new LogFormatter();

            handler.setFormatter(formatter);

            switch (level) {
                case "severe":
                    logger.log(Level.SEVERE, message, ex);
                    break;
                case "warning":
                    logger.log(Level.WARNING, message, ex);
                    break;
                case "info":
                    logger.log(Level.INFO, message, ex);
                    break;
                default:
                    logger.log(Level.INFO, message, ex);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            handler.close();

        }

    }
}
