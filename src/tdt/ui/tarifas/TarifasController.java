package tdt.ui.tarifas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tdt.db.dao.IZonaDao;
import tdt.db.daoImpl.ZonaImpl;
import tdt.model.Zona;
import tdt.ui.tarifas.tabContent.TabContentController;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class TarifasController implements Initializable {

    @FXML
    private TabPane tabPane;

    @FXML
    private Button btnAddZona;

    private IZonaDao zonaDao;

    private ObservableList<Zona> listaZonas;

    private ArrayList<TabContentController> controllerArrayList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        zonaDao = new ZonaImpl();

        listaZonas = zonaDao.obtenerZonasUI();

        Image icon = new Image("file:resources/add_icon.png");

        ImageView imageView = new ImageView(icon);

        imageView.setFitHeight(24);

        imageView.setFitWidth(24);

        btnAddZona.setGraphic(imageView);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        controllerArrayList = new ArrayList<>();

        for (int i = 0; i < listaZonas.size(); i++) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("tabContent/tabContent.fxml"));
            try {

                TabContentController controller = new TabContentController(listaZonas.get(i).getIdZona());

                loader.setController(controller);

                Tab tab = new Tab(listaZonas.get(i).getNombre(), loader.load());

                tab.setClosable(false);

                controllerArrayList.add(controller);

                tabPane.getTabs().add(tab);

                controllerArrayList.get(i).getTxtNombreZona().setText(listaZonas.get(i).getNombre());

                controllerArrayList.get(i).getTxtPais().setText(listaZonas.get(i).getPais());

                controllerArrayList.get(i).getTxtDescripcion().setText(listaZonas.get(i).getDescripcion());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void addNewZona(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("tabContent/tabContent.fxml"));
        try {

            loader.setController(new TabContentController(-1));

            Tab tab = new Tab("Nueva Zona ", loader.load());

            controllerArrayList.add(loader.getController());

            tabPane.getTabs().add(0, tab);

            tabPane.getSelectionModel().select(tab);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
