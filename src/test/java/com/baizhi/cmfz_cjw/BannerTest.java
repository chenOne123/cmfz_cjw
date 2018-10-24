package com.baizhi.cmfz_cjw;

import com.baizhi.cmfz_cjw.dao.BannerDao;
import com.baizhi.cmfz_cjw.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BannerTest {
    @Autowired
    private BannerDao bannerDao;

    @Test
    public void test1(){
       /* List<Banner> list = bannerDao.getAll(11,11);
        System.out.println(list.size());
        for (Banner banner : list) {
            System.out.println(banner);
        }*/
       int i = bannerDao.getCount();
        System.out.println(i);
    }

    @Test
    public void test2(){
        Banner banner = new Banner();
        banner.setName("abc");
        banner.setUrl("abc/abc");
        banner.setDescription("哈哈");
        bannerDao.insert(banner);
    }

    @Test
    public void test3(){
        bannerDao.update(7,"n");
    }
}
