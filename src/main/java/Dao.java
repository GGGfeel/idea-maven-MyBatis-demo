import Entity.User;

import java.util.List;

/**
 * @author jingdu
 * @date 2018/12/19
 */
public interface Dao {
    boolean find(String userName);

    User find(String userName, String password);

    Boolean add(User user);

    Boolean delete(User user);

    List<User> findAll();
}
