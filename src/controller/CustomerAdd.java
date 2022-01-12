package controller;

import model.Customer;

import java.sql.SQLException;


public interface CustomerAdd {
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String custId) throws SQLException, ClassNotFoundException;
    /*public Item getItem(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException;*/
}
