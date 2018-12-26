
import java.util.HashMap;
import java.util.Map;

import Entity.User;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

/**
 * 封装的用户注册表单bean，用来接收register.jsp中的表单输入项的值
 * RegisterFormBean中的属性与register.jsp中的表单输入项的name一一对应
 * RegisterFormBean的职责除了负责接收register.jsp中的表单输入项的值之外还担任着校验表单输入项的值的合法性
 * @author gacl
 *
 */
public class RegisterForm extends User{


    
    /**
     * 存储校验不通过时给用户的错误提示信息
     */
    private Map<String, String> errors = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        errors = errors;
    }

    /*
     * validate方法负责校验表单输入项
     * 表单输入项校验规则：
     *         private String userName; 用户名不能为空，并且要是3-8的字母 abcdABcd
     *         private String password; 密码不能为空，并且要是3-8的数字
     *         private String confirmPwd; 两次密码要一致
     *         private String email; 可以为空，不为空要是一个合法的邮箱
     *         private String birthday; 可以为空，不为空时，要是一个合法的日期
     */
    
    public boolean validate() {

        boolean isOk = true;

        if (userName == null || userName.trim().equals("")) {
            isOk = false;
            errors.put("userName", "用户名不能为空！！");
        }

        if (nickName == null || nickName.trim().equals("")) {
            isOk = false;
            errors.put("userName", "绰号不能为空！！");
        }

//        else {
//            if (!userName.matches("[a-zA-Z]{3,8}")) {
//                isOk = false;
//                errors.put("userName", "用户名必须是3-8位的字母！！");
//            }
//        }

        if (password == null || password.trim().equals("")) {
            isOk = false;
            errors.put("password", "密码不能为空！！");
        }
//        else {
//            if (!password.matches("\\d{3,8}")) {
//                isOk = false;
//                errors.put("password", "密码必须是3-8位的数字！！");
//            }
//        }


        // private String email; 可以为空，不为空要是一个合法的邮箱
        if (email != null && !email.trim().equals("")) {
            if (!email.matches("\\w+@\\w+(\\.\\w+)+")) {
                isOk = false;
                errors.put("email", "邮箱不是一个合法邮箱！！");
            }
        }

        // private String birthday; 可以为空，不为空时，要是一个合法的日期
        if (birthday != null && !birthday.trim().equals("")) {
            try {
                DateLocaleConverter conver = new DateLocaleConverter();
                conver.convert(birthday);
            } catch (Exception e) {
                isOk = false;
                errors.put("birthday", "生日必须要是一个日期！！");
            }
        }

        return isOk;
    }


}