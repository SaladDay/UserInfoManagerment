package cn.saladday.test;

import cn.saladday.domain.User;
import cn.saladday.service.UserService;
import cn.saladday.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class UserServletTest {

    @Test
    public void test01(){
        UserService userService = new UserServiceImpl();
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    };
}
