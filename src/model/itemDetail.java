package model;

public class itemDetail {
    private String code;
    private int orderQty;
    private double disscount;

    public itemDetail() {
    }

    public itemDetail(String code, int orderQty, double disscount) {
        this.setCode(code);
        this.setOrderQty(orderQty);
        this.setDisscount(disscount);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getDisscount() {
        return disscount;
    }

    public void setDisscount(double disscount) {
        this.disscount = disscount;
    }

    @Override
    public String toString() {
        return "itemDetail{" +
                "code='" + code + '\'' +
                ", orderQty=" + orderQty +
                ", disscount=" + disscount +
                '}';
    }
}