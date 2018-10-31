package com.baizhi.cmfz_cjw.service.impl;

import com.baizhi.cmfz_cjw.dao.AlbumDao;
import com.baizhi.cmfz_cjw.entity.Album;
import com.baizhi.cmfz_cjw.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map getAll(int page, int rows) {
        Map map = new HashMap();
        int start = (page-1)*rows;
        List<Album> list = albumDao.getAll(start, rows);
        int total = albumDao.getCount();

        map.put("rows", list);
        map.put("total", total);
        return map;
    }

    @Override
    public Album particulars(int id) {
        Album album = albumDao.getId(id);
        if(album == null){
            return null;
        }
        albumDao.getUpdate(id);
        Album album2 = albumDao.getId(id);
        return album2;
    }

    @Override
    public Boolean addAlbum(MultipartFile file, Album album, HttpServletRequest request) {
        //查看当前路径
        String pash = request.getSession().getServletContext().getRealPath("/");
        File file1 = new File(pash+"/"+"album");
        //判断album包是否存在如果不存在就创建
        if(!file1.exists()){
            file1.mkdir();
        }
        System.out.println(file1+"111");

        //获取文件的名字
        String fileName = file.getOriginalFilename();
        //给文件重新命名
        String extension = FilenameUtils.getExtension(fileName);
        System.out.println(extension);
        //创建随机数
        String s = UUID.randomUUID().toString();
        String newName = s+"."+extension;

        album.setCoverImg(newName);
        albumDao.insert(album);
        try {
            file.transferTo(new File(file1,newName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
