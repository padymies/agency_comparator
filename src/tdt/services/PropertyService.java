package tdt.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Properties;

public class PropertyService {

    private File file;
    private InputStream propsFile;
    private OutputStream out;
    private Properties props;

    public PropertyService() {

        try {
            file = new File("resources/properties");

            if (!file.exists()) {
                file.createNewFile();
            }

            propsFile = new FileInputStream(file);
            props = new Properties();
            props.load(propsFile);

        } catch (IOException e) {
            System.out.println("ERROR CARGANDO PROPIEDADES: " + e.getMessage());
            e.getMessage();
        } finally {
            try {

                propsFile.close();

            } catch (IOException e) {
                System.out.println("ERROR CERRANDO ARCHIVO DE PROPIEDADES: " + e.getMessage());
                e.getMessage();
            }
        }

    }

    public Object getProps(String prop) {
        return props.get(prop);
    }

    public Integer getPropsInt(String prop) {
        int result = -1;
        try {
            result = Integer.parseInt(props.get(prop).toString());

        } catch (NumberFormatException e) {
            System.out.println("ERROR CONVIRTIENDO A NUMERO LA PROPIEDAD" + Arrays.toString(e.getStackTrace()));
        }
        return result;
    }

    public void setProp(String prop, String value) {

        props.setProperty(prop, value);

        try {

            out = new FileOutputStream("properties");

            props.store(out, null);

        } catch (IOException e) {
            System.out.println("ERROR GUARDANDO PROPIEDADES: " + Arrays.toString(e.getStackTrace()));
        } finally {

            try {

                out.close();

            } catch (IOException e) {
                System.out.println("ERROR CERRANDO ARCHIVO DE PROPIEDADES" + Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
