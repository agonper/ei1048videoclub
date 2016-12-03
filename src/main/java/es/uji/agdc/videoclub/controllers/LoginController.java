package es.uji.agdc.videoclub.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by daniel on 1/12/16.
 */
public class LoginController {

    @FXML
    TextField login_UsernameTextfield;

    @FXML
    PasswordField login_PasswordField;

    @FXML
    Button loginButton;

    // Method used by JavaFX
    @FXML
    private void initialize() {

    }

    @FXML
    public void handleLoginAction() {
        String introducedUsername = getIntroducedUsername();
        String introducedPassword = getIntroducedPassword();
        boolean voidUsername = introducedUsername.trim().equals("");
        boolean voidPassword = introducedPassword.trim().equals("");

        //TODO: Search a better method than dialogs (more like the web forms)

        if (voidUsername) {
            Alert voidUsernameAlert = new Alert(Alert.AlertType.ERROR);
            voidUsernameAlert.setTitle("Usuario vacío");
            voidUsernameAlert.setHeaderText("No se ha introducido ningún nombre de usuario");
            login_UsernameTextfield.clear();
            voidUsernameAlert.showAndWait();
        }

        else if (voidPassword) {
            Alert voidPasswordAlert = new Alert(Alert.AlertType.ERROR);
            voidPasswordAlert.setTitle("Contraseña vacía");
            voidPasswordAlert.setHeaderText("No se ha introducido ninguna contraseña");
            login_PasswordField.clear();
            voidPasswordAlert.showAndWait();
        }
        else {
            try {
                //TODO: Introduce credentials management
                successfulLogin_ViewChange();
            }
            //TODO: Introduce login failed exceptions
            catch (Exception e) {
                //TODO: Introduce login failed message to the user
            }
        }
    }

    private String getIntroducedUsername() {
        return login_UsernameTextfield.getText();
    }

    private String getIntroducedPassword() {
        return login_PasswordField.getText();
    }



    private void successfulLogin_ViewChange() {
        //TODO: Change the view
    }

    //TODO: Get user role
    //private String obtainUserRole(User user) {

    //}
}
