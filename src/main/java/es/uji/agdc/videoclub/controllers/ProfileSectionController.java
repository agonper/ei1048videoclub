package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.Main;
import es.uji.agdc.videoclub.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 10/12/16.
 */

//TODO: Finish

@Component
public class ProfileSectionController {

    // View components
    @FXML
    Label username_lb;

    @FXML
    Button personalData_button;

    @FXML
    Button rentedMovies_button;

    @FXML
    Button adminOptions_button;

    @FXML
    Button listOfUsers_button;

    @FXML
    Button closeSession_button;

    @FXML
    public void closeSession() {
        Main.setState(Main.State.LOGIN);
    }

    // Method used by JavaFX to initialize the FXML elements
    @FXML
    private void initialize() {
        User user = Main.getLoggedUser();
        username_lb.setText(user.getUsername());
    }
}
