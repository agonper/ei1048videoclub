package es.uji.agdc.videoclub.controllers;

import es.uji.agdc.videoclub.helpers.Services;
import es.uji.agdc.videoclub.models.User;
import es.uji.agdc.videoclub.services.UserQueryTypeMultiple;
import es.uji.agdc.videoclub.services.UserService;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableLongValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by daniel on 3/01/17.
 */
public class SlowPayersSectionController extends Controller {

    @FXML
    private TableView slowPayers_TableView;
    @FXML
    private TableColumn slowPayers_months_tableColumn;

    private UserService userService = Services.getUserService();

    @FXML
    public void initialize() {
        slowPayers_months_tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, Long>, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<User, Long> param) {

                //TODO: TEST
                LocalDate lastPayment = param.getValue().getLastPayment();
                Period between = Period.between(lastPayment, LocalDate.now());
                long unpaidMonths = between.toTotalMonths();

                return new ObservableLongValue() {
                    @Override
                    public long get() {
                        return unpaidMonths;
                    }

                    @Override
                    public int intValue() {
                        return between.getMonths();
                    }

                    @Override
                    public long longValue() {
                        return unpaidMonths;
                    }

                    @Override
                    public float floatValue() {
                        return unpaidMonths;
                    }

                    @Override
                    public double doubleValue() {
                        return unpaidMonths;
                    }

                    @Override
                    public void addListener(ChangeListener<? super Number> listener) {

                    }

                    @Override
                    public void removeListener(ChangeListener<? super Number> listener) {

                    }

                    @Override
                    public Number getValue() {
                        return unpaidMonths;
                    }

                    @Override
                    public void addListener(InvalidationListener listener) {

                    }

                    @Override
                    public void removeListener(InvalidationListener listener) {

                    }
                };
            }
        });
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
