package model;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-14-21:51
 * @Name Commodity
 * @Projrct Shopping-Mall-Product-Management-System
 */

/*
    ��Ʒ��
 */
public class Commodity {
    private String id;//��Ʒ���
    private String type;//��Ʒ����
    private double price;//�۸�
    private Supplier supplier;//��Ӧ��
    private int number;//����

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
