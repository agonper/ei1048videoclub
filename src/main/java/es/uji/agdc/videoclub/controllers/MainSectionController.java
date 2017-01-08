package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.ApplicationStateData;
import es.uji.agdc.videoclub.models.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 3/01/17.
 */

@Component
public class MainSectionController extends Controller {

    @FXML
    TabPane tabs;
    @FXML
    private TextField searchBar_textField;
    @FXML
    private Button searchBar_button;
    @FXML
    private Label search_label;
    @FXML
    private ListView searchResult_listView;


    @FXML
    public void initialize() {
        User loggedUser = ApplicationStateData.getLoggedUser();
        int ADMIN_TAB_POSITION = 1;

        if (!loggedUser.isAdmin()) {
            tabs.getTabs().remove(ADMIN_TAB_POSITION);
        }
    }

    public void searchMovies() {
        search_label.setText("Se ha buscado: " + searchBar_textField.getText());

        //TODO: Do search
    }
}
