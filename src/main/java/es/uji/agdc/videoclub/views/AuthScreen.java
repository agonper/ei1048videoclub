package es.uji.agdc.videoclub.views;

import es.uji.agdc.videoclub.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by daniel on 9/12/16.
 */

@Component
public class AuthScreen extends AbstractScreen {

    // Auth screen elements
    private BorderPane root_Auth;

    // Auth screen configuration
    private String title = "Aplicación videoclub";

    @Autowired
    ApplicationContext context;

    /**
     * Window functionality
     */

    @Override
    public void showScreen() {
        buildRootLayout();
        showScene();
    }

    private void buildRootLayout() {
        try {
            // Load and initialize the fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/app/root.fxml"));
            loader.setControllerFactory(aClass -> context.getBean(aClass));

            root_Auth = (BorderPane) loader.load();

            BorderPane login = (BorderPane) loadLoginLayout();
            root_Auth.setCenter(login);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pane loadLoginLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/app/auth/login.fxml"));
        loader.setControllerFactory(aClass -> context.getBean(aClass));

        Pane login = loader.load();
        return login;
    }

    private void showScene() {
        Scene scene = new Scene(root_Auth);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * Getters & setters
     */

    public String getTitle() {
        return new String(title);
    }
}
