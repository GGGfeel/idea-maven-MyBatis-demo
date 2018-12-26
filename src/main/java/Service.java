import Entity.User;

/**
 * @author jingdu
 * @date 2018/12/20
 */
public class Service {


    private Dao dao = new DaoImpl();


    public Boolean register(User user) throws UserExistException {

        if (dao.findUserName(user.getUserName())) {
            System.out.println(user.getUserName() + "已经注册过");
            throw new UserExistException();
        }
        dao.add(user);
        System.out.println(user.getUserName() + "注册成功");
        return true;

    }

    public User login(String userName, String password) {
//        User res = dao.find(userName, password);


        return null;
    }
}
