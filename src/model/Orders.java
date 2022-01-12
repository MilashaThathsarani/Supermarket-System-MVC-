package model;

import java.util.ArrayList;

public class Orders {
     private String ordersId;
     private String cId;
     private String orderDate;
     private ArrayList<itemDetail> items;

     public Orders() {
     }

     public Orders(String ordersId, String cId, String orderDate, ArrayList<itemDetail> items) {
          this.setOrderId(ordersId);
          this.setcId(cId);
          this.setOrderDate(orderDate);
          this.setItems(items);
     }

     public String getOrderId() {
          return ordersId;
     }

     public void setOrderId(String orderId) {
          this.ordersId = orderId;
     }

     public String getcId() {
          return cId;
     }

     public void setcId(String cId) {
          this.cId = cId;
     }

     public String getOrderDate() {
          return orderDate;
     }

     public void setOrderDate(String orderDate) {
          this.orderDate = orderDate;
     }

     public ArrayList<itemDetail> getItems() {
          return items;
     }

     public void setItems(ArrayList<itemDetail> items) {
          this.items = items;
     }

     @Override
     public String toString() {
          return "Orders{" +
                  "orderId='" + ordersId + '\'' +
                  ", cId='" + cId + '\'' +
                  ", orderDate='" + orderDate + '\'' +
                  ", items=" + items +
                  '}';
     }
}