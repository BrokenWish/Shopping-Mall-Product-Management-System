package model;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-14-21:56
 * @Name Supplier
 * @Projrct Shopping-Mall-Product-Management-System
 */

/*
    ��Ӧ����
 */
public class Supplier {
    private String id;//��Ӧ�̱��
    private String supplierName;//��Ӧ������
    private String teltphone;//��ϵ�绰

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String name) {
        this.supplierName = name;
    }

    public String getTeltphone() {
        return teltphone;
    }

    public void setTeltphone(String teltphone) {
        this.teltphone = teltphone;
    }
}
