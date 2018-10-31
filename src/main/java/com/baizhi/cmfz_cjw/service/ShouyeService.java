package com.baizhi.cmfz_cjw.service;

import com.aliyuncs.exceptions.ClientException;
import com.baizhi.cmfz_cjw.entity.Album;
import com.baizhi.cmfz_cjw.entity.User;

import java.util.Map;

public interface ShouyeService {
    //首页
    Map  getSelect(Integer uid ,String type, String sub_type);
    //根据专辑id查询详细
    Album getZhuanji(Integer uid, Integer id);
    //根据电话和密码查询出用户信息
    Object getLogin(String province, String password );
    //注册
    Object getRegister(String phone, String passwrod);
    //更改
    Object getUpdate(User user);
    void fsxixin(String phone) throws ClientException;

}
