package shopping.control;

import model.Commodity;
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
    private JTextField txtOrderNumber;
    private JTextField txtOrderAmount;
    private JTextField txtSum;
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
        lblSum = new JLabel("总金额：");
        txtOrderNumber = new JTextField(20);
        txtOrderAmount = new JTextField(20);
        txtSum = new JTextField("10");

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



        lblOrderNumber.setBounds(10,10,50,20);
        lblOrderAmount.setBounds(10,40,90,20);
        lblOrderDetail.setBounds(10,70,110,20);
        lblSum.setBounds(10,280,50,20);
        txtOrderNumber.setBounds(70,10,130,20);
        txtOrderAmount.setBounds(70,40,130,20);
        productList.setBounds(70,70,200,200);
        txtSum.setBounds(70,280,80,20);
        commitButton.setBounds(105,330,90,20);

        add(lblOrderNumber);
        add(txtOrderNumber);
        add(lblOrderAmount);
        add(txtOrderAmount);
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