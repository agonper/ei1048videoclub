package es.uji.agdc.videoclub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by daniel on 3/01/17.
 */
public class InsertMovieController extends Controller {

    @FXML
    private Pagination movie_Pagination;

    @FXML
    private Button moviePagination_back_button;

    @FXML
    private Button moviePagination_ahead_button;

    @FXML
    private BorderPane borderPane;

    private String movie_01 = "/views/app/mainSection/adminOptions/insertMovie/insert_movie_1.fxml";
    private String movie_02 = "/views/app/mainSection/adminOptions/insertMovie/insert_movie_2.fxml";
    private String movie_03 = "/views/app/mainSection/adminOptions/insertMovie/insert_movie_3.fxml";
    private String movie_04 = "/views/app/mainSection/adminOptions/insertMovie/insert_movie_4.fxml";

    @FXML
    public void changedPage() {
        int newPage = movie_Pagination.getCurrentPageIndex();

        switch (newPage) {
            case 0:
                moviePagination_back_button.setDisable(true);
                moviePagination_ahead_button.setDisable(false);
                moviePagination_ahead_button.setText("Siguiente ->");

                borderPane.setCenter(loadResource(movie_01));
                break;

            case 1:
                moviePagination_back_button.setDisable(false);
                moviePagination_ahead_button.setDisable(false);
                moviePagination_ahead_button.setText("Siguiente ->");

                borderPane.setCenter(loadResource(movie_02));
                break;

            case 2:
                moviePagination_back_button.setDisable(false);
                moviePagination_ahead_button.setDisable(false);
                moviePagination_ahead_button.setText("Siguiente ->");

                borderPane.setCenter(loadResource(movie_03));
                break;

            case 3:
                moviePagination_back_button.setDisable(false);
                moviePagination_ahead_button.setDisable(false);
                moviePagination_ahead_button.setText("Finalizar");

                borderPane.setCenter(loadResource(movie_04));
                break;
        }
    }

    private GridPane loadResource(String dir) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(dir));
        GridPane loadedSection = null;

        try {
            loadedSection = (GridPane) loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return loadedSection;
    }

    public void back_button_pressed() {
        movie_Pagination.currentPageIndexProperty().setValue(movie_Pagination.getCurrentPageIndex() - 1);
        changedPage();
    }

    public void ahead_button_pressed() {
        if (moviePagination_ahead_button.getText().equals("Finalizar")) {
            //TODO: Save movie
            super.stage.close();
        }
        else {
            movie_Pagination.currentPageIndexProperty().setValue(movie_Pagination.getCurrentPageIndex() + 1);
            changedPage();
        }
    }
}
