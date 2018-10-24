package com.baizhi.cmfz_cjw.service.impl;

import com.baizhi.cmfz_cjw.dao.AdminDao;
import com.baizhi.cmfz_cjw.entity.Admin;
import com.baizhi.cmfz_cjw.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(String name, String pwd) {
        Admin admin = adminDao.getOne(name, pwd);
        if (admin == null){
            return admin;
        }
        return admin;
    }
}
