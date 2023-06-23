package service.impl;

import model.Commodity;
import model.MySQLUtils;
import model.Supplier;
import service.UserService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                commodity.setId(rs.getString("Commodity-id"));
                commodity.setCommodityName(rs.getString("Commodity-name"));
                commodity.setType(rs.getString("Commodity-type"));
                commodity.setPrice(rs.getFloat("Commodity-price"));
                commodity.setCost(rs.getFloat("Commodity-cost"));
                commodity.setNumber(rs.getInt("Commodity-number"));
                String string = rs.getString("Commodity-vendor");

                PreparedStatement vendorPs = MySQLUtils.getConn().prepareStatement("SELECT * FROM vendor WHERE Vendor-name=?");
                vendorPs.setString(1, string);
                ResultSet vendorData = vendorPs.executeQuery();

                Supplier supplier = new Supplier();
                while (vendorData.next()) {
                    supplier.setSupplierName(vendorData.getString("Vendor-name"));
                    supplier.setId(vendorData.getString("Vendor-id"));
                    supplier.setTeltphone(vendorData.getString("Vendor-phone"));
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
        return null;
    }

    @Override
    public Commodity findById(String id) {
        Commodity commodity = null;

        try {
            PreparedStatement ps = MySQLUtils.getConn().prepareStatement("SELECT * FROM commodity WHERE commodity-id=" + id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                commodity = new Commodity();
                commodity.setId(rs.getString("Commodity-id"));
                commodity.setCommodityName(rs.getString("Commodity-name"));
                commodity.setType(rs.getString("Commodity-type"));
                commodity.setPrice(rs.getFloat("Commodity-price"));
                commodity.setCost(rs.getFloat("Commodity-cost"));
                commodity.setNumber(rs.getInt("Commodity-number"));
                String string = rs.getString("Commodity-vendor");

                PreparedStatement vendorPs = MySQLUtils.getConn().prepareStatement("SELECT * FROM vendor WHERE Vendor-name=?");
                vendorPs.setString(1, string);
                ResultSet vendorData = vendorPs.executeQuery();

                Supplier supplier = new Supplier();
                while (vendorData.next()) {
                    supplier.setSupplierName(vendorData.getString("Vendor-name"));
                    supplier.setId(vendorData.getString("Vendor-id"));
                    supplier.setTeltphone(vendorData.getString("Vendor-phone"));
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

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        Commodity commodity = userService.findById("1");


        System.out.println(commodity);
    }
}
