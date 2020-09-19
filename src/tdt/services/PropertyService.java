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

            AlertExceptionService alert = new AlertExceptionService("Archivo propiedades", "No se ha podido cargar el archivo de propiedades", e);

            alert.showAndWait();

            e.getMessage();

        } finally {
            try {

                propsFile.close();

            } catch (IOException e) {

                AlertExceptionService alert = new AlertExceptionService("Archivo propiedades", "No se ha podido cerrar el archivo de propiedades", e);

                alert.showAndWait();

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

            out = new FileOutputStream("resources/properties");

            props.store(out, null);

        } catch (IOException e) {

            AlertExceptionService alert = new AlertExceptionService("Archivo de propiedades", "No se ha podido guardar la propiedad", e);

            alert.showAndWait();

        } finally {

            try {

                out.close();

            } catch (IOException e) {

                AlertExceptionService alert = new AlertExceptionService("Archivo propiedades", "No se ha podido cerrar el archivo de propiedades", e);

                alert.showAndWait();
            }
        }
    }
}
