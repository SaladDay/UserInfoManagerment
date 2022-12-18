package cn.saladday.web;

import cn.saladday.domain.User;
import cn.saladday.service.UserService;
import cn.saladday.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //因为要从jsp中获取数据，因此需要设置编码
        req.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        //分装数据
        User addUser = new User();
        try {
            BeanUtils.populate(addUser,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //调用service加入数据库
        UserService userService = new UserServiceImpl();
        userService.addSingle(addUser);

        //重定向到LoginServlet
        resp.sendRedirect(req.getContextPath()+"/userListServlet");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
