package service;

import model.Commodity;
import model.Customer;

import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-18-16:42
 * @Name CustomerService
 * @Projrct Shopping-Mall-Product-Management-System
 */
public interface CustomerService {
    //查看自己的购物车
    public List<Commodity> listShoppingCart(String customerId);

    //注册用户
    public void createCustomer(Customer customer);
}
