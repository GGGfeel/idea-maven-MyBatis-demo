package Utils;

import java.sql.DriverManager;

/**
 * @author jingdu
 * @date 2018/12/25
 */
public class testCon {


    private static String url = "jdbc:mysql://localhost:3309/userList?useSSL=false&serverTimezone=UTC&characterEncoding=utf8&rewriteBatchedStatements=true";//数据库服务地址
    private static String driver = "com.mysql.cj.jdbc.Driver";//驱动路径
    private static String username = "root";
    private static String passworld = "123456";


    public static void main(String[] args) throws Exception {
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(DriverManager.getConnection(url, username, passworld));
    }

}
