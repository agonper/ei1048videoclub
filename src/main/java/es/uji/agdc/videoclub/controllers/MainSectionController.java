package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.ApplicationStateData;
import es.uji.agdc.videoclub.helpers.Services;
import es.uji.agdc.videoclub.models.Movie;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.MovieQueryTypeMultiple;
import es.uji.agdc.videoclub.services.MovieService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by daniel on 3/01/17.
 */

@Component
public class MainSectionController extends Controller {

    @FXML
    TabPane tabs;
    @FXML
    private TextField searchBar_textField;
    @FXML
    private Button searchBar_button;
    @FXML
    private Label search_label;
    @FXML
    private ChoiceBox<String> searchBy_ChoiceBox;
    @FXML
    private VBox searchResult_VBox;

    private MovieService movieService = Services.getMovieService();


    @FXML
    public void initialize() {
        searchBy_ChoiceBox.getItems().add("Título");
        searchBy_ChoiceBox.getItems().add("Título VO");
        searchBy_ChoiceBox.getItems().add("Año");
        searchBy_ChoiceBox.getItems().add("Actor");
        searchBy_ChoiceBox.getItems().add("Director");
        searchBy_ChoiceBox.getItems().add("Género");
        searchBy_ChoiceBox.getItems().add("Por cualquiera");

        searchBar_button.setDisable(true);

        User loggedUser = ApplicationStateData.getLoggedUser();
        int ADMIN_TAB_POSITION = 1;

        if (!loggedUser.isAdmin()) {
            tabs.getTabs().remove(ADMIN_TAB_POSITION);
        }
    }

    public void searchMovies() {
        if (!searchBy_ChoiceBox.getValue().equals("")) {

            switch (searchBy_ChoiceBox.getValue()) {
                case "Título":
                    searchByTitle();
                    break;

                case "Título VO":
                    searchByTitleVO();
                    break;

                case "Año":
                    searchByYear();
                    break;

                case "Actor":
                    searchByActors();
                    break;

                case "Director":
                    searchByDirector();
                    break;

                case "Género":
                    searchByGenre();
                    break;

                case "Por cualquiera":
                    searchByAllFields();
                    break;
            }

            search_label.setText("Se ha buscado: " + searchBar_textField.getText());
        }
    }

    public void checkChoiceBox() {
        if (!searchBy_ChoiceBox.getValue().equals(""))
            searchBar_button.setDisable(false);
    }

    private void searchByTitle() {
        //TODO: Search by words, not by the full string
        String searchedBy = searchBar_textField.getText();
        searchResult_VBox.getChildren().clear();

        Iterator<Movie> movies = movieService.findAllBy(MovieQueryTypeMultiple.TITLE, searchedBy).iterator();

        while (movies.hasNext())
            searchResult_VBox.getChildren().add(generateSearchContainer(movies.next()));
    }

    private void searchByTitleVO() {
        //TODO: Search by words, not by the full string
        String searchedBy = searchBar_textField.getText();
        searchResult_VBox.getChildren().clear();

        //TODO: Terminate, lack of search by TITLE_VO
    }

    private void searchByYear() {
        //TODO: Search by words, not by the full string
        String searchedBy = searchBar_textField.getText();
        searchResult_VBox.getChildren().clear();

        Iterator<Movie> movies = movieService.findAllBy(MovieQueryTypeMultiple.YEAR, searchedBy).iterator();

        while (movies.hasNext())
            searchResult_VBox.getChildren().add(generateSearchContainer(movies.next()));
    }

    private void searchByActors() {
        //TODO: Search by words, not by the full string
        String searchedBy = searchBar_textField.getText();
        searchResult_VBox.getChildren().clear();

        Iterator<Movie> movies = movieService.findAllBy(MovieQueryTypeMultiple.ACTORS, searchedBy).iterator();

        while (movies.hasNext())
            searchResult_VBox.getChildren().add(generateSearchContainer(movies.next()));
    }

    private void searchByDirector() {
        //TODO: Search by words, not by the full string
        String searchedBy = searchBar_textField.getText();
        searchResult_VBox.getChildren().clear();

        Iterator<Movie> movies = movieService.findAllBy(MovieQueryTypeMultiple.DIRECTORS, searchedBy).iterator();

        while (movies.hasNext())
            searchResult_VBox.getChildren().add(generateSearchContainer(movies.next()));
    }

    private void searchByGenre() {
        //TODO: Search by words, not by the full string
        String searchedBy = searchBar_textField.getText();
        searchResult_VBox.getChildren().clear();

        Iterator<Movie> movies = movieService.findAllBy(MovieQueryTypeMultiple.GENRES, searchedBy).iterator();

        while (movies.hasNext())
            searchResult_VBox.getChildren().add(generateSearchContainer(movies.next()));
    }

    private void searchByAllFields() {
        //TODO: Search by words, not by the full string
        String searchedBy = searchBar_textField.getText();
        searchResult_VBox.getChildren().clear();

        Iterator<Movie> movies = movieService.findAllBy(MovieQueryTypeMultiple.ALL, searchedBy).iterator();

        while (movies.hasNext())
            searchResult_VBox.getChildren().add(generateSearchContainer(movies.next()));
    }

    private HBox generateSearchContainer(Movie movie) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/app/mainSection/search_result.fxml"));

        try {
            HBox resultContainer = (HBox) loader.load();
            resultContainer.setStyle("-fx-border-width: 1px; -fx-border-color: black");

            ResultController controller = loader.getController();
            controller.setMovie(movie);

            return resultContainer;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
