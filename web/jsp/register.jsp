<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户注册</title>
</head>

<body style="text-align: center;">
<form action="${pageContext.request.contextPath}/registerServlet" method="post">
    <table width="60%" border="1">
        <tr>
            <td>用户名</td>
            <td>
                <%--使用EL表达式${}提取存储在request对象中的formbean对象中封装的表单数据(formbean.userName)以及错误提示消息(formbean.errors.userName)--%>
                <input type="text" name="userName" value="${formbean.userName}">${formbean.errors.userName}
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="password" name="password" value="${formbean.password}">${formbean.errors.password}
            </td>
        </tr>
        <tr>
            <td>绰号</td>
            <td>
                <input type="text" name="nickName" value="${formbean.nickName}">${formbean.errors.confirmPwd}
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" value="${formbean.email}">${formbean.errors.email}
            </td>
        </tr>
        <tr>
            <td>生日</td>
        <td>
            <input type="text" name="birthday" value="${formbean.birthday}">${formbean.errors.birthday}
        </td>
        </tr>
        <tr>
            <td>
                <input type="reset" value="清空">
            </td>
            <td>
                <input type="submit" value="注册">
            </td>
        </tr>
    </table>
</form>
</body>
</html>