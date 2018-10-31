package com.baizhi.cmfz_cjw.dao;

import com.baizhi.cmfz_cjw.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //分页查询
    List<User> getAll(@Param("stats") int stats,@Param("count") int count);
    //查询全部
    List<User> getSelect();
    //添加
    void insert(User user);
    //计数
    int getCount();
    //查询
    int tian1();
    int tian2();
    int tian3();
    //统计城市
    List<User> getChen();
    //根据男查询
    List<User> getNan();
    //根据女查询
    List<User> getNs();
    //跟进电话和密码查询数据
    User getOne(@Param("province")String province, @Param("password") String password);
    User getphone(String phone);
    //注册
    User getZc();
    //更改
    void update(User user);
    //根据id查询
    User getId(int id);
}
