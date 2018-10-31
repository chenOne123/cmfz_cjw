package com.baizhi.cmfz_cjw;

import com.baizhi.cmfz_cjw.dao.UserDao;
import com.baizhi.cmfz_cjw.entity.User;
import com.baizhi.cmfz_cjw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {
    @Autowired
    private UserDao userService;

    @Test
    public void test1(){
//        int i = userService.tian1();
//        System.out.println(i);
        List<User> chen = userService.getChen();
        System.out.println(chen);
    }

    @Test
    public void test2(){
        User user = new User();
        user.setName("lyk123");
        user.setId(1);
        userService.update(user);
    }

}
