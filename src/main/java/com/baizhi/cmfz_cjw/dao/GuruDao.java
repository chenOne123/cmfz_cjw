package com.baizhi.cmfz_cjw.dao;

import com.baizhi.cmfz_cjw.entity.Artical;
import com.baizhi.cmfz_cjw.entity.Guru;

import java.util.List;

public interface GuruDao {
    //查询
    List<Guru> getGselect();
    //根据用户id查询上师言教
    List<Guru> getId(int id);
    //根据用户id查询显密法要
    List<Guru> getXmfy(int id);
}
