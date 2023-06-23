package service.impl;

import model.*;
import service.AdministratorService;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-18-16:47
 * @Name AdministratorsServiceImpl
 * @Projrct Shopping-Mall-Product-Management-System
 */
public class AdministratorsServiceImpl extends UserServiceImpl implements AdministratorService {

    private static int COMMODITY_ID;

    @Override
    public void addCommodity(Commodity commodity) {

        try {
            PreparedStatement psc = MySQLUtils.getConn().prepareStatement("SELECT COUNT(*) FROM commodity");
            ResultSet rs = psc.executeQuery();
            if (rs.next()) {
                COMMODITY_ID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Connection conn = MySQLUtils.getConn();
        PreparedStatement ps = null;
        try {



            //创建prepareStatement对象
            String sql = "insert into commodity values (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //执行sql语句
            ps.setString(1, String.valueOf(++COMMODITY_ID));
            ps.setString(2,commodity.getCommodityName());
            ps.setString(3,commodity.getType());
            ps.setDouble(4,commodity.getPrice());
            ps.setDouble(5,commodity.getCost());
            ps.setString(6,commodity.getSupplier().getSupplierName());
            ps.setInt(7,commodity.getNumber());

            //java.util.Date utilDate = new java.util.Date();//进行类型转换，由util类型的date转化为sql类型的
            //ps.execute();
            // 执行
            System.out.println(ps.execute());//执行表输出返回的结果，结果为false，因为没有返回的结果集

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyCommodity(Commodity commodity) {

    }

    @Override
    public List<Customer> listCustomer() {
        List<Customer> customerList = new ArrayList<>();

        try {
            PreparedStatement ps = MySQLUtils.getConn().prepareStatement("SELECT * FROM customer");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("Customer_id"));
                customer.setCustomerName(rs.getString("Customer_name"));
                customer.setUserName(rs.getString("Customer_account"));
                customer.setCusomerPhone(rs.getString("Customer_phone"));

                customerList.add(customer);

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    @Override
    public List<Order> listOrder() {
        List<Order> orderList = new ArrayList<>();


        try {
            PreparedStatement ps = MySQLUtils.getConn().prepareStatement("SELECT * FROM `order`");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getString("Order_id"));
                order.setOrderNum(rs.getInt("Order_number"));
                Timestamp timestamp = rs.getTimestamp("Order_time");
                order.setOrderTime(timestamp.toLocalDateTime());
                order.setCustomerId(rs.getString("Customer_id"));

                orderList.add(order);

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

}
