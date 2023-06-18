import java.sql.*;

/**
 * @author Broken wish
 * @coding : utf-8
 * @create 2023-06-18-16:23
 * @Name test
 * @Projrct Shopping-Mall-Product-Management-System
 */
public class test {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://rm-uf6tp8qmdd70a844bto.mysql.rds.aliyuncs.com/speechcloud?serverTimezone=GMT%2B8";

    // ���ݿ���û���������
    static final String USER = "brokenwish";
    static final String PASS = "1256338Mysql";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // ע�� JDBC ����
            Class.forName(JDBC_DRIVER);

            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // ִ�в�ѯ
            System.out.println(" ʵ����Statement����...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT Start, Username FROM test";
            ResultSet rs = stmt.executeQuery(sql);

            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("Start");
                String name = rs.getString("Username");

                // �������
                System.out.print("ID: " + id);
                System.out.print(", վ������: " + name);
                System.out.print("\n");
            }
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

}
