package com.baizhi.cmfz_cjw;

import com.baizhi.cmfz_cjw.dao.AlbumDao;
import com.baizhi.cmfz_cjw.dao.ChapterDao;
import com.baizhi.cmfz_cjw.entity.Album;
import com.baizhi.cmfz_cjw.entity.Chapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AlbumTest {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;

    @Test
    public void test1(){
        List<Album> list = albumDao.getAll(1,5);
        for (Album album : list) {
            System.out.println(album);
        }
    }

    @Test
    public void tesrt2(){
        Album album = albumDao.getId(1);
        System.out.println(album);
    }

    @Test
    public void test3(){
        Album album = new Album();
        album.setName("刘世勋的一生");
        album.setCoverImg("img");
        album.setBrief("nb");
        album.setBroadCast("达摩老祖");
        album.setAuthor("刘世勋读");
        albumDao.insert(album);
    }

    @Test
    public void test4(){
        chapterDao.insert(new Chapter("abc","heh","aaa",11,"duration",3));
    }


}
