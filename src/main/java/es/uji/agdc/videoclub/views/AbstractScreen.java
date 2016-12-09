package es.uji.agdc.videoclub.views;

import javafx.stage.Stage;

/**
 * Created by daniel on 9/12/16.
 */

public abstract class AbstractScreen {

    protected Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showScreen() {
        //TODO: Ver qué hacer con las llamadas a este método
    }
}
