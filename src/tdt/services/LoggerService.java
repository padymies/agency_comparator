package tdt.services;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerService {

    private static final Logger logger = Logger.getLogger("log");

    private static FileHandler handler;

    private final String fileName;

    public LoggerService(String fileName) {
        this.fileName = fileName;

    }

    public void writeLog(String level, String message, Exception ex) {
        try {

            handler = new FileHandler("resources/logs/" + fileName + ".log", 200000, 1, true);
            logger.addHandler(handler);
            SimpleFormatter formatter = new SimpleFormatter();
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
                    logger.log(Level.CONFIG, message, ex);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            handler.close();

        }

    }
}
