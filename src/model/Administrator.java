package model;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-14-22:00
 * @Name Administrators
 * @Projrct Shopping-Mall-Product-Management-System
 */

/*
    管理员类
 */
public class Administrator extends User{
    private String id;
    private String administratorName;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
