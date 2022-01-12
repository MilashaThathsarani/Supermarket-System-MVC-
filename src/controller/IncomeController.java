package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Item;

import java.io.IOException;

public class IncomeController {
    public AnchorPane incomeContext;

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) incomeContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdministratorForm.fxml"))));
        window.centerOnScreen();
    }

}
