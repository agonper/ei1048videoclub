package es.uji.agdc.videoclub.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * Created by daniel on 9/12/16.
 */

public abstract class AbstractScreen {

    protected Stage primaryStage;

    @Autowired
    ApplicationContext context;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showScreen() {
        //TODO: Ver qué hacer con las llamadas a este método
    }

    protected Pane loadScreen(String screenDir) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(screenDir));
        loader.setControllerFactory(aClass -> context.getBean(aClass));
        Pane loadedElement;

        loadedElement = (Pane) loader.load();
        return loadedElement;
    }
}
