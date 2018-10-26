package com.baizhi.cmfz_cjw.dao;

import com.baizhi.cmfz_cjw.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //查询
    List<Album> getAll(@Param("start")int start,@Param("count")int count);
    //计算
    int getCount();
    //根据id查询
    Album getId(int id);
    //根据id查询数量
    void getUpdate(int id);
    //添加专辑
    void insert(Album album);
}
