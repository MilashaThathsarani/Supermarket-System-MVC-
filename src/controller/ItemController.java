package controller;

import db.DbConnection;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemController implements ItemService {
    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item " +
                        "").executeQuery();
        List<String> ids= new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public ItemController() throws SQLException, ClassNotFoundException {
    }

    @Override
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,i.getItemCode());
        stm.setObject(2,i.getDescription());
        stm.setObject(3,i.getPackSize());
        stm.setObject(4,i.getUnitPrice());
        stm.setObject(5,i.getQtyOnHand());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET description=?, packSize=?,unitPrice=?, qtyOnHand=? WHERE itemCode=?");
        stm.setObject(1,i.getDescription());
        stm.setObject(2,i.getPackSize());
        stm.setObject(3,i.getUnitPrice());
        stm.setObject(4,i.getQtyOnHand());
        stm.setObject(5,i.getItemCode());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE itemCode='" + itemCode + "'").executeUpdate() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Item getItem(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item WHERE itemCode='" + itemCode + "'").
                executeQuery();
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)



            );
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)


            ));
        }
        return items;
    }
}
