package shopping.control;

import model.Administrator;
import model.Customer;
import service.AdministratorService;
import service.impl.AdministratorsServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoppingCartGUI extends JFrame implements ActionListener{
    private AdministratorsServiceImpl administratorService = new AdministratorsServiceImpl();

	 private JLabel role,account,password;//三个标签 用户身份 用户账号 密码
	 private JTextField usernameField;//文本框

	 private JButton enterBtn,enterzc; 
    private JComboBox roleCombox;
    private JPasswordField passwordField;

    public ShoppingCartGUI() {
       super("商城系统登录界面");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时，退出应用程序

        setLocationRelativeTo(null);
        role=new JLabel("用户身份:");
        account=new JLabel("用户名:");
        password=new JLabel("密码:");
        usernameField=new JTextField(50);
        passwordField=new JPasswordField(50);
        enterBtn=new JButton("登录");
        enterzc=new JButton("注册");
        roleCombox=new JComboBox(new String[] {"客户","管理员"});
        role.setFont(new Font("黑体",Font.PLAIN,30));
        account.setFont(new Font("黑体",Font.PLAIN,30));
        usernameField.setFont(new Font("黑体",Font.PLAIN,28));
        passwordField.setFont(new Font("黑体",Font.PLAIN,28));
        password.setFont(new Font("黑体",Font.PLAIN,30));
        enterBtn.setFont(new Font("黑体",Font.PLAIN,22));
        enterzc.setFont(new Font("黑体",Font.PLAIN,22));
        roleCombox.setFont(new Font("黑体",Font.PLAIN,28));
        setLayout(null);//设置布局方式为空布局
        role.setBounds(230,80,140,50);
        roleCombox.setBounds(400,80,150,50);
        account.setBounds(230,180,140,50);
        usernameField.setBounds(400,180,150,50);
        password.setBounds(230,280,150,50);
        passwordField.setBounds(400,280,150,50);
        enterBtn.setBounds(230,350,100,50);
        enterzc.setBounds(430,350,100,50);
        add(role);
        add(roleCombox);
        add(account);
        add(usernameField);
        add(password);
        add(passwordField);
        add(enterBtn);
        add(enterzc);
        setVisible(true);
        enterBtn.addActionListener(this);
        enterzc.addActionListener(this);
        roleCombox.addActionListener(this);
        
    }


    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource()==enterBtn)
    	{

            if (roleCombox.getSelectedItem()==("客户")){

                String account=new String (usernameField.getText());
                String password=new String(passwordField.getPassword());
                Customer customer = administratorService.findCustomerByAccount(account);
                if(account == null||password == null || customer == null || !customer.getUserName().equals(account) || !customer.getPassword().equals(password))
                {
                    JOptionPane.showMessageDialog(this, "登录失败");
                    return;
                } else if (customer.getUserName().equals(account) && customer.getPassword().equals(password)){
                    JOptionPane.showMessageDialog(this, "登录成功");
                    new CustomerGUI();
                }


            }else{

                String account=new String (usernameField.getText());
                String password=new String(passwordField.getPassword());
                Administrator administrator = administratorService.findAdministraotrByAccount(account);
                if(account == null||password == null || administrator == null || !administrator.getUserName().equals(account) || !administrator.getPassword().equals(password))
                {
                    JOptionPane.showMessageDialog(this, "登录失败");
                    this.dispose();
                    return;
                } else if (administrator.getUserName().equals(account) && administrator.getPassword().equals(password)){
                    JOptionPane.showMessageDialog(this, "登录成功");
                    this.dispose();
                    new AdminstratorGUI();
                }

            }


    	}
    	else if(e.getSource()==enterzc)
    	{
    		this.dispose();
    		new RegisterFrame();
    	}
    	
    	
    }
  

}
