package es.uji.agdc.videoclub;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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

@EnableJpaAuditing
@SpringBootApplication
public class Main extends Application {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    //TODO: Move configuration to a more confortable place
    // Window configuration
    private String title = "Aplicación videoclub";

    // Window elements
    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        log.info("Booting up application...");
        SpringApplication.run(Main.class, args);
        log.info("Starting GUI");
        launch(args);
    }

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
            loader.setLocation(Main.class.getResource("/views/app/root.fxml"));
            rootLayout = (BorderPane) loader.load();

            BorderPane login = (BorderPane) loadLoginLayout();
            rootLayout.setCenter(login);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pane loadLoginLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/app/auth/login.fxml"));
        Pane login = loader.load();
        return login;
    }

    private Pane loadMainSection() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/app/mainSection/mainSection.fxml"));
        Pane mainSection = loader.load();
        return mainSection;
    }

    private void showScenes() {
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}