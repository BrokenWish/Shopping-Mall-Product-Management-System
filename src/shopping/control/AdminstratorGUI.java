package shopping.control;

import model.Commodity;
import model.Supplier;
import service.AdministratorService;
import service.impl.AdministratorsServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminstratorGUI extends JFrame {
    private AdministratorsServiceImpl administratorsService = new AdministratorsServiceImpl();

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
                JTextField kindField = new JTextField();
                JTextField companyField = new JTextField();
                JTextField remainField = new JTextField();

                Object[] message = {
                        "商品名称:", nameField,
                        "商品类型:", kindField,
                        "价格:", priceField,
                        "成本:", costField,
                        "供应商:", companyField,
                        "商品数量:", remainField,
                };

                int option = JOptionPane.showConfirmDialog(AdminstratorGUI.this, message, "添加商品", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    if (nameField.getText().isEmpty() || kindField.getText().isEmpty() || priceField.getText().isEmpty() || costField.getText().isEmpty() || companyField.getText().isEmpty() || remainField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(AdminstratorGUI.this, "所有信息都不能为空", "添加商品", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String name = nameField.getText();
                    String kind = kindField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    double cost = Double.parseDouble(costField.getText());
                    String company = companyField.getText();
                    double remain = Double.parseDouble(remainField.getText());


                    Commodity commodity = new Commodity();
                    commodity.setCommodityName(name);
                    commodity.setType(kind);
                    commodity.setPrice(price);
                    commodity.setCost(cost);
                    Supplier supplier = new Supplier();
                    supplier.setSupplierName(company);
                    commodity.setSupplier(supplier);
                    commodity.setNumber((int) remain);

                    boolean success = administratorsService.addCommodity(commodity);

                    if (success) {
                        JOptionPane.showMessageDialog(AdminstratorGUI.this, "新增商品成功", "新增商品", JOptionPane.INFORMATION_MESSAGE);
                        new AdminstratorGUI();
                    } else {
                        JOptionPane.showMessageDialog(AdminstratorGUI.this, "新增商品失败，该商品可能已经存在", "新增商品", JOptionPane.ERROR_MESSAGE);
                    }

                    }
            }
        });

        // 创建删除按钮
        deleteButton = new JButton("删除商品");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(AdminstratorGUI.this, "请输入要删除的商名称", "删除商品", JOptionPane.PLAIN_MESSAGE);
                if (input != null && !input.isEmpty()) {
                    // 在这里处理删除商品的逻辑
                    boolean success = administratorsService.deleteCommodity(input);

                    if (success) {
                        JOptionPane.showMessageDialog(AdminstratorGUI.this, "删除商品成功", "删除商品", JOptionPane.INFORMATION_MESSAGE);
                        new AdminstratorGUI();
                    } else {
                        JOptionPane.showMessageDialog(AdminstratorGUI.this, "删除商品失败，请检查商品名称是否正确", "删除商品", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
     // 创建查找按钮
        searchButton = new JButton("查找");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                // 在这里处理根据文本框内容查找商品的逻辑

                List<Commodity> searchData = administratorsService.findByName(searchText);



                String[][] searchDataArray = new String[searchData.size()][];
                for (int i = 0; i < searchData.size(); i++) {
                    Commodity commodity = searchData.get(i);
                    searchDataArray[i] = new String[] {
                            commodity.getId(),
                            commodity.getCommodityName(),
                            commodity.getType(),
                            String.valueOf(commodity.getPrice()),
                            String.valueOf(commodity.getCost()),
                            commodity.getSupplier().getSupplierName(),
                            String.valueOf(commodity.getNumber())
                    };
                }

                String[][] mergedData = new String[searchDataArray.length + productTable.getRowCount()][7];
                for (int i = 0; i < productTable.getRowCount(); i++) {
                    for (int j = 0; j < 7; j++) {
                        mergedData[i][j] = productTable.getValueAt(i, j).toString();
                    }
                }
                for (int i = 0; i < searchDataArray.length; i++) {
                    for (int j = 0; j < 7; j++) {
                        mergedData[i + productTable.getRowCount()][j] = searchDataArray[i][j];
                    }
                }

                String result = "查找结果：\n\n";
                boolean found = false;

                for (int i = 0; i < mergedData.length - searchData.size(); i++) {
                    if (mergedData[i][1].contains(searchText)) {
                        found = true;
                        result += "商品编号：" + mergedData[i][0] + "\n";
                        result += "商品名称：" + mergedData[i][1] + "\n";
                        result += "商品类型：" + mergedData[i][2] + "\n";
                        result += "价格：" + mergedData[i][3] + "\n";
                        result += "进价：" + mergedData[i][4] + "\n";
                        result += "供应商：" + mergedData[i][5] + "\n";
                        result += "剩余数量：" + mergedData[i][6] + "\n\n";
                    }
                }

                if (!found) {
                    result += "没有找到匹配的商品。";
                }

                JTextArea textArea = new JTextArea(result);
                textArea.setEditable(false);
                JOptionPane.showMessageDialog(AdminstratorGUI.this, new JScrollPane(textArea), "查找商品", JOptionPane.PLAIN_MESSAGE);

            }
        });
        
        // 创建查找文本框
        searchField = new JTextField(20);

        List<Commodity> data = administratorsService.listCommodities();

        // 将 List 转换为 Object[][]
        Object[][] dataArray = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            Commodity commodity = data.get(i);
            dataArray[i] = new Object[] {
                    commodity.getId(),
                    commodity.getCommodityName(),
                    commodity.getType(),
                    commodity.getPrice(),
                    commodity.getCost(),
                    commodity.getSupplier().getSupplierName(),
                    commodity.getNumber()
            };
        }

// 创建商品列表表格
        String[] columnNames = {"商品编号", "商品名称", "商品类型","价格","进价","供应商","剩余数量"};
        productTable = new JTable(dataArray, columnNames);

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

        // 创建修改按钮
        JButton updateButton = new JButton("修改商品");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取选中的行索引
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(AdminstratorGUI.this, "请先选择要修改的商品", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 获取选中行的商品编号
                String productId = productTable.getValueAt(selectedRow, 0).toString();

                // 弹出输入对话框，要求输入修改后的商品信息
                JTextField numField = new JTextField(productTable.getValueAt(selectedRow, 0).toString());
                JTextField nameField = new JTextField(productTable.getValueAt(selectedRow, 1).toString());
                JTextField kindField = new JTextField(productTable.getValueAt(selectedRow, 2).toString());
                JTextField priceField = new JTextField(productTable.getValueAt(selectedRow, 3).toString());
                JTextField costField = new JTextField(productTable.getValueAt(selectedRow, 4).toString());
                JTextField companyField = new JTextField(productTable.getValueAt(selectedRow, 5).toString());
                JTextField remainField = new JTextField(productTable.getValueAt(selectedRow, 6).toString());

                Object[] message = {
                        "商品编号:",numField,
                        "商品名称:", nameField,
                        "商品类型:", kindField,
                        "价格:", priceField,
                        "成本:", costField,
                        "公司名称:", companyField,
                        "剩余数量:", remainField,
                };

                int option = JOptionPane.showConfirmDialog(AdminstratorGUI.this, message, "修改商品", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String num = numField.getText();
                    String name = nameField.getText();
                    String kind = kindField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    double cost = Double.parseDouble(costField.getText());
                    String company = companyField.getText();
                    double remain = Double.parseDouble(remainField.getText());

                    // 在这里处理修改商品的逻辑
                    System.out.println("修改商品：" + productId + "商品编号:" + num +"商品名称:" + name + "商品类型:" + kind + "价格：" + price + "，成本：" + cost + "公司名称：" + company + "剩余数量：" + remain);
                }
            }
        });

        // 创建查看客户订单按钮
        JButton viewOrdersButton = new JButton("查看客户订单");
        viewOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 在这里处理查看客户订单的逻辑
                // 可以打开一个新的窗口或对话框来显示客户订单信息
                // 也可以执行相应的数据库操作来获取客户订单数据并显示在界面上
                System.out.println("查看客户订单");
            }
        });

        // 添加查看客户订单按钮到窗口的左上角
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(viewOrdersButton, gbc);

        // 添加修改按钮到窗口的左上角
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(updateButton, gbc);

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
            new AdminstratorGUI();

    }
}

