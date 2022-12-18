package cn.saladday.web;

import cn.saladday.domain.User;
import cn.saladday.service.UserService;
import cn.saladday.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用UserService来查询
//        UserService userService = new UserServiceImpl();
//        List<User> allUsers = userService.findAll();
//        //此时已经拿到了Users的集合，需要封装给request对象进行转发
//        request.setAttribute("allUsers",allUsers);
//        request.getRequestDispatcher("/list.jsp").forward(request,response);
        request.getRequestDispatcher("/findUserByPageServlet").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
