package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    public TextField txtPassword;
    public TextField txtUserName;
    public AnchorPane logInContext;

    public void openAdministratorForm(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("Admin") && txtPassword.getText().equalsIgnoreCase("1234")) {
            Stage window = (Stage) logInContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdministratorForm.fxml"))));
        }else {
            new Alert(Alert.AlertType.WARNING,"Incorrect UserName,Password And Try Again", ButtonType.CLOSE).show();
        }
    }

    public void openCashierForm(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("Cashier") && txtPassword.getText().equalsIgnoreCase("0000")) {
            Stage window = (Stage) logInContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CashierForm.fxml"))));
        }else {
            new Alert(Alert.AlertType.WARNING,"Incorrect UserName,Password And Try Again", ButtonType.CLOSE).show();
        }
    }

    public void moveToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}
