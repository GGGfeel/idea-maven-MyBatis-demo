import Entity.User;
import Utils.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author jingdu
 * @date 2018/12/26
 */
public class DaoMyBaitsImpl implements Dao {

    @Override
    public boolean find(String userName) {
        return false;
    }

    @Override
    public User find(String userName, String password) {
        return null;
    }

    @Override
    public Boolean add(User user) {
        return null;
    }

    @Override
    public Boolean delete(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = null;
        List<User> users = null;
        try{
            session = SessionFactoryUtil.getSqlSession();
            Dao mapper = session.getMapper(Dao.class);
            users = mapper.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return users;
    }
}
