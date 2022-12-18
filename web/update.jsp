<%--
  Created by IntelliJ IDEA.
  User: SaladDay
  Date: 2022/12/17
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>

    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        function check(){
            var qqPattern = /^[1-9][0-9]{4,10}$/;
            var emailPattern = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            var inputElement = document.getElementById("name");
            if(inputElement.value===""){
                alert("请输入用户名");//当用户名为空时，弹出警告
                return false;//将onsubmint值置为false，即用户名为空时无法提交
            }
            inputElement = document.getElementById("age");
            if(inputElement.value===""){
                alert("请填写年龄");//当密码为空时，弹出警告
                return false;//将onsubmint值置为false，即密码为空时无法提交
            }
            inputElement = document.getElementById("qq");
            if(inputElement.value===""){
                alert("请填写QQ");//当密码为空时，弹出警告
                return false;//将onsubmint值置为false，即密码为空时无法提交
            }
            if(!qqPattern.test(inputElement.value)){
                alert("QQ格式错误");
                return false;
            }
            inputElement = document.getElementById("email");
            if(inputElement===""){
                alert("请填写Email");//当密码为空时，弹出警告
                return false;//将onsubmint值置为false，即密码为空时无法提交
            }
            if(!emailPattern.test(inputElement.value)){
                alert("Email格式错误");
                return false;
            }

            return true;

        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post" onsubmit="return check()">
        <%-- 若不提交id的话，在Servlet中BeanUtils无法正确封装对象--%>
        <%-- 使用隐藏域提交id--%>
        <input type="hidden" name="id" value="${requestScope.user.id}">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  readonly="readonly" value="${requestScope.user.name}" placeholder="请输入姓名" />
        </div>


        <div class="form-group">
            <label>性别：</label>
            <c:if test="${requestScope.user.gender == '男'}">
                <input type="radio" name="gender" value="男" checked />男
                <input type="radio" name="gender" value="女"  />女
            </c:if>
            <c:if test="${requestScope.user.gender == '女'}">
                <input type="radio" name="gender" value="男"  />男
                <input type="radio" name="gender" value="女" checked />女
            </c:if>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${requestScope.user.age}" placeholder="请输入年龄" />
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <c:if test="${requestScope.user.address == '广东'}">
                <select name="address" class="form-control" >
                        <option value="广东" selected>广东</option>
                        <option value="广西">广西</option>
                        <option value="湖南">湖南</option>
                        <option value="北京">北京</option>
                        <option value="陕西">陕西</option>
                </select>
            </c:if>
            <c:if test="${requestScope.user.address == '广西'}">
                <select name="address" class="form-control" >
                    <option value="广东" >广东</option>
                    <option value="广西" selected>广西</option>
                    <option value="湖南">湖南</option>
                    <option value="北京">北京</option>
                    <option value="陕西">陕西</option>
                </select>
            </c:if>
            <c:if test="${requestScope.user.address == '湖南'}">
                <select name="address" class="form-control" >
                    <option value="广东">广东</option>
                    <option value="广西">广西</option>
                    <option value="湖南" selected>湖南</option>
                    <option value="北京">北京</option>
                    <option value="陕西">陕西</option>
                </select>
            </c:if>
            <c:if test="${requestScope.user.address == '北京'}">
                <select name="address" class="form-control" >
                    <option value="广东">广东</option>
                    <option value="广西">广西</option>
                    <option value="湖南">湖南</option>
                    <option value="北京" selected>北京</option>
                    <option value="陕西">陕西</option>
                </select>
            </c:if>
            <c:if test="${requestScope.user.address == '陕西'}">
                <select name="address" class="form-control" >
                    <option value="广东">广东</option>
                    <option value="广西">广西</option>
                    <option value="湖南">湖南</option>
                    <option value="北京">北京</option>
                    <option value="陕西" selected>陕西</option>
                </select>
            </c:if>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" id="qq" value="${requestScope.user.qq}" name="qq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" value="${requestScope.user.email}" name="email" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>