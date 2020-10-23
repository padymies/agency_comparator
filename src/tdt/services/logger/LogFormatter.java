package tdt.services.logger;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

    public LogFormatter() {
        super();
    }

    @Override
    public synchronized String format(LogRecord rec) {
        StringBuilder build = new StringBuilder(1000);
        
        build.append(HTMLFactory.getCardBody());

        build.append(HTMLFactory.getDate(rec.getMillis()));

        build.append(HTMLFactory.getLevel(rec.getLevel()));
        
        build.append(rec.getMessage());

        build.append("</span></div>");

        return build.toString();
    }

    @Override
    public String getHead(Handler h) {

        return HTMLFactory.getHtmlHead(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTail(Handler h) {
        return HTMLFactory.getHtmlTail(); //To change body of generated methods, choose Tools | Templates.
    }

}
