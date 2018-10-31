package com.baizhi.cmfz_cjw.service.impl;

import com.baizhi.cmfz_cjw.dao.UserDao;
import com.baizhi.cmfz_cjw.entity.ChenDto;
import com.baizhi.cmfz_cjw.entity.User;
import com.baizhi.cmfz_cjw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Map getAll(int page, int rows) {
        Map map = new HashMap();
        int stats = (page-1)*rows;

        List list = userDao.getAll(stats,rows);
        map.put("rows",list);
        int total = userDao.getCount();
        map.put("total",total);
        return map;
    }

    @Override
    public List getSelect() {
        List<User> select = userDao.getSelect();
        return select;
    }

    @Override
    public void getInsert(User user) {
        userDao.insert(user);
    }

    @Override
    public List<User> tongji() {
        int tian1 = userDao.tian1();
        int tian2 = userDao.tian2();
        int tian3 = userDao.tian3();
        List list = new ArrayList();
        list.add(tian1);
        list.add(tian2);
        list.add(tian3);
        return list;
    }

    @Override
    public Map chenshi() {
        Map map = new HashMap();
        List<User> chen = userDao.getChen();
        List<ChenDto> chenDtos = new ArrayList<>();
        for (User user : chen) {
            ChenDto chenDto = new ChenDto();
            chenDto.setName(user.getProvince());
            chenDto.setValue(user.getShu());
            chenDtos.add(chenDto);
        }
        map.put("quanbu",chenDtos);

        List<User> nan = userDao.getNan();
        List<ChenDto> nans = new ArrayList<>();
        for (User user : nan) {
            ChenDto chenDto = new ChenDto();
            chenDto.setName(user.getProvince());
            chenDto.setValue(user.getShu());
            nans.add(chenDto);
        }
        map.put("nan",nans);

        List<User> ns = userDao.getNs();
        List<ChenDto> nss = new ArrayList<>();
        for (User user : ns) {
            ChenDto chenDto = new ChenDto();
            chenDto.setName(user.getProvince());
            chenDto.setValue(user.getShu());
            nss.add(chenDto);
        }
        map.put("nss",nss);
        System.out.println(map.size());
        return map;
    }
}
