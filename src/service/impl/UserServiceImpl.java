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
            PreparedStatement ps = MySQLUtils.getConn().prepareStatement("SELECT * FROM commodity WHERE Commodity_name LIKE '%" + name + "%'");
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

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        List<Commodity> list = userService.findByName("因");

        for (Commodity commodity : list){
            System.out.println(commodity.getCommodityName());
        }

    }
}
