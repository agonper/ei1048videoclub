package es.uji.agdc.videoclub.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.asm.Label;
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


}
