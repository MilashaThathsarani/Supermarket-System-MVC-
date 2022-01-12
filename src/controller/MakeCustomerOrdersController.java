package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MakeCustomerOrdersController extends CustomerController {

    public AnchorPane makeCustomerOrdersContext;
    public JFXTextField txtCustomerCity;
    public ComboBox cmbCustomerId;
    public JFXTextField txtProvince;
    public JFXTextField txtCustomerName;
    public JFXTextField txtPostalCode;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerTittle;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public ComboBox cmbItemCode;
    public TableView<CartTm> tblListOfItems;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;

    static ArrayList<Customer> customerList = new ArrayList();
    public Label lblDate;
    public Label lblTime;
    public TableColumn colQty;
    public JFXTextField tXtQty;
    public Label lblTotal;
    public TextField txtOrderId;

    int cartSelectedRowForRemove = -1;

    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        try {
            loadItemIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadCustomersIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData((String) newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        tblListOfItems.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }


    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPackSize());
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
        }
    }

    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result");
        } else {
            txtCustomerTittle.setText(c1.getCustTittle());
            txtCustomerName.setText(c1.getCustName());
            txtCustomerAddress.setText(c1.getCustAddress());
            txtCustomerCity.setText(c1.getCity());
            txtProvince.setText(c1.getProvince());
            txtPostalCode.setText(c1.getPostalCode());
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getAllItemIds();
        cmbItemCode.getItems().addAll(itemIds);
    }

    private void loadCustomersIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);
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
        Stage window = (Stage) makeCustomerOrdersContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CashierForm.fxml"))));
        window.centerOnScreen();
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AddNewCustomer.fxml"));
        Parent parent = loader.load();
        AddNewCustomerController controller = loader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        controller.closeStage(makeCustomerOrdersContext);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public void confirmOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<itemDetail> items = new ArrayList<>();
        for (CartTm tempTm : obList
        ) {
            items.add(
                    new itemDetail(
                          tempTm.getItemCode(),
                            tempTm.getQty(),
                            tempTm.getUnitPrice()
                    )
            );
        }
        Orders order = new Orders(
                txtOrderId.getText(),
                (String) cmbCustomerId.getValue(),
                lblDate.getText(),
                items

        );
        if (new OrderController().placeOrder(order)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void selectCustomerId(ActionEvent actionEvent) {
    }

    public void Delete(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove== -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else {
            obList.remove(cartSelectedRowForRemove);
            calculate();
            tblListOfItems.refresh();
        }
    }

    ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyForCustomer = Integer.parseInt(tXtQty.getText());
        double total = qtyForCustomer * unitPrice;

        if (qtyOnHand < qtyForCustomer) {
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            return;
        }

        CartTm tm = new CartTm(
                (String) cmbItemCode.getValue(),
                description,
                qtyForCustomer,
                unitPrice,
                total
        );

        int rowNumber = isExists(tm);

        if ( rowNumber==-1) {
            obList.add(tm);
        } else {
            CartTm temp = obList.get(rowNumber);
            CartTm newTm = new CartTm(
                    temp.getItemCode(),
                    temp.getDescription(),
                    temp.getQty()+qtyForCustomer,
                    unitPrice,
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }

        tblListOfItems.setItems(obList);

        calculate();
    }

    private int isExists(CartTm tm) {

        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }
    void calculate() {
        double ttl = 0;
        for (CartTm tm : obList
        ) {
            ttl += tm.getTotal();
        }
        lblTotal.setText(ttl+"/=");
    }

}
