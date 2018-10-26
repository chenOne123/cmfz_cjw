package com.baizhi.cmfz_cjw.service;

import com.baizhi.cmfz_cjw.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AlbumService {
    //查询
    Map getAll(int page, int rows);
    //查看详情
    Album particulars(int id);
    //添加专辑
    Boolean addAlbum(MultipartFile file, Album album, HttpServletRequest request);
}
