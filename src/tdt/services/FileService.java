package tdt.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tdt.db.dao.IFileVariableDao;
import tdt.db.dao.IsoDao;
import tdt.db.daoImpl.FileVariableImpl;
import tdt.db.daoImpl.IsoImpl;
import tdt.model.FileVariable;
import tdt.model.Note;

public class FileService {

    private static File file;

    private static IFileVariableDao variableDao;

    public static ArrayList<String> extractRegisters(File file) {

        FileService.file = file;

        BufferedReader br = null;

        InputStreamReader fr = null;

        FileInputStream is = null;

        ArrayList<String> registerList = new ArrayList();

        try {

            is = new FileInputStream(file);

            fr = new InputStreamReader(is, "ISO-8859-1");
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                if (sCurrentLine.length() > 0) {

                    registerList.add(sCurrentLine);
                }

            }
        } catch (IOException e) {
            AlertExceptionService alert = new AlertExceptionService("Lectura de archivo", "No se ha podido leer el archivo " + file.getName(), e);

            alert.showAndWait();

        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {

                AlertExceptionService alert = new AlertExceptionService("Lectura de archivo", "No se ha podido cerrar el archivo " + file.getName(), ex);

                alert.showAndWait();

            }
        }
        return registerList;
    }

    public static void updateNote(Note note) {
        variableDao = new FileVariableImpl();

        FileVariable var = null;

        var = variableDao.getVariable("REF");

        BufferedReader br = null;

        FileReader fr = null;

        String path = file.getParentFile().getAbsolutePath();;

        File tempFile = new File(path, ".tmp");

        BufferedWriter out = null;

        try {
            tempFile.createNewFile();

            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(tempFile), "ISO-8859-1"));

            br = new BufferedReader(new InputStreamReader(new FileInputStream(FileService.file), "ISO-8859-1"));

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                if (sCurrentLine.length() > 0) {

                    String refFile = sCurrentLine.substring(var.getStart() - 1, var.getEnd() - 1).trim();

                    if (refFile.equals(note.getRef())) {

                        String newLine = RegisterFactory.generateNoteRegister(note);

                        out.write(newLine + "\n");

                    } else {
                        out.write(sCurrentLine + "\n");
                    }
                }
            }
        } catch (IOException e) {

            AlertExceptionService alert = new AlertExceptionService("Escritura de archivo", "No se ha podido sobreescribir el archivo " + file.getName(), e);

            alert.showAndWait();

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
                if (out != null) {
                    out.close();
                }
                if (tempFile.exists() && tempFile.canRead()) {
                    file.delete();

                    tempFile.renameTo(file);

                    tempFile = file;

                }

            } catch (IOException ex) {

                AlertExceptionService alert = new AlertExceptionService("Escritura de archivo", "No se ha podido cerrar el archivo " + file.getName(), ex);

                alert.showAndWait();

            }
        }

    }

    public static boolean writeOutFiles(Map<String, List<Note>> resultNotes) {

        boolean result = true;

        boolean append = true;

        for (String key : resultNotes.keySet()) {

            List<Note> notes = resultNotes.get(key);

            String desktopPath = null;

            BufferedReader br = null;

            FileReader fr = null;

            try {

                desktopPath = System.getProperty("user.home") + "\\Desktop";

            } catch (Exception e) {

                AlertExceptionService a = new AlertExceptionService("DEBUG", "Error obteniendo path de archivos", e);

                a.showAndWait();
            }

            File outputFile = null;

            if (desktopPath != null) {

                String folderName = key;

                if (folderName.equals("Correos express")) {

                    folderName = "CorreosExpress";
                } else {
                    folderName = folderName.toUpperCase();
                }

                new File(desktopPath, folderName).mkdir(); // Create folder

                boolean existFile = new File(desktopPath + "\\" + folderName + "\\Listado de facturas.txt").exists(); // Check if exists the file

                outputFile = new File(desktopPath + "\\" + folderName, "Listado de facturas.txt"); // Get or create file

                if (existFile && !isTodayFile(outputFile.lastModified())) { // Override file if there is a file and it is from past day
                    append = false;
                }

            } else {
                AlertExceptionService alertPath = new AlertExceptionService("Escritura de archivo", "No se ha podido obtener la ruta de archivos: " + desktopPath, null);

                alertPath.showAndWait();
            }

            BufferedWriter out = null;

            try {

                out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFile, append), "ISO-8859-1"));

                br = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile), "ISO-8859-1"));

                for (Note note : notes) {
                    
                    parseCountryToISO(note);
                    
                    try {

                        String newLine = RegisterFactory.generateNoteRegister(note);

                        out.write(newLine + "\n");

                    } catch (IOException ex) {
                        AlertExceptionService alertWrite = new AlertExceptionService("Escritura de archivo", "No se ha podido escribir el archivo de salida", ex);

                        alertWrite.showAndWait();
                    }
                }

            } catch (IOException e) {

                AlertExceptionService alert = new AlertExceptionService("Escritura de archivo", "No se ha podido guardar el archivo de salida" + outputFile.getName(), e);

                alert.showAndWait();

                result = false;

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    if (fr != null) {
                        fr.close();
                    }
                    if (out != null) {
                        out.close();
                    }

                } catch (IOException ex) {

                    AlertExceptionService alert = new AlertExceptionService("Escritura de archivo", "No se ha podido guardar el archivo de salida" + outputFile.getName(), ex);

                    alert.showAndWait();

                }
            }
        }
        return result;
    }

    public static boolean overrideFile(List<Note> notes) {
        boolean result = false;

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, false), "ISO-8859-1"));

            for (Note line : notes) {

                String newLine = RegisterFactory.generateNoteRegister(line);

                bw.write(newLine + "\n");

            }

            result = true;

        } catch (IOException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        return result;
    }

    private static boolean isTodayFile(long time) {

        Calendar currentCalendar = Calendar.getInstance();

        Calendar fileCalendar = Calendar.getInstance();

        currentCalendar.setTime(new Date());

        fileCalendar.setTime(new Date(time));

        return currentCalendar.get(Calendar.DAY_OF_YEAR) == fileCalendar.get(Calendar.DAY_OF_YEAR);
    }

    
   private static void parseCountryToISO(Note note) {
       IsoDao isoDao = new IsoImpl();
       
       HashMap<String,String> isos = isoDao.getIsos();
       
       String country = note.getCountry();
       
       String isoCountry = isos.get(country.toUpperCase());
       
       if (isoCountry != null) {
           note.setCountry(isoCountry);
       }
       
   }
}
