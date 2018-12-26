package Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author jingdu
 * @date 2018/12/19
 */
@Getter
@Setter
public class User {
    // 用户ID
    protected String id;
    // 用户名
    protected String userName;
    // 用户密码
    protected String password;
    // 用户邮箱
    protected String email;
    // 绰号
    protected String nickName;

    // 用户生日
    protected String birthday;


    @Override
    public String toString() {
        return "id:" + id + " userName: " + userName + " password: " + password +
                " email:" + email + " birthday:" + birthday + " nickName:" + nickName;
    }
}
