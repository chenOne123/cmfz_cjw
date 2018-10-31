package com.baizhi.cmfz_cjw;

import com.baizhi.cmfz_cjw.dao.BannerDao;
import com.baizhi.cmfz_cjw.dao.GuruDao;
import com.baizhi.cmfz_cjw.entity.Artical;
import com.baizhi.cmfz_cjw.entity.Banner;
import com.baizhi.cmfz_cjw.entity.Guru;
import com.baizhi.cmfz_cjw.service.ShouyeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BannerTest {
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private GuruDao guruDao;
    @Autowired
    private ShouyeService shouyeService;

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

        List<Banner> select = bannerDao.getSelect();
        for (Banner banner : select) {
            System.out.println(banner);
        }
    }

    @Test
    public void test4(){
        List<Guru> gselect = guruDao.getGselect();
        for (Guru guru : gselect) {
            System.out.println(guru);
        }
    }

    @Test
    public void test5(){
        List<Guru> id = guruDao.getId(1);
        System.out.println(id);
     /*   List<Guru> xmfy = guruDao.getXmfy(1);
        for (Guru guru: xmfy) {
            System.out.println(guru);
        }*/
    }

    @Test
    public void test6(){
        Map all = shouyeService.getSelect(1, "all", "");
        System.out.println(all);
    }
}
