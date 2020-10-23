package tdt.services.logger;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import tdt.model.LogModel;
import tdt.services.AlertExceptionService;

public class LoggerService {

    private static final Logger logger = Logger.getLogger("");

    private static Handler handler;

    private final String fileName;

    public LoggerService(String fileName) {

        this.fileName = fileName;
    }

    public void writeResultLog(ArrayList<LogModel> logs) {
        try {

            String desktopPath = System.getProperty("user.home") + "\\Desktop";

            new File(desktopPath, "Log TDT Envios").mkdir();

            handler = new FileHandler(desktopPath + "\\Log TDT Envios\\" + fileName + "_%u.html", 1000000, 1, true);

            LogFormatter formatter = new LogFormatter();
            handler.setFormatter(formatter);
            handler.setEncoding("UTF-8");

            logger.addHandler(handler);

            try {
                for (LogModel log : logs) {

                    logger.log(log.getLevel(), log.getMessage());
                    handler.flush();

                }
            } catch (Exception e) {
                AlertExceptionService alert = new AlertExceptionService("Error de escritura en archivo", "Ha habido un error al escribir el archivo de log", e);
                alert.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            handler.close();

        }

    }
}
