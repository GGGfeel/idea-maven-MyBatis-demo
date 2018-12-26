import Entity.User;
import Utils.JDBCUtils;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author jingdu
 * @date 2018/12/19
 */
public class DaoImpl implements Dao {

    public Boolean add(User user) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
//            String sql = "select * from user where  username=? and password=? and id=? and nickname=?" +
//                    "and email=? and birth=?";
            String sql = "INSERT INTO user VALUES (?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getNickName());
            preparedStatement.setString(3, user.getBirth());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println(user.getId() + "添加成功");
                return true;
            } else {
                System.out.println(user.getId() + "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, preparedStatement, rs);
        }
        return false;
    }

    @Override
    public Boolean delete(Map<String, String> map) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    public User find(String userName, String password) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        User user = new User();

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from user where  userName=? and password=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setNickName(rs.getString("nickName"));
                user.setUserName(rs.getString("userName"));
//                user.setBirthday(LocalDateTime.parse(rs.getString("birth")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, preparedStatement, rs);
        }

        return user == null ? null : user;

    }

    @Override
    public boolean findUserName(String userName) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            String str = "SELECT * FROM user where userName = ?";
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery(str);
            while (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            JDBCUtils.release(connection, preparedStatement, resultSet);
        }

        return false;
    }

    @Override
    public User find(Map<String, String> map) {
        return null;
    }

//    @Override
//    public Boolean delete(User user) {
//        Connection connection = null;
//        ResultSet resultSet = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = JDBCUtils.getConnection();
//            String str = "DELETE * FROM user where userName = ?";
//            preparedStatement.setString(1, user.getUserName());
//            boolean execute = preparedStatement.execute(str);
//            if (execute) {
//                return true;
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            JDBCUtils.release(connection, preparedStatement, resultSet);
//        }
//        return false;
//    }

}
