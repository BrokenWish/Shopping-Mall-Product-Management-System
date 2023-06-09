package service.impl;

import model.Commodity;
import model.MySQLUtils;
import model.Order;
import model.Supplier;
import service.UserService;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-18-16:47
 * @Name UserServiceImpl
 * @Projrct Shopping-Mall-Product-Management-System
 */
public class UserServiceImpl implements UserService {
    MySQLUtils mySQLUtils = new MySQLUtils();

    @Override
    public List<Commodity> listCommodities() {
        List<Commodity> commodityList = new ArrayList<>();

        try {
            PreparedStatement ps = MySQLUtils.getConn().prepareStatement("SELECT * FROM commodity");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Commodity commodity = new Commodity();
                commodity.setId(rs.getString("Commodity_id"));
                commodity.setCommodityName(rs.getString("Commodity_name"));
                commodity.setType(rs.getString("Commodity_type"));
                commodity.setPrice(rs.getFloat("Commodity_price"));
                commodity.setCost(rs.getFloat("Commodity_cost"));
                commodity.setNumber(rs.getInt("Commodity_number"));
                String string = rs.getString("Commodity_vendor");

                PreparedStatement vendorPs = MySQLUtils.getConn().prepareStatement("SELECT * FROM vendor WHERE Vendor_name=?");
                vendorPs.setString(1, string);
                ResultSet vendorData = vendorPs.executeQuery();

                Supplier supplier = new Supplier();
                while (vendorData.next()) {
                    supplier.setSupplierName(vendorData.getString("Vendor_name"));
                    supplier.setId(vendorData.getString("Vendor_id"));
                    supplier.setTeltphone(vendorData.getString("Vendor_phone"));
                }

                commodity.setSupplier(supplier);
                commodityList.add(commodity);

                vendorData.close();
                vendorPs.close();
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commodityList;
    }

    @Override
    public List<Commodity> findByName(String name) {
        List<Commodity> commodityList = new ArrayList<>();

        try {
            PreparedStatement ps = MySQLUtils.getConn().prepareStatement("SELECT * FROM commodity WHERE Commodity_name LIKE ?");
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Commodity commodity = new Commodity();
                commodity.setId(rs.getString("Commodity_id"));
                commodity.setCommodityName(rs.getString("Commodity_name"));
                commodity.setType(rs.getString("Commodity_type"));
                commodity.setPrice(rs.getFloat("Commodity_price"));
                commodity.setCost(rs.getFloat("Commodity_cost"));
                commodity.setNumber(rs.getInt("Commodity_number"));
                String string = rs.getString("Commodity_vendor");

                PreparedStatement vendorPs = MySQLUtils.getConn().prepareStatement("SELECT * FROM vendor WHERE Vendor_name=?");
                vendorPs.setString(1, string);
                ResultSet vendorData = vendorPs.executeQuery();

                Supplier supplier = new Supplier();
                while (vendorData.next()) {
                    supplier.setSupplierName(vendorData.getString("Vendor_name"));
                    supplier.setId(vendorData.getString("Vendor_id"));
                    supplier.setTeltphone(vendorData.getString("Vendor_phone"));
                }

                commodity.setSupplier(supplier);
                commodityList.add(commodity);

                vendorData.close();
                vendorPs.close();
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commodityList;
    }

    @Override
    public Commodity findById(String id) {
        Commodity commodity = new Commodity();

        try {
            PreparedStatement ps = MySQLUtils.getConn().prepareStatement("SELECT * FROM commodity WHERE commodity_id=" + id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                commodity = new Commodity();
                commodity.setId(rs.getString("Commodity_id"));
                commodity.setCommodityName(rs.getString("Commodity_name"));
                commodity.setType(rs.getString("Commodity_type"));
                commodity.setPrice(rs.getFloat("Commodity_price"));
                commodity.setCost(rs.getFloat("Commodity_cost"));
                commodity.setNumber(rs.getInt("Commodity_number"));
                String string = rs.getString("Commodity_vendor");

                PreparedStatement vendorPs = MySQLUtils.getConn().prepareStatement("SELECT * FROM vendor WHERE Vendor_name=?");
                vendorPs.setString(1, string);
                ResultSet vendorData = vendorPs.executeQuery();

                Supplier supplier = new Supplier();
                while (vendorData.next()) {
                    supplier.setSupplierName(vendorData.getString("Vendor_name"));
                    supplier.setId(vendorData.getString("Vendor_id"));
                    supplier.setTeltphone(vendorData.getString("Vendor_phone"));
                }

                commodity.setSupplier(supplier);

                vendorData.close();
                vendorPs.close();
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commodity;
    }

    @Override
    public void createOrder(Order order) {
        try {
            // 创建数据库连接
            Connection connection = MySQLUtils.getConn();

            List<Commodity> commodityList = order.getCommodityList();

            for (Commodity commodity : commodityList) {
                try{

                    // 创建插入订单数据的SQL语句
                    String sql = "INSERT INTO `order` VALUES (?, ?, ?, ?, ?, ?, ? )";

                    // 创建PreparedStatement对象
                    PreparedStatement statement = connection.prepareStatement(sql);

                    // 设置参数值
                    statement.setString(1, order.getCustomerId());
                    statement.setString(2, commodity.getId());
                    statement.setString(3, commodity.getCommodityName());
                    statement.setInt(4, order.getOrderNum());
                    Date date = new Date();     //先获取一个Date对象
                    DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
                    String format = simpleDateFormat.format(date);


                    statement.setString(5, format);
                    statement.setFloat(6, (float) commodity.getPrice());
                    statement.setString(7, order.getOrderId());

                    // 执行插入操作
                    statement.execute();

                    // 关闭连接和语句对象
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        UserServiceImpl userService = new UserServiceImpl();
//
//        List<Commodity> list = userService.findByName("鸦片");
//
//        for (Commodity commodity : list){
//            System.out.println(commodity.toDetailString());
//        }
//
//    }
}
