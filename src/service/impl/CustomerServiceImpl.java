package service.impl;

import model.*;
import service.CustomerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-18-16:47
 * @Name CustomerServiceImpl
 * @Projrct Shopping-Mall-Product-Management-System
 */
public class CustomerServiceImpl extends UserServiceImpl implements CustomerService {

    private static int CUSTOMERID;

//    @Override
//    public List<Commodity> listShoppingCart(String customerId) {
//        List<Commodity> commodityList = new ArrayList<>();
//
//        String query = "SELECT sc.Commodity_id, sc.Commodity_name, sc.Shopping_Cart_number, c.Commodity_type, c.Commodity_price, c.Commodity_cost, v.Vendor_name, v.Vendor_id, v.Vendor_phone " +
//                "FROM shopping_cart sc " +
//                "JOIN commodity c ON sc.Commodity_id = c.Commodity_id " +
//                "JOIN vendor v ON c.Commodity_vendor = v.Vendor_name " +
//                "WHERE sc.Customer_id = ?";
//
//        try (Connection conn = MySQLUtils.getConn();
//             PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setString(1, customerId);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Commodity commodity = new Commodity();
//                commodity.setId(rs.getString("Commodity_id"));
//                commodity.setCommodityName(rs.getString("Commodity_name"));
//                commodity.setNumber(rs.getInt("Shopping_Cart_number"));
//                commodity.setType(rs.getString("Commodity_type"));
//                commodity.setPrice(rs.getFloat("Commodity_price"));
//                commodity.setCost(rs.getFloat("Commodity_cost"));
//
//                Supplier supplier = new Supplier();
//                supplier.setSupplierName(rs.getString("Vendor_name"));
//                supplier.setId(rs.getString("Vendor_id"));
//                supplier.setTeltphone(rs.getString("Vendor_phone"));
//
//                commodity.setSupplier(supplier);
//                commodityList.add(commodity);
//            }
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return commodityList;
//    }

    @Override
    public void createCustomer(Customer customer) {
        try {
            PreparedStatement psc = MySQLUtils.getConn().prepareStatement("SELECT * FROM customer ORDER BY Customer_id DESC");
            ResultSet rs = psc.executeQuery();
            if (rs.next()) {
                CUSTOMERID = Integer.parseInt(rs.getString("Customer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        try {
            Connection conn = MySQLUtils.getConn();
            PreparedStatement ps = null;


                //创建prepareStatement对象
                String sql = "insert into customer values (?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                //执行sql语句
                ps.setString(1, String.valueOf( ++CUSTOMERID));
                ps.setString(2,customer.getCustomerName());
                ps.setString(3,customer.getCusomerPhone());
                ps.setString(4,customer.getUserName());
                ps.setString(5,customer.getPassword());


            System.out.println(ps.execute());
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

//    public static void main(String[] args) {
//        CustomerService customerService = new CustomerServiceImpl();
//
//        List<Commodity> commodityList = customerService.listShoppingCart("2001");
//
//        for (Commodity commodity : commodityList){
//            System.out.println(commodity.getCommodityName());
//        }
//    }
}
