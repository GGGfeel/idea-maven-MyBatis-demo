import Entity.User;
import Utils.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jingdu
 * @date 2018/12/26
 */
public class DaoMyBaitsImpl implements Dao {
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass());

    @Override
    public boolean findUserName(String userName) {
        SqlSession session = null;
        try {
            session = SessionFactoryUtil.getSqlSession();
            Dao mapper = session.getMapper(Dao.class);
            boolean b = mapper.findUserName(userName);
            return b;
        } catch (Exception e) {
            logger.info("find " + userName + " 出错" + e);
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public User find(Map<String, String> param) {
        SqlSession session = null;
        try {
            session = SessionFactoryUtil.getSqlSession();
            Dao mapper = session.getMapper(Dao.class);
            //这里通过map进行查询
            User user = mapper.find(param);
            return user;
        } catch (Exception e) {
//            logger.info("find " + userName + " 出错" + e);
            System.out.println(e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean add(User user) {
        SqlSession session = null;
        try {
            session = SessionFactoryUtil.getSqlSession();
            Dao mapper = session.getMapper(Dao.class);
            mapper.add(user);
            session.commit();
            return true;
        } catch (Exception e) {
//            logger.info("add User 出错" + e);
            System.out.println(e);
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean delete(Map<String, String> map) {

        SqlSession session = null;
        try {
            session = SessionFactoryUtil.getSqlSession();
            Dao mapper = session.getMapper(Dao.class);
            mapper.delete( map);
            session.commit();
            return true;
        } catch (Exception e) {
            logger.info("delete User 出错" + e);
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = null;
        List<User> users = null;
        try {
            session = SessionFactoryUtil.getSqlSession();
            Dao mapper = session.getMapper(Dao.class);
            users = mapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }
}
