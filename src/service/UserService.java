package service;

import model.Commodity;
import model.Order;

import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-18-15:45
 * @Name UserService
 * @Projrct Shopping-Mall-Product-Management-System
 */
public interface UserService {
    //展示所有商品
    public List<Commodity> listCommodities();

    //根据名称查询商品，可模糊查询
    public List<Commodity> findByName(String name);

    //根据id查询商品
    public Commodity findById(String id);

    //创建订单
    public void createOrder(Order order);
}
