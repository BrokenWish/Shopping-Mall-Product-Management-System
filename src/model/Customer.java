package model;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-14-21:59
 * @Name Customer
 * @Projrct Shopping-Mall-Product-Management-System
 */

/*
    客户类
 */
public class Customer extends User{
    private String customerId;
    private String customerName;
    private String cusomerPhone;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCusomerPhone() {
        return cusomerPhone;
    }

    public void setCusomerPhone(String cusomerPhone) {
        this.cusomerPhone = cusomerPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + super.getUserName() + '\'' +
                ", customerPassword='" + super.getPassword() + '\'' +
                ", cusomerPhone='" + cusomerPhone + '\'' +
                '}';
    }
}
