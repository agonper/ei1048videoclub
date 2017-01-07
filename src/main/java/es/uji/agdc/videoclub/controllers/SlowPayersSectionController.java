package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.Services;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.UserQueryTypeMultiple;
import es.uji.agdc.videoclub.services.UserService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by daniel on 3/01/17.
 */
public class SlowPayersSectionController extends Controller {

    @FXML
    private TableView slowPayers_TableView;

    private UserService userService = Services.getUserService();

    @FXML
    public void initialize() {
        loadData();
    }

    private void loadData() {
        Stream<User> members = userService.findAllBy(UserQueryTypeMultiple.ROLE, "MEMBER");

        ObservableList<User> usersToTableView = slowPayers_TableView.getItems();
        usersToTableView.clear();

        Iterator<User> membersIterator = members.iterator();

        while (membersIterator.hasNext()) {
            User member = membersIterator.next();
            if (isSlowPayer(member))
                usersToTableView.add(member);
        }

        slowPayers_TableView.setItems(usersToTableView);
    }

    private boolean isSlowPayer(User user) {
        LocalDate actualDate = LocalDate.now();
        LocalDate lastPayment = user.getLastPayment();

        if (lastPayment != null && actualDate.minusMonths(1).isAfter(lastPayment))
            return true;

        return false;
    }

    @FXML
    public void closeWindow() {
        super.stage.close();
    }
}
