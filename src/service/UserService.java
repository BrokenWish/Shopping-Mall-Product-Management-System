package service;

import model.Commodity;

import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-18-15:45
 * @Name UserService
 * @Projrct Shopping-Mall-Product-Management-System
 */
public interface UserService {
    public List<Commodity> list();

    public Commodity findByName(String name);

    public Commodity findById(String id);
}
