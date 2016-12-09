package es.uji.agdc.videoclub;


import es.uji.agdc.videoclub.views.AuthScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Created by daniel on 1/12/16.
 */

@EnableJpaAuditing
@SpringBootApplication
public class Main extends Application {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private Stage primaryStage;

    private static String[] args;

    public static void main(String[] args) {
        Main.args = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        AuthScreen authScreen = context.getBean(AuthScreen.class);

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(authScreen.getTitle());

        authScreen.setPrimaryStage(primaryStage);
        authScreen.showScreen();
    }
}