package org.worker.ormframework.jdbc;

import java.sql.*;

/**
 * @author peiru wang
 * @date 2021/7/21
 */
public class JDBCDemo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/coder?useSSL=false", "root", "214998");
            System.out.println(conn);
            ps = conn.prepareStatement("select name from user_innodb");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println(name);
            }
            resultSet.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
