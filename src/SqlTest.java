import java.sql.*;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-18-16:23
 * @Name test
 * @Projrct Shopping-Mall-Product-Management-System
 */
public class SqlTest {

    /**
     * 	*java连接mysql数据库
     * 	*1、加载驱动程序
     * 	*2、数据库连接字符串"jdbc:mysql://localhost:3306/数据库名?"
     * 	*3、数据库登录名
     * 	*3、数据库登录密码
     */

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://rm-uf6tp8qmdd70a844bto.mysql.rds.aliyuncs.com/speechcloud?serverTimezone=GMT%2B8";

    // // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String USER = "brokenwish";
    static final String PASS = "1256338Mysql";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT Start, Username FROM test";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("Start");
                String name = rs.getString("Username");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 姓名: " + name);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

}
