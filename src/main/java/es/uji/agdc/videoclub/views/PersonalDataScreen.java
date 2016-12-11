package es.uji.agdc.videoclub.views;

import es.uji.agdc.videoclub.models.User;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by daniel on 11/12/16.
 */

public class PersonalDataScreen extends AbstractScreen {

    private User loggedUser;
    private GridPane personalDataScreen;
    private Stage personalDataStage = new Stage();

    public PersonalDataScreen(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void showScreen() {
        buildPersonalDataScreen();
        showScene();
    }

    private void buildPersonalDataScreen() {
        try {
            this.personalDataScreen = (GridPane) super.loadScreen("/views/app/mainSection/profile/personal_data.fxml");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showScene() {
        personalDataStage.setScene(new Scene(personalDataScreen));
        personalDataStage.show();
    }

    public boolean isOpen() {
        return personalDataStage.isShowing();
    }

    public void close() {
        personalDataStage.close();
    }
}
