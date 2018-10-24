package com.baizhi.cmfz_cjw.service;

import com.baizhi.cmfz_cjw.entity.Menu;

import java.util.List;

public interface MenuService {
    //查询所有一级菜单和二级菜单
    public List<Menu> getAll();
}
