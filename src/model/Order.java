package model;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-23-13:50
 * @Name Order
 * @Projrct AdministratorService.class
 */
public class Order {
    private String customerId;
    private Commodity commodity;
    private int orderNum;
    private LocalDateTime orderTime;
    private String orderId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    @Override
    public String toString() {
        return "Order{" +
                "customerId='" + customerId + '\'' +
                ", orderNum=" + orderNum +
                ", orderTime=" + orderTime +
                ", orderId='" + orderId + '\'' +
                '}';
    }

}
