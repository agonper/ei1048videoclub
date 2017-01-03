package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.ApplicationStateData;
import es.uji.agdc.videoclub.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 3/01/17.
 */

@Component
public class MainSectionController extends Controller {

    @FXML
    TabPane tabs;

    @FXML
    public void initialize() {
        User loggedUser = ApplicationStateData.getLoggedUser();
        int ADMIN_TAB_POSITION = 1;

        if (!loggedUser.isAdmin()) {
            tabs.getTabs().remove(ADMIN_TAB_POSITION);
        }
    }
}
