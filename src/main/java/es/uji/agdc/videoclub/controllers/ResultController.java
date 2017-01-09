package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.models.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        //TODO: Link to the full section of the movie
        System.out.println("Full section: " + movie.getTitle());
    }

    @FXML
    public void rentMovie() {
        //TODO: Do the rent action
    }
}
