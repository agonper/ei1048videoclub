package es.uji.agdc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by daniel on 1/12/16.
 */
public class Main extends Application {

    //TODO: Move configuration to a more confortable place
    // Window configuration
    private String title = "Aplicación videoclub";

    // Window elements
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(title);

        buildRootLayout();
        showScenes();
    }

    private void buildRootLayout() {
        try {
            // Load and initialize the fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../../../views/root.fxml"));
            rootLayout = (BorderPane) loader.load();

            GridPane login = (GridPane) loadLoginLayout();
            rootLayout.setCenter(login);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pane loadLoginLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../../../views/login.fxml"));
        GridPane login = (GridPane) loader.load();
        return login;
    }

    private void showScenes() {
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
