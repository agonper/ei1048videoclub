package es.uji.agdc.videoclub.views;

import es.uji.agdc.videoclub.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by daniel on 9/12/16.
 */

public class MainSectionScreen extends AbstractScreen {

    @Override
    public void showScreen() {

    }

    private Pane loadMainSection() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/app/mainSection/mainSection.fxml"));
        Pane mainSection = loader.load();
        return mainSection;
    }
}
