package cn.saladday.web;

import cn.saladday.domain.PageBean;
import cn.saladday.domain.User;
import cn.saladday.service.UserService;
import cn.saladday.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码并且获取currentPage && row
        request.setCharacterEncoding("utf-8");
        int currentPage = Integer.parseInt(request.getParameter("currentPage")==null?
                "1":request.getParameter("currentPage"));
        int rows = Integer.parseInt(request.getParameter("rows")==null?
                "5":request.getParameter("rows"));

        //提取查询参数
        Map<String, String[]> parameterMap = request.getParameterMap();



        //调用service中的findUserByPage(int currentPage,int row)返回PageBean
        UserService userService = new UserServiceImpl();
        PageBean<User> pageBean = userService.findUserByPage(currentPage, rows,parameterMap);
//        System.out.println(pageBean);
        //将PageBean存入request，转发回list.jsp
        request.setAttribute("pageBean",pageBean);
        //将查询条件转发回list.jsp
        request.setAttribute("condition",parameterMap);
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
