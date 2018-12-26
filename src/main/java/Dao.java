import Entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jingdu
 * @date 2018/12/19
 */
public interface Dao {
    boolean findUserName(String userName);


    User find(Map<String, String> map);

    Boolean add(User user);

    Boolean delete(Map<String, String> map);

    List<User> findAll();
}
