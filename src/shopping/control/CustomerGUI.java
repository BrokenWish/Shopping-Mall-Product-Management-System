package shopping.control;

import model.Commodity;
import model.Supplier;
import model.User;
import service.AdministratorService;
import service.impl.AdministratorsServiceImpl;
import service.impl.UserServiceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.*;

public class CustomerGUI extends JFrame{
    private AdministratorsServiceImpl administratorService = new AdministratorsServiceImpl();

	private DefaultListModel<Commodity> productListModel;
    private DefaultListModel<Commodity> cartListModel;
    private Map<Commodity, Integer> cartMap;
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
            addButton.setPreferredSize(new Dimension(30,30));
            productPanel.add(new JLabel(commodity.getCommodityName() + "  ￥" + commodity.getPrice()+ "  剩余数量:" + commodity.getNumber()));
            productPanel.add(addButton);
        }

        // 将商品列表和加号按钮面板添加到左边面板
    
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
               Commodity product = (Commodity) button.getClientProperty("Commodity");
               removeFromCart(product);
           }
       };

       JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
       totalAmountLabel = new JLabel("总金额：");

       JButton confirmButton = new JButton("支付");
       confirmButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               try {
                   for (Map.Entry<Commodity, Integer> entry : cartMap.entrySet()) {
                       Commodity commodity = entry.getKey();
                       int quantity = entry.getValue();
                       System.out.println(commodity.toDetailString() + " 数量：" + quantity);
                       modify(commodity, quantity);
                   }
                   refreshGUI();
                   JOptionPane.showMessageDialog(null, "订单支付成功！");
                 //  new OrderGUI(Customer.getCustomerId()) ;
               } catch (Exception ex) {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(null, "An error occurred while processing the order.");
               }
           }
       });
       bottomPanel.add(totalAmountLabel);
       bottomPanel.add(confirmButton);
       rightPanel.add(bottomPanel, BorderLayout.SOUTH);

       // 添加商品列表和加号按钮
       cartMap = new HashMap<>();
       for (Commodity commodity : commodityList) {
           JButton addButton = new JButton("+");
           addButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   addToCart(commodity);
               }
           });
           productPanel.add(new JLabel(commodity.getCommodityName() + "  ￥" + commodity.getPrice() + "  剩余数量:" + commodity.getNumber()));
           productPanel.add(addButton);
       }

       // 将搜索面板和商品列表和加号按钮面板添加到左边面板
       leftPanel.add(productPanel, BorderLayout.CENTER);

       // 将左边面板和右边面板添加到主面板
       mainPanel.add(leftPanel, BorderLayout.WEST);
       mainPanel.add(rightPanel, BorderLayout.EAST);

       // 将主面板添加到主窗口中
       add(mainPanel);
       setVisible(true);
   }

   public void addToCart(Commodity commodity) {
       if (cartMap.containsKey(commodity)) {
           int quantity = cartMap.get(commodity);
           if (quantity < commodity.getNumber()) {
               cartMap.put(commodity, quantity + 1);
               totalAmount += commodity.getPrice();
               updateTotalAmountLabel();
           } else {
               JOptionPane.showMessageDialog(null, "商品数量不足！");
           }
       } else {
           cartMap.put(commodity, 1);
           totalAmount += commodity.getPrice();
           updateTotalAmountLabel();
       }
       refreshCartList();
   }

   public void removeFromCart(Commodity commodity) {
       if (cartMap.containsKey(commodity)) {
           int quantity = cartMap.get(commodity);
           if (quantity > 1) {
               cartMap.put(commodity, quantity - 1);
               totalAmount -= commodity.getPrice();
           } else {
               cartMap.remove(commodity);
               totalAmount -= commodity.getPrice();
           }
           updateTotalAmountLabel();
           refreshCartList();
       }
   }

   public void updateTotalAmountLabel() {
       totalAmountLabel.setText("总金额：" + String.format("%.2f", totalAmount));
   }

   public void refreshCartList() {
       cartListModel.clear();
       for (Map.Entry<Commodity, Integer> entry : cartMap.entrySet()) {
           Commodity commodity = entry.getKey();
           int quantity = entry.getValue();
           cartListModel.removeElement(commodity.getCommodityName() + "  数量：" + quantity);
       }
   }

   public void refreshGUI() {
       // 重新获取商品列表
       List<Commodity> commodityList = administratorService.listCommodities();

       // 清空现有的商品列表模型
       productListModel.clear();
       cartListModel.clear();
       cartMap.clear();

       // 重新添加商品到商品列表模型
       for (Commodity commodity : commodityList) {
           productListModel.addElement(commodity);
       }

       // 还需要清空购物车和总金额
       totalAmount = 0.0;
       updateTotalAmountLabel();
   }

   public void modify(Commodity commodity, int quantity) {
       Commodity byId = administratorService.findById(commodity.getId());
       int number = byId.getNumber();

       Commodity modifycommodity = new Commodity();
       modifycommodity.setId(commodity.getId());
       modifycommodity.setNumber(number - quantity);

       boolean success = administratorService.modifyCommodity(modifycommodity);

       if (success) {
           System.out.println("修改商品库存数量成功！");
       } else {
           System.out.println("修改商品库存数量失败！");
       }
   }

  public static void main(String[] args) {
       new CustomerGUI();
   }
}
  