package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.ApplicationStateData;
import es.uji.agdc.videoclub.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

/**
 * Created by daniel on 9/01/17.
 */
public class RentedMoviesController extends Controller {

    @FXML
    private TableView rentedMovies_TableView;
    @FXML
    private Button returnMovie;
    @FXML
    private Button viewMovie;


    @FXML
    public void initialize() {
        User loggedUser = ApplicationStateData.getLoggedUser();
        //TODO: Get rent data and set it in the view
    }

    public void refreshButtons() {
        returnMovie.setDisable(rentedMovies_TableView.getSelectionModel().getSelectedIndices().size() == 0);
        viewMovie.setDisable(rentedMovies_TableView.getSelectionModel().getSelectedIndices().size() == 0);
    }

    @FXML
    public void returnMovie() {
        if (rentedMovies_TableView.getSelectionModel().getSelectedIndices().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Película no seleccionada");
            alert.setHeaderText("Ha de seleccionarse una película para poder devolverla");
            alert.showAndWait();
        }
        else if (rentedMovies_TableView.getSelectionModel().getSelectedIndices().size() > 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Demasiadas películas seleccionadas");
            alert.setHeaderText("Ha de seleccionarse una única película para poder devolverla");
            alert.showAndWait();
        }
        else {
            int selectedIndex = rentedMovies_TableView.getSelectionModel().getSelectedIndex();
            //TODO: Get movie, return movie
            rentedMovies_TableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    public void viewMovie() {
        if (rentedMovies_TableView.getSelectionModel().getSelectedIndices().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Película no seleccionada");
            alert.setHeaderText("Ha de seleccionarse una película para poder verla");
            alert.showAndWait();
        }
        else if (rentedMovies_TableView.getSelectionModel().getSelectedIndices().size() > 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Demasiadas películas seleccionadas");
            alert.setHeaderText("Ha de seleccionarse una única película para poder verla");
            alert.showAndWait();
        }
        else {
            //TODO: Call view movie window
        }
    }
}
