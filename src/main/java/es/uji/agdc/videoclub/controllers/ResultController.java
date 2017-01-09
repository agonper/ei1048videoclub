package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.models.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by daniel on 8/01/17.
 */
public class ResultController extends Controller {

    @FXML
    private Label title_searchResult;
    @FXML
    private Button rentMovie_button;


    private Movie movie;


    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void initState() {
        title_searchResult.setText(movie.getTitle() + ": " + movie.getTitleOv());
        rentMovie_button.setText("Reservar película (" + movie.getAvailableCopies() + " disponibles)");
        if (movie.getAvailableCopies() == 0)
            rentMovie_button.setDisable(true);
    }

    @FXML
    public void fullSection() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/app/mainSection/movie_complete_description.fxml"));
        try {
            BorderPane window = loader.load();
            MovieCompleteDescriptionController controller = loader.getController();
            controller.setMovie(movie);
            controller.initWindow();
            Scene scene = new Scene(window);
            controller.stage = new Stage();
            controller.stage.setScene(scene);
            controller.stage.setTitle("Descripción completa");
            controller.stage.initModality(Modality.WINDOW_MODAL);
            controller.stage.showAndWait();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void rentMovie() {
        //TODO: Do the rent action
    }
}
