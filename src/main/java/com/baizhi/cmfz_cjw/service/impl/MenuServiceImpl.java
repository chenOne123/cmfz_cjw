package com.baizhi.cmfz_cjw.service.impl;

import com.baizhi.cmfz_cjw.dao.MenuDao;
import com.baizhi.cmfz_cjw.entity.Menu;
import com.baizhi.cmfz_cjw.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getAll() {
        List<Menu> list = menuDao.getAll();
        return list;
    }
}
