package shopping.control;

import model.Customer;
import service.AdministratorService;
import service.CustomerService;
import service.impl.AdministratorsServiceImpl;
import service.impl.CustomerServiceImpl;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;


public class RegisterFrame extends JFrame implements ActionListener{
    private AdministratorsServiceImpl administratorService = new AdministratorsServiceImpl();
    private CustomerServiceImpl customerService = new CustomerServiceImpl();

	private JLabel account,password,espassword,telephone,name;//三个标签 用户身份 用户账号 密码
	 private JTextField usernameField,phonenumber,namefield;//文本框
	 private JButton enterexit,enterzc; 
   private JPasswordField tpassword1,tpassword2;
   public RegisterFrame()
   {
	   super("注册");
       setSize(500,600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时，退出应用程序
       setLocationRelativeTo(null);
       account=new JLabel("账号名:");
       name=new JLabel("用户名:");
       password=new JLabel("请输入密码:");
       espassword=new JLabel("请确认密码:");
       telephone=new JLabel("电话号码:");
       usernameField=new JTextField(50);
       phonenumber=new JTextField(50);
       namefield=new JTextField(50);
       tpassword1=new JPasswordField(50);
       tpassword2=new JPasswordField(50);
       enterexit=new JButton("退出");
       enterzc=new JButton("注册");
       account.setFont(new Font("黑体",Font.PLAIN,30));
       usernameField.setFont(new Font("黑体",Font.PLAIN,28));
       name.setFont(new Font("黑体",Font.PLAIN,30));
       namefield.setFont(new Font("黑体",Font.PLAIN,30));
       espassword.setFont(new Font("黑体",Font.PLAIN,30));
       password.setFont(new Font("黑体",Font.PLAIN,30));
       tpassword1.setFont(new Font("黑体",Font.PLAIN,20));
       tpassword2.setFont(new Font("黑体",Font.PLAIN,20));
       telephone.setFont(new Font("黑体",Font.PLAIN,30));
       phonenumber.setFont(new Font("黑体",Font.PLAIN,28));
       enterexit.setFont(new Font("黑体",Font.PLAIN,22));
       enterzc.setFont(new Font("黑体",Font.PLAIN,22));
       setLayout(null);//设置布局方式为空布局
       account.setBounds(80,20,150,50);
       usernameField.setBounds(300,20,150,50);
       name.setBounds(80,120,150,50);
       namefield.setBounds(300,120,150,50);
       password.setBounds(80,220,200,50);
       tpassword1.setBounds(300,220,150,50);
       espassword.setBounds(80,320,200,50);
       tpassword2.setBounds(300,320,150,50);
       telephone.setBounds(80,420,150,50);
       phonenumber.setBounds(300,420,150,50);
       enterzc.setBounds(150,480,100,50);
       enterexit.setBounds(250,480,100,50);
       
       add(account);
       add(usernameField);
       add(name);
       add(namefield);
       add(password);
       add(tpassword1);
       add(espassword);
       add(tpassword2);
       add(telephone);
       add(phonenumber);
       add(enterzc);
       add(enterexit);
       
       setVisible(true);
       enterexit.addActionListener(this);
       enterzc.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e)
   {
   	if(e.getSource()==enterzc)
   	{
   		String password1=new String (tpassword1.getPassword());
   		String password2=new String(tpassword2.getPassword());
   		if(!password1.equals(password2))
   		{
   			JOptionPane.showMessageDialog(this, "两个密码不相同");
   			return;
   		}
   		String account=usernameField.getText();
   		String telephone=phonenumber.getText();
   		String name=namefield.getText();

        List<Customer> customerList = administratorService.listCustomer();
        for (Customer customer : customerList) {
            if (customer.getUserName().equals(account)){
                JOptionPane.showMessageDialog(this, "账号名已存在");
                return;
            }else if(customer.getCusomerPhone().equals(telephone)){
                JOptionPane.showMessageDialog(this, "该手机号已经注册");
                return;
            }
        }

        Customer customer = new Customer();
        customer.setUserName(account);
        customer.setCustomerName(name);
        customer.setPassword(password1);
        customer.setCusomerPhone(telephone);
        customerService.createCustomer(customer);

        JOptionPane.showMessageDialog(this, "注册成功");
   		
   	}
   	else if(e.getSource()==enterexit)
   	{
   		this.dispose();
   		new ShoppingCartGUI();
   	}
   }


}
