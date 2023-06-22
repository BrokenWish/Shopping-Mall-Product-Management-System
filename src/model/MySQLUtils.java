package model;

import java.sql.*;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-20-10:17
 * @Name MySQLUtils
 * @Projrct AdministratorService.class
 */

/**
 * 数据库操作类
 */
public class MySQLUtils {

    public static Connection getConn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        //mysql用户名
        String userName = "brokenwish";
        //mysql密码
        String password = "1256338Mysql";
        //数据库URL
        String url = "jdbc:mysql://rm-uf6tp8qmdd70a844bto.mysql.rds.aliyuncs.com/speechcloud?serverTimezone=GMT%2B8";

        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }




}
