package cn.saladday.web;

import cn.saladday.utils.BufferCodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    final int WIDTH = 80;
    final int HEIGHT = 30;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String securityCode = BufferCodeUtils.getSecurityCode(4, BufferCodeUtils.SecurityCodeLevel.Medium, false);
        BufferedImage image = BufferCodeUtils.createImage(securityCode);
        //此时只有CheckCodeServlet有验证码数据
        //我也不想把他给前端返回，那么我做一个session
        HttpSession hs = request.getSession();
        hs.setAttribute("bufferCode",securityCode);


        ImageIO.write(image,"jpg",response.getOutputStream());


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
