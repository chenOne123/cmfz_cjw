package com.baizhi.cmfz_cjw.service.impl;

import com.baizhi.cmfz_cjw.dao.BannerDao;
import com.baizhi.cmfz_cjw.entity.Banner;
import com.baizhi.cmfz_cjw.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerDao bannerDao;

    @Override
    public Map getAll(int page, int rows) {
        Map map = new HashMap();
        //计算出开始条数
        int start = (page-1)*rows;
        List<Banner> list = bannerDao.getAll(start,rows);
        map.put("rows",list);
        int total = bannerDao.getCount();
        map.put("total",total);

        return map;
    }

    @Override
    public void getAdd(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public boolean getUpdate(int id, String status) {
        System.out.println(id+" "+status+" service");
        if ("n".equals(status)){
            bannerDao.update(id,status);
            return true;
        }
        return false;
    }

    @Override
    public void getDelete(int id) {
        bannerDao.delete(id);
    }
}
