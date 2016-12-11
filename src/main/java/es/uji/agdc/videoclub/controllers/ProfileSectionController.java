package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.ApplicationState;
import es.uji.agdc.videoclub.helpers.ApplicationStateData;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.views.PersonalDataScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 10/12/16.
 */

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

    // Data & inner sections
    private User loggedUser;
    private PersonalDataScreen personalData;


    // Method used by JavaFX to initialize the FXML elements
    @FXML
    private void initialize() {
        this.loggedUser = ApplicationStateData.getLoggedUser();
        username_lb.setText(loggedUser.getUsername());
        if (loggedUser.isMember()) {
            //TODO: Delete admin options
        }
    }

    @FXML
    public void showPersonalData_Screen() {
        if (personalData == null || personalData.isOpen() == false) {
            personalData = new PersonalDataScreen(loggedUser);
            personalData.showScreen();
        }
    }

    //TODO: Finish controller
    @FXML
    public void showRentedMovies_Screen() {
        System.out.println("Rented movies");
    }

    @FXML
    public void showAdministration_Screen() {
        System.out.println("Administration screen");
    }

    @FXML
    public void showListOfUsers_Screen() {
        System.out.println("List of users");
    }

    @FXML
    public void closeSession() {
        this.loggedUser = null;
        if (personalData.isOpen())
            personalData.close();
        ApplicationStateData.setNewState(ApplicationState.LOGIN);
    }


}
