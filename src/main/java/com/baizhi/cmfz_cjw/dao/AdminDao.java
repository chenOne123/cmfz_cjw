package com.baizhi.cmfz_cjw.dao;

import com.baizhi.cmfz_cjw.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    public Admin getOne(@Param("name")String name,@Param("pwd") String pwd);
}
