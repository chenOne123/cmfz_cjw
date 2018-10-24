package com.baizhi.cmfz_cjw.service;

import com.baizhi.cmfz_cjw.entity.Banner;

import java.util.Map;

public interface BannerService {
    //查询
    public Map getAll(int page, int rows);
    //添加
    public void getAdd(Banner banner);
    //更改
    public boolean getUpdate(int id, String status);
    //删除
    public void getDelete(int id);

}
