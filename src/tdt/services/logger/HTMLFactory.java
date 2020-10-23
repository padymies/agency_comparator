
package tdt.services.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;


public class HTMLFactory {

    private static final String scriptRoll
            = "<script>"
            + "function load() {"
            + "setTimeout(function(){ "
            + "var scrollingElement = (document.scrollingElement || document.body);"
            + "scrollingElement.scrollTop = scrollingElement.scrollHeight;"
            + " }, 2000); }"
            + "window.onload = load;"
            + "</script>";
    
    private static final String htmlHead = "<!DOCTYPE html><html style='background-color:#ededed'><head><title>Log</title> "
            + HTMLFactory.scriptRoll + " </head><body "
            + "style='padding: 1rem!important;font-size:1.4em'>";

    private static final String htmlTail = "</body></html>";

    public static String getHtmlHead() {
        return htmlHead;
    }

    public static String getScriptRoll() {
        return scriptRoll;
    }

    public static String getHtmlTail() {
        return htmlTail;
    }

    public static String getLevel(Level level) {
        return HTMLFactory.formatLevel(level);
    }

    public static String getDate(long millis) {
        String logDate = HTMLFactory.formatDate(millis);
        return "<span style='font-weight: bold;min-width: 120px; margin-right:15px'> " + logDate + " </span>";

    }

    public static String getCardBody() {
        return "<div style=" + styleCard() + ">";
    }

    private static String formatLevel(Level level) {
        if (level.intValue() == Level.SEVERE.intValue()) {
            return "<span style= 'font-weight: bold;color: red; margin-right:15px'> [ERROR] ";
        } else if (level.intValue() == Level.WARNING.intValue()) {
            return "<span style= 'color: #e8b44c;font-weight: bold'> ";
        } else {
            return " ";
        }
    }

    public static String getException(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String exDetails = sw.toString();
        return "<p style='color: red'>" + exDetails + "<p/>";
    }

    private static String formatDate(long millis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm:ss");

        Date date = new Date(millis);
        return "\n[" + dateFormat.format(date) + "]  ";
    }

    private static String styleCard() {
        return "'background-color:white;width: fit-content;box-shadow: -1px -1px 6px 0px rgba(0,0,0,0.46);"
                + "position: relative;background-clip: border-box;border: 1px solid rgba(0,0,0,.125);"
                + "-webkit-box-flex: 1;-ms-flex: 1 1 auto;flex: 1 1 auto;padding: 1.25rem;"
                + "border-radius: .25rem!important;display: flex!important;flex-direction: row!important;"
                + "padding: .4rem!important;margin-left: 3rem!important;margin-right: 3rem!important;"
                + "margin-top: .5rem!important;-webkit-box-orient: horizontal!important;-webkit-box-direction: normal!important"
                + "background-color: #f8f9fa!important;'";
    }
}
