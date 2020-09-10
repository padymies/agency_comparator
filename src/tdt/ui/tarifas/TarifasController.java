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
import tdt.db.daoImpl.ZonaImpl;
import tdt.model.Zone;
import tdt.services.AlertExceptionService;
import tdt.services.ConfigStage;
import tdt.ui.tarifas.tabContent.TabContentController;
import tdt.db.dao.IZoneDao;

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

    private IZoneDao zonaDao;

    private ObservableList<Zone> listaZonas;

    private ArrayList<TabContentController> controllerArrayList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        zonaDao = new ZonaImpl();

        listaZonas = zonaDao.getZonesUI();

        ConfigStage.setIcon(btnAddZona, "add.png", 16);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        controllerArrayList = new ArrayList<>();

        for (int i = 0; i < listaZonas.size(); i++) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("tabContent/tabContent.fxml"));
            try {

                TabContentController controller = new TabContentController(listaZonas.get(i).getZoneId());

                loader.setController(controller);

                Tab tab = new Tab(listaZonas.get(i).getName(), loader.load());

                tab.setClosable(false);

                controllerArrayList.add(controller);

                tabPane.getTabs().add(tab);

                controllerArrayList.get(i).getTxtNombreZona().setText(listaZonas.get(i).getName());

                controllerArrayList.get(i).getTxtPais().setText(listaZonas.get(i).getCountry());

                controllerArrayList.get(i).getTxtDescripcion().setText(listaZonas.get(i).getDescription());

            } catch (IOException e) {
                AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido cargar el contenido de la zona " + listaZonas.get(i).getName(), e);

                alert.showAndWait();
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
            AlertExceptionService alert = new AlertExceptionService("Nueva zona", "No se ha podido cargar una nueva zona", e);

            alert.showAndWait();
        }
    }

}
