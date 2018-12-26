package Utils;

import Entity.User;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author jingdu
 * @date 2018/12/20
 */
public class JDBCUtils {
    private static String driverClass = null;
    private static String url = null;
    private static String name = null;
    private static String password = null;
    private static Properties proper = new Properties();

    static {
        try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/mysql.properties")) {
//        try (InputStream resourceAsStream = Object.class.getResourceAsStream("/mysql.properties")) {
//            this.getClass().getClassLoader().getResourceAsStream("/mysql.properties");
            proper.load(resourceAsStream);
            driverClass = proper.getProperty("driverClass");
            //MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
            url = proper.getProperty("url");
            name = proper.getProperty("name");
            password = proper.getProperty("password");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 获取连接对象
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    /**
     * 释放资源
     *
     * @param
     * @param
     * @param
     */
    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        closeConn(connection);
        closeRs(resultSet);
        closeSt(statement);
    }

    public static void release(Connection conn, Statement st) {
        closeSt(st);
        closeConn(conn);
    }

    public static void release(Connection conn, PreparedStatement ps) {
        closePs(ps);
        closeConn(conn);
    }

    public static void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        closeConn(connection);
        closeRs(resultSet);
        closePs(preparedStatement);
    }

    private static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }
    }

    private static void closeSt(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st = null;
        }
    }

    private static void closePs(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps = null;
        }
    }

    private static void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String str = "SELECT * FROM user where userName = ?";
        User user = new User();
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1, "jianglan");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setNickName(rs.getString("nickName"));
                user.setUserName(rs.getString("userName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            release(connection, preparedStatement, rs);
        }

    }
}
