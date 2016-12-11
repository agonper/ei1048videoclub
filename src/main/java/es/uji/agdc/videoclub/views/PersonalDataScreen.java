package es.uji.agdc.videoclub.views;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by daniel on 11/12/16.
 */

public class PersonalDataScreen extends AbstractScreen {

    // Auth screen elements
    private GridPane personalDataScreen;
    private Stage personalDataStage = new Stage();


    /** Window functionality */

    @Override
    public void showScreen() {
        buildPersonalDataScreen();
        showScene();
    }

    public boolean isOpen() {
        return personalDataStage.isShowing();
    }

    public void close() {
        personalDataStage.close();
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
}
