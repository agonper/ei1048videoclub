package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.Services;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.UserQueryTypeMultiple;
import es.uji.agdc.videoclub.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by daniel on 15/12/16.
 */

@Component
public class UsersListController {

    @FXML
    TableView users_TableView;
    @FXML
    TableColumn DNI_TableColumn;
    @FXML
    TableColumn completeName_TableColumn;
    @FXML
    TableColumn address_TableColumn;
    @FXML
    TableColumn phone_TableColumn;
    @FXML
    TableColumn email_TableColumn;
    @FXML
    TableColumn lastPayment_TableColumn;
    @FXML
    TableColumn username_TableColumn;
    @FXML
    TableColumn role_TableColumn;

    private Stage listOfUsersStage;
    private UserService userService = Services.getUserService();

    public void setStage(Stage stage) {
        listOfUsersStage = stage;
    }

    public void loadData() {
        Stream<User> members = userService.findAllBy(UserQueryTypeMultiple.ROLE, "MEMBER");
        Stream<User> admins = userService.findAllBy(UserQueryTypeMultiple.ROLE, "ADMIN");
        ObservableList<User> usersToTableView = FXCollections.observableArrayList();

        Iterator<User> membersIterator = members.iterator();
        while (membersIterator.hasNext())
            usersToTableView.add(membersIterator.next());

        Iterator<User> adminsIterator = admins.iterator();
        while (adminsIterator.hasNext())
            usersToTableView.add(adminsIterator.next());
    }
}
