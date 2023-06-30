package shopping.control;

import model.Commodity;
import model.Customer;
import model.Order;
import service.impl.AdministratorsServiceImpl;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-30-10:32
 * @Name OrderGUI
 * @Projrct AdministratorService.class
 */

public class OrderGUI extends JFrame {
    private AdministratorsServiceImpl administratorsService = new AdministratorsServiceImpl();

    private JLabel lblOrderNumber;
    private JLabel lblOrderAmount;
    private JLabel lblOrderDetail;
    private JLabel lblSum;
    private JLabel lblName;
    private JTextField txtOrderNumber;
    private JTextField txtOrderAmount;
    private JTextField txtSum;
    private JTextField txtName;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> productList = new JList<>(model);
    private JButton commitButton;

    public OrderGUI(String customerId) {
        setTitle("订单界面");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = size.width;
        int screenHeight = size.height;
        int width = this.getWidth();
        int height = this.getHeight();
        setLocation((screenWidth - width) / 2 - 150,(screenHeight - height) / 2 - 200) ;
        setSize(300,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//正常关闭窗体
        setLayout(null);


        lblOrderNumber = new JLabel("订单号：");
        lblOrderAmount = new JLabel("下单时间：");
        lblOrderDetail = new JLabel("订单详情");
        lblName = new JLabel("用户名：");
        lblSum = new JLabel("总金额：");
        txtOrderNumber = new JTextField(20);
        txtOrderAmount = new JTextField(20);
        txtSum = new JTextField("10");
        txtName = new JTextField("15");

        commitButton = new JButton("支付订单");


        double sum = 0;
        Order orderById = administratorsService.findOrderById(customerId);
        List<Commodity> commodityList = orderById.getCommodityList();
        for (Commodity commodity : commodityList) {
            DefaultListModel<String> model = (DefaultListModel<String>) productList.getModel();
            model.addElement(commodity.getCommodityName() + " ×" + commodity.getNumber());
            Commodity byId = administratorsService.findById(commodity.getId());
            sum = sum + byId.getPrice() * commodity.getNumber();
        }

        txtOrderNumber.setText(orderById.getOrderId());
        txtSum.setText(String.valueOf(sum));
        txtOrderNumber.setEditable(false);
        txtSum.setEditable(false);

        txtOrderAmount.setText(String.valueOf(orderById.getOrderTime()));
        txtOrderAmount.setEditable(false);

        Customer customerByAccount = administratorsService.findCustomerByID(customerId);
        txtName.setText(customerByAccount.getCustomerName());
        txtName.setEditable(false);


        lblOrderNumber.setBounds(10,10,50,20);
        lblOrderAmount.setBounds(10,40,90,20);
        lblName.setBounds(10,70,50,20);
        lblOrderDetail.setBounds(10,100,110,20);
        lblSum.setBounds(10,300,50,20);
        txtOrderNumber.setBounds(70,10,130,20);
        txtOrderAmount.setBounds(70,40,130,20);
        txtName.setBounds(70,70,120,20);
        productList.setBounds(70,100,200,180);
        txtSum.setBounds(70,300,80,20);
        commitButton.setBounds(105,330,90,20);

        add(lblOrderNumber);
        add(txtOrderNumber);
        add(lblOrderAmount);
        add(txtOrderAmount);
        add(lblName);
        add(txtName);
        add(lblOrderDetail);
        add(productList);
        add(lblSum);
        add(txtSum);
        add(commitButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new OrderGUI("2001");
    }


}