package service;

import model.Commodity;
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
    public List<Commodity> listCommodity();

    //查询所有用户的订单
    public List<Order> listOrder();
}
