package cn.saladday.web;

import cn.saladday.service.UserService;
import cn.saladday.service.impl.UserServiceImpl;
import com.sun.jdi.IntegerType;
import org.apache.commons.collections.ArrayStack;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/deleteSelectedUserServlet")
public class DeleteSelectedUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码获取数据
        request.setCharacterEncoding("utf-8");
        String[] idsStr = request.getParameterValues("multiCheck");
        List<Integer> ids = new ArrayList<Integer>();
        for (String s : idsStr) {
            ids.add(Integer.parseInt(s));
        }
        //调用service中的deleteMultiById(List<Integer> ids);
        UserService userService = new UserServiceImpl();
        if(userService.deleteMultiById(ids)){
            //重定向到userListServlet
            response.sendRedirect(request.getContextPath()+"/userListServlet");
        }else{
            response.sendRedirect(request.getContextPath()+"/userListServlet?msg=deleteError");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
