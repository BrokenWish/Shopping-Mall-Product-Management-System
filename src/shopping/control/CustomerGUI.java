package shopping.control;

import model.Commodity;
import model.User;
import service.AdministratorService;
import service.impl.AdministratorsServiceImpl;
import service.impl.UserServiceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.*;


public class CustomerGUI extends JFrame{
    private AdministratorsServiceImpl administratorService = new AdministratorsServiceImpl();

	private DefaultListModel<Commodity> productListModel;
    private DefaultListModel<Commodity> cartListModel;
    private double totalAmount = 0.0;
    private JLabel totalAmountLabel;
    public CustomerGUI() {
        setTitle("用户界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        // 创建左右分割的面板
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 创建左边商品列表和相关信息面板
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(300, 400));

        // 创建商品列表标题
        JLabel productListLabel = new JLabel("商品列表");
        productListLabel.setFont(new Font("黑体", Font.BOLD, 16));
        leftPanel.add(productListLabel, BorderLayout.NORTH);

        List<Commodity> commodityList = administratorService.listCommodities();

        // 创建商品列表的数据




        // 创建商品列表组件
        productListModel = new DefaultListModel<>();
        JList<Commodity> productListUI = new JList<>(productListModel);

        // 创建商品列表面板
        JPanel productListPanel = new JPanel(new BorderLayout());
        productListPanel.add(new JScrollPane(productListUI), BorderLayout.CENTER);//滚动视图

        // 创建商品列表和加号按钮面板
        JPanel productPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        // 创建加号按钮的监听器
        ActionListener addButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                Commodity product =  (Commodity) button.getClientProperty("Commodity");
                addToCart(product);
            }
        };

        // 添加商品列表和加号按钮
        for (Commodity commodity : commodityList) {
            JButton addButton = new JButton("+");
            addButton.putClientProperty("Commodity", commodity);
            addButton.addActionListener(addButtonListener);
            productPanel.add(new JLabel(commodity.getCommodityName() + "  ￥" + commodity.getPrice()+ "  剩余数量:" + commodity.getNumber()));
            productPanel.add(addButton);
        }

        // 将搜索面板和商品列表和加号按钮面板添加到左边面板
    
        leftPanel.add(productPanel, BorderLayout.CENTER);

        // 创建右边购物车面板
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(new Dimension(400, 400));

        // 创建购物车标题
        JLabel cartLabel = new JLabel("购物车");
        cartLabel.setFont(new Font("黑体", Font.BOLD, 16));
        rightPanel.add(cartLabel, BorderLayout.NORTH);

        // 创建购物车商品列表组件
        cartListModel = new DefaultListModel<>();
        JList<Commodity> cartListUI = new JList<>(cartListModel);
        rightPanel.add(new JScrollPane(cartListUI), BorderLayout.CENTER);

        // 创建减号按钮的监听器
        ActionListener minusButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                Commodity product = (Commodity) button.getClientProperty("product");
                removeFromCart(product);
            }
        };

        // 创建减号按钮
        JButton minusButton = new JButton("-");
        minusButton.addActionListener(minusButtonListener);
        rightPanel.add(minusButton, BorderLayout.SOUTH);

        // 创建总金额和确认按钮面板
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalAmountLabel = new JLabel("总金额：");
     
        JButton confirmButton = new JButton("支付");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "订单支付成功！");
            }
        });
        bottomPanel.add(totalAmountLabel);
 
        bottomPanel.add(confirmButton);
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        // 将左边面板和右边面板添加到主面板
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        // 将主面板添加到主窗口中
        add(mainPanel);
        setVisible(true);
    }

    public void addToCart(Commodity product) {
        cartListModel.addElement(product);
        totalAmount += product.getPrice();
        updateTotalAmountLabel();
    }

    public void removeFromCart(Commodity product) {
        cartListModel.removeElement(product);
        totalAmount -= product.getPrice();
        updateTotalAmountLabel();
    }

    public void updateTotalAmountLabel() {
        totalAmountLabel.setText("总金额：" + String.format("%.2f", totalAmount));
    }


   
    public static void main(String[] args) {

                new CustomerGUI();
            }
  

}