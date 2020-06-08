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
import tdt.db.dao.IVariableArchivoDao;
import tdt.db.daoImpl.VariableArchivoImpl;
import tdt.model.Albaran;
import tdt.model.VariableArchivo;

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

            System.out.println("ERROR LEYENDO ARCHIVO: " + e.getMessage());

            e.printStackTrace();

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

                System.out.println("ERROR CERRANDO ARCHIVO: " + ex.getMessage());

                ex.printStackTrace();
            }
        }
        return registerList;
    }

    public static void actualizarAlbaran(Albaran albaran) {
        System.out.println("PAIS =============> "+ albaran.getPais());
        variableDao = new VariableArchivoImpl();

        VariableArchivo var = null;

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

                        System.out.println(sCurrentLine);

                        System.out.println(newLine);

                        System.out.println(newLine.equals(sCurrentLine));

                        out.write(newLine + "\n");

                    } else {
                        out.write(sCurrentLine + "\n");
                    }
                }
            }
        } catch (IOException e) {

            System.out.println("ERROR SOBREESCRIBIENDO ARCHIVO: " + e.getMessage());

            e.printStackTrace();

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

                System.out.println("ERROR SOBREESCRIBIENDO ARCHIVO: " + ex.getMessage());

                ex.printStackTrace();
            }
        }

    }

}
