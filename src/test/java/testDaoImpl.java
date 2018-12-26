import Entity.User;
import Utils.JDBCUtils;
import Utils.newUser;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author jingdu
 * @date 2018/12/20
 */
public class testDaoImpl {

    /**
     *
     */
    @Test
    public void testMybatis() {
        Logger logger = Logger.getLogger(testDaoImpl.class);

        DaoMyBaitsImpl daoMyBaits = new DaoMyBaitsImpl();
        List<User> depts = daoMyBaits.findAll();
        logger.debug(depts);

    }


    @Test
    public void testCon() {
        User user = new User();
        user.setUserName("wangyingyue");
        user.setNickName("wyy");
        user.setId("2");
        user.setBirthday(LocalDateTime.now().toString());
        user.setPassword("wyy");
        user.setEmail("wyy@");

        DaoImpl dao = new DaoImpl();
        dao.add(user);
    }

    @Test
    public void testGet() {
        DaoImpl dao = new DaoImpl();
        testCon();
        User user = dao.find("jianglan", "sa11");
        System.out.println(user.toString());
    }

    @Test
    public void getMysql() {
        String sql = "select * from user where  userName=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        User user = new User();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "jianglan");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setNickName(rs.getString("nickName"));
                user.setUserName(rs.getString("userName"));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            JDBCUtils.release(connection, preparedStatement, rs);
        }

    }

    @Test
    public void getConn() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        String driverClass = "com.mysql.jdbc.Driver";
//        String  url = "jdbc:mysql://localhost:3309/userList?useSSL=false&serverTimezone=UTC&characterEncoding=utf8&rewriteBatchedStatements=true
//        name = root
//        password = 123456
        Properties properties = new Properties();
//        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("/mysql.properties");
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/mysql.properties");
        properties.load(resourceAsStream);
//        String driverClass1 = properties.getProperty("driverClass");
        String url = properties.getProperty("url");
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        Class.forName(driverClass).newInstance();
        Connection connection = DriverManager.getConnection(url, name, password);
        if (connection != null) {
            System.out.println(connection);
        }
    }

    @Test
    public void testService() {
        User user = new User();
        user.setUserName("wangyingyue");
//        user.setNickName("wyy");
        user.setId("2");
//        user.setBirthday(LocalDateTime.now());
//        user.setPassword("wyy");
        user.setEmail("wyy@");

        Service service = new Service();
        service.login("jianglan", "sa11");
//        service.register(user);
    }


    @Test
    public void testSetProperty() throws Exception {
        newUser user = new newUser();
        System.out.println(user);
        //设置String类型的属性
        BeanUtils.setProperty(user, "user", "李四");
        //设置int类型的属性
        BeanUtils.setProperty(user, "age", 25);
        //设置boolean类型的属性
        BeanUtils.setProperty(user, "gender", "false");
        //设置Date类型的属性
        //异常信息：DateConverter does not support default String to 'Date' conversion.
        //注册String->日期类型转换器
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                if (type != Date.class) {
                    return null;
                }
                if (value == null && "".equals(value.toString().trim())) {
                    return null;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse((String) value);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                return date;
            }
        }, Date.class);
        BeanUtils.setProperty(user, "birthday", "2016-92-11");
        //设置数组类型的属性
        String[] hobbies = {"11", "22", "33"};
        BeanUtils.setProperty(user, "hobbies", hobbies);
        //设置List、Set、Map、内嵌对象等...
        System.out.println(user);
    }


}
