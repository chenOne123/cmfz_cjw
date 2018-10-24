package com.baizhi.cmfz_cjw;

import com.baizhi.cmfz_cjw.dao.AdminDao;
import com.baizhi.cmfz_cjw.dao.MenuDao;
import com.baizhi.cmfz_cjw.entity.Admin;
import com.baizhi.cmfz_cjw.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private MenuDao menuDao;

    @Test
    public void contextLoads() {
        Admin list = adminDao.getOne("lykAbc","123456");
        System.out.println(list);
    }

    @Test
    public void test(){
        List<Menu> list = menuDao.getAll();
        for (Menu menu : list) {
            System.out.println(menu);

        }
    }

}
