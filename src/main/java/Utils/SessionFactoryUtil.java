package Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jingdu
 * @date 2018/12/26
 */
public class SessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory;

    public static synchronized SqlSession getSqlSession(){
        try {
            InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
            // 判断SqlSessionFactory是否为空，如果为空则创建
            if(sqlSessionFactory==null){
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactory.openSession();
    }


}
