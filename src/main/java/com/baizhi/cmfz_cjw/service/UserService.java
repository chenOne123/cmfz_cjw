package com.baizhi.cmfz_cjw.service;

import com.baizhi.cmfz_cjw.entity.ChenDto;
import com.baizhi.cmfz_cjw.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //分页查询
    Map getAll(int page, int rows);
    //查询全部
    List getSelect();
    //添加
    void getInsert(User user);

    List tongji();

    Map chenshi();
}
