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
import java.util.List;
import java.util.Map;
import tdt.db.dao.IVariableArchivoDao;
import tdt.db.daoImpl.VariableArchivoImpl;
import tdt.model.Albaran;
import tdt.model.FileVariable;

public class FileService {

    private static File file;

    private static IVariableArchivoDao variableDao;

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
                is.close();
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

    public static void actualizarAlbaran(Albaran albaran) {
        variableDao = new VariableArchivoImpl();

        FileVariable var = null;

        var = variableDao.getVariable("REF");

        BufferedReader br = null;

        FileReader fr = null;

        String path = file.getParentFile().getAbsolutePath();;

        File tempFile = new File(path, ".tmp");

        BufferedWriter out = null;

        try {

            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(tempFile), "ISO-8859-1"));

            br = new BufferedReader(new InputStreamReader(new FileInputStream(FileService.file), "ISO-8859-1"));

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                if (sCurrentLine.length() > 0) {

                    String refFile = sCurrentLine.substring(var.getStart() - 1, var.getEnd() - 1).trim();

                    if (refFile.equals(albaran.getRef())) {

                        String newLine = RegisterFactory.generarRegistroAlbaran(albaran);

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

                    FileService.file = tempFile;

                }

            } catch (IOException ex) {

                AlertExceptionService alert = new AlertExceptionService("Escritura de archivo", "No se ha podido cerrar el archivo " + file.getName(), ex);

                alert.showAndWait();

            }
        }

    }

    public static boolean writeOutFiles(Map<String, List<Albaran>> result) {
        boolean resultado = true;

        for (String key : result.keySet()) {

            List<Albaran> albaranes = result.get(key);

            String desktopPath = null;

            BufferedReader br = null;

            FileReader fr = null;

            try {

                desktopPath = System.getProperty("user.home") + "\\Desktop";

            } catch (Exception e) {
                AlertExceptionService a = new AlertExceptionService("DEBUG", "Error obteniendo path de archivos", e);
                a.showAndWait();
            }

            File newFile = null;

            if (desktopPath != null) {

                new File(desktopPath, key.toUpperCase()).mkdir();

                newFile = new File(desktopPath + "\\" + key.toUpperCase(), "Listado de facturas.txt");
            } else {
                AlertExceptionService alertPath = new AlertExceptionService("Escritura de archivo", "No se ha podido obtener la ruta de archivos: " + desktopPath.toString(), null);

                alertPath.showAndWait();
            }

            BufferedWriter out = null;

            try {

                out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(newFile), "ISO-8859-1"));

                br = new BufferedReader(new InputStreamReader(new FileInputStream(newFile), "ISO-8859-1"));

                for (Albaran line : albaranes) {
                    try {
                        
                        String newLine = RegisterFactory.generarRegistroAlbaran(line);

                        out.write(newLine + "\n");
                        
                    } catch (IOException ex) {
                        AlertExceptionService alertWrite = new AlertExceptionService("Escritura de archivo", "No se ha podido escribir el archivo de salida", ex);

                        alertWrite.showAndWait();
                    }
                }

            } catch (IOException e) {

                AlertExceptionService alert = new AlertExceptionService("Escritura de archivo", "No se ha podido guardar el archivo de salida" + newFile.getName(), e);

                alert.showAndWait();

                resultado = false;

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

                    AlertExceptionService alert = new AlertExceptionService("Escritura de archivo", "No se ha podido guardar el archivo de salida" + newFile.getName(), ex);

                    alert.showAndWait();

                }
            }
        }
        return resultado;
    }

}
