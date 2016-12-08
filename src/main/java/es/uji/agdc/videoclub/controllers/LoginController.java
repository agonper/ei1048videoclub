package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.PasswordEncryptor;
import es.uji.agdc.videoclub.helpers.PasswordEncryptorBCrypt;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.AuthenticationService;
import es.uji.agdc.videoclub.services.UserService;
import es.uji.agdc.videoclub.services.UserServiceDB;
import es.uji.agdc.videoclub.services.utils.Result;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 1/12/16.
 */

@Component
public class LoginController {

    @FXML
    TextField login_UsernameTextfield;

    @FXML
    PasswordField login_PasswordField;

    @FXML
    Button loginButton;

    // Utility objects
    AuthenticationService authService;
    User.Role userRole;

    @Autowired
    public LoginController() {
        PasswordEncryptor passEncrypt = new PasswordEncryptorBCrypt();
        //UserService userService = new UserServiceDB();
        //authService = new AuthenticationServiceDB(userService, passEncrypt);
    }

    // Method used by JavaFX to initialize the FXML elements
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
            Result loginResult = authService.auth(introducedUsername, introducedPassword);

            if (loginResult.isOk()) {
                // TODO: Get user role
                successfulLogin_ViewChange();
            }

            else
                unsuccessfulLogin_errorMessage();
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

    private void unsuccessfulLogin_errorMessage() {
        Alert incorrectLogin = new Alert(Alert.AlertType.ERROR);
        incorrectLogin.setTitle("Autentificación fallida");
        incorrectLogin.setHeaderText("Se ha introducido un nombre de usuario inexistente o una contraseña incorrecta.");
        login_PasswordField.clear();
        incorrectLogin.showAndWait();
    }
}
