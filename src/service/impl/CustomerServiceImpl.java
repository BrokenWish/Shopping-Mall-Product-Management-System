package service.impl;

import model.Commodity;
import model.MySQLUtils;
import model.Supplier;
import service.CustomerService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public List<Commodity> listShoppingCart(String customerId) {
        List<Commodity> commodityList = new ArrayList<>();

        try {
            PreparedStatement ps = MySQLUtils.getConn().prepareStatement("SELECT * FROM shopping_cart WHERE Commodity_id=" + customerId);
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
}
