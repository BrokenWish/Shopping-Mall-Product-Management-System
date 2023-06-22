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

    /**
     * 新增方法，输入一段SQL插入语句
     * @param TSQLLanguage
     */
    public static void addData(String TSQLLanguage){
        if (TSQLLanguage == null){
            throw new RuntimeException("SQL语句不能为空");
        }

        Connection conn = getConn();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = TSQLLanguage;
            stmt.executeQuery(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * 删除方法，输入一段SQL删除语句，返回boolean表示是否成功
     * @param TSQLLanguage
     * @return
     */
    public static boolean deleteData(String TSQLLanguage){
        if (TSQLLanguage == null){
            return false;
        }

        Connection conn = getConn();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = TSQLLanguage;
            stmt.executeQuery(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 更新方法，输入一段SQL更新语句，返回boolean表示是否成功
     * @param TSQLLanguage
     * @return
     */
    public static boolean updateData(String TSQLLanguage){
        if (TSQLLanguage == null){
            return false;
        }

        Connection conn = getConn();
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = TSQLLanguage;
            stmt.executeQuery(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 查询方法，输入一段SQL查询语句，返回一个结果集对象，如何提取其中数据，见SQLTest类
     * @param TSQLLanguage
     * @return
     */
    public static ResultSet getData(String TSQLLanguage){
        if (TSQLLanguage == null){
            throw new RuntimeException("SQL语句不能为空");
        }

        Connection conn = getConn();
        ResultSet rs = null;
        Statement stmt;

        try {
            stmt = conn.createStatement();
            String sql = TSQLLanguage;
            rs = stmt.executeQuery(sql);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public static

}
