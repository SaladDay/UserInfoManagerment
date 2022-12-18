package cn.saladday.web;

import cn.saladday.service.UserService;
import cn.saladday.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import cn.saladday.domain.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.需要从页面中获取数据，因此需要设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取数据
        String loginBufferCode = request.getParameter("bufferCode");
        Map<String, String[]> map = request.getParameterMap();


        //4.比对验证码
        HttpSession hs = request.getSession();
        String bufferCode = (String)hs.getAttribute("bufferCode");
        //获取完验证码之后，需要将其从session中删除
        hs.removeAttribute("bufferCode");
        if(!bufferCode.equalsIgnoreCase(loginBufferCode)){
            //此时验证码比对不正确
            //转发回Login.jsp并且带回失败的消息
            response.sendRedirect(request.getContextPath()+"/login.jsp?msg=bufferError");
            return;


        }
        //3.封装对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }


        //4.调用service查询和数据库比对，
        UserService userService = new UserServiceImpl();
        User login = userService.login(loginUser);
        //判断并作出回应
        if(login==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp?msg=error");
            //账号密码错误
        }else {
            //登录成功
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
