package service;

import model.Administrator;
import model.Commodity;
import model.Customer;
import model.Order;

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
    public void addCommodity(Commodity commodity);

    //删除

    //修改
    public void modifyCommodity(Commodity commodity);

    //查询用户
    public List<Customer> listCustomer();

    //根据用户名查询用户
    public Customer findCustomerByAccount(String account);

    ////根据用户名查询管理员
    public Administrator findAdministraotrByAccount(String account);

    //查询所有用户的订单
    public List<Order> listOrder();
}
