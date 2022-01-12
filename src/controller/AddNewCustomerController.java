package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import model.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static controller.MakeCustomerOrdersController.customerList;
import static java.lang.Integer.parseInt;

public class AddNewCustomerController extends CustomerController {
    public AnchorPane addNewCustomerContext;
    public JFXTextField txtCustomerTittle;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXTextField txtCustomerId;

    static ArrayList<Customer> customerArrayList = customerList;

    public void closeStage(AnchorPane makeCustomerOrdersContext) {
        this.addNewCustomerContext = addNewCustomerContext;
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void addOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            Customer c1 = new Customer(
                    txtCustomerId.getText(), txtCustomerTittle.getText(),
                    txtCustomerName.getText(), txtCustomerAddress.getText(),
                    txtCustomerCity.getText(), txtProvince.getText(),
                    txtPostalCode.getText()
            );

            if (new CustomerController().saveCustomer(c1))
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            else
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public AddNewCustomerController(){

    }
        public void updateOnAction (ActionEvent actionEvent){
            try {
                Customer c2 = new Customer(
                        txtCustomerId.getText(), txtCustomerTittle.getText(),
                        txtCustomerName.getText(), txtCustomerAddress.getText(),
                        txtCustomerCity.getText(), txtProvince.getText(),
                        txtPostalCode.getText()
                );


                if (new CustomerController().updateCustomer(c2)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                new Alert(Alert.AlertType.WARNING, e.getMessage());
            }
        }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            if (new CustomerController().deleteCustomer(txtCustomerId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
            //setItemsToTable(new ItemController().getAllItem());

        }

    public void selectCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String custId = txtCustomerId.getText();

        Customer c1= new CustomerController().getCustomer(custId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }

    }

    private void setData(Customer c) {
        txtCustomerId.setText(c.getCustId());
        txtCustomerTittle.setText(c.getCustTittle());
        txtCustomerName.setText(c.getCustName());
        txtCustomerAddress.setText(c.getCustAddress());
        txtCustomerCity.setText(c.getCity());
        txtProvince.setText(c.getProvince());
        txtPostalCode.setText(c.getPostalCode());
    }
}
