package model;

import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-14-21:57
 * @Name User
 * @Projrct Shopping-Mall-Product-Management-System
 */

/*
    �û���
    ��Ϊ�ͻ��͹���Ա�ĸ���
 */
public class User {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
