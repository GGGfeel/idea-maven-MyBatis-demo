package Utils;

import java.util.Enumeration;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import Entity.User;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @author gacl
 * 把request对象中的请求参数封装到bean中
 */
public class WebUtils {

    /**
     * 将request对象转换成T对象
     *
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            User user = new User();
            newUser newUser = new newUser();
            Enumeration<String> e = request.getParameterNames();
//            BeanUtils.setProperty(newUser,"user");
            BeanUtils.setProperty(newUser, "user", "李四");

            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(newUser, name, value);

                BeanUtils.setProperty(bean, "name", value);
                BeanUtils.setProperty(user, name, value);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String makeId() {
        return UUID.randomUUID().toString();
    }

}