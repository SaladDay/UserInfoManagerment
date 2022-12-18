package cn.saladday.web;

import cn.saladday.domain.User;
import cn.saladday.service.UserService;
import cn.saladday.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码，获取id参数
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        //调用service
        UserService userService = new UserServiceImpl();
        User user = userService.findById(id);
        //获得了数据后，传入request域中，转发到User
        request.setAttribute("user",user);
        request.getRequestDispatcher("/update.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
