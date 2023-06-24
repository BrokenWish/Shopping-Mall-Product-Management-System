package shopping.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorsGUI extends JFrame {


	private JLabel titleLabel;
    private JButton addButton;
    private JButton deleteButton;
    private JTable productTable;
    private JButton searchButton;
    private JTextField searchField;

    public AdministratorsGUI() {
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
                JTextField numberField = new JTextField();
                JTextField kindField = new JTextField();
                JTextField companyField = new JTextField();
                JTextField remainField = new JTextField();

                Object[] message = {
                		"商品编号:", numberField,
                        "商品名称:", nameField,
                        "商品类型:", kindField,
                        "价格:", priceField,
                        "成本:", costField,
                        "公司名称:", companyField,
                        "剩余数量:", remainField,
                };

                int option = JOptionPane.showConfirmDialog(AdministratorsGUI.this, message, "添加商品", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                	String number = numberField.getText();
                    String name = nameField.getText();
                    String kind = kindField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    double cost = Double.parseDouble(costField.getText());
                    String company = companyField.getText();
                    double remain = Double.parseDouble(remainField.getText());

                    // 在这里处理添加商品的逻辑
                    System.out.println("添加商品：" + number + "商品名称:" + name + "商品类型:" + kind + "价格：" + price + "，成本：" + cost + "公司名称：" + company + "剩余数量：" + remain);
                }
            }
        });

        // 创建删除按钮
        deleteButton = new JButton("删除商品");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(AdministratorsGUI.this, "请输入要删除的商品编号", "删除商品", JOptionPane.PLAIN_MESSAGE);
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
                        {"4","商品4" ,"食品","40.0","45.0","食品有限公司", "150"},
                        {"5","商品5","食品", "50.0","55.0","食品有限公司", "80"}
                };

                String[][] mergedData = new String[searchData.length + productTable.getRowCount()][7];
                for (int i = 0; i < productTable.getRowCount(); i++) {
                    for (int j = 0; j < 7; j++) {
                        mergedData[i][j] = productTable.getValueAt(i, j).toString();
                    }
                }
                for (int i = 0; i < searchData.length; i++) {
                    for (int j = 0; j < 7; j++) {
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
                result +="商品编号:";
                result += mergedData[0][0] + "\n";
                result +="商品名称:";
                result += mergedData[0][1] + "\n";
                result +="商品类型:";
                result += mergedData[0][2] + "\n";
                result +="价格:";
                result += mergedData[0][3] + "\n";
                result +="进价:";
                result += mergedData[0][4] + "\n";
                result +="供应商:";
                result += mergedData[0][5] + "\n";
                result +="剩余数量:";
                result += mergedData[0][6] + "\n";

                JOptionPane.showMessageDialog(AdministratorsGUI.this, result, "查找结果", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // 创建查找文本框
        searchField = new JTextField(20);

        // 创建商品列表表格
        String[] columnNames = {"商品编号", "商品名称", "商品类型","价格","进价","供应商","剩余数量"};
        Object[][] data = {
                {"1","泡面", "食品", 10.0,15.0 ,"食品有限公司",100},
                {"2","洗发水","日用品", 20.0,25.0 ,"日用品有限公司",50},
                {"3","牙刷","日用品", 30.0,35.0 ,"日用品有限公司",200}
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
            new AdministratorsGUI();
        });
    }
}
