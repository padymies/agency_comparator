package tdt.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler {

    File file;
    
    public FileHandler() {
        this.file = new File("testFile.txt");
    }
    
    public void getTextFile() {
        try {
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\\Z");
            // we just need to use \\Z as delimiter
            while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String [] vals = line.split(" ");
            for(String value : vals) {
                if(value.length() > 0) {
                    System.out.println(value);
                }
                
            }
            }     
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
