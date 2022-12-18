<%--
  Created by IntelliJ IDEA.
  User: SaladDay
  Date: 2022/12/16
  Time: 16:07
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
  <!-- 使用Edge最新的浏览器的渲染方式 -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
  width: 默认宽度与设备的宽度相同
  initial-scale: 初始的缩放比，为1:1 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>用户信息管理系统</title>


  <!-- 1. 导入CSS的全局样式 -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
  <script src="js/jquery-2.1.0.min.js"></script>
  <!-- 3. 导入bootstrap的js文件 -->
  <script src="js/bootstrap.min.js"></script>
  <%--  4.js函数--%>
  <script>
    function del() {
      var msg = "您真的确定要删除吗？\n\n请确认！";
      if (confirm(msg) == true) {
        return true;
      } else {
        return false;
      }
    }
  </script>
  <script>
    //检查复选框
    let user = document.getElementsByClassName('user');
    function checkStart(){
      if(del()){
        check();
      }
    }
    function check(){
      var flag = false;
      for(let i =0;i<user.length;i++){
        if(user[i].checked===true){
          flag=true;
          break;
        }
      }
      if(flag){
        document.getElementById("checkBoxForm").submit();
      }else{
        alert('请选择需要删除的对象');
        return;
      }

    }
  </script>
  <script>
    window.onload = function () {
      //实现复选框选中其他选中功能
      let user = document.getElementsByClassName('user');
      let allChecked = document.getElementById('allChecked');
      allChecked.addEventListener('click', () => {
        for (let i = 0; i < user.length; i++) {
          user[i].checked = allChecked.checked;
        }
      })
      //实现其他选中，复选框即选中功能
      let arr = [];
      for (let j = 0; j < user.length; j++) {
        //给每一个user都加入一个点击事件，点击了就遍历所有user更新按钮状态
        user[j].addEventListener('click', () => {
          for (let i = 0; i < user.length; i++) {
            arr.splice(i, 1, user[i].checked);//相当于更新其按钮状态
          }
          allChecked.checked = !arr.includes(false) && arr.length === user.length;
        })
      }
    }


  </script>
  <style type="text/css">
    td, th {
      text-align: center;
    }
  </style>
</head>
<body>
<div class="container">
  <h3 style="text-align: center">用户信息列表</h3>
  <div style="float: left;margin: 5px;">
    <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
      <div class="form-group">
        <label for="exampleInputName2">姓名</label>
        <input type="text" name="name" class="form-control"
               value="${requestScope.condition.name[0]==null?"":requestScope.condition.name[0]}" id="exampleInputName2">
      </div>
      <div class="form-group">
        <label for="exampleInputEmail2">籍贯</label>
        <input type="text" name="address" class="form-control"
               value="${requestScope.condition.address[0]==null ?"":requestScope.condition.address[0]}" id="exampleInputEmail2">
      </div>
      <div class="form-group">
        <label for="exampleInputEmail3">邮箱</label>
        <input type="email" name="email" class="form-control"
                value="${requestScope.condition.email[0]==null?"":requestScope.condition.email[0]}" id="exampleInputEmail3">
      </div>
      <button type="submit" class="btn btn-default">查找</button>
    </form>

  </div>
  <div style="float: right;margin: 5px;">
    <td colspan="8" align="center"><a class="btn btn-primary" href="add.jsp">添加联系人</a></td>
    <td colspan="8" align="center"><a class="btn btn-primary" onclick="javascript:checkStart()">删除选中</a></td>
  </div>

  <form id="checkBoxForm" name="checkBoxForm" action="${pageContext.request.contextPath}/deleteSelectedUserServlet" method="post">
    <table border="1" class="table table-bordered table-hover">
      <tr class="success">
        <th><input id="allChecked" type="checkbox"></th>
        <th>编号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>籍贯</th>
        <th>QQ</th>
        <th>邮箱</th>
        <th>操作</th>
      </tr>
      <c:forEach items="${requestScope.pageBean.list}" var="user" varStatus="s">
        <tr>
<%--          此时在域对象中有user，因此可以用复选框将user.id提交上去--%>
          <th><input class="user" type="checkbox" name="multiCheck" value="${user.id}"></th>
          <td>${user.id}</td>
          <td>${user.name}</td>
          <td>${user.gender}</td>
          <td>${user.age}</td>
          <td>${user.address}</td>
          <td>${user.qq}</td>
          <td>${user.email}</td>
          <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;
              <a class="btn btn-default btn-sm" onclick="return del()" href="${pageContext.request.contextPath}/deleteUserServlet?id=${user.id}">删除</a>
          </td>
        </tr>

      </c:forEach>

    </table>
  </form>
  <div >
    <nav aria-label="Page navigation">
      <ul class="pagination">


        <c:if test="${requestScope.pageBean.currentPage == 1}">
          <li class="disabled">
            <span href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${requestScope.pageBean.currentPage-1}&rows=5&name=${requestScope.condition.name[0]}&address=${requestScope.condition.address[0]}&email=${requestScope.condition.email[0]}"
               aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </span>
          </li>
        </c:if>
        <c:if test="${requestScope.pageBean.currentPage != 1}">
          <li>
            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${requestScope.pageBean.currentPage-1}&rows=5&name=${requestScope.condition.name[0]}&address=${requestScope.condition.address[0]}&email=${requestScope.condition.email[0]}"
               aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
        </c:if>



        <c:forEach begin="1" end="${requestScope.pageBean.totalPage}" var="i">
          <c:if test="${requestScope.pageBean.currentPage == i}">
            <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&&rows=5&name=${requestScope.condition.name[0]}&address=${requestScope.condition.address[0]}&email=${requestScope.condition.email[0]}">${i}</a></li>
          </c:if>
          <c:if test="${requestScope.pageBean.currentPage != i}">
            <li ><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&&rows=5&name=${requestScope.condition.name[0]}&address=${requestScope.condition.address[0]}&email=${requestScope.condition.email[0]}">${i}</a></li>
          </c:if>
        </c:forEach>

        <c:if test="${requestScope.pageBean.currentPage == requestScope.pageBean.totalPage}">
          <li class="disabled">
            <span href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${requestScope.pageBean.currentPage+1}&rows=5&name=${requestScope.condition.name[0]}&address=${requestScope.condition.address[0]}&email=${requestScope.condition.email[0]}"
               aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </span>
          </li>
        </c:if>
        <c:if test="${requestScope.pageBean.currentPage != requestScope.pageBean.totalPage}">
          <li>
            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${requestScope.pageBean.currentPage+1}&rows=5&name=${requestScope.condition.name[0]}&address=${requestScope.condition.address[0]}&email=${requestScope.condition.email[0]}"
               aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </c:if>


        <span style="font-size: 25px;margin-left: 15px;">
          共${requestScope.pageBean.totalCount}条记录，${requestScope.pageBean.totalPage}页
        </span>
      </ul>
    </nav>

  </div>
</div>
</body>
</html>
