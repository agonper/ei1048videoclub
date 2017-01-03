package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.Services;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * Created by daniel on 15/12/16.
 */

public class PersonalDataScreenController extends Controller {

    @FXML
    private TextField dni_TextField;
    @FXML
    private TextField name_TextField;
    @FXML
    private TextField dir_TextField;
    @FXML
    private TextField phone_TextField;
    @FXML
    private TextField email_TextField;
    @FXML
    private DatePicker lastPayment_TextField;
    @FXML
    private TextField username_TextField;
    @FXML
    private Button editData_Button;

    private Stage personalDataStage;

    private boolean editingData = false;
    private UserService userService = Services.getUserService();

    @FXML
    public void initialize() {
        changeEditableProperty_In_TextFields(false);
    }


    public void setUserData(User user) {
        dni_TextField.setText(user.getDni());
        name_TextField.setText(user.getName());
        dir_TextField.setText(user.getAddress());
        phone_TextField.setText(Integer.toString(user.getPhone()));
        email_TextField.setText(user.getEmail());
        LocalDate lastPayment = user.getLastPayment();
        if (lastPayment != null)
            lastPayment_TextField.setUserData(user.getLastPayment());

        lastPayment_TextField.setEditable(false);

        username_TextField.setText(user.getUsername());
    }
    public void setStage(Stage dialogStage) {
        this.personalDataStage = dialogStage;
    }

    private void changeEditableProperty_In_TextFields(boolean newValue) {
        dni_TextField.setEditable(newValue);
        dni_TextField.setMouseTransparent(!newValue);
        dni_TextField.setFocusTraversable(newValue);

        name_TextField.setEditable(newValue);
        name_TextField.setMouseTransparent(!newValue);
        name_TextField.setFocusTraversable(newValue);

        dir_TextField.setEditable(newValue);
        dir_TextField.setMouseTransparent(!newValue);
        dir_TextField.setFocusTraversable(newValue);

        phone_TextField.setEditable(newValue);
        phone_TextField.setMouseTransparent(!newValue);
        phone_TextField.setFocusTraversable(newValue);

        email_TextField.setEditable(newValue);
        email_TextField.setMouseTransparent(!newValue);
        email_TextField.setFocusTraversable(newValue);

        lastPayment_TextField.setEditable(newValue);
        lastPayment_TextField.setMouseTransparent(!newValue);
        lastPayment_TextField.setFocusTraversable(newValue);

        username_TextField.setEditable(newValue);
        username_TextField.setMouseTransparent(!newValue);
        username_TextField.setFocusTraversable(newValue);
    }

    @FXML
    public void editData() {
        if (editingData == false) {
            changeEditableProperty_In_TextFields(true);
            editData_Button.setText("Guardar cambios");
            editingData = true;
        }
        else {
            changeEditableProperty_In_TextFields(false);
            editData_Button.setText("Editar datos");
            editingData = false;
            //TODO: Validar y guardar cambios
            userService.update(new User());
        }
    }

    @FXML
    public void closeWindow() {
        personalDataStage.close();
    }
}
