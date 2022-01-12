package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Item;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class ManageItemController extends ItemController{

    public AnchorPane manageItemContext;
    public JFXTextField txtItemCode;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtPackSize;
    public TableView<Item> tblManageItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colQtyOnHand;
    public TableColumn colUnitPrice;

    public ManageItemController() throws SQLException, ClassNotFoundException {
    }

    public void initialize() throws SQLException, ClassNotFoundException {


        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        setItemsToTable(new ItemController().getAllItem());

    }


    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) manageItemContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdministratorForm.fxml"))));
        window.centerOnScreen();
    }

    public void itemAddOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        try {
            Item i3 = new Item(
                   txtItemCode.getText(), txtDescription.getText(),
                   txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()),
                    parseInt(txtQtyOnHand.getText())

            );

            if (new ItemController().saveItem(i3))
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            else
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();


            setItemsToTable(new ItemController().getAllItem());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setItemsToTable(ArrayList<Item> items) {
        ObservableList<Item> obList = FXCollections.observableArrayList();
        items.forEach(e -> {
            obList.add(
                    new Item(e.getItemCode(), e.getDescription(), e.getPackSize(), e.getQtyOnHand(), (int) e.getUnitPrice()));
        });
        tblManageItem.setItems(obList);
    }
    public void itemModifyOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            Item i1 = new Item(txtItemCode.getText(),txtDescription.getText(),
                    txtPackSize.getText(),Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText())
                    );


            if (new ItemController().updateItem(i1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
                setItemsToTable(new ItemController().getAllItem());
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();

            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.WARNING, e.getMessage());
        }
    }

    public void selectItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = txtItemCode.getText();

        Item i1= new ItemController().getItem(itemId);
        if (i1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(i1);
        }

    }
    void setData(Item i) {
        txtItemCode.setText(i.getItemCode());
        txtDescription.setText(i.getDescription());
        txtPackSize.setText(i.getPackSize());
        txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
    }

    public void itemDeleteOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new ItemController().deleteItem(txtItemCode.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        setItemsToTable(new ItemController().getAllItem());

    }

        public void itemSearchOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String itemCode = txtItemCode.getText();

            Item i1 = new ItemController().getItem(itemCode);
            if (i1 == null) {
                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            } else {
                setData(i1);
            }
        }


}

