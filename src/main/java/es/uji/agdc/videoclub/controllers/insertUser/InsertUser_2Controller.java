package es.uji.agdc.videoclub.controllers.insertUser;

import es.uji.agdc.videoclub.controllers.Controller;
import es.uji.agdc.videoclub.controllers.Form;
import es.uji.agdc.videoclub.controllers.RootController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

/**
 * Created by daniel on 4/01/17.
 */
public class InsertUser_2Controller extends Controller implements Form {

    @FXML
    private TextField userPhoneNumber_textField;
    @FXML
    private TextField userEmail_textField;

    private boolean valid_phone = false;
    private boolean valid_email = false;

    private InsertUserController rootController = null;


    @FXML
    public void checkPhone_TextField() {
        if (validPhone(userPhoneNumber_textField.getText())) {
            userPhoneNumber_textField.setStyle("-fx-border-color: lawngreen ; -fx-border-width: 2px ;");
            valid_phone = true;
        }
        else {
            userPhoneNumber_textField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            valid_phone = false;
        }
        rootController.updateFormState(allFieldsValid(), 2);
        rootController.finishedForm();
    }

    private boolean validPhone(String phone) {
        if (phone.length() == 9 && isNumber(phone))
            return true;

        return false;
    }

    private boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    public void checkEmail_TextField() {
        if (validEmail(userEmail_textField.getText())) {
            userEmail_textField.setStyle("-fx-border-color: lawngreen ; -fx-border-width: 2px ;");
            valid_email = true;
        }
        else {
            userEmail_textField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            valid_email = false;
        }
        rootController.updateFormState(allFieldsValid(), 2);
        rootController.finishedForm();
    }

    private boolean validEmail(String email) {
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern p = Pattern.compile(EMAIL_PATTERN);

        return p.matcher(email).matches();
    }

    @Override
    public boolean allFieldsValid() {
        return valid_phone && valid_email;
    }

    @Override
    public String[] getAllData() {
        return new String[] {userPhoneNumber_textField.getText(), userEmail_textField.getText()};
    }

    @Override
    public void setAllData(String[] data) {
        userPhoneNumber_textField.setText(data[0]);
        checkPhone_TextField();

        userEmail_textField.setText(data[1]);
        checkEmail_TextField();
    }

    @Override
    public void setRootController(RootController rootController) {
        this.rootController = (InsertUserController) rootController;
    }
}
