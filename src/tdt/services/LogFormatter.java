/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 *
 * @author padym
 */
public class LogFormatter extends Formatter {

    @Override
    public String format(LogRecord rec) {
        StringBuilder build = new StringBuilder(1000);

        String logDate = formatDate(rec.getMillis());
        build.append( logDate );
        
        String logLevel = formatLevel(rec.getLevel());
        build.append(logLevel);
        build.append(rec.getMessage());
        
        return build.toString();
    }

    private String formatDate(long millis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm:ss");

        Date date = new Date(millis);
        return "\n[" + dateFormat.format(date) + "] ";
    }
    
    private String formatLevel(Level level) {
            if (level.intValue() == Level.SEVERE.intValue()) {
            return "\uD83C\uDD67\uD83C\uDD67 ERROR \uD83C\uDD67\uD83C\uDD67 ";
        } else if (level.intValue() == Level.WARNING.intValue()) {
            return "\uD83D\uDF93 Aviso: ";
        } else {
            return "\uD83D\uDDF9 ";
        }
    } 
}
