package es.uji.agdc.videoclub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by daniel on 3/01/17.
 */

@Component
public class AdminSectionController extends Controller {

    private Stage nonPaymentsStage = null;
    private Stage newUserStage = null;
    private Stage userEditStage = null;
    private Stage movieInsertionStage = null;
    private Stage movieEditStage = null;

    private String nonPaymentsSection = "/views/app/mainSection/adminOptions/users_with_non_payments.fxml";
    private String newUserSection = "/views/app/mainSection/adminOptions/insertUser/insert_user_root.fxml";
    private String userEditSection = "/views/app/mainSection/adminOptions/editUser/edit_user.fxml";
    private String movieInsertionSection = "/views/app/mainSection/adminOptions/insertMovie/insert_movie_root.fxml";
    private String movieEditSection = "/views/app/mainSection/adminOptions/editMovie/edit_movie.fxml";


    private void loadSection(String resource, Stage stage, String title) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(resource));
        BorderPane loadedSection;

        try {
            loadedSection = (BorderPane) loader.load();
            stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(loadedSection);
            stage.setScene(scene);

            Controller controller = (Controller) loader.getController();
            controller.setStage(stage);

            stage.showAndWait();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showNonPaymentsSection() {
        loadSection(nonPaymentsSection, nonPaymentsStage, "Usuarios con impagos");
    }

    @FXML
    public void showNewUserSection() {
        loadSection(newUserSection, newUserStage, "Creación de un nuevo usuario");
    }

    @FXML
    public void showUserEditSection() {
        loadSection(userEditSection, userEditStage, "Edición de un usuario");
    }

    @FXML
    public void showMovieInsertionSection() {
        loadSection(movieInsertionSection, movieInsertionStage, "Inserción de una película");
    }

    @FXML
    public void showMovieEditSection() {
        loadSection(movieEditSection, movieEditStage, "Edición de una película");
    }
}
