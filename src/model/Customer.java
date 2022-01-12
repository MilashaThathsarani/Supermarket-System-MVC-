package model;

public class Customer {
    private String custId;
    private String custTittle;
    private String custName;
    private String custAddress;
    private String city;
    private String province;
    private String postalCode;

    public Customer() {
    }

    public Customer(String custId, String custTittle, String custName, String custAddress, String city, String province, String postalCode) {
        this.setCustId(custId);
        this.setCustTittle(custTittle);
        this.setCustName(custName);
        this.setCustAddress(custAddress);
        this.setCity(city);
        this.setProvince(province);
        this.setPostalCode(postalCode);
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustTittle() {
        return custTittle;
    }

    public void setCustTittle(String custTittle) {
        this.custTittle = custTittle;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId='" + custId + '\'' +
                ", custTittle='" + custTittle + '\'' +
                ", custName='" + custName + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
