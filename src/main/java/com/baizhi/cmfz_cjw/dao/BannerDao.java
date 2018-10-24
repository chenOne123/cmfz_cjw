package com.baizhi.cmfz_cjw.dao;

import com.baizhi.cmfz_cjw.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    //查询
    public List<Banner> getAll(@Param("start")int start, @Param("total")int total);
    //计算
    public int getCount();
    //添加
    public void insert(Banner banner);
    //更改
    public void update(@Param("id") int id, @Param("status") String status);
    //删除
    public void delete(int id);

}
