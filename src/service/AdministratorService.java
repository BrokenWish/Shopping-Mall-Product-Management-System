package service;

import model.Administrator;
import model.Commodity;
import model.Customer;
import model.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-18-16:44
 * @Name Administrators
 * @Projrct Shopping-Mall-Product-Management-System
 */
public interface AdministratorService {
    //增加
    public boolean addCommodity(Commodity commodity);

    //修改
    public boolean modifyCommodity(Commodity commodity);

    //删除
    public boolean deleteCommodity(String commodityName);

    //查询用户
    public List<Customer> listCustomer();

    //根据用户名查询用户
    public Customer findCustomerByAccount(String account);

    //根据用户名ID查询用户
    public Customer findCustomerByID(String ID);

    //根据用户名查询管理员
    public Administrator findAdministraotrByAccount(String account);

    //查询所有用户的订单
    public List<Order> listOrder();

    //根据用户名查订单
    public Order findOrderById(String customerId);
}
