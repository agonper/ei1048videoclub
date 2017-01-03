package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.ApplicationState;
import es.uji.agdc.videoclub.helpers.ApplicationStateData;
import es.uji.agdc.videoclub.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by daniel on 10/12/16.
 */

@Component
public class ProfileSectionController extends Controller {

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

    private Stage personalDataStage = null;
    private Stage rentedMoviesStage = null;
    private Stage administrationPanelStage = null;
    private Stage listOfUsersStage = null;

    private User loggedUser;

    // Data & inner sections
    //private PersonalDataScreen personalData = new PersonalDataScreen();


    // Method used by JavaFX to initialize the FXML elements
    @FXML
    private void initialize() {
        loggedUser = ApplicationStateData.getLoggedUser();
        username_lb.setText(loggedUser.getUsername());
        if (loggedUser.isMember()) {
            //TODO: Revisar validez
            listOfUsers_button.setVisible(false);
            adminOptions_button.setVisible(false);
        }
    }

    @FXML
    public void showPersonalData_Screen() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/app/mainSection/profile/personal_data.fxml"));
        BorderPane page;
        try {
            page = (BorderPane) loader.load();
            personalDataStage = new Stage();
            personalDataStage.setTitle("Datos personales");
            personalDataStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            personalDataStage.setScene(scene);

            PersonalDataScreenController controller = loader.getController();
            controller.setUserData(loggedUser);
            controller.setStage(personalDataStage);

            personalDataStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: Finish controller
    @FXML
    public void showRentedMovies_Screen() {
        System.out.println("Rented movies");
    }

    @FXML
    public void showAdministrationPanel() {
        System.out.println("Administration screen");
    }

    @FXML
    public void showListOfUsers_Screen() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/app/mainSection/adminOptions/list_of_users.fxml"));
        BorderPane page;
        try {
            page = (BorderPane) loader.load();
            listOfUsersStage = new Stage();
            listOfUsersStage.setTitle("Listado de usuarios");
            listOfUsersStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            listOfUsersStage.setScene(scene);

            UsersListController controller = loader.getController();
            controller.setStage(listOfUsersStage);
            controller.loadData();

            listOfUsersStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeSession() {
        if (personalDataStage != null && personalDataStage.isShowing())
            personalDataStage.close();

        if (rentedMoviesStage != null && rentedMoviesStage.isShowing())
            rentedMoviesStage.close();

        if (administrationPanelStage != null && administrationPanelStage.isShowing())
            administrationPanelStage.close();

        if (listOfUsersStage != null && listOfUsersStage.isShowing())
            listOfUsersStage.close();

        ApplicationStateData.setLoggedUser(null);
        ApplicationStateData.setNewState(ApplicationState.LOGIN);
    }


}
