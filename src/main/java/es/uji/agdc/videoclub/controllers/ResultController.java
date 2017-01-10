package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.ApplicationStateData;
import es.uji.agdc.videoclub.helpers.Services;
import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.models.VisualizationLink;
import es.uji.agdc.videoclub.services.MovieService;
import es.uji.agdc.videoclub.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmación de alquiler");
        confirmationAlert.setHeaderText("¿Está seguro de querer alquilar la película?");
        Optional<ButtonType> answer = confirmationAlert.showAndWait();

        if (answer.get().getButtonData().isDefaultButton()) {
            User loggedUser = ApplicationStateData.getLoggedUser();
            VisualizationLink link = new VisualizationLink(loggedUser, movie);
            movie.setAvailableCopies(movie.getAvailableCopies() - 1);

            UserService userService = Services.getUserService();
            MovieService movieService = Services.getMovieService();

            userService.update(loggedUser);
            movieService.update(movie);

            initState();
        }
    }
}
