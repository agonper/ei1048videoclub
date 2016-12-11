package es.uji.agdc.videoclub.views;

import es.uji.agdc.videoclub.models.User;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by daniel on 9/12/16.
 */

@Component
public class MainSectionScreen extends AbstractScreen {

    private BorderPane main_section;

    @Override
    public void showScreen() {
        buildMainSection();
        showScene();
    }

    private void buildMainSection() {
        try {
            this.main_section = (BorderPane) loadMainSection();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pane loadMainSection() throws IOException {
        Pane mainSection = super.loadScreen("/views/app/mainSection/mainSection.fxml");
        return mainSection;
    }

    private void showScene() {
        Scene scene = new Scene(main_section);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}