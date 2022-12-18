package cn.saladday.web;

import cn.saladday.service.UserService;
import cn.saladday.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //id通过URL传来，因此数据类型为String
        int id = Integer.parseInt(request.getParameter("id"));
        //调用service
        UserService userService = new UserServiceImpl();
        if(userService.deleteSingle(id)){
            response.sendRedirect(request.getContextPath()+"/userListServlet");
        }else {
            response.sendRedirect(request.getContextPath()+"/userListServlet?msg=deleteError");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
