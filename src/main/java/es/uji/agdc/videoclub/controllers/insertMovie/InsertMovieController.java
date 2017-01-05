package es.uji.agdc.videoclub.controllers.insertMovie;

import es.uji.agdc.videoclub.controllers.Controller;
import es.uji.agdc.videoclub.controllers.Form;
import es.uji.agdc.videoclub.controllers.RootController;
import es.uji.agdc.videoclub.models.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by daniel on 5/01/17.
 */

public class InsertMovieController extends Controller implements RootController {

    @FXML
    private Pagination movie_Pagination;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button submitButton;

    private String movie_01 = "/views/app/mainSection/adminOptions/insertMovie/insert_movie_1.fxml";
    private String movie_02 = "/views/app/mainSection/adminOptions/insertMovie/insert_movie_2.fxml";
    private String movie_03 = "/views/app/mainSection/adminOptions/insertMovie/insert_movie_3.fxml";
    private String movie_04 = "/views/app/mainSection/adminOptions/insertMovie/insert_movie_4.fxml";

    private Form movie_01_controller = null;
    private Form movie_02_controller = null;
    private Form movie_03_controller = null;
    private Form movie_04_controller = null;

    private GridPane movie_01_section = null;
    private GridPane movie_02_section = null;
    private GridPane movie_03_section = null;
    private GridPane movie_04_section = null;

    private String[] data_movie_01 = null;
    private String[] data_movie_02 = null;
    private String[] data_movie_03 = null;
    private String[] data_movie_04 = null;

    private boolean valid_01 = false;
    private boolean valid_02 = false;
    private boolean valid_03 = false;
    private boolean valid_04 = false;

    private Movie movieToCreate;

    private int actualPage = 0;

    @FXML
    public void initialize() {
        movie_Pagination.setPageFactory(param -> changedPage());
        loadResource(movie_01, 0);
        loadResource(movie_02, 1);
        loadResource(movie_03, 2);
        loadResource(movie_04, 3);
    }

    @FXML
    public GridPane changedPage() {
        int newPage = movie_Pagination.getCurrentPageIndex();
        GridPane loadedResource = null;

        switch (actualPage) {
            case 0:
                data_movie_01 = movie_01_controller.getAllData();
                break;

            case 1:
                data_movie_02 = movie_02_controller.getAllData();
                break;

            case 2:
                data_movie_03 = movie_03_controller.getAllData();
                break;

            case 3:
                data_movie_04 = movie_04_controller.getAllData();
                break;
        }

        switch (newPage) {
            case 0:
                if (data_movie_01 != null)
                    movie_01_controller.setAllData(data_movie_01);

                loadedResource = movie_01_section;
                borderPane.setCenter(loadedResource);
                actualPage = 0;
                break;

            case 1:
                if(data_movie_02 != null)
                    movie_02_controller.setAllData(data_movie_02);

                loadedResource = movie_02_section;
                borderPane.setCenter(loadedResource);
                actualPage = 1;
                break;

            case 2:
                if(data_movie_03 != null)
                    movie_03_controller.setAllData(data_movie_03);

                loadedResource = movie_03_section;
                borderPane.setCenter(loadedResource);
                actualPage = 2;
                break;

            case 3:
                if(data_movie_04 != null)
                    movie_04_controller.setAllData(data_movie_04);

                loadedResource = movie_04_section;
                borderPane.setCenter(loadedResource);
                actualPage = 3;
                break;
        }

        return loadedResource;
    }

    public void updateFormState(boolean allFieldsValid, int formNumber) {
        switch (formNumber) {
            case 1:
                valid_01 = allFieldsValid;
                break;

            case 2:
                valid_02 = allFieldsValid;
                break;

            case 3:
                valid_03 = allFieldsValid;
                break;
            case 4:
                valid_04 = allFieldsValid;
                break;
        }
    }

    private void loadResource(String dir, int formPage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(dir));
        GridPane loadedSection;

        try {
            loadedSection = (GridPane) loader.load();

            switch (formPage) {
                case 0:
                    movie_01_controller = loader.getController();
                    movie_01_controller.setRootController(this);
                    movie_01_section = loadedSection;
                    break;

                case 1:
                    movie_02_controller = loader.getController();
                    movie_02_controller.setRootController(this);
                    movie_02_section = loadedSection;
                    break;

                case 2:
                    movie_03_controller = loader.getController();
                    movie_03_controller.setRootController(this);
                    movie_03_section = loadedSection;
                    break;
                case 3:
                    movie_04_controller = loader.getController();
                    movie_04_controller.setRootController(this);
                    movie_04_section = loadedSection;
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void finishedForm() {
        submitButton.setDisable(!(valid_01 && valid_02 && valid_03));
    }

    @FXML
    public void submitForm() {
        movieToCreate = new Movie();
        System.out.println("Submit");
        setAllMovieData();
        super.stage.close();
    }

    private void setAllMovieData() {
        //TODO: Finish method
    }
}
