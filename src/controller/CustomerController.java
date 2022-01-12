package controller;

import db.DbConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerAdd {

    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    @Override
        public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
            Connection con= DbConnection.getInstance().getConnection();
            String query="INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setObject(1,c.getCustId());
            stm.setObject(2,c.getCustTittle());
            stm.setObject(3,c.getCustName());
            stm.setObject(4,c.getCustAddress());
            stm.setObject(5,c.getCity());
            stm.setObject(6,c.getProvince());
            stm.setObject(7,c.getPostalCode());

            return stm.executeUpdate()>0;
        }

        public CustomerController(){
        }

    @Override
    public boolean updateCustomer (Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET custTittle=?, custName=?,custAddress=?, custCity=?, custProvince=?, custPostalCode=? WHERE custId=?");
        stm.setObject(1,c.getCustId());
        stm.setObject(2,c.getCustTittle());
        stm.setObject(3,c.getCustName());
        stm.setObject(4,c.getCustAddress());
        stm.setObject(5,c.getCity());
        stm.setObject(6,c.getProvince());
        stm.setObject(7,c.getPostalCode());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String custId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE custId='" + custId + "'").executeUpdate() > 0){
            return true;
        }else{
            return false;
        }
    }

   public Customer getCustomer (String custId) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Customer WHERE custId='" + custId + "'").
                executeQuery();
        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }else{
            return null;
        }
    }

}
