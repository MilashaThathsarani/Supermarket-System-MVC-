package controller;

import db.DbConnection;
import model.Orders;
import model.itemDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    public boolean placeOrder(Orders order) throws SQLException, ClassNotFoundException {

        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            PreparedStatement stm = con.
                    prepareStatement("INSERT INTO `Orders` VALUES(?,?,?)");

            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getcId());
            stm.setObject(3, order.getOrderDate());

            if (stm.executeUpdate() > 0) {
                return saveOrderDetail(order.getOrderId(), order.getItems());

            } else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean saveOrderDetail(String orderId, ArrayList<itemDetail> items) throws SQLException, ClassNotFoundException {
        for (itemDetail temp : items
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO `Order Detail` VALUES(?,?,?,?)");
            stm.setObject(1, temp.getCode());
            stm.setObject(2, orderId);
            stm.setObject(3, temp.getOrderQty());
            stm.setObject(4, temp.getDisscount());

            if (stm.executeUpdate() > 0) {

            } else {
                return false;
            }
        }
        return true;

    }

    public List<String> getAllOrderIDs() throws SQLException, ClassNotFoundException {
            ResultSet rst = DbConnection.getInstance().getConnection().
                    prepareStatement("SELECT * FROM Orders").executeQuery();
            List<String> ids= new ArrayList<>();
            while (rst.next()){
                ids.add(
                        rst.getString(1)
                );
            }
            return ids;
        }
    }


   /* private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE ITEM SET qtyOnHand=(qtyOnHand-" + qty
                                + ") WHERE code='" + itemCode + "'");
        return stm.executeUpdate()>0;
    }*/


