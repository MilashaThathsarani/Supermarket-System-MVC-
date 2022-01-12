package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorFormController {
    public AnchorPane administratorContext;

    public void exitOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) administratorContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LogIn.fxml"))));
        window.centerOnScreen();
    }

    public void incomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) administratorContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Income.fxml"))));
        window.centerOnScreen();
    }

    public void movebleItemsOnAtion(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) administratorContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MovebleItem.fxml"))));
        window.centerOnScreen();
    }

    public void manageItemOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) administratorContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManageItem.fxml"))));
        window.centerOnScreen();
    }
}
