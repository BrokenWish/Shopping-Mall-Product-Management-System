package shopping.control;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-30-19:59
 * @Name UserOrderGUI
 * @Projrct AdministratorService.class
 */
import model.Commodity;
import model.Order;
import service.impl.AdministratorsServiceImpl;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserOrderGUI extends JFrame {
    private JTable orderTable;
    private JScrollPane scrollPane;
    private static AdministratorsServiceImpl administratorsService = new AdministratorsServiceImpl();

    public UserOrderGUI(List<Order> orders) {
        setTitle("User Orders");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//正常关闭窗体
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Create a table model to hold the order data
        OrderTableModel orderTableModel = new OrderTableModel(orders);

        // Create a table using the table model
        orderTable = new JTable(orderTableModel);

        // Create a scroll pane and add the table to it
        scrollPane = new JScrollPane(orderTable);

        // Add the scroll pane to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Create some sample orders
        List<Order> orders = administratorsService.listOrder();


        // Create an instance of the UserOrderGUI and make it visible
            UserOrderGUI userOrderGUI = new UserOrderGUI(orders);
            userOrderGUI.setVisible(true);
    }
}


class OrderTableModel extends AbstractTableModel {
    private List<Order> orders;
    private String[] columnNames = {"Customer ID", "Order Number", "Order Time", "Order ID"};

    public OrderTableModel(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = orders.get(rowIndex);
//        List<Commodity> commodityList = order.getCommodityList();
//        for (Commodity commodity : commodityList) {
//            System.out.println(commodity.toDetailString());
//        }


        switch (columnIndex) {
            case 0:
                return order.getCustomerId();
            case 1:
                return order.getOrderNum();
            case 2:
                return order.getOrderTime();
            case 3:
                return order.getOrderId();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
