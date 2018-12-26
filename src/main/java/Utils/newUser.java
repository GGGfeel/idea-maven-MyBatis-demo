package Utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jingdu
 * @date 2018/12/21
 */
@Data
public class newUser implements Serializable {

    private static final long serialVersionUID = 5210726534179789239L;

    private String name;
    private int age;
    private boolean gender;   //性别：trye-男，false-女
    private Date birthday; //生日
    private String[] hobbies; //爱好
    private String address;
    private List<String> strong; //特长
    private Map<String, String> fault; //缺点
//    public Student student; //内嵌学生对象


}