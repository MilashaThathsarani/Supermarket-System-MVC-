package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierFormController {

    public AnchorPane cashierContext;

    public void exitOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) cashierContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LogIn.fxml"))));
    }

    public void manageOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage)cashierContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManageOrders.fxml"))));
        window.centerOnScreen();
    }

    public void makeCustomerOrdersOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage)cashierContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MakeCustomersOrders.fxml"))));
        window.centerOnScreen();

    }
}
