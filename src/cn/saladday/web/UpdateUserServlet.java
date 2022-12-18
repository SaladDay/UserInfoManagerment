package cn.saladday.web;

import cn.saladday.domain.User;
import cn.saladday.service.UserService;
import cn.saladday.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码获取数据//整合为User对象
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        User updateUser = new User();
        try {
            BeanUtils.populate(updateUser,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        System.out.println(updateUser);
        //调用service中的update方法更新数据//返回UserListServlet
        UserService userService = new UserServiceImpl();
        if(userService.update(updateUser)){
            response.sendRedirect(request.getContextPath()+"/userListServlet");
        }else{
            response.sendRedirect(request.getContextPath()+"/userListServlet?msg=updateError");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
