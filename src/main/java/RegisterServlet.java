import Entity.User;
import Utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author jingdu
 * @date 2018/12/20
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
//    private String userName;
//    //<input type="password" name="userPwd"/>
//    private String userPwd;
//    //<input type="password" name="confirmPwd"/>
//    private String confirmPwd;
//    //<input type="text" name="email"/>
//    private String email;
//    //<input type="text" name="birthday"/>
//    private String birthday;


//        RegisterForm registerForm = WebUtils.request2Bean(request, RegisterForm.class);
        RegisterForm registerForm = new RegisterForm();
        registerForm.setEmail(request.getParameter("email"));
        registerForm.setNickName(request.getParameter("nickName"));
//        registerForm.setBirthday(request.getParameter("birthday"));
        registerForm.setPassword(request.getParameter("password"));
        registerForm.setUserName(request.getParameter("userName"));
        registerForm.setId(WebUtils.makeId());

        boolean validate = registerForm.validate();
        if (!validate) {
            request.setAttribute("registerForm", registerForm);
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            return;
        }


        try {
//            User user = new User();
//            ConvertUtils.register(new DateLocaleConverter(), Date.class);
//            BeanUtils.copyProperties(user, registerForm);


            Service service = new Service();
            service.register(registerForm);
            request.setAttribute("message", "注册成功3秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='3;url=\"+request.getContextPath()+\"/LoginUIServlet'");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return;
        } catch (UserExistException e) {
            registerForm.getErrors().put("username", "注册的用户名已存在！！！");
            request.setAttribute("registerForm", registerForm);
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace(); // 其他异常不要抛给用户，对用户不友好，所以应该try，并在后台记录异常
            request.setAttribute("message", "服务器出现未知错误！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

    }
}