package tdt.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileHandler {

    public ArrayList<String> extractRegisters(File file) {
        BufferedReader br = null;
        FileReader fr = null;
        ArrayList<String> registerList = new ArrayList();
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "ISO-8859-1"));
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.length() > 0) {
                    registerList.add(sCurrentLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Error Leyendo archivo");
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                System.out.println("Error cerrando archivo");
                ex.printStackTrace();
            }
        }
        return registerList;
    }
}