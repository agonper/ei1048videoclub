package es.uji.agdc.videoclub;

import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.views.AuthScreen;
import es.uji.agdc.videoclub.views.MainSectionScreen;
import javafx.scene.Scene;
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

    private static Stage primaryStage;
    private static ApplicationContext context;

    private static String[] args;
    private static State actualState = State.LOGIN;
    private static User loggedUser;

    public enum State {
        LOGIN, APPLICATION
    }

    public static void main(String[] args) {
        Main.args = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        context = SpringApplication.run(Main.class, args);
        AuthScreen authScreen = context.getBean(AuthScreen.class);

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(authScreen.getTitle());

        authScreen.setPrimaryStage(primaryStage);
        authScreen.showScreen();
    }

    public static void setState(State newState) {
        if (newState == State.APPLICATION) {
            actualState = State.APPLICATION;
            primaryStage.close();
            MainSectionScreen mainScreen = context.getBean(MainSectionScreen.class);
            mainScreen.setPrimaryStage(primaryStage);
            mainScreen.showScreen();
        }
        else {
            actualState = State.LOGIN;
            primaryStage.close();
            AuthScreen authScreen = context.getBean(AuthScreen.class);
            authScreen.setPrimaryStage(primaryStage);
            authScreen.showScreen();
        }
    }

    //FIXME: Provisional
    public static void setLoggedUser(User user) {
        Main.loggedUser = user;
    }

    public static User getLoggedUser() {
        return Main.loggedUser;
    }
}