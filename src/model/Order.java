package model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-23-13:50
 * @Name Order
 * @Projrct AdministratorService.class
 */
public class Order {
    private String customerId;
    private List<Commodity> commodityList;
    private int orderNum;
    private LocalDateTime orderTime;
    private String orderId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
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
        String str = "Order{" +
                "customerId='" + customerId + '\'' +
                ", orderNum=" + orderNum +
                ", orderTime=" + orderTime +
                ", orderId='" + orderId + '\'' +
                '}';

        for (Commodity commodity : commodityList) {
            str += commodity.getCommodityName() + " " + commodity.getNumber();
        }


        return str;
    }

}
