/*package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.Orders;
import model.itemDetail;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ManageOrdersController {


    public AnchorPane ManageOrdersContext;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public TableView <CartTm>tblListOfItems;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public Label txtTotal;
    public Label txtDisscount;
    public JFXTextField txtItemCode;
    public Label lblDate;
    public Label lblTime;
    public TableColumn colTotal;
    public ComboBox cmbOrderId;
    public TableColumn colEdit;
    public Label lblSelectCustomer;
    public JFXTextField txtQty;
    public Label lblTotal;
    public Label lblDisscount;
    public JFXButton btnUpdate;
    public JFXButton btnConfirmEdits;
    public JFXButton btnSearch;

    public void initialize() {
        loadDateAndTime();
       lblTotal.setText("0.00");
        btnUpdate.setDisable(true);
        btnConfirmEdits.setDisable(true);

        try {
            loadOrderIDs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


        /*tblListOfItems.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int selectedRowForUpdate = (int) newValue;
            try {
                if(selectedRowForUpdate!=-1) {
                    setItemData(selectedRowForUpdate);
                    btnUpdate.setDisable(false);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });*/


       /* colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }*/

   /* private void setItemData(int rowNumber) throws SQLException, ClassNotFoundException {
        CartTm tm = obList.get(rowNumber);
        Item item = new ItemController().getItem(tm.getItemCode());

        txtItemCode.setText(item.getItemCode());
        txtDescription.setText(item.getDescription());
        txtPackSize.setText(item.getPackSize());
        txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        txtQty.setText(String.valueOf(tm.getQty()));
    }*/

   /* private void loadOrderIDs() throws SQLException, ClassNotFoundException {
        List<String> orderIds = new OrderController().getAllOrderIDs();
        cmbOrderId.getItems().addAll(orderIds);
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) ManageOrdersContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CashierForm.fxml"))));
        window.centerOnScreen();
    }

    public void removeOnAction(ActionEvent actionEvent) {
    }

    public void confirmOrderOnAction(ActionEvent actionEvent) {
    }


    public void UpdateOnAction(ActionEvent actionEvent) {
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        if(cmbOrderId.getValue()!=null){
            try {
                loadOrderToTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            //System.out.println("err");
        }

    }

    ObservableList<CartTm> obList= FXCollections.observableArrayList();
    private void loadOrderToTable() {
        ResultSet rst = new OrderController().getOrderItems(orderID);
        OrderDetails order = new OrderController().getOrder(orderID);

        while (rst.next()){
            Button btn = new Button("remove");
            btn.setStyle("-fx-background-color: #ff5e5e; ");


            if(rst.getString(1).equals(orderId)) {
                ItemDetails itemDetails = new OrderController().getItemDetails(rst.getString(2),orderID);
                Item item = new ItemController().getItem(rst.getString(2));

                double total = item.getUnitPrice() * itemDetails.getQtyForSell();

                OrderTM tm = new OrderTM(itemDetails.getItemCode(), item.getDescription(), item.getUnitPrice(), itemDetails.getQtyForSell(), total, btn);

                int rowNumber = -1;
                rowNumber = isExists(tm);

                if (rowNumber == -1) {
                    obList.add(tm);
                }else {
                    if(selectedRowForUpdate==rowNumber) {
                        OrderTM temp = obList.get(rowNumber);
                        OrderTM newTm = new OrderTM(temp.getItemCode(), temp.getDescription(), temp.getUnitPrice(), Integer.parseInt(txtQty.getText()), (temp.getUnitPrice() * Integer.parseInt(txtQty.getText())), btn);
                        obList.remove(rowNumber);
                        obList.add(newTm);
                        selectedRowForUpdate=-1;
                        btn.setOnAction((e) -> {
                            obList.remove(newTm);
                        });
                    }else{

                    }
                }

                btn.setOnAction((e) -> {
                    obList.remove(tm);
                });
            }
        }

        tblItemList.setItems(obList);
        setTotal();
    }
    }
}*/
