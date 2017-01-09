package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.models.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by daniel on 8/01/17.
 */
public class ResultController extends Controller {

    @FXML
    private Label title_searchResult;
    @FXML
    private Label titleVO_searchResult;
    @FXML
    private Label year_searchResult;
    @FXML
    private Label genres_searchResult;

    private Movie movie;


    @FXML
    public void initialize() {
        title_searchResult.setText(movie.getTitle());
        titleVO_searchResult.setText(movie.getTitleOv());
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @FXML
    public void fullSection() {
        //TODO: Link to the full section of the movie
        System.out.println("Full section: " + movie.getTitle());
    }
}
