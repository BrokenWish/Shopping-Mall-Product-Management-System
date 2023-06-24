package shopping.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-24-10:16
 * @Name AdminstratorGUI
 * @Projrct AdministratorService.class
 */
public class AdminstratorGUI extends JFrame{
    private JLabel titleLabel;
    private JButton addButton;
    private JButton deleteButton;
    private JTable productTable;
    private JButton searchButton;
    private JTextField searchField;

    public AdminstratorGUI() {
        // 设置窗口标题
        setTitle("商城系统管理");

        // 创建标题标签
        titleLabel = new JLabel("商品列表");
        titleLabel.setFont(new Font("黑体", Font.BOLD, 20));


        // 创建添加按钮
        addButton = new JButton("添加商品");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 弹出输入对话框，要求输入商品名称、价格和成本
                JTextField nameField = new JTextField();
                JTextField priceField = new JTextField();
                JTextField costField = new JTextField();

                Object[] message = {
                        "商品名称:", nameField,
                        "价格:", priceField,
                        "成本:", costField
                };

                int option = JOptionPane.showConfirmDialog(AdminstratorGUI.this, message, "添加商品", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String name = nameField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    double cost = Double.parseDouble(costField.getText());

                    // 在这里处理添加商品的逻辑
                    System.out.println("添加商品：" + name + "，价格：" + price + "，成本：" + cost);
                }
            }
        });

        // 创建删除按钮
        deleteButton = new JButton("删除商品");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(AdminstratorGUI.this, "请输入要删除的商品名", "删除商品", JOptionPane.PLAIN_MESSAGE);
                if (input != null && !input.isEmpty()) {
                    // 在这里处理删除商品的逻辑
                    System.out.println("删除商品：" + input);
                }
            }
        });

        // 创建查找按钮
        searchButton = new JButton("查找");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                // 在这里处理根据文本框内容查找商品的逻辑
                String[][] searchData = {
                        {"商品4", "40.0", "150"},
                        {"商品5", "50.0", "80"}
                };

                String[][] mergedData = new String[searchData.length + productTable.getRowCount()][3];
                for (int i = 0; i < productTable.getRowCount(); i++) {
                    for (int j = 0; j < 3; j++) {
                        mergedData[i][j] = productTable.getValueAt(i, j).toString();
                    }
                }
                for (int i = 0; i < searchData.length; i++) {
                    for (int j = 0; j < 3; j++) {
                        mergedData[i + productTable.getRowCount()][j] = searchData[i][j];
                    }
                }

                String result = "查找结果：\n";
//                result += "商品名称  价格   成本\n";
//                for (int i = 0; i < mergedData.length; i++) {
//                    if (mergedData[i][0].contains(searchText)) {
//                        for (int j = 0; j < 3; j++) {
//                            result += mergedData[i][j] + "  ";
//                        }
//                        result += "\n";
//                    }
//                };
                result +="商品名称:";
                result += mergedData[0][0] + "\n";
                result +="价格:";
                result += mergedData[0][1] + "\n";
                result +="成本:";
                result += mergedData[0][2] + "\n";

                JOptionPane.showMessageDialog(AdminstratorGUI.this, result, "查找结果", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 创建查找文本框
        searchField = new JTextField(20);

        // 创建商品列表表格
        String[] columnNames = {"商品名称", "价格", "成本"};
        Object[][] data = {
                {"商品1", 10.0, 100},
                {"商品2", 20.0, 50},
                {"商品3", 30.0, 200}
        };
        productTable = new JTable(data, columnNames);

        // 设置布局管理器为GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 添加查找文本框到窗口的左上角
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(searchField, gbc);

        // 添加查找按钮到窗口的左上角
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(searchButton, gbc);

        // 添加标题标签到窗口的左上角
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(titleLabel, gbc);

        // 添加添加按钮到窗口的左上角
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(addButton, gbc);

        // 添加删除按钮到窗口的左上角
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(deleteButton, gbc);

        // 添加商品列表表格到窗口的中间
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 0, 10, 10);
        add(new JScrollPane(productTable), gbc);

        Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth=(int) size.getWidth();
        int screenHeight=(int) size.getHeight();
        int x=(screenWidth-getWidth())/2;
        int y=(screenHeight-getHeight())/2;
        setLocation(0,0);
//        setLocationRelativeTo(null);
        // 设置窗口大小和可见性
        setSize(screenWidth, screenHeight-100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminstratorGUI();
        });
    }
}