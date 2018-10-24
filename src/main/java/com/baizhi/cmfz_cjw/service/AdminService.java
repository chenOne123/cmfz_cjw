package com.baizhi.cmfz_cjw.service;

import com.baizhi.cmfz_cjw.entity.Admin;

public interface AdminService {
    //根据账号和密码查询
    public Admin login(String name, String pwd);
}
